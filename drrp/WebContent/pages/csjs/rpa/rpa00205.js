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
function doChushutsu(object) {
	window.top.showAjaxConfirm(object, 'Q00001,operation_extraction');
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