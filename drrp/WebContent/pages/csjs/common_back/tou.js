
//================================================
//棟名の設定
//【入力】_touCodeId　　棟コード
//【入力】_touNameId　　棟名
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function setTouName(_touCodeId,_touNameId){
    var touName = "";
    $("[id='"+_touNameId+"'] option").each(function(){
           if($(this).val() == $("[id='"+_touCodeId+"']").val()){
               touName = $("[id='"+_touCodeId+"']").val();
           }
          });
    $("[id='"+_touNameId+"']").val(touName);
    if (touName == ""){
        $("[id='"+_touCodeId+"']").val("");
    }
}

//================================================
//棟IDの設定
//【入力】_touNameId　　棟名
//【入力】_touCodeId　　棟コード
//【返却】なし
//【作成】
//【更新】
//【概要】
//================================================
function setTouId(_touNameId,_touCodeId){
    if  ($("[id='"+_touNameId+"']").val() != null){
        $("[id='"+_touCodeId+"']").val( $("[id='"+_touNameId+"']").val());
    }else{
        $("[id='"+_touCodeId+"']").val( "");
    }
    
}
//================================================
//棟リスト取得処理
//【入力】_danchiCodeId 団地コード
//【入力】_touCodeId　　棟コード
//【入力】doAfter　　　取得した後の処理
//【返却】なし
//【作成】ztg
//【更新】
//【概要】
//================================================
function doGetTouList(_danchiCodeId,_touNameId,_touCodeId,doAfter) {


if ($("[id='"+_danchiCodeId+"']").length == 0) {
   return;
}
var _touValue = $("[id='"+_touNameId+"']").val();
    if  ((_touValue == ""||_touValue == null) & _touCodeId != undefined){
        _touValue= $("[id='"+_touCodeId+"']").val();
    }
// パラメタ設定
var params = {
       "touForm.danchiCd":$("[id='"+_danchiCodeId+"']").val()
};
$.ajax ({
   data : params,
   url : "touJson!doGetTouInfo.do",
   success : function(result) {
       // 地区名リストをクリアする
       $("[id='"+_touNameId+"']").empty();
       if(result.touForm.errorMessage==null){
           if ($("[id='"+_touNameId+"']")[0].className.indexOf('mustdata')<0) {
               var $option = $('<option value=""></option>');
               $("[id='"+_touNameId+"']").append($option);
               }
       // 地区名リストを作成する
           var touInfoList = result.touForm.touInfoList;
           for (var data in touInfoList) {
               var $option = $('<option value="' + touInfoList[data].recordCode + '">' + touInfoList[data].recordValue + '</option>');
               $("[id='"+_touNameId+"']").append($option);
           }
           if ($("[id='"+_touNameId+"']")[0].className.indexOf('mustdata')>=0 && _touValue != null && _touValue != '') {
               $("[id='"+_touNameId+"']").val(_touValue);
                   if (doAfter != undefined) {
                       eval(doAfter);
                   }
           } else if ($("[id='"+_touNameId+"']")[0].className.indexOf('mustdata')<0){
               $("[id='"+_touNameId+"']").val(_touValue);
               if (doAfter != undefined) {
                   eval(doAfter);
               }
           }
       }else{
           window.top.showAjaxMessage(result.touForm.errorMessage);
       }
   },
   error : function() {
       window.top.showAjaxConnectFailed();
   }
});
}

