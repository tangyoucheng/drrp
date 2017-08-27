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
        if (_rerurnObject.sex == "1"
            || _rerurnObject.sex == "2") {
            $("input[name='rpc00101Form.sexId'][value='"+_rerurnObject.sex+"']").attr("checked",true);
        }
        $('#birthday_date').attr("value",_rerurnObject.birthday);
        $('#birthday_hidden').attr("value",_rerurnObject.birthday);
        getAge('birthday');
//        $('#age').val(_rerurnObject.age);
        
//        $('#phoneNumber').val(_rerurnObject.phoneNumber);
        $('#ceelNumber').val(_rerurnObject.ceelNumber);
//        $('#idNumber').val(_rerurnObject.idNumber);
        $('#addr').val(_rerurnObject.addr);
        
        $('#nation').val(_rerurnObject.nation);
        $('#placeOfBirth').val(_rerurnObject.placeOfBirth);
        $('#maritalStatus').val(_rerurnObject.maritalStatus);
        $('#lunarBirthday_date').attr("value",_rerurnObject.lunarBirthday);
        $('#lunarBirthday_hidden').attr("value",_rerurnObject.lunarBirthday);
        $('#timeOfBirth_time').attr("value",_rerurnObject.timeOfBirth);
        $('#timeOfBirth_hidden').attr("value",_rerurnObject.timeOfBirth);
        $('#zodiac').val(_rerurnObject.zodiac);
        $('#company').val(_rerurnObject.company);
        $('#profession').val(_rerurnObject.profession);
        
        
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



