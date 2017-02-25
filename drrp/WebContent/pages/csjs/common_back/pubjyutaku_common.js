
// デバッグ
var isDebug = false;
// 画面サイズ調整終了标识
var isFinishAdjustFrameSize = false;

// ダウンロード開始标识
var isDownloaded = false;
// マイ菜单初期表示标识
var isMyMenuControlShowOnly = true;

var LeshanConstantsIF = {
    // 初期化
    CD_OPT_DIV_INIT : "00",
    // 検索
    CD_OPT_DIV_SEARCH : "01",
    // 新規
    CD_OPT_DIV_NEW : "02",
    // 更新
    CD_OPT_DIV_UPDATE : "03",
    // 削除
    CD_OPT_DIV_DELETE : "04",
    // 取消
    CD_OPT_DIV_CANCEL : "05",
    // 新規確認
    CD_OPT_DIV_NEW_CONFIRM : "06",
    // バッチ処理正常終了
    CD_OPT_DIV_BATCH_SUCCESS : "07",
    // バッチ処理異常終了
    CD_OPT_DIV_BATCH_FAIL : "08",
    // 成功
    CD_RESULT_DIV_SUCCESS : "01",
    // 新規された排他 错误
    CD_RESULT_DIV_INSERTED_EXCLUTION_ERROR : "02",
    // 更新された排他错误
    CD_RESULT_DIV_UPDATED_EXCLUTION_ERROR : "03",
    // 削除された排他错误
    CD_RESULT_DIV_DELETED_EXCLUTION_ERROR : "04",
    // 変更件数上限値
    KYOTU_HENKO_KENSUU_JOGENCHI: 99
};

// ================================================
// formユーティリティクラス
// 【入力】なし
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
var FormUtils = function(){};
// ================================================
// 最初のinput要素にフォーカス的设定
// 【入力】なし
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
FormUtils.focusOnFirst = function(){
    // $('form:first *:input[type!=hidden]:first').focus();
    if (document.forms.length > 0) {
        for (var i = 0; i < document.forms[0].elements.length; i++){
            var oField = document.forms[0].elements[i];
            if (oField.type != "hidden" && !oField.disabled) {
                oField.focus();
                return;
            }
        }
    }
};


// ====================================================
// 確認メッセージ（OK、CANCEL）画面表示
//
// 【入力】
// メッセージキー
// メッセージパラメータ（複数指定可）
// 【返却値】
// 選択結果
// ====================================================
function messageConfirm(messageKey, method) {
    var parameter = "messageForm.messageKey=" + encodeURI(messageKey);
    if(arguments.length > 2) {
        for(var i = 2; i < arguments.length; i++) {
            parameter += "&messageForm.messageParam=";
            parameter += encodeURI(encodeURI(arguments[i]));
        }
     }
    MessageDialog.data.parameter = parameter;
    MessageDialog.show(function(result){
        if (result.data.resultValue){
            eval(method + "();");
        }
    });
}

// ================================================
// ダウンロード開始
// 【入力】 なし
// 【返却】 なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function connectDownload() {
    try {
        if (!isDownloaded) {
            $("#downloadFrame").attr("src", getContextPath("/framework/downloadAction.do"));
        }
        isDownloadead = true;
    } catch(e) {
        alert(e.message);
    }
}

// ================================================
// マイ菜单切替
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function myMenuControl(_menuId) {

    // パラメータ設定
    var params = {
        "form.menuId" : _menuId,
        "form.isShowOnly" : isMyMenuControlShowOnly
    };

    // action起動
    $.ajax ({
        url : "myMenuJson.do",
// type : "post",
// dataType : "json",
        data : params,
        success : function(result) {
            $("#myMenuControl").removeClass("bookmark_btn");
            $("#myMenuControl").removeClass("bookmark_del_btn");
            if (result.form.isFoundMyMenu) {
                $("#myMenuControl").addClass("bookmark_del_btn");

                $("#myMenuControl")[0].title="マイ菜单から削除";
            } else {
                $("#myMenuControl").addClass("bookmark_btn");

                $("#myMenuControl")[0].title="マイ菜单に登録";
            }
            if (!isMyMenuControlShowOnly) {
                window.top.showAjaxMessage(result.form.message);
            }
            isMyMenuControlShowOnly = false;
        },
        error : function() {
            // window.top.showAjaxConnectFailed();
            window.top.location.replace(getContextPath("/pages/jsp/common/system_error_next.jsp"));
        }
    });

}

