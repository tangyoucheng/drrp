//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {

    $('#rpm00203Form').fileupload({
        maxNumberOfFiles:1,
        fileNameInputWidth:300,
//        acceptFileTypes: /(png)|(jpe?g)|(gif)$/i,
        dropZone:$('#rpm00203Form').find('.fileupload-buttonbar'),
        autoUpload : true, //这里为true，则选中文件后就会自动上传
        url:'rpm00203Action!uploadAjax.do'
    })
    .bind('fileuploadadded', function(e, data) {
        var regexp = /\.(png)|(jpg)|(jpeg)|(gif)$/i;
        if (!regexp.test(data.files[0].name)) {
            $('#rpm00203Form').find('.files .cancel').click();
            window.top.showAjaxMessage('上传的图片类型只能为：PNG、JPG、JPEG、GIF。');
            return false;
        }
    })
    .bind('fileuploaddone', function(e, data) {
        if (data.errorThrown == undefined) {
            $.ajax({
                url : "rpm00203Json!doImage.do",
                type : "post",
                dataType : "json",
//                data : params,
                success : function(result) {
//                    if (result.rpm00203Form.errorMessage != null) {
//                        window.top.showAjaxMessage(result.scha00101Form.errorMessage);
//                    }
                    var _recordCode = result.rpm00203Form.sessionImageDataSource[0].recordCode;
                    var _recordValue = result.rpm00203Form.sessionImageDataSource[0].recordValue;
                    if (_recordCode == 'error') {
                        window.top.showAjaxMessage(_recordValue);
                    } else {
                        $("#newidentityImage").attr('src', result.rpm00203Form.sessionImageDataSource[0].recordValue);
                        $("#hiddenNewIdentityImage").val(result.rpm00203Form.sessionImageDataSource[0].recordValue)
                    }
                },
                error : function() {
                    window.top.showAjaxConnectFailed();
                    return;
                }
            });
            
        }
    });
//    .bind('fileuploaddestroy', function(e, data) {
//        $('.fileupload-buttonbar').show();
//    })
    
    // 読み込んだ画像データをsrcにセット
    if ($("#hiddenOldIdentityImage").val() == "" || $("#hiddenOldIdentityImage").val() == null) {
        $("#oldidentityImage").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#oldidentityImage").attr('src', $("#hiddenOldIdentityImage").val());
    }
    // 読み込んだ画像データをsrcにセット
    if ($("#hiddenNewIdentityImage").val() == "" || $("#hiddenNewIdentityImage").val() == null) {
//        $("#newidentityImage").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#newidentityImage").attr('src', $("#hiddenNewIdentityImage").val());
    }
});

// ================================================
// 登録処理を行う
// 【 入力 】 なし
// 【返却値】 なし
// 【作成者】 fsb
// 【作成日】 2012/11/29
// 【更新者】 fsb
// 【更新日】 2012/11/29
// 【 概要 】
// ================================================
function doUpdate(object) {
    window.top.showAjaxConfirm(object, 'Q00001,operation_update');
    return false;
}
//================================================
//删除処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doDelete(object) {
    window.top.showAjaxConfirm(object, 'Q00001,operation_delete');
    return false;
}
