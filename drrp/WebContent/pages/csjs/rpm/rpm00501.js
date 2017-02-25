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
function doEntry(object) {
	window.top.showAjaxConfirm(object, 'Q00001,operation_entry');
//	window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:400px;height:300px"
//		, url: getContextPath('/rpm002/rpm00202Action!doInit.do')}
//		, undefined
//		, null);

//	window.parent.showDlg({style: "height:600px", url: 'http://www.baidu.com'}, undefined, null);
    return false;
// if (($('#hidUserId').val() == "")
//      || ($('#hidUserId').val() != $('#userId').val())) {
//        window.top.showAjaxMessageById('W30005');
//        return false;
//    } else {
//        return window.top.showAjaxConfirm(object, 'Q00001,operation_entry');
//    }
}