// ================================================
// <g:submit> の Submit処理
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function submitUrl(_function, _target) {

    // ドキュメントの読み込み完了以外の場合、Submit禁止
    if (window.top.right.document.readyState != 'complete') {
        return false;
    }

    // ダブルクリック禁止
    if (isSubmit) {
        return false;
    }

    // ファンクションを実行
    var result_ = _function();

    // form名
    var form_ = _target.parents("form").attr("name");

    // 処理継続（=true）の場合のみ、サブミットする
    if (result_ || (result_ == undefined)) {
        // サブミット中标识
        isSubmit = true;
        // Submit処理
        var form = _target.parents('form')[0];
        var action_ = form.action;
        form.action = _target.attr("href");
        form.submit();
        $(_target).parents('form').attr('action', action_);
    }

    // ボタンを隠す
    _target.fadeOut(1500);
    _target.attr("disabled", "disabled");

    // FIXME 3秒後に再度押せるようにする
    _target.fadeIn(1500, function() {
        $(this).removeAttr("disabled");
    });

    // 本来のリンクは常に無効とする
    return false;

}

// ================================================
// 菜单押下二重制御のクリア処理
// 【入力】なし
// 【返却】なし
// 【作成】kourei
// 【更新】
// 【概要】
// ================================================
function releaseDblClickFlg(){
	$(window.top.left.document).find("input[id=leftMenuDblClickFlag]").val(false);
    return false;
}

// ================================================
// エンコード変換
// 【入力】 _param 処理文字
// 【返却】 なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function  encode(_param) {
    if (typeof(_param) == "string") {
         _param = _param.split("&").join("&amp;");
         _param = _param.split(" ").join("&nbsp;");
         _param = _param.split("<").join("&lt;");
         _param = _param.split(">").join("&gt;");
         _param = _param.split("\"").join("&quot;");
         _param = _param.split("\n").join("<br/>");
    }
    return _param;
}

// ================================================
// 外字サーバーAjax後処理コール
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
$.ajaxSetup({
    type : "post",
    dataType : "json",
    async:false,
    beforeSend: function(jqXHR, settings) {
    	if (settings.data != undefined && settings.data != null) {
    		settings.data = settings.data.replace(/(undefined)/g, function($0,$1) { return ''; });
		}
    	if (settings.url != undefined && settings.url != null) {
    		settings.url = settings.url.replace(/(undefined)/g, function($0,$1) { return ''; });
		}
    },
    complete: function(jqXHR, textStatus) {
        try {
            // 外字サーバースクリプト
            useBigPackage();
        } catch(e) {
            // 外字サーバー無効時の例外は無視
        }
        try {
            // HTMLをリフレッシュ
            window.top.head.doZoom(
                    window.top.head.document.scz990101Form["fontSize"].value);
        } catch(e) {
        }
    }
});

var TAG_FILE = "tag_file";
// ================================================
// 上传のファイルパス的取得
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function getPath(obj) {
    if(obj) {
        if (window.navigator.userAgent.indexOf("MSIE")>=1) {
            obj.select();
            $("#" + TAG_FILE).focus();
            $("#button").focus();
            return document.selection.createRange().text;
        }else if(window.navigator.userAgent.indexOf("Firefox")>=1) {
            if(obj.files) {
                return obj.files.item(0).getAsDataURL();
            }
            return obj.value;
        }
        return obj.value;
    }
}

// ================================================
// 次画面へ遷移する
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function goNext(url) {
    if (is_form_changed()) {
        window.top.showAjaxMessageById('W30003');
    } else {
        window.location.href = url;
    }
}

// ================================================
// 郵便番号より該当の住所的取得
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setAddress(yubimId,addressId) {
    $("#"+addressId).val(this.getAddress($("#" + yubimId).val()));
    doShowWaterMark();
}
// ================================================
// 郵便番号よりを编辑用に変更する
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doFZip(id) {
    if (document.getElementById(id).value != '')
    document.getElementById(id).value = document.getElementById(id).value.substring(0,3)+"-"+document.getElementById(id).value.substring(3,7);
    
}
// ================================================
// 郵便番号よりを表示用に変更する
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doUnfZip(id) {
    
    getCursurPosition(id);
    document.getElementById(id).value = document.getElementById(id).value.replace("-","");
    setCursurPosition(id);
    
}
// ================================================
// マウスのロケションを設定を取得
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
var cursurPosition=-1;
function getCursurPosition(id){
    var oTxt1 = document.getElementById(id);
    cursurPosition=-1;
    if(oTxt1.selectionStart){
        cursurPosition= oTxt1.selectionStart;
    }else{// IE
        var range = document.selection.createRange();
        range.moveStart("character",-oTxt1.value.length);
        cursurPosition=range.text.length;
    }
}
// ================================================
// マウスのロケションを設定
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setCursurPosition(id){  
    var oTxt1 = document.getElementById(id);
    if (oTxt1.createTextRange) {
        var v = oTxt1.value;
        if(cursurPosition> v.length && v.length !=0) {
            cursurPosition = v.length;
        }
        var r = oTxt1.createTextRange();
        r.move("character", cursurPosition);
        if (cursurPosition != 0) {
            r.select();
        }
    }
}
// ================================================
// 住所取得
// 【入力】なし
// 【返却】住所
// 【作成】
// 【更新】
// 【概要】
// ================================================
function getAddress(zipCd) {
    var jsAddress = "";
    // パラメタ設定// パラメータ設定
    var params = {
            "form.zipCd" : zipCd
        };
    $.ajax ({
        url : getContextPath('/commonJsonData/zipCodeJson!doGetAddress.do'),
        data:params,
        success : function(result) {
            jsAddress = result.form.address;
            return;
        },
        error : function() {
            window.top.showAjaxConnectFailed();
            return;
       }
    });
    return jsAddress;
}

