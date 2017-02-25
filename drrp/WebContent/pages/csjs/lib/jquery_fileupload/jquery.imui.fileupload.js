/*
 * jQuery File Upload jQuery UI Plugin 1.1.1
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2012, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/*jslint nomen: true */
/*global define, window */

(function (factory) {
    'use strict';
    if (typeof define === 'function' && define.amd) {
        // Register as an anonymous AMD module:
        define(['jquery', './jquery.fileupload-jui.js'], factory);
    } else {
        // Browser globals:
        factory(window.jQuery);
    }
}(function ($, tmpl, loadImage) {
    'use strict';
    $.widget('blueimp.fileupload', $.blueimpJUI.fileupload, {
        options: {

            /** ファイル名テキスト配列 */
            fileNameInputArray: [],

            /** ファイル名编辑可能标识 */
            fileNameEditable: false,

            /** ファイル名入力テキストwidth */
            fileNameInputWidth: '150',

            cancel: null,

            failure: null,

            // The add callback is invoked as soon as files are added to the fileupload
            // widget (via file input selection, drag & drop or add API call).
            // See the basic file upload widget for more information:
            add: function (e, data) {
                var that = $(this).data('fileupload'),
                    thisFileupload = e.data.fileupload,
                    options = that.options,
                    files = data.files,
                    i;

                for(i = 0; i < files.length; i++){
                    if (files[i].type && that._isEmptyFile(files[i])) {
                        files.splice(i, 1);
                        i--;
                        continue;
                    }
                }
                $(this).fileupload('resize', data).done(data, function () {
                    var fileNameInputNum = $('.upload-file-name', thisFileupload.widget()).size();
                    that._adjustMaxNumberOfFiles(-files.length, e, data);
                    data.isAdjusted = true;
                    data.files.valid = data.isValidated = that._validate(files);
                    if (data && data.files && data.files.valid === false && !this.options.exceedMaxNumberOfFiles) {
                        that._adjustMaxNumberOfFiles(files.length, e, data);
                    }
                    data.context = that._renderUpload(files)
                        .appendTo(options.filesContainer)
                        .data('data', data);
                    that._renderPreviews(files, data.context);
                    that._forceReflow(data.context);
                    that._exTransition(data.context).done(
                        function () {
                            var node = $('.template-upload', thisFileupload.widget());
                            for(i = 0; i < options.fileNameInputArray.length; i++){
                              $('.upload-file-name', thisFileupload.widget()).eq(i + fileNameInputNum).text('');
                              $('.upload-file-name', thisFileupload.widget()).eq(i + fileNameInputNum).append(options.fileNameInputArray.shift());
                            }
                            if (node.hasClass('fade')) {
                                node.fadeIn();
                            }
                            node.show();
                            if ((that._trigger('added', e, data) !== false) &&
                                    (options.autoUpload || data.autoUpload) &&
                                    data.autoUpload !== false && data.isValidated) {
                                data.submit();
                            }
                            node.parents("form").find(".imui-fileupload-add").focus();
                        }
                    );
                });
            },
            // Callback for successful uploads:
            done: function (e, data) {
                var that = $(this).data('fileupload'),
                    template,
                    preview;
                if (data.context) {
                    data.context.each(function (index) {
                        var file = ($.isArray(data.result) &&
                                data.result[index]) || {error: 'emptyResult'},
                            downloadArea = $('.template-download', $(this).parent());
                        if (file.error) {
                            that._adjustMaxNumberOfFiles(1, e, data);
                        }
                        that._transition($(this)).done(
                            function () {
                                var node = $(this);
                                template = that._renderDownload([file])
                                    .replaceAll(node);
                                that._forceReflow(template);

                                var fileData = template.find(".download-file-name");
                                var fileNameInput = $('<input type="text" readonly="readonly" class="imui-text-readonly" />').css('width', that.options.fileNameInputWidth + 'px').val(fileData.text());
                                fileNameInput.keypress(function(e) {
                                    return e.keyCode != 13;
                                });
                                fileData.html(fileNameInput);

                                that._transition(template).done(
                                    function () {
                                        $('.template-download', that.options.dropZone).show();
                                        data.context = $(this);
                                        that._trigger('completed', e, data);
                                    }
                                );
                            }
                        );
                    });
                } else {
                    template = that._renderDownload(data.result)
                        .appendTo(that.options.filesContainer);

                    that._forceReflow(template);
                    that._transition(template).done(
                        function () {
                            data.context = $(this);
                            that._trigger('completed', e, data);
                        }
                    );
                }
            },

            drop: function (e) {
                var that = e.data.fileupload,
                    dataTransfer = e.dataTransfer = e.originalEvent.dataTransfer,
                    data = {
                        files: $.each(
                            $.makeArray(dataTransfer && dataTransfer.files),
                            that._normalizeFile
                        ),
                        fileInput: $(e.target).find('input[type=file]'),
                        form: $(e.target)
                    },
                    uplodedFlag = false,
                    i;
                if (typeof that.options.maxNumberOfFiles === 'number' && data.files.length > that.options.maxNumberOfFiles) {
                    return false;
                }
                if(uplodedFlag){
                  return false;
                }
                if (that._onAdd(e, data) === false) {
                    return false;
                }
                e.preventDefault();
            },

            dragover: function (e) {
                var that = e.data.fileupload,
                    dataTransfer = e.dataTransfer = e.originalEvent.dataTransfer,
                    items = $.each(
                        $.makeArray(dataTransfer && dataTransfer.items),
                        that._normalizeFile
                    );
                if(typeof that.options.maxNumberOfFiles === 'number' && items.length > that.options.maxNumberOfFiles){
                    dataTransfer.dropEffect = dataTransfer.effectAllowed = 'none';
                    return false;
                }
                if (dataTransfer) {
                    dataTransfer.dropEffect = 'copy';
                }
                e.preventDefault();
            },
            // Callback for failed (abort or error) uploads:
            fail: function (e, data) {
                var that = $(this).data('fileupload'),
                    template;
                that._adjustMaxNumberOfFiles(data.files.length, e, data);
                if (data.context) {
                    data.context.each(function (index) {
                        if (data.errorThrown !== 'abort') {
                            var file = data.files[index];
                            file.error = file.error || data.errorThrown ||
                                true;
                            that._transition($(this)).done(
                                function () {
                                    var node = $(this);
                                    template = that._renderDownload([file])
                                        .replaceAll(node);
                                    that._forceReflow(template);
                                    that._transition(template).done(
                                        function () {
                                            data.context = $(this);
                                            that._trigger('failed', e, data);
                                        }
                                    );
                                }
                            );
                        } else {
                            that._transition($(this)).done(
                                function () {
                                    $(this).remove();
                                    that._trigger('failed', e, data);
                                }
                            );
                        }
                    });
                } else if (data.errorThrown !== 'abort') {
                    that._adjustMaxNumberOfFiles(-data.files.length, e, data);
                    data.context = that._renderUpload(data.files)
                        .appendTo(that.options.filesContainer)
                        .data('data', data);
                    that._forceReflow(data.context);
                    that._transition(data.context).done(
                        function () {
                            data.context = $(this);
                            that._trigger('failed', e, data);
                        }
                    );
                } else {
                    that._trigger('failed', e, data);
                }
            },
            // Callback for file deletion:
            destroy: function (e, data) {
                var that = $(this).data('fileupload');
                if (data.url) {
                    $.ajax(data).always(function(response, textStatus, jqXHR) {
                        that._adjustMaxNumberOfFiles(1, e, data);
                        that._transition(data.context).done(
                            function () {
                                $(this).remove();
                                data.response = response;
                                data.textStatus = textStatus;
                                data.jqXHR = jqXHR;
                                that._trigger('destroyed', e, data);
                            }
                        );
                    });
                } else {
                    // クライアントだけで削除処理を行う
                    that._adjustMaxNumberOfFiles(1, e, data);
                    that._transition(data.context).done(
                        function () {
                            $(this).remove();
                            that._trigger('destroyed', e, data);
                        }
                    );
                }
            }
        },

        _isEmptyFile: function (file) {
            if (!file.type && !file.fileSize){
                return true;
            }
            return false;
        },

        _onAdd: function (e, data) {
            var that = this,
                thisFileupload = e.data.fileupload,
                result = true,
                options = $.extend({}, this.options, data),
                limit = options.limitMultiFileUploads,
                paramName = this._getParamName(options),
                paramNameSet,
                paramNameSlice,
                fileSet,
                i;
            if (!(options.singleFileUploads || limit) ||
                    !this._isXHRUpload(options)) {
                fileSet = [data.files];
                paramNameSet = [paramName];
            } else if (!options.singleFileUploads && limit) {
                fileSet = [];
                paramNameSet = [];
                for (i = 0; i < data.files.length; i += limit) {
                    fileSet.push(data.files.slice(i, i + limit));
                    paramNameSlice = paramName.slice(i, i + limit);
                    if (!paramNameSlice.length) {
                        paramNameSlice = paramName;
                    }
                    paramNameSet.push(paramNameSlice);
                }
            } else {
                paramNameSet = paramName;
            }
            data.originalFiles = data.files;
            $.each(fileSet || data.files, function (index, element) {
                var newData = $.extend({}, data);
                newData.files = fileSet ? element : [element];
                newData.paramName = paramNameSet[index];
                var fileNameInput = $('<input type="text" />').css('width', options.fileNameInputWidth + 'px').val(newData.files[0].name);
                if(!options.fileNameEditable){
                    fileNameInput.attr("readonly", "readonly");
                    fileNameInput.addClass("imui-text-readonly");
                }
                fileNameInput.keypress(function(e) {
                    return e.keyCode != 13;
                });
                options.fileNameInputArray.push(fileNameInput);
                newData.submit = function () {
                    $('input[name="uploadFileName"]', thisFileupload.widget()).val(fileNameInput.val());
                    if (fileSet) {
                        if (newData.files instanceof Array) {
                            newData.files[0].uploadFileName = fileNameInput.val();
                        } else {
                            newData.files.uploadFileName = fileNameInput.val();
                        }
                    } else {
                        newData.files[0].uploadFileName = fileNameInput.val();
                    }
                    newData.jqXHR = this.jqXHR =
                        (that._trigger('submit', e, this) !== false) &&
                        that._onSend(e, this);
                    return this.jqXHR;
                };
                return (result = that._trigger('add', e, newData));
            });
            return result;
        },

        _exTransition: function (node) {
            var that = this,
                deferred = $.Deferred();
            deferred.resolveWith(node);

            return deferred;
        },

        _create: function () {
            $.blueimpJUI.fileupload.prototype._create.call(this);
            var userAgent = window.navigator.userAgent.toLowerCase();
            var appVersion = window.navigator.appVersion.toLowerCase();
            if (userAgent.indexOf("msie") != -1) {
              if (appVersion.indexOf("msie 10.") === -1) {
                var addFileButton = this.element.find("input[name='local_file']").css({
                  'filter':   'alpha(opacity=0); BORDER-LEFT: transparent 200px solid;',
                  'position': 'absolute',
                  'right':    '0',
                  'top':      '0',
                  'border-bottom': 'transparent 100px solid',
                  'width': '100%',
                  'height': '100%',
                  'font-size': '40px'
                });
                addFileButton.parent().css({
                  'overflow':       'hidden',
                  'vertical-align': 'top'
                });
              }
            } else if (userAgent.indexOf("firefox") != -1) {
                this.element.find("input[name='local_file']").css({
                  'height': '100%'
                });
            } else if (userAgent.indexOf("safari") != -1) {
                this.element.find("input[name='local_file']").css({
                    'font-size': '23px'
                  });
            }
            this.options.failed = this._onFailed;

            this.element.find("input[name='local_file']").keypress(function(e) {
                return e.keyCode != 13;
            });
        },
        _initButtonBarEventHandlers: function () {
            $.blueimpJUI.fileupload.prototype._initButtonBarEventHandlers.call(this);
            var filesList = this.element.find('.fileupload-buttonbar'),
                ns = this.options.namespace;
            this.element.find('input[type=submit]').bind('click', function(e) {
                e.preventDefault();
                filesList.find('.start').click();
            });
        },
        _onFailed: function (e, data) {
            var that = $(this).data('fileupload'),
            options = that.options;
            if(options.failure != null){
                if(data.errorThrown !== 'abort') {
                    options.failure(e, data);
                }
            }
            if(options.cancel != null){
                if (data.errorThrown === 'abort') {
                    options.cancel(e, data);
                }
            }
        },
        _adjustMaxNumberOfFiles: function (operand, e, data) {
            if (this.hasError(data)) {
                return;
            }
            if (typeof this.options.maxNumberOfFiles === 'number') {
                if (this.options.initialMaxNumberOfFiles == undefined) {
                    this.options.initialMaxNumberOfFiles = this.options.maxNumberOfFiles;
                }
                this.options.maxNumberOfFiles += operand;
                // 0 以下になったら 0 に戻します。ファイルの数は 0 を含めた自然数のはず。
                // 負の値になったら、exceedmaxnumberoffiles を true にし、_hasError で错误メッセージを表示させます。
                if (this.options.maxNumberOfFiles < 0) {
                    this.options.maxNumberOfFiles = 0;
                    this.options.exceedMaxNumberOfFiles = true;
                } else {
                    this.options.exceedMaxNumberOfFiles = false;
                }
                // 初期値と比較します。超えたら初期値に戻す。
                if (this.options.initialMaxNumberOfFiles < this.options.maxNumberOfFiles) {
                    this.options.maxNumberOfFiles = this.options.initialMaxNumberOfFiles;
                }
                if (this.options.maxNumberOfFiles < 1) {
                    this._disableFileInputButton();
                } else {
                    this._enableFileInputButton();
                }
            }
        },
        // maxNumberOfFiles のチェックロジックを変更
        // this.options.maxNumberOfFiles < 0 -> this.options.exceedmaxnumberoffiles
       _hasError: function (file) {
            if (file.error) {
                return file.error;
            }
            // Files are accepted if either the file type or the file name
            // matches against the acceptFileTypes regular expression, as
            // only browsers with support for the File API report the type:
            if (!(this.options.acceptFileTypes.test(file.type) ||
                    this.options.acceptFileTypes.test(file.name))) {
                return 'acceptFileTypes';
            }
            // The number of added files is subtracted from
            // maxNumberOfFiles before validation, so we check if
            // maxNumberOfFiles is below 0 (instead of below 1):
            // if (this.options.maxNumberOfFiles < 0) {
            if (this.options.exceedMaxNumberOfFiles) {
                return 'maxNumberOfFiles';
            }
            if (this.options.maxFileSize &&
                    file.size > this.options.maxFileSize) {
                return 'maxFileSize';
            }
            if (typeof file.size === 'number' &&
                    file.size < this.options.minFileSize) {
                return 'minFileSize';
            }
            return null;
        },
        // utility
        hasError: function(data) {
            if (data && data.context && data.context.find('.imui-error-message').length > 0) {
                return true;
            } else {
                return false;
            }
        }
    });
}));
