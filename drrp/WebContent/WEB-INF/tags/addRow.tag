<%@tag import="java.sql.Timestamp"%>
<%@tag import="cn.com.prescription.framework.util.TimestampUtils"%>
<%@tag import="cn.com.prescription.framework.util.DateUtils"%>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@tag import="cn.com.prescription.framework.StandardConstantsIF"%>
<%@tag import="cn.com.prescription.framework.common.interceptor.ValidationInterceptor"%>
<%@tag import="java.util.List"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@attribute name="tableId" type="java.lang.String" required="true"%>
<%@attribute name="id" type="java.lang.String" required="false"%>
<%@attribute name="tagTitle" type="java.lang.String" required="false"%>
<%@attribute name="rowSizeName" type="java.lang.String" required="false"%>
<%@attribute name="subForm" type="java.lang.String" required="false"%>
<%@attribute name="msgId" type="java.lang.String" required="false"%>
<%@attribute name="msgPrams" type="java.lang.String" required="false"%>
<%@attribute name="maxRowLength" type="java.lang.Integer" required="false"%>
<%@attribute name="cellResteMaxNoFun" type="java.lang.String" required="false"%>
<%@attribute name="cellStartBake" type="java.lang.String" required="false"%>
<%@attribute name="cellReplaceHtml" type="java.lang.String" required="false"%>
<%@attribute name="cellEndBake" type="java.lang.String" required="false"%>
<%@attribute name="xaRow" type="java.lang.String" required="false"%>
<%@attribute name="yaRow" type="java.lang.String" required="false"%>
<%@attribute name="resetLineColor" type="java.lang.String" required="false"%>
<%@attribute name="resetNo" type="java.lang.String" required="false"%>
<%@attribute name="messageShow" type="java.lang.String" required="false"%>
<%
	// tag id
	String tag_id = id;
	// maxNo name
	String maxNoName = "tagAddRowMaxNo";
	if (CheckUtils.isEmpty(tag_id)) {
		tag_id = "addRow_" + tableId
				+ DateUtils.format(TimestampUtils.getSysTimestamp(),"yyyyMMddHHmmssSSS")
				+ "_" + Math.random();
		tag_id = tag_id.replaceAll("\\.", "");
		Integer index = (Integer) request
				.getAttribute("tag_addRow_maxNo_name_index");
		if (index == null) {
			index = new Integer(0);
		} else {
			index = new Integer(index + 1);
		}
		request.setAttribute("tag_addRow_maxNo_name_index", index);
		maxNoName = maxNoName + index.intValue();
	} else {
		maxNoName = maxNoName + tag_id;
	}

	if (CheckUtils.isEmpty(tagTitle)) {
		tagTitle = "行追加";
	}

	if (CheckUtils.isEmpty(subForm)) {
		subForm = "subForm1";
	}

	if (CheckUtils.isEmpty(msgId)) {
		msgId = "E00020";
	}
	if (CheckUtils.isEmpty(xaRow)) {
		xaRow = "0";
	}
	if (CheckUtils.isEmpty(yaRow)) {
		yaRow = "0";
	}
	String jsMsg = msgId;
	if (CheckUtils.isEmpty(msgPrams)) {
		msgPrams = "一覧";
	}
	if (CheckUtils.isEmpty(cellResteMaxNoFun)) {
		cellResteMaxNoFun = "false";
	}
	if (CheckUtils.isEmpty(resetNo)) {
		resetNo = "false";
	}
	jsMsg = jsMsg + "," + msgPrams;
	// 明細件数上限値
	if (maxRowLength == null) {
		maxRowLength = StandardConstantsIF.KYOTU_HENKO_KENSUU_JOGENCHI
				.intValue();
	}
	jsMsg = jsMsg + "," + maxRowLength;

	final String KEY_FG = "tag_row_filter_subformNULL_key_fg";
	Boolean isfg = (Boolean) request.getAttribute(KEY_FG);
	if (isfg == null) {
		isfg = true;
		request.setAttribute(KEY_FG, false);
	}
	String maxNo = "0";
	if (request.getParameter(maxNoName) != null) {
		maxNo = request.getParameter(maxNoName);
	}
