<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@attribute name="name" type="java.lang.String" required="true" %>
<%@attribute name="value" type="java.lang.Boolean" required="false" %>
<%
request.setAttribute("fieldName",name);
%>
<%if(value){%>
    <img src="<%=request.getContextPath()%>/pages/img/checkbox_readonly.jpg" />
<%}else{%>
    <img src="<%=request.getContextPath()%>/pages/img/checkbox_readonly_off.jpg" />
<%} %>
<s:hidden name="%{#request.fieldName}"></s:hidden>