// ================================================
// 名義人情報設定
// 【入力】なし
// 【返却】住所
// 【作成】
// 【更新】
// 【概要】
// ================================================
// function setNominalPersonInfo_arg3(_jutakubangoId) {
// var jsDanchiMei = "";
// if ($("[id='"+_jutakubangoId+"_danchiCode']").val() == ""
// || $("[id='"+_jutakubangoId+"_toCode']").val() == ""
// || $("[id='"+_jutakubangoId+"_jukoCode']").val() == "") {
//        
// // 住宅番号（枝番）
// if ($("[id='"+_jutakubangoId+"_branch']").length >0) {
// $("[id='"+_jutakubangoId+"_branch']").val("");
// }
// // カナ氏名
// if ($("[id='"+_jutakubangoId+"_kanaShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanaShimei']").val("");
// }
// // 漢字氏名
// if ($("[id='"+_jutakubangoId+"_kanjiShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanjiShimei']").val("");
// }
// return;
// }
//
// // パラメタ設定
// var params = {
// "kojinInfoForm.housingNoDanchiCd" :
// $("[id='"+_jutakubangoId+"_danchiCode']").val(),
// "kojinInfoForm.housingNoTouCd" : $("[id='"+_jutakubangoId+"_toCode']").val(),
// "kojinInfoForm.housingNoJukoCd" :
// $("[id='"+_jutakubangoId+"_jukoCode']").val()
// };
// $.ajax ({
// data : params,
// url :
// getContextPath('/commonJsonData/kojinInfoJson!doGetNominalPersonInfo.do'),
// success : function(result) {
// // 住宅番号（枝番）
// if ($("[id='"+_jutakubangoId+"_branch']").length >0) {
// $("[id='"+_jutakubangoId+"_branch']").val(result.kojinInfoForm.housingNoBranch);
// }
// // カナ氏名
// if ($("[id='"+_jutakubangoId+"_kanaShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanaShimei']").val(result.kojinInfoForm.kanaShimei);
// }
// // 漢字氏名
// if ($("[id='"+_jutakubangoId+"_kanjiShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanjiShimei']").val(result.kojinInfoForm.kanjiShimei);
// }
// return;
// },
// error : function() {
// window.top.showAjaxConnectFailed();
// return;
// }
// });
// }

