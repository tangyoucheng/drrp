//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {
    //年龄
    $("#age").val(getAgeByBirthday($("#birthday").val()));
});

//================================================
//登録処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doUpdate(object) {
	window.top.showAjaxConfirm(object, 'Q00001,operation_update');
	return false;
}

//================================================
//削除処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doDelete(object) {
return window.top.showAjaxConfirm(object, 'Q00001,operation_delete');
}




