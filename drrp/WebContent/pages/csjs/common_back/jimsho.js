// ================================================
// 市町選択処理
// 【入力】なし
// 【返却】なし
// 【作成】ztg
// 【更新】
// 【概要】
// ================================================
function doShichoChange(_danchiObject,_touFromObject,_touToObject) {
    var danchiObject = _danchiObject.split(",");
    for (var i = 0; i < danchiObject.length; i++) {
        $("#" + danchiObject[i] + "_code").val('');
        $("#" + danchiObject[i] + "_name").val('');
    }
    
    $("#" + _touFromObject + "_touCode").val('');
    $("#" + _touFromObject + "_touName").empty();
    
    $("#" + _touToObject + "_touCode").val('');
    $("#" + _touToObject + "_touName").empty();
}


var message = "";
var mustdata = "";
var url      = "";
//================================================
//市町リスト取得処理
//【入力】なし
//【返却】なし
//【作成】ztg
//【更新】
//【概要】
//================================================
function doGetShichoList(_jimushoObject,_shichoObject,_url) {

 if ($("[id='"+_jimushoObject+"']").length == 0 || $("[id='"+_shichoObject+"']").length == 0) {
     return;
 }
 var _shichoValue = $("[id='"+_shichoObject+"']").val();
 // パラメタ設定
 var params = {
         "jimushoForm.jimushoCd":$("[id='"+_jimushoObject+"']").val()
 };
 
 $.ajax ({
     data : params,
     url : "jimushoJson!doGetShichoInfo.do",
     success : function(result) {
         // 地区名リストをクリアする
         $("[id='"+_shichoObject+"']").empty();
         if(result.jimushoForm.errorMessage==null){
             if ($("[id='"+_shichoObject+"']")[0].className != 'mustdata') {
                 var $option = $('<option value=""></option>');
                 $("[id='"+_shichoObject+"']").append($option);
                 }
         // 地区名リストを作成する
             var shichoInfoList = result.jimushoForm.shichoInfoList;
             for (var data in shichoInfoList) {
                 var $option = $('<option value="' + shichoInfoList[data].recordCode + '">' + shichoInfoList[data].recordValue + '</option>');
                 $("[id='"+_shichoObject+"']").append($option);
             }
             if ($("[id='"+_shichoObject+"']")[0].className == 'mustdata' && _shichoValue != null && _shichoValue != '') {
                 $("[id='"+_shichoObject+"']").val(_shichoValue);
             } else if ($("[id='"+_shichoObject+"']")[0].className != 'mustdata'){
                 $("[id='"+_shichoObject+"']").val(_shichoValue);
             }
         }else{
             if ($("[id='"+_shichoObject+"']")[0].className == 'mustdata'||
                 result.jimushoForm.errorMessage.indexOf("E00033") >=0){
                 mustdata = $("[id='"+_shichoObject+"']")[0].className;
                 url = _url;
                window.top.showAjaxMessageCallback(result.jimushoForm.errorMessage,callbackfunc);
             }
         }
     },
     error : function() {
         window.top.showAjaxConnectFailed();
     }
 });
}

//================================================
//市町リスト取得処理（管轄権限の制限を除外する）
//【入力】なし
//【返却】なし
//【作成】ztg
//【更新】
//【概要】
//================================================
function doGetKengenNashiShichoList(_jimushoObject,_shichoObject,_url) {

if ($("[id='"+_jimushoObject+"']").length == 0 || $("[id='"+_shichoObject+"']").length == 0) {
   return;
}
var _shichoValue = $("[id='"+_shichoObject+"']").val();
// パラメタ設定
var params = {
       "jimushoForm.jimushoCd":$("[id='"+_jimushoObject+"']").val(),
       "jimushoForm.kengenNashiFlg":"1"
};

$.ajax ({
   data : params,
   url : "jimushoJson!doGetShichoInfo.do",
   success : function(result) {
       // 地区名リストをクリアする
       $("[id='"+_shichoObject+"']").empty();
       if(result.jimushoForm.errorMessage==null){
           if ($("[id='"+_shichoObject+"']")[0].className != 'mustdata') {
               var $option = $('<option value=""></option>');
               $("[id='"+_shichoObject+"']").append($option);
               }
       // 地区名リストを作成する
           var shichoInfoList = result.jimushoForm.shichoInfoList;
           for (var data in shichoInfoList) {
               var $option = $('<option value="' + shichoInfoList[data].recordCode + '">' + shichoInfoList[data].recordValue + '</option>');
               $("[id='"+_shichoObject+"']").append($option);
           }
           if ($("[id='"+_shichoObject+"']")[0].className == 'mustdata' && _shichoValue != null && _shichoValue != '') {
               $("[id='"+_shichoObject+"']").val(_shichoValue);
           } else if ($("[id='"+_shichoObject+"']")[0].className != 'mustdata'){
               $("[id='"+_shichoObject+"']").val(_shichoValue);
           }
       }else{
           if ($("[id='"+_shichoObject+"']")[0].className == 'mustdata'||
               result.jimushoForm.errorMessage.indexOf("E00033") >=0){
               mustdata = $("[id='"+_shichoObject+"']")[0].className;
               url = _url;
              window.top.showAjaxMessageCallback(result.jimushoForm.errorMessage,callbackfunc);
           }
       }
   },
   error : function() {
       window.top.showAjaxConnectFailed();
   }
});
}

function callbackfunc(){
if (url != undefined && url != null && url != "") {
         window.location.href = url;
    }
}