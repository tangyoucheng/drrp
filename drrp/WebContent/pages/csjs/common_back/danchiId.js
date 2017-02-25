//================================================
//  初期化時、データ的设定。
//  【入力】 なし
//  【返却】 なし
//  【作成】 
//  【更新】
//  【概要】
//================================================
$(document).ready(function() {
    // マウスを載せたセルを着色する機能を追加する
    // $('#tablecloth_row_danchiId').tableHover();

});

var tempJsDanchiIdCd = "";
// ================================================
// 団地コード変更しているがの判断項目
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setDanchiIdTemp(_codeId) {

    tempJsDanchiIdCd = $("[id='" + _codeId + "']").val();
}

// ================================================
// 団地コードより該当の団地名的取得
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setDanchiIdMei(_jimushoId, _shichoId, _codeId, _nameId, kariFlg, hasiFlg, _kengenNashiFlg,_chushajoFlg) {
    var jsJimusho = '';
    var jsShicho = '';
    if (_jimushoId!="" &&  $("[id='" + _jimushoId + "']").val() != undefined) {
        jsJimusho = $("[id='" + _jimushoId + "']").val();
    }
    if (_shichoId!="" &&  $("[id='" + _shichoId + "']").val() != undefined) {
        jsShicho = $("[id='" + _shichoId + "']").val();
    }

    if (kariFlg == null || kariFlg == undefined || kariFlg == "") {
        kariFlg = "0";
    }

	if (hasiFlg == true){
		hasiFlg = "1";
	}
    if (hasiFlg == null || hasiFlg == undefined || hasiFlg == "") {
    	hasiFlg = "0";
    }
    var jsDanchiIdCd = $("[id='" + _codeId + "']").val();

        if (jsDanchiIdCd != null && jsDanchiIdCd.length > 0) {
            // パラメタ設定
            var params = {
                "danchiIdForm.jimushoCd" : jsJimusho,
                "danchiIdForm.shichoCd" : jsShicho,
                "danchiIdForm.danchiIdCd" : jsDanchiIdCd,
                "danchiIdForm.kariFlg" : kariFlg,
                "danchiIdForm.hasiFlg" : hasiFlg,
                "danchiIdForm.kengenNashiFlg" : _kengenNashiFlg,
                "danchiIdForm.chushajoFlg" : _chushajoFlg
            };
            $
                    .ajax({
                        data : params,
                        url : getContextPath('/commonJsonData/danchiIdJson!doGetDanchiIdMei.do'),
                        success : function(result) {
                            if (result.danchiIdForm.errorMessage == null) {
                                var jsDanchiIdMei = result.danchiIdForm.danchiIdMei;
                                var jsDanchiIdExistFlg = result.danchiIdForm.danchiIdExistFlg;
                                if (jsDanchiIdExistFlg) {
                                    $("[id='" + _nameId + "']").val(
                                            jsDanchiIdMei);
                                } else {
                                    $("[id='" + _nameId + "']").val('');
                                    $("[id='" + _codeId + "']").val('');
                                }
                            } else {
                                window.top
                                        .showAjaxMessage(result.danchiIdForm.errorMessage);
                            }
                        },
                        error : function() {
                            window.top.showAjaxConnectFailed();
                        }
                    });
        } else {
            $("[id='" + _nameId + "']").val('');
        }

    
}
var tempdDanchiIdNameId  = null;
// ================================================
// 団地のオートコンプリート機能
// 【入力】なし
// 【返却】なし
// 【作成】syugyokuhi
// 【更新】
// 【概要】
// ================================================
function autocompleteDanchiIdName(_jimushoId, _shichoId, _danchiIdCodeId,
        _danchiIdNameId, kariFlg,_hasiFlg, _kengenNashiFlg,_chushajoFlg) {

    var jsJimusho = '';
    var jsShicho = '';
    if (_jimushoId!="" &&  $("[id='" + _jimushoId + "']").val() != undefined) {
        jsJimusho = $("[id='" + _jimushoId + "']").val();
    }
    if (_shichoId!="" &&  $("[id='" + _shichoId + "']").val() != undefined) {
        jsShicho = $("[id='" + _shichoId + "']").val();
    }
    if (kariFlg == null || kariFlg == undefined || kariFlg == "") {
        kariFlg = "0";
    }

	if (_hasiFlg == true){
		_hasiFlg = "1";
	}
    if (_hasiFlg == null || _hasiFlg == undefined || _hasiFlg == "") {
    	_hasiFlg = "0";
    }
    $("[id='" + _danchiIdNameId + "']")
            .autocomplete(
                    {
                        // autoFocus: true,
                        position : {
                            my : "right top",
                            at : "right bottom",
                            collision : "fit"
                        },
                        source : function(request, response) {
//                            if (tempdDanchiIdNameId ==  $( "[id='" + _danchiIdNameId + "']").val()){return;};
//                            tempdDanchiIdNameId = $( "[id='" + _danchiIdNameId + "']").val();
                            var params = {
                                "danchiIdForm.jimushoCd" : jsJimusho,
                                "danchiIdForm.shichoCd" : jsShicho,
                                "danchiIdForm.danchiIdMei" : $(
                                        "[id='" + _danchiIdNameId + "']").val(),
                                "danchiIdForm.kariFlg" : kariFlg,
                                "danchiIdForm.hasiFlg" : _hasiFlg,
                                "danchiIdForm.kengenNashiFlg" : _kengenNashiFlg,
                                "danchiIdForm.chushajoFlg" : _chushajoFlg
                            };
                            $
                                    .ajax({
                                        url : getContextPath('/commonJsonData/danchiIdJson!doGetDanchiIdInfo.do'),
                                        cache : false,
                                        data : params,
                                        success : function(data) {

                                            autocompleteCloseFlag = false;
                                            response(data.danchiIdForm.subForm);
                                        }
                                    });
                        },
                        search : function(event, ui) {
                            $("[id='" + _danchiIdCodeId + "']").val("")
                                    .change();

                        },
                        select : function(event, ui) {
                            $("[id='" + _danchiIdNameId + "']").val(
                                    ui.item.recordValue);
                            $("[id='" + _danchiIdCodeId + "']").val(
                                    ui.item.recordCode.substring(0, 3)
                                            + "."
                                            + ui.item.recordCode
                                                    .substring(3, 6)).change();
                            return false;
                        },
                        change : function() {
                            if ($("[id='" + _danchiIdNameId + "']").val()
                                    .replace(/\s/g, '').length == 0) {
                                $("[id='" + _danchiIdNameId + "']").val("");
                                $("[id='" + _danchiIdCodeId + "']").val("")
                                        .change();
                            }
                        },
                        close : function(event) {
//                            $("[id='" + _danchiIdNameId + "']").blur();
                        }
                    }).data("autocomplete")._renderItem = function(ul, item) {
        ul.removeClass("ui-anchiId-autocomplete");
        ul.addClass("ui-anchiId-autocomplete");
        return $("<li></li>").data("item.autocomplete", item).append(
                "<a>" + item.recordValue + "</a>").appendTo(ul);
    };
}

// ================================================
// 画面を閉じる
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doCheckDanchiIdCode(_danchiIdCodeObject, _danchiIdNameObject) {
    if ($("[id='" + _danchiIdCodeObject + "']").val() == '') {
        $("[id='" + _danchiIdNameObject + "']").val('');
    }
    if ($("[id='" + _danchiIdNameObject + "']").val() == '') {
        $("[id='" + _danchiIdCodeObject + "']").val('');
    }
}
