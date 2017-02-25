
var LODOP; //声明为全局变量 

//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {
    
    $("#barcodeTarget").barcode($('#hiddenPrintUserId').val(), "code39",{barWidth:1, barHeight:30});

    // 読み込んだ画像データをsrcにセット
    if ($("#barcodeFilePath").val() == "" || $("#barcodeFilePath").val() == null) {
        $("#barPicture").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#barPicture").attr('src', "../.." + $("#barcodeFilePath").val());
    }
    if ($("#qrcodeFilePath").val() == "" || $("#qrcodeFilePath").val() == null) {
        $("#qrPicture").attr('src', "../.." + nonPictureUrl);
    } else {
        $("#qrPicture").attr('src', $("#qrcodeFilePath").val());
    }
});
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
function doPrint(object) {  
    if (CreateOneFormPage()) {
        LODOP.PREVIEW();
    }
}

function CreateOneFormPage(){
    LODOP=getLodop();  
    if (!LODOP) {
        return false;
    }
//    LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
//    LODOP.SET_PRINT_STYLE("FontSize",18);
//    LODOP.SET_PRINT_STYLE("Bold",1);
//    LODOP.ADD_PRINT_TEXT(50,231,260,39,"打印页面部分内容");
//    LODOP.ADD_PRINT_HTM(88,200,350,600,document.documentElement.innerHTML);
    LODOP.ADD_PRINT_HTM(30,30,"100%","100%",document.getElementById("contents").innerHTML);
    return true;
};  


