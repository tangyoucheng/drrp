
// ================================================
// 检索処理を行う
// 【 入力 】 なし
// 【返却値】 なし
// 【作成者】 fsb
// 【作成日】 2012/11/29
// 【更新者】 fsb
// 【更新日】 2012/11/29
// 【 概要 】
// ================================================
function doSearch(form) {

    var url = getContextPath("/rpm/rpm00401Action!doSearch.do");
    form.action = url;
    form.submit();
}
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
    return false;
}

// ================================================
// 削除処理を行う
// 【 入力 】 なし
// 【返却値】 なし
// 【作成者】 fsb
// 【作成日】 2012/11/29
// 【更新者】 fsb
// 【更新日】 2012/11/29
// 【 概要 】
// ================================================
function doDelete(object) {
    if ($('#hidUserId').val() != $('#userId').val()) {
        window.top.showAjaxMessageById('W30005');
        return false;
    } else {
        return window.top.showAjaxConfirm(object, 'Q00001,operation_delete');
    }
}