// ================================================
// 名義人情報設定
// 【入力】なし
// 【返却】住所
// 【作成】
// 【更新】
// 【概要】
// ================================================
// function setNominalPersonInfo_arg4(_jutakubangoId,autoflg) {
// var jsDanchiMei = "";
//    
// if ($("[id='"+_jutakubangoId+"_danchiCode']").val() == ""
// || $("[id='"+_jutakubangoId+"_toCode']").val() == ""
// || $("[id='"+_jutakubangoId+"_jukoCode']").val() == ""
// || $("[id='"+_jutakubangoId+"_branch']").val() == "") {
//        
// // カナ氏名
// if ($("[id='"+_jutakubangoId+"_kanaShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanaShimei']").val("");
// }
// // 漢字氏名
// if ($("[id='"+_jutakubangoId+"_kanjiShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanjiShimei']").each(function() {
// if ($(this)[0].tagName == 'TD') {
// $(this).text('');
// } else {
// $(this).val('');
// }
// });
// }
// if ($("[id='"+_jutakubangoId+"_danchiCode']").val() != ""
// && $("[id='"+_jutakubangoId+"_toCode']").val() != ""
// && $("[id='"+_jutakubangoId+"_jukoCode']").val() != ""
// && $("[id='"+_jutakubangoId+"_branch']").val() == "")
// {
// if(autoflg == undefined) {
//                    
// autoflg = true;
// }
// if(autoflg){
// setNominalPersonInfo_arg3(_jutakubangoId);
// }
//                
// }
// return;
// }
//
//    
// // パラメタ設定
// var params = {
// "kojinInfoForm.housingNoDanchiCd" :
// $("[id='"+_jutakubangoId+"_danchiCode']").val(),
// "kojinInfoForm.housingNoTouCd" : $("[id='"+_jutakubangoId+"_toCode']").val(),
// "kojinInfoForm.housingNoJukoCd" :
// $("[id='"+_jutakubangoId+"_jukoCode']").val(),
// "kojinInfoForm.housingNoBranch" : $("[id='"+_jutakubangoId+"_branch']").val()
// };
// $.ajax ({
// data : params,
// url :
// getContextPath('/commonJsonData/kojinInfoJson!doGetNominalPersonInfo.do'),
// success : function(result) {
// // カナ氏名
// if ($("[id='"+_jutakubangoId+"_kanaShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanaShimei']").val(result.kojinInfoForm.kanaShimei);
// }
// // 漢字氏名
// if ($("[id='"+_jutakubangoId+"_kanjiShimei']").length >0) {
// $("[id='"+_jutakubangoId+"_kanjiShimei']").each(function() {
// if ($(this)[0].tagName == 'TD') {
// $(this).text(result.kojinInfoForm.kanjiShimei);
// } else {
// $(this).val(result.kojinInfoForm.kanjiShimei);
// }
// });
// }
// return;
// },
// error : function() {
// window.top.showAjaxConnectFailed();
// return;
// }
// });
// }

// ================================================
// チェックボックス全て選択
// 【入力】対象ID、チェック状態
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
 function doCheckedAll(objectId, checkFlag){
         $("[id='"+objectId+"']").attr("checked",checkFlag);
     
 }

// ================================================
// 選択件数取得処理
// 【入力】 なし
// 【返却】 なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function getCheckCount(checkObject, valueObject){
    $("#"+valueObject+"").val(CommonUtilJs.addComma($(":input[id='"+checkObject+"'][checked]").length));
    return false;
}

// ================================================
// TRソート処理
// 【入力】対象ID、チェック状態
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function clickTrSortSeq(sortObjectId, sortSeq,_objectTr){
    $(_objectTr).find("td:eq(1) a").each(function() {
        return submitUrl(function() {doClearWaterMark();return setSortSeq(sortObjectId, sortSeq,_objectTr);},$(this));
  
    });
}
 
// ================================================
// ソート順設定
// 【入力】対象ID、チェック状態
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
 function setSortSeq(sortObjectId, sortSeq,_objectTr){
    if (is_form_changed()) {
        doShowWaterMark();
        window.top.showAjaxMessageById('W30003');
        return false;
    } else {
         $("[id='"+sortObjectId+"']").val(sortSeq);
         $("[id='pageStartRowNo']").val('1');
         $(_objectTr).find("td:eq(1) a")[0].href=$(_objectTr).find("td:eq(0) a")[0].href;
    }
 }

// ================================================
// idTabsIndex設定
// 【入力】対象ID、チェック状態
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setIdTabsIndex(objectId, index){
    $("[id='"+objectId+"']").val(parseInt(index));
}

// ================================================
// 明細行のNOの再設定処理
// 【入力】なし
// 【返却】なし
// 【作成】z.f
// 【更新】
// 【概要】
// ================================================
function resetNo(tableObj, dataRowCount) {
    var visibleRowIndex = 1;
    tableObj.find("tr:visible").each(function() {
        var jsRemainder = $(this)[0].rowIndex%dataRowCount;
        if (jsRemainder == 0) {
            $(this).find("td:eq(0)").html(visibleRowIndex);
            visibleRowIndex = visibleRowIndex + 1;
        }
        var validDataIndexObj1 = $(this).find("#validDataIndex");
        if(validDataIndexObj1.is("input")){
            validDataIndexObj1.val(visibleRowIndex-1);
        }
        var validDataIndexObj1 = $(this).find("#no");
        if(validDataIndexObj1.is("input")){
            validDataIndexObj1.val(visibleRowIndex-1);
        }
        
    });
}

