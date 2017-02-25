var rpa00150InitFileContents;
var LODOP; //声明为全局变量 

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
    var prescriptionType = $('input[name="rpa00105Form.prescriptionType"]:checked').val();
    if (prescriptionType == "0") {
        $('#prescriptionContent').hide();
        $('#prescriptionFilePreview').hide();
        $('#addPrescriptionImage').hide();
        $("input[name='rpa00105Form.prescriptionType'][value='1']").attr("disabled",true);
        $("input[name='rpa00105Form.prescriptionType'][value='2']").attr("disabled",true);
    }
    if (prescriptionType == "1") {
        $('#prescriptionContent').hide();
        $('#prescriptionFilePreview').show();
        $('#addPrescriptionImage').show();
        $("input[name='rpa00105Form.prescriptionType'][value='0']").attr("disabled",true);
        $("input[name='rpa00105Form.prescriptionType'][value='2']").attr("disabled",true);
    }
    if (prescriptionType == "2") {
        $('#prescriptionContent').show();
        $('#prescriptionFilePreview').hide();
        $('#addPrescriptionImage').hide();
        $("input[name='rpa00105Form.prescriptionType'][value='0']").attr("disabled",true);
        $("input[name='rpa00105Form.prescriptionType'][value='1']").attr("disabled",true);
    }
    
    // 読み込んだ画像データをsrcにセット
    if ($("#fileContents").val() == "" || $("#fileContents").val() == null) {
        $("#kaoPicture").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#kaoPicture").attr('src', $("#fileContents").val());
        rpa00150InitFileContents = $("#fileContents").val();
    }
    
    // 第一次审核成功后弹出自动印刷对话框
    if ($('#firstReviewFlag').val() == "true") {
        window.top.showAjaxConfirm(null, 'Q00001,operation_print', null, doPrint);
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
//登録処理を行う
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doPrint() {
    if (CreateOneFormPage()) {
        LODOP.PREVIEW();
    } 
}

function CreateOneFormPage(){
    LODOP=getLodop();  
    if (!LODOP) {
        return false;
    }
// LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
// LODOP.SET_PRINT_STYLE("FontSize",18);
// LODOP.SET_PRINT_STYLE("Bold",1);
// LODOP.ADD_PRINT_TEXT(50,231,260,39,"打印页面部分内容");
// LODOP.ADD_PRINT_HTM(88,200,350,600,document.documentElement.innerHTML);
// LODOP.ADD_PRINT_HTM(88,"5%","90%",600,document.getElementById("contents").innerHTML);
    var strBodyStyle="<style>#rpa00105_title {display: none;}</style>"
    var strFormHtml=strBodyStyle+"<body>";
    strFormHtml=strFormHtml+"<table style='width:785px;'><tr>";
    strFormHtml=strFormHtml+"<td style='text-align:center;font-size:28px;'>处&nbsp;&nbsp;&nbsp;&nbsp;方&nbsp;&nbsp;&nbsp;&nbsp;笺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
    strFormHtml=strFormHtml+"</tr><tr>";
    strFormHtml=strFormHtml+"<td style='text-align:right;padding-right:80px;'>处方单编号："+$('#hiddenPrescriptionNumber').val()+"</td>";
    strFormHtml=strFormHtml+"</tr></table>";
    strFormHtml=strFormHtml+document.getElementById("contents").innerHTML;
    strFormHtml=strFormHtml+"</body>";
    LODOP.ADD_PRINT_HTM(30,30,"100%","100%",strFormHtml);
    return true;
};  



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

