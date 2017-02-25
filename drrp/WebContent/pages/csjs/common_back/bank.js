//================================================
//  初期化時、データ的设定。
//  【入力】 なし
//  【返却】 なし
//  【作成】 
//  【更新】
//  【概要】
//================================================
$(document).ready(function() {
    // マウスを載せたセルを着色する機能を追加する
    //$('#tablecloth_row_bank').tableHover();
    
    });
//================================================
//金融機関コードより該当の金融機関名的取得
//【入力】_bankCodeId 金融機関コード
//【入力】_bankNameId 金融機関名
//【入力】_shopCodeId 金融機関本支店コード
//【入力】_shopNameId 金融機関本支店名
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function setBankName(_bankCodeId, _bankNameId, _shopCodeId, _shopNameId,_kozaFurikaeBaraiKubun) {

    var jsBankCd = $("[id='"+_bankCodeId+"']").val();
    if (jsBankCd != null && jsBankCd.length >0) {
        var params = {
                "bankForm.bankCode" : jsBankCd
        };
        // パラメタ設定
        $.ajax ({
            data : params,
            url : getContextPath('/commonJsonData/bankJson!doGetBankName.do'),
            success : function(result) {
                $("[id='"+_bankNameId+"']").val(result.bankForm.bankName);
                if ($("[id='"+_bankNameId+"']").val() == '') {
                    $("[id='"+_bankCodeId+"']").val('');
                }
                return;
            },
            error : function() {
                window.top.showAjaxConnectFailed();
                return;
            }
        });
        
    } else {
        $("[id='"+_bankNameId+"']").val('');
    }
    setShopName(_bankCodeId, _shopCodeId, _shopNameId,_kozaFurikaeBaraiKubun) ;
//        $("[id='"+_shopCodeId+"']").val('');
//        $("[id='"+_shopNameId+"']").val('');
}

//================================================
//金融機関コード、店舗コードより該当の店舗名的取得
//【入力】_bankCodeId 金融機関コード
//【入力】_shopCodeId 金融機関本支店コード
//【入力】_shopNameId 金融機関本支店名
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function setShopName(_bankCodeId, _shopCodeId, _shopNameId,_kozaFurikaeBaraiKubun) {

    var jsBankCd = $("[id='"+_bankCodeId+"']").val();
    var jsShopCd = $("[id='"+_shopCodeId+"']").val();

    if (jsBankCd == null || jsBankCd.length ==0) {

        $("[id='"+_shopCodeId+"']").val('');
        $("[id='"+_shopNameId+"']").val('');
        return;
    }
    if (jsShopCd != null && jsShopCd.length >0) {
        var params = {
                "bankForm.bankCode" : jsBankCd,
                "bankForm.shopCode" : jsShopCd,
                "bankForm.kozaFurikaeBaraiKubun" : _kozaFurikaeBaraiKubun
        };
        // パラメタ設定
        $.ajax ({
            data : params,
            url : getContextPath('/commonJsonData/bankJson!doGetShopName.do'),
            success : function(result) {
                $("[id='"+_shopNameId+"']").val(result.bankForm.shopName);
                if ($("[id='"+_shopNameId+"']").val() == '') {
                    $("[id='"+_shopCodeId+"']").val('');
                }
                return;
            },
            error : function() {
                window.top.showAjaxConnectFailed();
                return;
            }
        });
    } else {
        $("[id='"+_shopNameId+"']").val('');
    }
    doShowWaterMark();
}

//================================================
// 金融機関のオートコンプリート機能
//【入力】_bankCodeId 金融機関コード
//【入力】_bankNameId 金融機関名
//【返却】なし
//【作成】syugyokuhi
//【更新】
//【概要】
//================================================
function autocompleteBankName(_bankCodeId,_bankNameId){
    
    var _bankNameKanaId = _bankCodeId.replace("_code","_kanaName");
    $("[id='"+_bankNameId+"']").autocomplete({
        autoFocus: true,
        position: { my : "right top", at: "right bottom",collision: "fit" },
        source : function(request, response) {
            
            var params = {
                    "bankForm.bankName" : $("[id='"+_bankNameId+"']").val()
                };
            $.ajax({
                url : getContextPath('/commonJsonData/bankJson!doGetBankInfo.do'),
                cache : false,
                data : params,
                success : function(data) {
                    response(data.bankForm.subForm);
                }
            });
        },
        search : function(event, ui) {
                $("[id='"+_bankCodeId+"']").val("");
        },
        select : function(event, ui) {
            $("[id='"+_bankNameId+"']").val(ui.item.bankName);
            $("[id='"+_bankCodeId+"']").val(ui.item.bankCode);
            return false;
        },
        change : function() {
            if ($("[id='"+_bankNameId+"']").val().replace(/\s/g,'').length == 0) {
                $("[id='"+_bankNameId+"']").val("");
                $("[id='"+_bankCodeId+"']").val("");
            }
        }
    }).data("autocomplete")._renderItem = function(ul, item) {
        ul.removeClass("ui-bank-autocomplete");
        ul.addClass("ui-bank-autocomplete");
        return $("<li></li>").data("item.autocomplete", item).append("<a>" + item.bankName + "</a>").appendTo(ul);
    };
}

