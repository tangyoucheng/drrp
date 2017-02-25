<%@tag pageEncoding="UTF-8" %>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@attribute name="id" type="java.lang.String" required="false" %>
<%@attribute name="name" type="java.lang.String" required="false" %>
<%@attribute name="style" type="java.lang.String" required="false" %>
<%@attribute name="fieldCssClass" type="java.lang.String" required="false" %>

<%

if(CheckUtils.isEmpty(fieldCssClass)){
	fieldCssClass = "readonlydata";
};
request.setAttribute("fieldId",id);
request.setAttribute("fieldName",name);
request.setAttribute("fieldStyle",style);
request.setAttribute("fieldCssClass",fieldCssClass);

%>

<s:textfield id="%{#request.fieldId}" name="%{#request.fieldName}" cssErrorStyle="width:173px;%{#request.fieldStyle}"  cssStyle="width:173px;%{#request.fieldStyle}" cssClass="%{#request.fieldCssClass}" readonly="true" cssErrorClass="error"></s:textfield>