%>
<div id ="<%=tag_id%>" >
<a id="button"  href="javascript:void(0);" class="aid_btn" ><span><%=tagTitle%></span></a>

<input id="maxNo" type="hidden" name="<%=maxNoName%>" value="<%=maxNo%>"/>
<%
	if (isfg) {
%>
<input id="tag_row_filter_subformNULL_key" type="hidden" name="<%=ValidationInterceptor.TAG_DELROW_KEY%>" value=""/>
<%
	}
%>

</div>
<SCRIPT language="JavaScript">
/**
 * 行追加buttion
 */
$(document).ready(
        function(){
            var addRowBut = $("#<%=tag_id%>");
            var buttonObj = addRowBut.find("#button");
            var maxNoObj = addRowBut.find("#maxNo");
            if("false" != "<%=cellResteMaxNoFun%>"){
                var retCheckFg =<%=cellResteMaxNoFun%>();
                if(retCheckFg){
                    maxNoObj.val("0");
                }
            }
            var maxNo = maxNoObj.val();
            if(maxNo == "null" || maxNo == "0" ||maxNo == "NaN"){
                var tableObj = $("#<%=tableId%>");
                var modeTR = tableObj.find("tr:eq(0)");
                var noTd = modeTR.find("td:eq(0)");
                var rowspan = <%=yaRow%>+1+<%=xaRow%> ; 
                var trVisLength = tableObj.find("tr:visible").length;
                var newMaxNo = 0;
                tableObj.find("tr:visible").each(function(i){
                    if(i>= (trVisLength-<%=yaRow%>-1-<%=xaRow%>)){
                        if ($(this).find("#no").val() != undefined){
                            newMaxNo = $(this).find("#no").val();
                        }
                    }
                });
                //var newMaxNo = trVisLength/rowspan;
                maxNoObj.val(newMaxNo);
            }            
            
            buttonObj.click(function (){
            	<%if (!CheckUtils.isEmpty(messageShow)) {%>
            	 var tableObj = $("#<%=tableId%>");
            	 var rowspan =  <%=yaRow%>+1+<%=xaRow%> ;
                 var trVisLength = tableObj.find("tr:visible").length;
                 // not no
                 var newVisIndexNotNo = trVisLength/rowspan+1;
                 
             //  明細件数上限値
                 var maxRowLength = <%=maxRowLength%>;
                 var disRowCount = newVisIndexNotNo;
                 
                 if(disRowCount>maxRowLength){
                         // エーラメッセージとする
                         window.top.showAjaxMessageById("<%=jsMsg%>");
                         return ;
                     }else{
            	     window.top.showAjaxConfirm(null, 'Q00015,第'+ newVisIndexNotNo+'行追加', false,
            	              function(ok){
            	                  if(ok ==false){
            	                      return;
            	                  }
            	              return addRowThis();
            	   });
                     }
            	 <%}%>
            	<%if (CheckUtils.isEmpty(messageShow)) {%> 
            	return addRowThis();
            	<%}%>
                });
            
            function addRowThis()
            {
                var tableObj = $("#<%=tableId%>");
                var modeTR = tableObj.find("tr:eq(0)");
                var noTd = modeTR.find("td:eq(0)");
                var rowspan =  <%=yaRow%>+1+<%=xaRow%> ;
                var trLength = tableObj.find("tr").length;
                var trVisLength = tableObj.find("tr:visible").length;
                // not no
                var newVisIndexNotNo = trVisLength/rowspan+1;
                var newVisIndex = parseInt(maxNoObj.val())+1;
                
                //var newIndex = (trLength/rowspan);
                
                var trVisLength = tableObj.find("tr:visible").length;
                var newMaxNo = 0;
                tableObj.find("tr:visible").each(function(i){
                    if(i>= (trVisLength-<%=yaRow%>-1-<%=xaRow%>)){
                        if ($(this).find("#no").val() != undefined){
                            newMaxNo = $(this).find("#no").val();
                        }
                    }
                });
                var newIndex = 0;
                var trLength = tableObj.find("tr").length;
                tableObj.find("tr").each(function(i){
                	isBeing = false;
                    if(i>= (trLength-<%=yaRow%>-1-<%=xaRow%>)){
                        
                        $(this).find(":input").each(function(i){
                        // name
                            if(!isBeing){
                                var name = $(this).attr("name");
                                var reg = RegExp("([\\S]*)(.<%=subForm%>)(\\[)(\\d{1,})(\\].)","g");
                                var regName = reg.exec(name);
                                 if(regName != null){
                                   newIndex = regName[4];
                                   isBeing = true;
                                  }
                             }
                         });
                     }
                });
                
                newIndex = parseInt(newIndex)+1;
                //  明細件数上限値
                var maxRowLength = <%=maxRowLength%>;
                var disRowCount = newVisIndexNotNo;
                
                    if(disRowCount>maxRowLength){
                        // エーラメッセージとする
                        window.top.showAjaxMessageById("<%=jsMsg%>");
                        return ;
                    }
                    
                <%if (!CheckUtils.isEmpty(cellStartBake)) {%>
                     var checkFlag = <%=cellStartBake%>(0);
                     if(checkFlag==false){
                         return;
                     }
                   <%}%>
                // クローンを作成
                var bakTRObj = tableObj.find("tr:lt("+rowspan+")").each(function(i){
                     var keyVal = $("#tag_row_filter_subformNULL_key").val();
                     var isBeing = false;
                    
                     $(this).find(":input").each(function(i){
                            // name
                            if(!isBeing){
                                var name = $(this).attr("name");
                                var reg = RegExp("([\\S]*)(.<%=subForm%>)(\\[)(\\d{1,})(\\].)","g");
                                
                                var regName = reg.exec(name);
                                if(regName != null){
                                    var subFromName = regName[1]+regName[2];
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
                    var bakTRObj = $(this).clone(true);
                    bakTRObj.find("#no").val(newVisIndex);
                    bakTRObj.find("#validDataIndex").val(newVisIndexNotNo);
                    bakTRObj.find("#notValidate").val(false);
                   
                    if(i == 0){
                        //bakTRObj.find("td:eq(0)").html(newIndex);
                        bakTRObj.find("td:eq(0)").html(newVisIndex);
                    }
                    var strHtml = bakTRObj.html().replace(/(.<%=subForm%>\[)(\d{1,})+(\].)/g,
                            function($0, $1, $2, $3) {
                        return $1 + newIndex + $3;
                    });
                    <%if (!CheckUtils.isEmpty(cellReplaceHtml)) {%>
                    strHtml = <%=cellReplaceHtml%>(strHtml,newIndex);
                       <%}%>
                    var bakHtmlTR = "<tr addRow=true>";
                    //var bakHtmlTR = "<tr class=odd addRow=true>";
                    //if(newVisIndex%2 ==0){
                    //    var bakHtmlTR = "<tr class=even  addRow=true>";
                    //}
                    bakHtmlTR = bakHtmlTR+strHtml+"</tr>";
                    tableObj.append(bakHtmlTR);
                });
                 $("#<%=rowSizeName%>").val(newVisIndex);
                 maxNoObj.val(newVisIndex);
                <%if (!CheckUtils.isEmpty(cellEndBake)) {%>
                 <%=cellEndBake%>(newIndex);
                 <%}%>
                 $("#abc").val(tableObj.html());

                <%if ("true" == resetNo) {%>
                    resetNo(tableObj,rowspan);
                    <%}%>
                 <%if ("true" == resetLineColor) {%>
                    resetLineColor(tableObj,rowspan);
                 <%}%>
                 doAfterAddRow(tableObj,rowspan);
                 doLoadWaterMark();
                return;
            }
        }
);
</SCRIPT>
