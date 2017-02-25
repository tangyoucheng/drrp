// ================================================
// click delete buttion
// 【入力】btnObj,delFgName,validateFgName
// 【返却】なし
// 【作成】t.c
// 【更新】
// 【概要】
// ================================================
function _clickDelRow (btnObj,delFgName,noName,subForm,cellStartBake,cellReplaceHtml,cellEndBake,deletePrev,deleteAfte,xRow,yRow,parentLv,_resetLineColor,_resetNo){

    if(deletePrev(btnObj)){
        return;
    }
    window.top.showAjaxConfirm(null, 'Q00004,operation_delete', false,
            function(ok){
                if(ok ==false){
                    return;
                }
                 var tableObj = $(btnObj).parent().parent().parent();
                 var trObj = $(btnObj).parent().parent();
                 var tdObj = $(btnObj).parent();
                 for (i=0;i<parentLv;i++)
                 {
                     tableObj = tableObj.parent();
                     trObj= trObj.parent();
                     tdObj =tdObj.parent();
                 }
                 var rowspan =tdObj.attr("rowspan");
                 var newVisIndex = 0;
                 newVisIndex = trObj.find("td:eq(0)").html();
                 var notValidateObj = trObj.find("#notValidate");
                 if(notValidateObj.is("input")){
                     var notValidate = notValidateObj.val();
                 }else{
                     alert("notValidate, not find!");
                     return;
                 }
                 var delFgObj = trObj.find("#"+delFgName);
                 if(delFgObj.is("input")){
                      delFgObj.val(true);
                 }else{
                     alert(delFgName+", not find!");
                     return;
                 }
                 var noObj = trObj.find("#"+noName);
                 if(!noObj.is("input")){
                     alert(noName+", not find!");
                     return;
                 }
                 var isAddRow = false;
                 if(notValidate=="false"){
                    isAddRow = true;
                 }
                 
                 trObj.attr("style","display:none;");
                 
                 var rowIndexNo = 0;
                 var keyVal = $("#tag_row_filter_subformNULL_key").val();
                 var isBeing = false;
                 
                 trObj.find(":input").each(function(i){
                        // name
                        if(!isBeing){
                            var name = $(this).attr("name");
                            var reg = RegExp("([\\S]*)(."+subForm+")(\\[)(\\d{1,})(\\].)","g");
                           
                            var regName = reg.exec(name);
                           
                            if(regName != null){
                                var subFromName = regName[1]+regName[2];
                                rowIndexNo = regName[4];
                                if(keyVal == null || keyVal ==""){
                                    keyVal=subFromName;
                                }else if(keyVal.indexOf(subFromName)<0){
                                    keyVal += ","+subFromName;
                                }
                                 $("#tag_row_filter_subformNULL_key").val(keyVal);
                                 
                                isBeing = true;
                            }
                        }
                 });
                 trObj.prevAll("tr:visible").each(function(i){
                     if((i)<xRow){
                         if(!isAddRow){
                             $(this).attr("style","display:none;");
                         }else{
                             $(this).remove(); 
                         }
                     }
                 });
                 cellStartBake(rowIndexNo);
                 trObj.nextAll("tr:visible").each(function(i){
                     if((i)<yRow){
                         cellStartBake(rowIndexNo);
                         if(!isAddRow){
                             $(this).attr("style","display:none;");
                         }else{
                             $(this).remove(); 
                         }
                     }else {
                         var noObj1 = $(this).find("#"+noName);
                         if(noObj1.val() != null && rowIndexNo == 0){
                             rowIndexNo = noObj1.val();
                         }
                            
                         if(isAddRow){
                          // cellStartBake(rowIndexNo); //TODO 2013/01/14 tyc
                             isBeing = false;
                            $(this).find(":input").each(function(i){
                                // name
                                if(!isBeing){
                                    var name = $(this).attr("name");
                                    var reg = RegExp("([\\S]*)(."+subForm+")(\\[)(\\d{1,})(\\].)","g");
                                    var regName = reg.exec(name);
// alert(rowIndexNo);
                                  if(regName != null){
                                    rowIndexNo = regName[4];
                                  }
                                  isBeing = true;
                                }
                            });

                             cellEndBake(rowIndexNo);
                         }else{ 
                         }
                     }
                     
                 });
                 if(!isAddRow){
                     // bakTrObj.attr("style","display:none;");
                     // tableObj.append(bakTrObj);
                 }else{
                     trObj.remove();
                     
                 }
                 

                 var disTrSize=tableObj.find("tr:visible").length;
                 var rowLength = disTrSize/(parseInt(xRow)+1+parseInt(yRow));
                 if (_resetNo== "true" ){
                    resetNo(tableObj,parseInt(xRow)+1+parseInt(yRow));
                 }
                 if (_resetLineColor == "true")
                 {
                     resetLineColor(tableObj,parseInt(xRow)+1+parseInt(yRow));
                 };
              
    });
    if(deleteAfte(btnObj)){
        return;
    }

}

function _cellStartBake(){
    
}
function _cellReplaceHtml(bakHtmlTR,index){
    return bakHtmlTR;
}
function _cellEndBake(){
    
}
function _cellDelPrev(delBtuObj){
    return false;
}
function _cellDelAfte(delBtuObj){
    return false;
}



