<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@tag import="cn.com.prescription.framework.util.ReflectUtils"%>
<%@tag import="cn.com.prescription.framework.CodeValueRecord"%>
<%@tag import="java.util.List"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="form" type="java.lang.String" required="true" %>
<%@attribute name="dateValue" type="java.lang.String" required="false" %>
<%@attribute name="mustFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="calendarBlur" type="java.lang.String" required="false" %>
<%

if(mustFlg == null) {
    mustFlg = Boolean.FALSE;
}
request.setAttribute("dateId",id +"_date");

request.setAttribute("dateValue",form + "." + dateValue);

request.setAttribute("calendarBlurMethod",calendarBlur);
%>

<span group="<%= id %>" >
<% if (mustFlg) {%>
        <s:textfield id="%{#request.dateId}" name="%{#request.dateValue}"  style="width:120px;ime-mode:disabled;" cssErrorClass="error"  />
        <input type="hidden" id="<%= id %>_hidden" datepicker="datepicker" value="" onblur='<%=calendarBlur %>'/>
<%} else if (!mustFlg) {%>
    	<h:readonlyfield id='<%=id +"_date" %>' name='<%=form + "." + dateValue %>' style="width:120px;" ></h:readonlyfield>
        <input type="hidden" id="<%= id %>_hidden" datepicker="datepicker" value="" onblur='<%=calendarBlur %>'/>
<%}%>
</span>