// ================================================
// 住宅番号保存処理
// 【入力】なし
// 【返却】なし
// 【作成】ztg
// 【更新】
// 【概要】
// ================================================
function doSetHousingNo(_idObject) {
  // パラメータ設定
  var params = {
      "backupDataInfo.housingNoDanchiCd" : $("#" + _idObject + "_danchiCode").val(),
      "backupDataInfo.housingNoTouCd" :  $("#" + _idObject + "_toCode").val(),
      "backupDataInfo.housingNoJukoCd" : $("#" + _idObject + "_jukoCode").val(),
      "backupDataInfo.housingNoBranch" : $("#" + _idObject + "_branch").val()
  };
  
	 $.ajax ({
	     url : "backupDataJson!doSetHousingNo.do",
	     data : params,
	     success : function(result) {

	     },
	     error : function() {
	         window.top.showAjaxConnectFailed();
	     }
	 });

}

// ================================================
// 住宅番号引継処理
// 【入力】なし
// 【返却】なし
// 【作成】ztg
// 【更新】
// 【概要】
// ================================================
function getHikitsugiHousingNoInfo(_idObject) {
    $.ajax ({
        url : "backupDataJson!doGetHousingNo.do",
        success : function(result) {
            $("#" + _idObject + "_danchiCode").val(result.backupDataInfo.housingNoDanchiCd);
            $("#" + _idObject + "_toCode").val(result.backupDataInfo.housingNoTouCd);
            $("#" + _idObject + "_jukoCode").val(result.backupDataInfo.housingNoJukoCd);
            $("#" + _idObject + "_branch").val(result.backupDataInfo.housingNoBranch);
        },
        error : function() {
            window.top.showAjaxConnectFailed();
        }
    });
    
}

// ================================================
// 駐車場番号保存処理
// 【入力】なし
// 【返却】なし
// 【作成】ztg
// 【更新】
// 【概要】
// ================================================
function doSetHikitsugiChushajoNoInfo(_idObject) {
// パラメータ設定
var params = {
  "backupDataInfo.chushajoNoDanchiCd" : $("#" + _idObject + "_danchiCd").val(),
  "backupDataInfo.chushajoNoKukakuCd" :  $("#" + _idObject + "_kukakuCd").val(),
  "backupDataInfo.chushajoNoBranch" : $("#" + _idObject + "_edaban").val()
};

	 $.ajax ({
	     url : "backupDataJson!doSetChushajoNo.do",
	     data : params,
	     success : function(result) {

	     },
	     error : function() {
	         window.top.showAjaxConnectFailed();
	     }
	 });

}

// ================================================
// 駐車場番号引継処理
// 【入力】なし
// 【返却】なし
// 【作成】ztg
// 【更新】
// 【概要】
// ================================================
function getHikitsugiChushajoNoInfo(_idObject) {
    $.ajax ({
        url : "backupDataJson!doGetChushajoNo.do",
        success : function(result) {
            $("#" + _idObject + "_danchiCd").val(result.backupDataInfo.chushajoNoDanchiCd);
            $("#" + _idObject + "_kukakuCd").val(result.backupDataInfo.chushajoNoKukakuCd);
            $("#" + _idObject + "_edaban").val(result.backupDataInfo.chushajoNoBranch);
        },
        error : function() {
            window.top.showAjaxConnectFailed();
        }
    });
    
}

// ================================================
// 操作した直近の収納日引継処理
// 【入力】なし
// 【返却】なし
// 【作成】ztg
// 【更新】
// 【概要】
// ================================================
function getHikitsugiStorageDate(_idObject) {
// alert("getHikitsugiHousingNoInfo 製造待ち 未確認");
    $.ajax ({
        url : "backupDataJson!doGetStorageDate.do",
        success : function(result) {
            var jsStorageDate = result.backupDataInfo.storageDate;
            if (jsStorageDate != null && jsStorageDate.length == 8) {
                var jsYear = jsStorageDate.substring(0,4);
                var jsMonth = jsStorageDate.substring(4,6);
                var jsDay = jsStorageDate.substring(6,8);
                $("#" + _idObject + "_era").val(seireki_to_wareki_select(jsStorageDate));
                $("#" + _idObject + "_year").val(parseInt(seireki_to_wareki(jsStorageDate,jsYear)));
                $("#" + _idObject + "_month").val(parseInt(jsMonth));
                $("#" + _idObject + "_day").val(parseInt(jsDay));
            } else {
                $("#" + _idObject + "_era").val('');
                $("#" + _idObject + "_year").val('');
                $("#" + _idObject + "_month").val('');
                $("#" + _idObject + "_day").val('');
            }
        },
        error : function() {
            window.top.showAjaxConnectFailed();
        }
    });
    
}

