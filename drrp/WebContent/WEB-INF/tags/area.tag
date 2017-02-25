<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@tag import="cn.com.prescription.framework.util.StringUtils"%>
<%@attribute name="areaId" type="java.lang.String" required="true" %>
<%@attribute name="areaCode" type="java.lang.String" required="false" %>
<%@attribute name="areaName" type="java.lang.String" required="false" %>
<%@attribute name="onchange" type="java.lang.String" required="false" %>
<%@attribute name="mustFlg" type="java.lang.Boolean" required="false" %>

<%

if(mustFlg == null) {
    mustFlg = Boolean.FALSE;
}
onchange = StringUtils.defaultString(onchange);

request.setAttribute("areaId",areaId);
request.setAttribute("areahiddenId",areaId + "_hidden");
request.setAttribute("areaName",areaName);
request.setAttribute("onchangeMethod",onchange);

%>
<input id='<%=areaId + "_hidden" %>' type="hidden" value="<%=areaCode %>">
<%if (mustFlg) {%>

        <s:select id="%{#request.areaId}" name="%{#request.areaName}" list="#PubjyutakuInfoUtils.areaTemplateList" listKey="recordCode" listValue="recordValue" cssErrorClass="error" onchange="eval(%{#request.onchangeMethod});" cssClass="mustdata" style="width:135px;" ></s:select>
    
<%} else {%>

        <s:select id="%{#request.areaId}" name="%{#request.areaName}" list="#PubjyutakuInfoUtils.areaTemplateList" listKey="recordCode" listValue="recordValue" cssErrorClass="error" onchange="eval(%{#request.onchangeMethod});" style="width:135px;" ></s:select>

<%}%>
