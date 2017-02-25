<%@tag pageEncoding="UTF-8"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="g" uri="prescriptionTagLib"%>
<%@tag import="cn.com.prescription.leshan.common.LeshanConstantsIF"%>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%@attribute name="sortSeq" type="java.lang.String" required="false" %>
<%@attribute name="sortSeqValue" type="java.lang.String" required="false" %>
<%@attribute name="style" type="java.lang.String" required="false" %>

<%

request.setAttribute("sortSeqHiddenId",sortSeq +"_sortSeqHidden");
request.setAttribute("sortSeqId",sortSeq);
request.setAttribute("sortSeqAsc",LeshanConstantsIF.SORT_SEQ_ASC);
request.setAttribute("sortSeqDesc",LeshanConstantsIF.SORT_SEQ_DESC);

%>


<table id="<%= sortSeq +"_headTag" %>" style="width: 100%;">
    <s:hidden id="%{#request.sortSeqHiddenId}" name="%{#request.sortSeqId}"/>
    <%if(CheckUtils.isEmpty(sortSeqValue)){%>
	    <tr style="background-color: #B8B8B8;cursor: pointer;" onclick="return clickTrSortSeq('<%=sortSeq +"_sortSeqHidden" %>','<%=LeshanConstantsIF.SORT_SEQ_ASC %>',this);"> 
    <%} else if(!CheckUtils.isEmpty(sortSeqValue) && CheckUtils.isEqual(LeshanConstantsIF.SORT_SEQ_ASC ,sortSeqValue)){%>
	    <tr style="background-color: #B8B8B8;cursor: pointer;" onclick="return clickTrSortSeq('<%=sortSeq +"_sortSeqHidden" %>','<%=LeshanConstantsIF.SORT_SEQ_DESC %>',this);"> 
    <%} else if(!CheckUtils.isEmpty(sortSeqValue) && CheckUtils.isEqual(LeshanConstantsIF.SORT_SEQ_DESC ,sortSeqValue)){%>
	    <tr style="background-color: #B8B8B8;cursor: pointer;" onclick="return clickTrSortSeq('<%=sortSeq +"_sortSeqHidden" %>','',this);"> 
        
    <%} %>
	        <td style="text-align:center;<%= style %>">
	            <%= title %>
				<g:submit method="doSort" cssStyle="display:none;" onclick="return false;"></g:submit>
	        </td> 
	        <td style="padding-left:1px;padding-right:1px; vertical-align: middle;" >
	            <%if(CheckUtils.isEmpty(sortSeqValue)){%>
	                <a id="<%= sortSeq +"_A_Sort_Tag" %>" class="headerBtnSortBtn" title="設定なし" href="#" ></a>
	                    
	            <%} else if(!CheckUtils.isEmpty(sortSeqValue) && CheckUtils.isEqual(LeshanConstantsIF.SORT_SEQ_ASC ,sortSeqValue)){%>
	                <a id="<%= sortSeq +"_A_Sort_Tag" %>" class="headerSortBtn_up" title="昇順" href="#" ></a>
	                    
	            <%} else if(!CheckUtils.isEmpty(sortSeqValue) && CheckUtils.isEqual(LeshanConstantsIF.SORT_SEQ_DESC ,sortSeqValue)){%>
	                <a id="<%= sortSeq +"_A_Sort_Tag" %>" class="headerSortBtn_down" title="降順" href="#" ></a>
	                
	            <%} %>
	        </td>
    	</tr>
</table>
    
