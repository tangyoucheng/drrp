/**
 * @author tangdaiming
 */
//$(document).ready(function(){
// 	var box;
// 	var result = [];
// 	var callback = function(){};
// 	Dialog = function(){};
// 	Dialog.data = [];
//	Dialog.createDialog = function(){
//		var url = getContextPath("/common/dialogCompleteAction.do?");
//		if (this.data.parameter) {
//			url = url + this.data.parameter + "&";
//		}
//		var html = "<div id='dialogDiv' style='display:none;'>" +
//			 	   "<iframe id='dialogFrame' name='dialogFrame' src='" + url + "' class='model' height='100%' width='100%'  frameborder='0'></iframe>"+
//				   "</div>";
//		var boxy = new Boxy($(html), {
//		    modal: true,
//			unloadOnHide: true
//		});
//		boxy.resize(570, 200);
//		return boxy;
//	}
//	Dialog.show = function(func){
//		box = this.createDialog();
//		callback = func;
//	}
//	Dialog.hide = function(){
//		box.hide();
//		callback.call(this,result);
//	}
//	Dialog.setResult = function(data){
//		result.data = data;
//	}
//	Dialog.getResult = function(){
//		return result;
//	}
//	window.Dialog = Dialog;
//}) ;

// デバッグ用アラート
function debug(_message) {
//	alert("#debug#\n\n" + _message);
}

// jQueryオブジェクト（showDialogで設定され、releaseVarで破棄される）
var applicationFrame = undefined;
var dialogLayer = undefined;
var dialogLayout = undefined;
var dialogFrame = undefined;

// ターゲットform
var actionForm = undefined;
// クリックされたターゲット
var processTarget = undefined;
// コールバックファンクション
var callBackFunc = undefined;
// ダイアログオープン時にターゲットを実行する場合 true
var isBootOnClick = false;
// ターゲット実行の際、「処理中」ダイアログを表示する
var isChainPleaseWait = false;
// ダイアログ表示用メッセージ
var dialogMessage = undefined;
var isYes = false;
// 確認メッセージだけ
var isNoActionConfirm = false;

//================================================
// ダイアログ表示
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function showDialog(_args, _actionForm, _processTarget, _callBackFunc, _isBootOnClick, _isChainPleaseWait) {debug("showDialog");

	// 開いていたら閉じる
//	releaseVar();

	// パラメータ保存
	actionForm = _actionForm;
	processTarget = _processTarget;
	callBackFunc = _callBackFunc;
	isBootOnClick = _isBootOnClick;
	isChainPleaseWait = _isChainPleaseWait;

	// 業務画面フレーム
	applicationFrame = $("#right").contents().find("body");

	// form
	if (isEmpty(actionForm)) {
		actionForm = isEmpty(processTarget)
			? applicationFrame.find("form").get(0)
			: actionForm = processTarget.parents("form");
	}

	// Body部を非表示とする
//	applicationFrame.css("visibility", "hidden");

	// 背景レイヤー生成
//	$(createModalLayer()).appendTo("body");
	// ダイアログ生成
//	$(createModalDialog()).appendTo("body");

	// ダイアログ枠を変数に保存
//	dialogLayer = $("#dialogLayer");
//	dialogLayout = $("#dialogLayout");
//	dialogFrame = $("#modalDialog");

	window.top.showDlg({style: "margin-left: auto;margin-right: auto;width:570px;height:316px"
		, url: getContextPath('/framework/dialogAction.do' + _args)}
		, undefined
		, null);
	// form作成
//	var form_ = "";
//	form_ += "<form";
//	form_ += " style='display:none'";
//	form_ += " method='POST'";
//	form_ += " action='dialogAction.do" + _args + "'";
//	form_ += " target='modalDialog'";
//	form_ += ">";
//	form_ += "<input";
//	form_ += " id='formMessage'";
//	form_ += " name='form.message'";
//	form_ += " />";
//	form_ += "</form>";
//	form_ = $(form_).appendTo("body");
//
//	// メッセージ設定
//	if (!isEmpty(dialogMessage)) {
//		form_.find("#formMessage").val(dialogMessage);
//		dialogMessage = undefined;
//	}
//
//	// メッセージが空の場合INPUTを消す
//	if (isEmpty(form_.find("#formMessage").val())) {
//		form_.find("#formMessage").remove();
//	}
//
//	// サブミット
//	form_.submit();
//	// 削除
//	form_.remove();
//	
//	window.scrollTo(0, 0);

}