// ================================================
// HTMLロード時処理
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
$(document).ready(function(){

    // --------------------------------------------------
    // ダイアログ初期化
    // --------------------------------------------------
//    window.top.releaseVar();

    // --------------------------------------------------
    // スクロール位置をトップに戻す
    // --------------------------------------------------
    try {
        if (window.name == "right") {
            window.top.goTop();
        }
    } catch(e) {
        // do nothing
    }

    // --------------------------------------------------
    // 画面文字サイズ反映
    // --------------------------------------------------
    try {
        window.top.head.doZoom(
            window.top.head.document.scz990101Form["fontSize"].value);
    } catch(e) {
        // do nothing
    }

    // --------------------------------------------------
    // Aタグ・ツールチップ設定
    // --------------------------------------------------
    try {
        $("a").tooltip();
    } catch (e) {
        // do nothing
    }

    // --------------------------------------------------
    // マイ菜单ボタン画像表示
    // --------------------------------------------------
    try {
        $("#myMenuControl").click();
    } catch(e) {
        // do nothing
    }

    // --------------------------------------------------
    // 画面ロックタイマー起動
    // --------------------------------------------------
    window.top.addTimerEvent($(document));

    // --------------------------------------------------
    // サブミット标识解除
    // --------------------------------------------------
    isSubmit = false;
    
    // --------------------------------------------------
    // 画面で入力した内容を保存する
    // --------------------------------------------------
    saveCondition(); 

    // --------------------------------------------------
    // ウォーターマークで入力規則を表示
    // --------------------------------------------------
    doLoadWaterMark();
    
    
    // --------------------------------------------------
    // 数値カンマ编辑（追加）
    // --------------------------------------------------
    $(".formatComma").blur(function() {
        var numberData = CommonUtilJs.delComma($(this).val());
        if (numberData.indexOf('.') + 1 == numberData.length) {
            numberData = numberData.substring(0, numberData.indexOf('.'));
        }
        $(this).val(CommonUtilJs.addComma(numberData));
        $(this).attr('_value', numberData);
    });
    // --------------------------------------------------
    // 数値カンマ编辑（削除）
    // --------------------------------------------------
    $(".formatComma").focus(function() {
        $(this).val(CommonUtilJs.delComma($(this).val()));
    });
    
    // --------------------------------------------------
    // idTabs
    // --------------------------------------------------
    var idTabsIndex = parseInt($("[id='idTabsIndexName']").val());
    if (!isNaN(idTabsIndex)) {
        var settings = { start:idTabsIndex}; 
        $(".idTabs").idTabs(settings);
    }
});

// ================================================
// 入力内容の長度的取得
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function getFieldDataLength(msg, obj) {
    var inputDataLength = 0;
    if (obj.val() != "") {
        inputDataLength = obj[0].value.length;
    }
    if (obj[0].maxLength < 20) {
        return msg;
    } else {
        return msg + " #" +inputDataLength;
    }

}

// 入力内容の長度を制御する
// 【入力】なし
// 【返却】なし
// 【作成】t.d.m
// 【更新】
// 【概要】
function checkTextareaDataLength(obj,maxlength){  
    if(obj.value.length > maxlength){  
        obj.value = obj.value.substring(0,maxlength);  
    }
}

// ================================================
// 画面で入力した内容を保存する
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function saveCondition(){
    doClearWaterMark();

    var framesName = getFrameName(this);;
	if(framesName != "head" && framesName != "left"){
		var _frame = eval("window.top."+framesName);
		if (_frame != undefined && _frame != null) {
			var _document = eval("window.top."+framesName+".document");
			$(_document).find(".input_condition input, .input_condition textarea, .input_condition select").each(function(){
				if($(this)[0].type == "hidden"){
					// continue
					return true;
				}
				if ($(this)[0].type != null && $(this)[0].type.toLowerCase() == 'radio' && $(this)[0].checked) {
					$(this).attr('_value_condition', "checked_condition");
				} else if ($(this)[0].type != null && $(this)[0].type.toLowerCase() == 'checkbox' && $(this)[0].checked) {
					$(this).attr('_value_condition', "checked_condition");
				} else if ($(this)[0].type != null && $(this)[0].type.toLowerCase() == 'checkbox' && !$(this)[0].checked) {
					$(this).attr('_value_condition', $(this).attr('value'));
				} else if($(this)[0].type != null && $(this)[0].type.toLowerCase() != 'radio' && $(this)[0].type.toLowerCase() != 'checkbox'){
					$(this).attr('_value_condition', $(this).attr('value'));
				}
			}); 
		}
	}
} 

