//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {

});


// ================================================
// 患者信息录入
// 【 入力 】 なし
// 【返却値】 なし
// 【作成者】 fsb
// 【作成日】 2012/11/29
// 【更新者】 fsb
// 【更新日】 2012/11/29
// 【 概要 】
// ================================================
function doSearchPatient() {
    window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:800px;height:570px"
        , url: getContextPath('/rpa002/rpa00204Action!doInit.do')}
        , undefined
        , null);

    return false;
}

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
function searchPatientCallback(_rerurnObject) {
    if (_rerurnObject != undefined) {
        $('#caseNumber').val(_rerurnObject.patientId);
        $('#patientName').val(_rerurnObject.patientName);
        if (_rerurnObject.sex == "1") {
            $("#sex").attr("value",'男');
        }
        if (_rerurnObject.sex == "2") {
            $("#sex").attr("value",'女');
        }
        $('#birthday').attr("value",_rerurnObject.birthday);
        //年龄
        $("#birthday_age").val(getAgeByBirthday($("#birthday").val()));
        
    }
}

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
function doEntry(object) {
//    doCalcDrugPrice();
    window.top.showAjaxConfirm(object, 'Q00001,operation_entry');
    return false;
}
