
var scz990101TabMenuId = "";
var scz990101TabMenuObject = "";

var scz990101SelectMenuCallBackFlag = false;
var scz990101selectedMenuId = "";

//================================================
//  菜单の設定
//  【入力】なし
//  【返却】なし
//  【作成】kourei
//  【更新】
//  【概要】
//================================================
$(document).ready(function(){
	setStorage("f");
	// initialize scrollable
	$(".scrollable").scrollable({
		vertical : true,
		size : 2,
		clickable : false
	});
	var to = $(".scrollable").data("scrollable").getSize() - 2;
	if (to >0) {
		$(".scrollable").data("scrollable").seekTo(to);
	}
	
	$('#mainMenuCtrl table:eq('+$(".scrollable").data("scrollable").getIndex()+')').css('margin-left','40px');

	var fade = function(id,s){

		scz990101TabMenuObject = s;
		scz990101TabMenuId = id;

		s.tabs.removeClass(s.selected);
		s.tab(id).addClass(s.selected);
		s.items.fadeOut();
		s.item(id).fadeIn();
		return false;
	};
	$.fn.fadeTabs = $.idTabs.extend(fade);
	$(".idVerticalTabs").fadeTabs();
	
	// マイ菜单表示
//	$("[id=scz990101Mymenu]").click();
	
	// タイマーevent
//	window.top.addTimerEvent($(document));
	
	// 打开待处理信息
    $('#menuTop_0_0').addClass("selected");
	selectMenu('rpa','menuTop_0_0');
        
});

//================================================
// 前ページ処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function doPrev(obj){
	$('#mainMenuCtrl table:eq('+($(".scrollable").data("scrollable").getIndex() - 1)+')').css('margin-left','40px');
	$('#mainMenuCtrl table:eq('+$(".scrollable").data("scrollable").getIndex()+')').css('margin-left','0px');
}


//================================================
// 次ページ処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function doNext(obj){
	$('#mainMenuCtrl table:eq('+($(".scrollable").data("scrollable").getIndex() + 1)+')').css('margin-left','40px');
}

//================================================
//イメージの変更処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function changeMenuImg(obj, flg){
  var hdnMenuId = scz990101Form.hdnMenuId.value;
  if($(obj)[0].name != hdnMenuId){
      if(flg == 'mo'){
    	  $(obj)[0].src = $(obj)[0].src.replace(/_([^_]*)\./, "_mo.");
      } else {
    	  $(obj)[0].src = $(obj)[0].src.replace(/_([^_]*)\./, "_off.");
      }
  }
}

//================================================
//  ボタン押下処理
//  【入力】なし
//  【返却】なし
//  【作成】kourei
//  【更新】
//  【概要】
//================================================
function selectMenu(_menuId,_thisObject){
	
	// 二重送信制御
	if ($(_thisObject).length > 0 && $(_thisObject)[0].className == "selected") {
		return false;
	}

	scz990101selectedMenuId = _menuId;
//	if ($(window.top.right.document).find("input[id=pubjutakuEditPage]").length > 0) {
//		window.top.showAjaxConfirm(null,'Q00002',false,selectMenuCallBack);
//		return false;
//	} else {
		selectMenuCallBack(true);
//	}
}

//================================================
//ボタン押下処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function selectMenuCallBack(_object){
	scz990101SelectMenuCallBackFlag = _object;
	if (_object) {
		scz990101SelectMenuCallBackFlag = false;
		if (scz990101TabMenuObject != "" && scz990101TabMenuId != "") {
			scz990101TabMenuObject.tabs.removeClass(scz990101TabMenuObject.selected);
			scz990101TabMenuObject.tab(scz990101TabMenuId).addClass(scz990101TabMenuObject.selected);
		}
		
		var _menuId = scz990101selectedMenuId;
		if(_menuId == null || _menuId.length == 0){
			$(".idVerticalTabs").find('a').removeClass("selected");
//			$("[id=scz990101Mymenu]")[0].src = $("[id=scz990101Mymenu]")[0].src.replace(/_([^_]*)\./, "_mo.");
//			scz990101Form.hdnMenuId.value = $("[id=scz990101Mymenu]")[0].name;
		} else {
//			$("[id=scz990101Mymenu]")[0].src = $("[id=scz990101Mymenu]")[0].src.replace(/_([^_]*)\./, "_off.");
			scz990101Form.hdnMenuId.value = _menuId;
		}
	    if (window.top.right.isSubmit != undefined) {
	        window.top.right.isSubmit = true;
	    }
		window.top.left.location.replace(getContextPath("/scz/scz990102Action.do?scz990102Form.parentMenuId=") + _menuId);
//		scz990102Action["scz990102Form.parentMenuId"].value = _menuId;
//		scz990102Action.submit();
	}
	
}