// ================================================
// 画面で入力した内容チェック
// 【入力】
// 【返却】
// 【作成】t.d.m
// 【更新】
// 【概要】
// ================================================
function is_form_changed(){
    var is_changed =false;
    doClearWaterMark();

    var framesName = getFrameName(this);;
	if(framesName != "head" && framesName != "left"){
		var _frame = eval("window.top."+framesName);
		if (_frame != undefined && _frame != null) {
			var _document = eval("window.top."+framesName+".document");
			$(_document).find(".input_condition input, .input_condition textarea, .input_condition select").each(function(){
				if($(this)[0].type == "hidden"){
					// continue
					return true;
				}
				
				var _v = $(this).attr('_value_condition');
				if(typeof(_v) =='undefined'){
					_v ='';
				}
				
				if($(this)[0].type != null && $(this)[0].type.toLowerCase() == 'radio' && $(this)[0].checked && _v != "checked_condition"){
					is_changed =true;
				}
				
				if($(this)[0].type != null && $(this)[0].type.toLowerCase() == 'checkbox'){
					if ($(this)[0].checked && _v != "checked_condition") {
						is_changed =true;
					} else if (!$(this)[0].checked && _v != $(this).attr('value')){
						is_changed =true;
					}
				}
				
				if($(this)[0].type != null && $(this)[0].type.toLowerCase() != 'radio'  && $(this)[0].type.toLowerCase() != 'checkbox' && _v != $(this).attr('value')){
					is_changed =true;
				}
			});
		}
	}
    
    return is_changed;
} 

var imageIndex = 0;
// ================================================
// 前ページ処理
// 【入力】なし
// 【返却】なし
// 【作成】kourei
// 【更新】
// 【概要】
// ================================================
function doImagePrev(obj){
// $('#mainMenuCtrl
// table:eq('+$(".scrollable").data("scrollable").getIndex()+')').css('margin-left','0px');
    $("#image_"+imageIndex).hide();
    imageIndex--;
    $("#image_"+imageIndex).show();
    $("#imgTitle").val($("#imgTitle_"+imageIndex).val());
    $("#imgBiko").val($("#imgBiko_"+imageIndex).val());
    if (imageIndex==0){
        $("#imagePrev").hide();
    }
    if (imageIndex<=$("#imageCout").val()-1){
        $("#imageNext").show();
    }
}


// ================================================
// 次ページ処理
// 【入力】なし
// 【返却】なし
// 【作成】kourei
// 【更新】
// 【概要】
// ================================================
function doImageNext(obj){
    $("#image_"+imageIndex).hide();
    imageIndex++;
    $("#image_"+imageIndex).show();
    $("#imgTitle").val($("#imgTitle_"+imageIndex).val());
    $("#imgBiko").val($("#imgBiko_"+imageIndex).val());
    if (imageIndex!=0){
        $("#imagePrev").show();
    }
    if (imageIndex>=$("#imageCout").val()-1){
        $("#imageNext").hide();
    }
}
// ================================================
// 名称に対数項目をクリアする
// 【入力】複数名称
// 【返却】なし
// 【作成】kourei
// 【更新】
// 【概要】
// ================================================
function doClear(){
    // 項目数的取得
    var length = arguments.length;
    // 項目を全部クリアする
    for(var i = 0; i<length; i++)
    {
        // alert(arguments[i]);
        $("#"+arguments[i]).val("");
        $("#"+arguments[i]+"_code").val("");
        $("#"+arguments[i]+"_name").val("");
        $("#"+arguments[i]+"_touCode").val("");
        $("#"+arguments[i]+"_touName").val("");
        
        }
}

// ================================================
// 名称に対数項目をクリアする
// 【入力】複数名称
// 【返却】なし
// 【作成】kourei
// 【更新】
// 【概要】
// ================================================
function doClearVal(){
    // 項目数的取得
    
    var length = arguments.length;
    // 項目を全部クリアする
    for(var i = 0; i<length; i++)
    {
        if ($("#"+arguments[i]).val() != undefined)
        {
            $("#"+arguments[i]).val("").change();
        }
        
    }
}
function popScz0201(_title,_imageWidth,_imageHeight,_imagePath){
    var params = {
            imageTitle : _title,
            imagePath : _imagePath,
            imageHeight : _imageHeight,
            imageWidth : _imageWidth 
    };
    parent.window.SCZ0201Dialog.data = params;
    parent.window.SCZ0201Dialog.show(function(result){
        FormUtils.focusOnFirst();
    });
}
// ================================================
// ウォーターマークで入力規則
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doClearWaterMark(_object) {

    // Start 2012年02月10日追加 ウォーターマークで入力規則が非表示
    if (_object!=undefined && !_object) {
        return;
    }

    var framesName = getFrameName(this);
	if(framesName != "head" && framesName != "left"){
		var _frame = eval("window.top."+framesName);
		if (_frame != undefined && _frame != null) {
			var _document = eval("window.top."+framesName+".document");
			$(_document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
				if($(this).val()!=this.getAttribute("grayMessage")){
					$(this).css({"color":"#000"});
				}else{
					$(this).val("").css({"color":"#888"});
				} 
				
			});
		}
	}
}
// ================================================
// ウォーターマークで入力規則
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doShowWaterMark() {
    // Start 2012年02月10日追加 ウォーターマークで入力規則が表示

    var framesName = getFrameName(this);;
	if(framesName != "head" && framesName != "left"){
		var _frame = eval("window.top."+framesName);
		if (_frame != undefined && _frame != null) {
			var _document = eval("window.top."+framesName+".document");
			$(_document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
				if(($(this).val()=='') || (($(this).val()==this.getAttribute("grayMessage")))){
					$(this).css({"color":"#888"}).val(this.getAttribute("grayMessage"));
				}else{
					$(this).css({"color":"#000"});
				} 
				
			});
		}
	}
}

