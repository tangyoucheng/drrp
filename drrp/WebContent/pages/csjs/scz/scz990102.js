
var scz990102GoNextPageFlag = false;
var scz990102MenuLinkObject = "";
var scz990102MenuTabCheckFlag = false;
//================================================
//  菜单押下の処理
//  【入力】なし
//  【返却】なし
//  【作成】kourei
//  【更新】
//  【概要】
//================================================
$(document).ready(function(){

	// タイマーevent
    window.top.addTimerEvent($(document));

	$(".menuMiddle").next().hide();

    $('.menuMiddle').click(function() {
		$(".menuMiddle").next().hide();
		$(this).next().show();
        return false;
    });

    if ($("[id=parentMenuId]").val() != '') {
    	scz990102MenuTabCheckFlag = true;
    	// 菜单タブクリック時は、先頭のサブ菜单タブの機能タブの画面を業務フレームに表示する。
    	if ($("[id=menuMiddleInitId]").length == 1) {
    		$("[id=menuMiddleInitId]").click();
		}
    	if ($("[id=menuLinkInitId]").length == 1) {
    		$("[id=menuLinkInitId]").click();
    		$('.menuLink').removeClass("menuSelected");
    		$("[id=menuLinkInitId]").addClass("menuSelected");
		}
    	
		scz990102MenuTabCheckFlag = false;
	} else {
		// 業務フレームに初期表示時は画面ＩＤ：SCHM05201「お知らせ」を表示する。
	    window.top.right.location.replace(getContextPath('/schm/schm05201Action!doInit.do'));
	}

});

//================================================
//  菜单押下の処理
//  【入力】_url URL
//  【入力】_menuId 菜单ＩＤ
//  【入力】_name 菜单表示名
//  【返却】なし
//  【作成】kourei
//  【更新】
//  【概要】
//================================================
function goNextPage(_target, _url, _menuId) {

    // ドキュメントの読み込み完了以外の場合、Submit禁止
    if (window.top.right.document.readyState != 'complete') {
        return false;
    }
	scz990102MenuLinkObject = $(_target);
	submitForm.menuId.value = _menuId;
	submitForm.url.value  = _url;
//	if (!scz990102MenuTabCheckFlag && $(window.top.right.document).find("input[id=pubjutakuEditPage]").length > 0) {
//		window.top.showAjaxConfirm(null,'Q00002',false,scz990102GoNextPage);
//	} else {
		scz990102GoNextPage(true);
//	}

}
//================================================
// 菜单押下二重制御のクリア処理
//【入力】なし
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function releaseDblClickFlg(){
	$("[id=leftMenuDblClickFlag]").val(false);
	return false;
}


//================================================
//菜单押下の処理
//【入力】_object
//【返却】なし
//【作成】kourei
//【更新】
//【概要】
//================================================
function scz990102GoNextPage(_object) {
	if (_object) {
		$('.menuLink').removeClass("menuSelected");
		$(scz990102MenuLinkObject).addClass("menuSelected");
		
		var parameter = "form.url=" + encodeURI(encodeURI(submitForm.url.value))
		+ "&form.menuId=" + encodeURI(encodeURI(submitForm.menuId.value));
		 window.top.right.location.replace(getContextPath("/common/menuBootAction.do?") + parameter);
	}
}