//================================================
// 店舗のオートコンプリート機能
//【入力】_bankCodeId 金融機関コード
//【入力】_shopCodeId 金融機関本支店コード
//【入力】_shopNameId 金融機関本支店名
//【返却】なし
//【作成】syugyokuhi
//【更新】
//【概要】
//================================================
function autocompleteShopName(_bankCodeId,_shopCodeId, _shopNameId,_kozaFurikaeBaraiKubun){

    var jsBankCd = $("[id='"+_bankCodeId+"']").val();
    if (jsBankCd == null || jsBankCd.length ==0) {
        return;
    }
    
    $("[id='"+_shopNameId+"']").autocomplete({
        autoFocus: true,
        position: { my : "right top", at: "right bottom",collision: "fit" },
        source : function(request, response) {
            
            var params = {
                    "bankForm.bankCode" : $("[id='"+_bankCodeId+"']").val(),
                    "bankForm.shopName" : $("[id='"+_shopNameId+"']").val(),
                    "bankForm.kozaFurikaeBaraiKubun" :_kozaFurikaeBaraiKubun
                };
            $.ajax({
                url : getContextPath('/commonJsonData/bankJson!doGetShopInfo.do'),
                cache : false,
                data : params,
                success : function(data) {
                    response(data.bankForm.subForm);
                }
            });
        },
        search : function(event, ui) {
                $("[id='"+_shopCodeId+"']").val("");
        },
        select : function(event, ui) {
            $("[id='"+_shopNameId+"']").val(ui.item.shopName);
            $("[id='"+_shopCodeId+"']").val(ui.item.shopCode);
            return false;
        },
        change : function() {
            if ($("[id='"+_shopNameId+"']").val().replace(/\s/g,'').length == 0) {
                $("[id='"+_shopNameId+"']").val("");
                $("[id='"+_shopCodeId+"']").val("");
            }
        }
    }).data("autocomplete")._renderItem = function(ul, item) {
        ul.removeClass("ui-shop-autocomplete");
        ul.addClass("ui-shop-autocomplete");
        return $("<li></li>").data("item.autocomplete", item).append("<a>" + item.shopName + "</a>").appendTo(ul);
    };
}

//================================================
//金融機関
//【入力】_bankCodeId 金融機関コード
//【入力】_bankNameId 金融機関名
//【入力】_shopCodeId 金融機関本支店コード
//【入力】_shopNameId 金融機関本支店名
//【返却】なし
//【作成】syugyokuhi
//【更新】
//【概要】
//================================================
function checkBankInfo(_bankCodeId,_bankNameId, _shopCodeId,_shopName){

    var params = {
            "bankForm.bankName" : $("[id='"+_bankNameId+"']").val()
        };
    $.ajax({
        url : getContextPath('/commonJsonData/bankJson!doGetBankInfo.do'),
        cache : false,
        data : params,
       success : function(data) {
    	   
                $("[id='"+_bankCodeId+"']").val(data.bankForm.bankCode);
                $("[id='"+_bankNameId+"']").val(data.bankForm.bankName);
       },
       error : function() {

            $("[id='"+_bankCodeId+"']").val('');
            $("[id='"+_bankNameId+"']").val('');
       }
    });

    var _bankNameKanaId = _bankCodeId.replace("_code","_kanaName");
    if ($("[id='"+_bankCodeId+"']").val() == '') {
        $("[id='"+_bankNameId+"']").val('');
    }
    if ($("[id='"+_bankNameId+"']").val() == '') {
        $("[id='"+_bankCodeId+"']").val('');
    }
    
    $("[id='"+_shopCodeId+"']").val('');
    $("[id='"+_shopName+"']").val('');
    doShowWaterMark();
}

//================================================
// 店舗情報チェック
//【入力】_bankCodeId 金融機関コード
//【入力】_shopCodeId 金融機関本支店コード
//【入力】_shopNameId 金融機関本支店名
//【返却】なし
//【作成】syugyokuhi
//【更新】
//【概要】
//================================================
function checkBankCode(_bankCodeId,_shopCodeId, _shopNameId){
    
    var _shopNameKanaId = _shopCodeId.replace("_code","_kanaName");
    
    var jsBankCd = $("[id='"+_bankCodeId+"']").val();
    if (jsBankCd == null || jsBankCd.length ==0) {
        $("[id='"+_shopCodeId+"']").val('');
        $("[id='"+_shopNameId+"']").val('');
    } else {

        var params = {
                "bankForm.bankCode" : $("[id='"+_bankCodeId+"']").val(),
                "bankForm.shopName" : $("[id='"+_shopNameId+"']").val()
            };
        $.ajax({
            url : getContextPath('/commonJsonData/bankJson!doGetShopCode.do'),
            cache : false,
            data : params,
           success : function(data) {

                    $("[id='"+_shopCodeId+"']").val(data.bankForm.shopCode);
           },
           error : function() {

                $("[id='"+_shopCodeId+"']").val('');
           }
        });
        
        if ($("[id='"+_shopCodeId+"']").val() == '') {
            $("[id='"+_shopNameId+"']").val('');
        }
        if ($("[id='"+_shopNameId+"']").val() == '') {
            $("[id='"+_shopCodeId+"']").val('');
        }
    }
    doShowWaterMark();
}