// ================================================
// ツールチップで入力規則
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doLoadWaterMark() {


    var framesName = getFrameName(this);;
	if(framesName != "head" && framesName != "left"){

		var _frame = eval("window.top."+framesName);
		if (_frame != undefined && _frame != null) {
			var _document = eval("window.top."+framesName+".document");
			// --------------------------------------------------
			// ウォーターマークで入力規則を表示
			// --------------------------------------------------
			$(_document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){   
				
				var oldVal=$(this).val();
				if (oldVal == "") {
					$(this).css({"color":"#888"}).val(this.getAttribute("grayMessage"));
				}
				
				if($(this).val()!=this.getAttribute("grayMessage")){
					$(this).css({"color":"#000"});
				}else{
					$(this).css({"color":"#888"});
				}
				
				$(this).focus(function(){
					if($(this).val()!=this.getAttribute("grayMessage")){
						$(this).css({"color":"#000"});
					}else{
						$(this).val("").css({"color":"#888"});
					}   
					if ($(this).attr("readonly") != true){
						$(this).poshytip('show');
						$(this).poshytip('update', getFieldDataLength(this.getAttribute("grayMessage"), $(this)));
					};
				}).blur(function(){
					if($(this).val()==""){
						$(this).val(this.getAttribute("grayMessage")).css({"color":"#888"});
					}
					
					$(this).poshytip('hide');
				}).keydown(function(){
					$(this).css({"color":"#000"});
				}).keyup(function(){
					$(this).poshytip('update', getFieldDataLength(this.getAttribute("grayMessage"), $(this)));
				});
			});
		}
		
		// --------------------------------------------------
		// ツールチップで入力規則と入力文字数を表示
		// --------------------------------------------------
		$(_document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){   
			var inputDataLength = 0;
			if ($(this).val() != "") {
				inputDataLength = $(this).val().length;
			}
			
			$(this).poshytip({
				className: 'tip-yellowsimple',
				showOn: 'focus',
				alignTo: 'target',
				alignX: 'right',
				alignY: 'center',
				offsetX: 5,
				showTimeout: 10,
				slide: false

			});    
		});
	}
}


// ================================================
// 行の色付変え
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function resetLineColor(_object,_rowSpan) {
    var tableObj = _object;
    rowid = 0;
    var tempClass_ = "even";
    tableObj.find("tr:visible").each(function(i){
        $(this).removeClass("odd");
        $(this).removeClass("even");
       if (rowid % _rowSpan ==0){
           if (tempClass_ == "odd"){
               tempClass_="even";
           }else
           {
               tempClass_="odd";
            }
        };
   $(this).addClass(tempClass_);
   
    rowid++;
    });
}
// ================================================
// 行追加の後実行する事件
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doAfterAddRow(_object,_rowSpan) {
 
}

// ================================================
// エリアーのステータスを設定
// 【 入力 】 エリアー番号
// 【返却値】なし
// 【作成者】jzw
// 【作成日】2013/01/17
// 【更新者】
// 【更新日】
// 【 概要 】なし
// ================================================
function setAreaStatus(_object, _areaIndex) {
 var groupText = _object.innerText;
 if ("－" == groupText) {
     _object.innerText = "＋";
     $("#area" + _areaIndex).hide();
 }
 if ("＋" == groupText) {
     _object.innerText = "－";
     $("#area" + _areaIndex).show();
 }
}

// ================================================
// フレーム名取得
// 【 入力 】
// 【返却値】なし
// 【作成者】jzw
// 【作成日】2013/01/17
// 【更新者】
// 【更新日】
// 【 概要 】なし
// ================================================
function getFrameName(_object) {
	var frameName_;
    if(_object.frames != undefined){
    	frameName_ = _object.frames.name;
    } else {
    	// ie9
    	frameName_ = _object.document.frames.name;
    }
	return frameName_;
}
