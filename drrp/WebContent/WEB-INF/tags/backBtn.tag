<%@tag pageEncoding="UTF-8"%>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@tag import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="prescriptionTagLib"%>
<%@attribute name="id" type="java.lang.String" required="false"%>
<%@attribute name="currentForm" type="java.lang.String" required="true"%>
<%@attribute name="backUrl" type="java.lang.String" required="true"%>
<%@attribute name="cssStyle" type="java.lang.String" required="false"%>

<%
    request.setAttribute("serializeInfo",currentForm + ".initSerialForm");
    request.setAttribute("serializeFlg",currentForm + ".backFlag");
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/serialize.js"></script>
<g:submit id="backBtn" onclick="return doBack();" cssClass="back_mainbtn" title="戻る"  cssStyle="${cssStyle}"/>

<input id="backUrl" type="hidden" name="<%=currentForm + ".backURL" %>" value='<%=backUrl%>'/>
<s:hidden id="serializeInfo" name="%{#request.serializeInfo}"></s:hidden>
<s:hidden id="serializeFlg" name="%{#request.serializeFlg}"></s:hidden>
<SCRIPT language="JavaScript">
    function doBack() {
        window.top.right.location.href = getContextPath($("#backUrl").val());
        return false;
    }
</SCRIPT>