//================================================
//  文字サイズ変更処理
//  【入力】なし
//  【返却】なし
//  【作成】kourei
//  【更新】
//  【概要】
//================================================
//function doZoom(_size) {
//	scz990101Action.fontSize.value = _size;
///*
//	var divTgs = window.parent.frames.right.document.getElementsByTagName("div");
//    for ( i = 0 ; i < divTgs.length ; i++ ) {
//        divTgs[i].style.fontSize = _size + "px";
//    }
//    var tableTgs = window.parent.frames.right.document.getElementsByTagName("table");
//    for ( i = 0 ; i < tableTgs.length ; i++ ) {
//        tableTgs[i].style.fontSize = _size + "px";
//    }
//*/
//	
//	var style_visibility =$(window.top.right.document).find("body")[0].currentStyle.visibility;
//	if (_size == 10) {
//		$("[id=fontSizeSubbtnS]").removeClass().addClass("font_size_subbtn_s_on");
//		$("[id=fontSizeSubbtnM]").removeClass().addClass("font_size_subbtn_m");
//		$("[id=fontSizeSubbtnL]").removeClass().addClass("font_size_subbtn_l");
//		
//		$(window.top.head.document).find("body").css("cssText", "font-size: 80% !important");
//		$(window.top.left.document).find("body").css("cssText", "font-size: 80% !important");
//		$(window.top.right.document).find("body").css("cssText", "font-size: 80% !important");
//	} else if (_size == 12) {
//		$("[id=fontSizeSubbtnS]").removeClass().addClass("font_size_subbtn_s");
//		$("[id=fontSizeSubbtnM]").removeClass().addClass("font_size_subbtn_m_on");
//		$("[id=fontSizeSubbtnL]").removeClass().addClass("font_size_subbtn_l");
//		
//		$(window.top.head.document).find("body").css("cssText", "font-size: 90% !important");
//		$(window.top.left.document).find("body").css("cssText", "font-size: 90% !important");
//		$(window.top.right.document).find("body").css("cssText", "font-size: 90% !important");
//	} else {
//		$("[id=fontSizeSubbtnS]").removeClass().addClass("font_size_subbtn_s");
//		$("[id=fontSizeSubbtnM]").removeClass().addClass("font_size_subbtn_m");
//		$("[id=fontSizeSubbtnL]").removeClass().addClass("font_size_subbtn_l_on");
//		
//		$(window.top.head.document).find("body").css("cssText", "font-size: 100% !important");
//		$(window.top.left.document).find("body").css("cssText", "font-size: 100% !important");
//		$(window.top.right.document).find("body").css("cssText", "font-size: 100% !important");
//	}
//	$(window.top.right.document).find("body").css("visibility", style_visibility);
//	
//	return false;
//}

//function openHelp(_url) {
//	window.open(_url, "helpWindow", "left=0,top=0,width=150,height=430");
//	return false;
//}

