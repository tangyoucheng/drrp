<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@attribute name="id" type="java.lang.String" required="false" %>
<%@attribute name="value" type="java.lang.String" required="false" %>
<%@attribute name="style" type="java.lang.String" required="false" %>

<%

request.setAttribute("disabledFieldData",request.getAttribute(value));

%>

<input id ="<%=id  %>_disabledTag" value='<s:property value="#request.disabledFieldData"/>' type="text" readonly="readonly"  style="<%=style  %>" class="readonlydata">