//================================================
// ダイアログの onload からコールされる
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//var __dialogResize_callBack;
//function dialogResize(_callBack) {debug("dialogResize");
//	// コールバックファンクション
//	__dialogResize_callBack = _callBack;
//	// レイヤー調整
//	adjustDialog();
//	// ダイアログ起動
//	dialogFrame.animate(
//		{width: "540px", height: "262px"},
//		220,
//		"swing",
//		function() {
//			// ダイアログ領域調整
//			adjustDialog();
//			// ウィンドウリサイズeventハンドリング
//			$(window).bind("resize", adjustDialog);
//			// ダイアログ側の初期化ファンクション的调用
//			__dialogResize_callBack();
//		}
//	);
//}

//================================================
// ダイアログ起動時処理（dialog.jspが表示完了後、この関数的调用）
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function bootOnClick() {
//	if (isBootOnClick) {debug("bootOnClick");
//		// 起動と同時に処理を実行する（「処理中」で使用）
//		beginProcess();
//	}
//}

//================================================
// 処理開始
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function beginProcess() {
	if (!isEmpty(processTarget)) {debug("beginProcess");
		var target_ = processTarget;
		processTarget = undefined;
		window.top.right.dblClickFlg = false;
		window.top.right.submitUrl(function(){}, target_);
	}
}

//================================================
// 「YES」押下時処理
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function targetClick() {debug("targetClick");

    isNoActionConfirm = false;
	if (isEmpty(processTarget)) {
	    isNoActionConfirm = true;
		return;
	}

	if (isChainPleaseWait) {
		showPleaseWait(processTarget);
	} else {
		beginProcess();
	}

}

//================================================
// ポップアップ错误画面表示
//【入力】 form（jQueryオブジェクト）
//【入力】 错误メッセージ設定部品（jQueryオブジェクト）
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showPopUpMessage(_type, _message, _form, _callBackFunc) {debug("showPopUpMessage");

	// GETパラメータ生成
	var args = "";
	// 错误メッセージ标识
	args += "?form." + _type + "=true&form.message=" + encodeURI(_message);

	// メッセージ
	dialogMessage = _message;

	// ダイアログオープン
	showDialog(args, _form, undefined, _callBackFunc, false, false);

}

//================================================
// AJAX通信異常メッセージ
//【入力】 错误メッセージ
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showAjaxConnectFailed() {debug("showAjaxConnectFailed");

	// GETパラメータ生成
	var args = "";
	// 错误メッセージ标识
	args += "?form.isAjaxConnectFailed=true";

	// ダイアログオープン
	showDialog(args, undefined, undefined, undefined, false, false);

}

//================================================
// AJAX処理異常メッセージ
//【入力】 错误メッセージ
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showAjaxMessage(_message) {debug("showAjaxMessage");

	// GETパラメータ生成
	var args = "";
	// インフォメーションメッセージ标识
	args += "?form.isInfo=true&form.message=" + encodeURI(_message);

	// メッセージ
//	dialogMessage = _message;

	// ダイアログオープン
	showDialog(args, undefined, undefined, undefined, false, false);

}
//================================================
//AJAX処理異常メッセージ
//【入力】 错误メッセージ
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showAjaxMessageCallback(_message , _callBackFunc) {debug("showAjaxMessageCallback");

	// GETパラメータ生成
	var args = "";
	// インフォメーションメッセージ标识
	args += "?form.isInfo=true&form.message=" + encodeURI(_message);

	// メッセージ
//	dialogMessage = _message;

	// ダイアログオープン
	showDialog(args, undefined, undefined, _callBackFunc, false, false);

}

//================================================
//AJAX処理異常メッセージ
//【入力】 错误メッセージ
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showAjaxMessageById(_message) {debug("showAjaxMessage");

	// GETパラメータ生成
	var args = "";
	// インフォメーションメッセージ标识
	args += "?form.isInfoId=true&form.message=" + encodeURI(_message);

	// メッセージ
//	dialogMessage = _message;

	// ダイアログオープン
	showDialog(args, undefined, undefined, undefined, false, false);

}

//================================================
// AJAX処理異常メッセージ
//【入力】 错误メッセージ
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showAjaxConfirm(_target, _message, _isChainPleaseWait, _callBackFunc) {debug("showAjaxConfirm");

	// GETパラメータ生成
	var args = "";
	// 確認メッセージ标识
	args += "?form.isConfirm=true&form.message=" + encodeURI(_message);

	// メッセージ
//	dialogMessage = _message;

	// ダイアログオープン
	showDialog(args, undefined, _target, _callBackFunc, false, _isChainPleaseWait);

	// 終了
	return false;

}

//================================================
// 処理中ダイアログ表示
//【入力】 なし
//【返却】 なし
//【作成】
//【更新】
//【概要】
//================================================
function showPleaseWait(_target) {debug("showPleaseWait");

	// GETパラメータ生成
	var args = "";
	// 処理中标识
	args += "?form.isPleaseWait=true";

	// ダイアログオープン
	showDialog(args, undefined, _target, undefined, true, false);

	// 終了
	return false;

}

//================================================
// ダイアログフレームサイズ調整
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function adjustDialog() {debug("adjustDialog");
//
//	var maxWidth = $(window).width() > $("body").width()
//		? $(window).width() : $("body").width();
//
//	var maxHeight = $(window).height() > $("body").height()
//		? $(window).height() : $("body").height();
//
//	// 全範囲を隠す
//	try {
//		dialogLayer.width(maxWidth);
//		dialogLayer.height(maxHeight);
//	} catch(e) {
//		debug(e.message);
//	}
//
//	// ウィンドウの真ん中
//	try {
//		dialogLayout.width($(window).width());
//		dialogLayout.height($(window).height());
//	} catch(e) {
//		debug(e.message);
//	}
//
//}

//================================================
// 「OK」「YES」押下時処理
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function clickYes(_action, _nonAction, _fieldID, _val) {
	
	isYes = true;

	// Start 2012年02月10日追加　ウォーターマークで入力規則が非表示
//	if ( processTarget!=null && processTarget!= undefined && processTarget.length > 0) {	
//		$(window.frames.document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
//			if($(this).val()!=this.getAttribute("grayMessage")){
//				$(this).css({"color":"#000"});
//			}else{
//				$(this).val("").css({"color":"#888"});
//			} 
//			
//		});
//		$(window.top.right.document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
//			if($(this).val()!=this.getAttribute("grayMessage")){
//				$(this).css({"color":"#000"});
//			}else{
//				$(this).val("").css({"color":"#888"});
//			} 
//			
//		});
//	}

	// End  2012年02月10日追加　ウォーターマークで入力規則が非表示
	// 错误情報クリア
//	clearErrorInfo();

	// 排他制御
//	setVal(_fieldID, _val);
	
	if(!isNoActionConfirm){
		// 用户指定のコールバック関数呼出
		callBack(true);
	}

	// ダイアログを閉じる
	closeDialogAction(_action, _nonAction, true, true);

}

//================================================
// 「NO」押下時処理
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function clickNo(_action, _nonAction, _fieldID, _val) {
	
	isYes = false;

//	window.top.closeDlg();
	
//	// 二重送信标识を解除
//	try {window.top.right.dblClickFlg = false;} catch(e) {debug(e.message);}
//
//	// 错误情報クリア
//	clearErrorInfo();
//
//	// 排他制御
//	setVal(_fieldID, _val);
//	
//	// 用户指定のコールバック関数呼出
//	callBack(false);
//
	// ダイアログを閉じる
	closeDialogAction(_action, _nonAction, false, false);

}

//================================================
// 错误情報クリア
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function clearErrorInfo() {
//
//	try {
//		// ポップアップ入力画面
//		$(window.top.document).find('#boxyDialog').find('iframe').contents().find("#popUpMessage").remove();
//	} catch(e) {
//		debug(e.message);
//	}
//
//	try {
//		// 通常画面
//		$(window.top.right.document).contents().find("#popUpMessage").remove();
//	} catch(e) {
//		debug(e.message);
//	}
//
//}

//================================================
// 値設定（ダイアログ or 通常画面）
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function setVal(_fieldID, _val) {
//	// 排他制御
//	if (!isEmpty(_fieldID)) {
//		// ダイアログ入力画面オープン中
//		if ($(window.top.document).find('#boxyDialog').find('iframe').size() > 0) {
//			debug("setVal in boxyDialog");
//			$(window.top.document).find('#boxyDialog').find('iframe').contents().find("#" + _fieldID).val(_val);
//		}
//		// 通常入力画面
//		else {
//			debug("setVal in normal");
//			$(window.top.right.document).contents().find("#" + _fieldID).val(_val);
//		}
//	}
//}

//================================================
// ボタン押下時コールバック
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function callBack(_result) {
	var func = callBackFunc;
	callBackFunc = undefined;
	if (!isEmpty(func)) {debug("callBack");
		if (!func(_result)) {
			return false;
		}
	}
	return true;
}

//================================================
//変数クリア
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function releaseVar() {debug("releaseVar");
//	try {dialogFrame.remove();} catch(e) {debug(e.message);}
//	try {dialogLayout.remove();} catch(e) {debug(e.message);}
//	try {dialogLayer.remove();} catch(e) {debug(e.message);}
//	try {$(window).unbind("resize", adjustDialog);} catch(e) {debug(e.message);}
//	dialogFrame = undefined;
//	dialogLayout = undefined;
//	dialogLayer = undefined;
//}

//================================================
// ダイアログクローズ
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
var __closeAction_url;
var __closeAction_nonAction;
var __closeAction_isCallTargetClick;
function closeDialogAction(_url, _nonAction, _isCallTargetClick,_isCallBackFunc) {debug("closeDialogAction");
	// パラメータ設定
	__closeAction_url = _url;
	__closeAction_nonAction = _nonAction;
	__closeAction_isCallTargetClick = _isCallTargetClick;
	// 閉じた後に実行
	closeDialog(null,_isCallBackFunc);
}

//================================================
// ダイアログクローズ
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function closeDialog(_callBack,_isCallBackFunc) {debug("closeDialog");


	window.top.closeDlg();
	// 閉じる処理
	closeAction(_isCallBackFunc);
}

//================================================
// ダイアログクローズ時処理
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function closeAction(_isCallBackFunc) {debug("closeAction");
	try {
		// クリック処理
		if (__closeAction_isCallTargetClick) {
			targetClick();
		}
		// actionを起動
		if (__closeAction_url != undefined && __closeAction_url != "" && __closeAction_url != __closeAction_nonAction) {
		    isNoActionConfirm = false;
		    // TODO 画面迁移问题未解决
			var form_ = actionForm;
			actionForm = undefined;
			form_.action = __closeAction_url;
			form_.submit();
//			window.top.right.location.replace(__closeAction_url);
		} else {
			// 隠していたBody部を表示
			if (applicationFrame != undefined) {
				applicationFrame.css("visibility", "visible");
			    
				if (!isYes || (isYes && isNoActionConfirm)) {
					// ウォーターマークで入力規則を表示
					$(window.frames.document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
						if($(this).val()==''){
							$(this).css({"color":"#888"}).val(this.getAttribute("grayMessage"));
						}
					});
					$(window.top.right.document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
						if($(this).val()==''){
							$(this).css({"color":"#888"}).val(this.getAttribute("grayMessage"));
						}
					});
					
				}
			}
			if(isYes && isNoActionConfirm){
				// 用户指定のコールバック関数呼出
				callBack(_isCallBackFunc);
			}
		}
	} catch(e) {
		debug(e.message);
	}
}

//================================================
// ダイアログ用背景レイヤー生成
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function createModalLayer() {debug("createModalLayer");
//	var html_ = "";
//	html_ += "<div id=\"dialogLayer\">";
//	html_ += "</div>";
//	return html_;
//}

//================================================
// ダイアログフレーム生成
//【入力】GETパラメータ
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
//function createModalDialog() {debug("createModalDialog");
//	var html_ = "";
//	html_ += "<table id=\"dialogLayout\">";
//	html_ += "<tr><td valign=\"center\" align=\"center\">"
//	html_ += "<iframe";
//	html_ += " id=\"modalDialog\"";
//	html_ += " name=\"modalDialog\"";
//	html_ += " src=\"about:blank\"";
//	html_ += " hspace='0'";
//	html_ += " vspace='0'";
//	html_ += " marginheight='0'";
//	html_ += " marginwidth='0'";
//	html_ += " border='0'";
//	html_ += " frameborder='0'";
//	html_ += " framespacing='0'";
//	html_ += " scrolling='no'";
//	html_ += ">";
//	html_ += "</iframe>";
//	html_ += "</td></tr>";
//	html_ += "</table>";
//	return html_;
//}

//================================================
// 空チェック
//【入力】
//【返却】
//【作成】
//【更新】
//【概要】
//================================================
function isEmpty(_val) {
	if (_val == undefined || _val == null) {
		return true;
	}
	if (typeof(_val) == "string" && _val == "") {
		return true;
	}
	return false;
}