//================================================
//クッキーの書く処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
//function writeCookie(_size) {
//	
//	doZoom(_size);
//	
//	// クッキーが利用可能かどうか
//	if(!navigator.cookieEnabled){
//		return;
//	}
//	var dt = new Date();
//	var keyAry = new Array();
//	var ckAry = new Array();
//	
//	dt.setYear(dt.getYear()+1); 
//	
//	keyAry[0] = document.scz990101Action["scz990101Form.userId"].value + "_fontSize";
//	ckAry[0] = window.top.head.document.scz990101Form["fontSize"].value;
//	
//	for (var i = 0; i < keyAry.length; i++) {
//		ckStr = "";
//		ckStr = keyAry[i] + "=" + ckAry[i] + ";";
//		ckStr += "expires=" + dt.toGMTString() + ";";
//		document.cookie = ckStr;
//	}
//}

//================================================
//クッキーの読み処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
//function readCookie() {
//	// クッキーが利用可能かどうか
//	if(!navigator.cookieEnabled){
//		return;
//	}
//	
//	if (document.scz990101Action["scz990101Form.cookieFlg"].value == "1") {
//		return;
//	} else {
//		document.scz990101Action["scz990101Form.cookieFlg"].value = "1";
//	}
//	// Cookie文字列
//	var sCookie = document.cookie;
//	// ";"で区切って"キー=値"の配列にする 
//	var aData = sCookie.split(";");
//	// すべての半角スペースを表す正規表現
//	var oExp = new RegExp(" ", "g");
//	
//	var i = 0;
//	while (aData[i]) {
//		// さらに"="で区切る
//		var aWord = aData[i].split("=");
//		// 半角スペース除去
//		aWord[0] = aWord[0].replace(oExp, "");
//		var fontSizeCookieKey =  document.scz990101Action["scz990101Form.userId"].value + "_fontSize";
//		if (fontSizeCookieKey == aWord[0]) {
//			if (unescape(aWord[1]) != "undefined") {
//                scz990101Action.fontSize.value = unescape(aWord[1]);
//				doZoom(parseInt(unescape(aWord[1])));
//			} else {
//				window.top.head.doZoom(window.top.head.document.scz990101Form["fontSize"].value);
//			}
//		} 
//		if (++i >= aData.length) {
//			break;
//		}
//	}
//}

//================================================
// 自画面閉じる
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function loginOut() {
//    window.top.close();
    window.parent.location = getContextPath('/login');
    return false;
}

window.onbeforeunload = function() {
	
	setStorage("t");
    // action起動
    $.ajax ({
        url : "scz990101Json!doLoginOut.do",
        success : function(result) {
        },
        error : function() {
        }
    });   
};


//================================================
// ヘルプ画面を開く
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
//function goSchz00601() {
//
//    // ドキュメントの読み込み完了以外の場合、Submit禁止
//    if (window.top.right.document.readyState != 'complete') {
//        return false;
//    }
//    
//	//定数定義
//	var MAIN_WINDOW_URL = getContextPath('/schz00601Action.do');
//	var MODE = 1;
//	var height = (screen.availHeight - 30) + "px";
//	
//	var iWidth = 823;
//	var iHeight = 720;
//	var iTop = (window.screen.availHeight-30-iHeight)/2;
//	var iLeft = (window.screen.availWidth-10-iWidth)/2;
//
//	//メインウィンドウオープン処理 //
//	var MAIN_WINDOW_NAME = "hyogojtkMainWindow" + Math.round(Math.random()*10000);
////	var MAIN_WINDOW_STYLE = "width=" + screen.availWidth + ",height=" + height + ",top=0, left=0,toolbar=no,location=no,directories=no," +
//	var MAIN_WINDOW_STYLE = "width=" + iWidth + ",height=" + iHeight + ",top=" + iTop + ", left=" + iLeft + ",toolbar=no,location=no,directories=no," +
//	                        "menubar=no,resizable=yes," +
//	                        "status=no,scrollbars=yes,titlebar=yes";
//	window.opener = "";
//	var wMainWin = window.open(MAIN_WINDOW_URL,MAIN_WINDOW_NAME, MAIN_WINDOW_STYLE);
//	
//    return false;
//}


//=================================================
// set localStorage
//【 入力 】なし
//【返却値】なし
//【作成者】gl
//【作成日】2013/04/11
//=================================================
function setStorage(value) {
    localStorage.setItem("flg", value);
}
