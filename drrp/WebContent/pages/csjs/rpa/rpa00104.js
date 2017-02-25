var rpa00150InitFileContents;
// ================================================
// HTMLロード時処理
// 【入力】
// 【返却】
// 【作成】
// 【更新】
// 【概要】
// ================================================
$(document).ready(function(){
    //年龄
    $("#age").val(getAgeByBirthday($("#birthday").val()));
    
    //处方类型初始化
    var prescriptionType = $('input[name="rpa00104Form.prescriptionType"]:checked').val();
    if (prescriptionType == "0") {
        $('#prescriptionContent').hide();
        $('#prescriptionFilePreview').hide();
        $('#addPrescriptionImage').hide();
        $("input[name='rpa00104Form.prescriptionType'][value='1']").attr("disabled",true);
        $("input[name='rpa00104Form.prescriptionType'][value='2']").attr("disabled",true);
    }
    if (prescriptionType == "1") {
        $('#prescriptionContent').hide();
        $('#prescriptionFilePreview').show();
        $('#addPrescriptionImage').show();
        $("input[name='rpa00104Form.prescriptionType'][value='0']").attr("disabled",true);
        $("input[name='rpa00104Form.prescriptionType'][value='2']").attr("disabled",true);
    }
    if (prescriptionType == "2") {
        $('#prescriptionContent').show();
        $('#prescriptionFilePreview').hide();
        $('#addPrescriptionImage').hide();
        $("input[name='rpa00104Form.prescriptionType'][value='0']").attr("disabled",true);
        $("input[name='rpa00104Form.prescriptionType'][value='1']").attr("disabled",true);
    }
    
    // 読み込んだ画像データをsrcにセット
    if ($("#fileContents").val() == "" || $("#fileContents").val() == null) {
        $("#kaoPicture").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#kaoPicture").attr('src', $("#fileContents").val());
        rpa00150InitFileContents = $("#fileContents").val();
    }

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


//================================================
//放大图片
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doEnlargeImage() {
  if (rpa00150InitFileContents != null && rpa00150InitFileContents != '') {
      window.parent.openPage({style: "margin-left: auto;margin-right: auto;width:850px;height:1180px"
          , url: getContextPath("/rpa001/rpa00150Action!doInit.do?rpa00150Form.pageType=readonly")}
      , undefined
      , null);
  }
  return false;
}

