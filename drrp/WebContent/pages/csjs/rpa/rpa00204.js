
// ================================================
// 選択したTRのカラス設定
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setSelectedTrColor(trInfo) {
    $('#tablecloth_row1 td').removeClass("selected");
    $("#" + trInfo.id + ' td').addClass("selected");
}

//================================================
//選択したTRのカラス設定
//【入力】なし
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function setSelectedData() {
    var selectedTd = $("#tablecloth_row1").find("td[class*=selected]");
    
    if (selectedTd.length == 0) {
        window.top.showAjaxMessage('请选择患者信息！');
        return false;
    } else {
        var dataIndex = selectedTd.parent()[0].id.replace("patientTr_", "");
        var _rpa00204RetuanObject = new Object();
        //用户ID
        _rpa00204RetuanObject.patientId = $("#hidUserId_" + dataIndex).val();
        //姓名
        _rpa00204RetuanObject.patientName = $("#hidUserName_" + dataIndex).val();
        //性别
        _rpa00204RetuanObject.sex = $("#hidSexId_" + dataIndex).val();
//        if ($("#hidSexId_" + dataIndex).val() == '1') {
//            _rpa00204RetuanObject.sex = '男';
//		}
//        if ($("#hidSexId_" + dataIndex).val() == '2') {
//            _rpa00204RetuanObject.sex = '女';
//        }
        //生日
        _rpa00204RetuanObject.birthday = $("#hidBirthday_" + dataIndex).val();
        //年龄
        _rpa00204RetuanObject.age = getAgeByBirthday($("#hidBirthday_" + dataIndex).val());
        //座机号码
        _rpa00204RetuanObject.phoneNumber = $("#hidPhoneNumber_" + dataIndex).val();
        //手机号码
        _rpa00204RetuanObject.ceelNumber = $("#hidCeelNumber_" + dataIndex).val();
        //身份证号码
        _rpa00204RetuanObject.idNumber = $("#hidIdNumber_" + dataIndex).val();
        //住址
        _rpa00204RetuanObject.addr = $("#hidAddr_" + dataIndex).val();
        //处方种类
        _rpa00204RetuanObject.prescriptionType = $("#hidPrescriptionType_" + dataIndex).val();
        //处方内容
        _rpa00204RetuanObject.contents = $("#hidContents_" + dataIndex).val();
        //处方图片内容
        _rpa00204RetuanObject.fileContents = $("#hidFileContents_" + dataIndex).val();
        //处方金额
        _rpa00204RetuanObject.price = $("#hidPrice_" + dataIndex).val();
    	window.top.closeDlg(window.top.right.searchPatientCallback(_rpa00204RetuanObject));
	}
}

