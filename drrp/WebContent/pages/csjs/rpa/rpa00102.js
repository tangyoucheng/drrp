// 10秒刷新一次页面
var rpa00102RefreshPage = setInterval(function(){doRefreshPage();},30000);

//================================================
// 初期化の場合、画面情報を保存する
//【入力】なし
//【返却】なし
//【作成】syousei
//【更新】
//【概要】
//================================================
$(document).ready(function() {
    
    $('input[type=hidden][id*=prescriptionStatus]').each(function () {
        if (($('#hiddenPageType').val() =='rpa00104' && $(this).val() == '1') 
                    || ($('#hiddenPageType').val() =='rpa00105' && $(this).val() == '3')) {
            if ($(window.top.document).find('[id*=dlg]').length == 0) {
                window.top.closeAllDlg();
                window.top.showAjaxMessageCallback('有未完成的待处理处方。',runRefreshPageInterval());
                return false;
            }
        }
    });
    
});

//================================================
//处方手写录入回调函数
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function runRefreshPageInterval() {
    rpa00102RefreshPage = setInterval(function(){doRefreshPage();},30000);
}

//================================================
//处方手写录入回调函数
//【 入力 】 なし
//【返却値】 なし
//【作成者】 fsb
//【作成日】 2012/11/29
//【更新者】 fsb
//【更新日】 2012/11/29
//【 概要 】
//================================================
function doRefreshPage() {
    var url = getContextPath("/rpa/rpa00102Action!doSearch.do");
    $("#rpa00102Form").action = url;
    $("#rpa00102Form").submit();
}
