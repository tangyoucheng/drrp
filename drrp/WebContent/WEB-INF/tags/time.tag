<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="form" type="java.lang.String" required="true" %>
<%@attribute name="timeValue" type="java.lang.String" required="false" %>
<%@attribute name="mustFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="calendarBlur" type="java.lang.String" required="false" %>

<%
if(mustFlg == null) {
    mustFlg = Boolean.FALSE;
}
request.setAttribute("timeId",id +"_time");

request.setAttribute("timeValue",form + "." + timeValue);

request.setAttribute("calendarBlurMethod",calendarBlur);

%>

<span group="<%= id %>" >

<% if (mustFlg) {%>
    <s:textfield id="%{#request.timeId}" name="%{#request.timeValue}"  style="width:70px;ime-mode:disabled;" cssErrorClass="error"  />
    <input type="hidden" id="<%= id %>_hidden" timepicker="timepicker" value="" />
<%} else if (!mustFlg) {%>
    <h:readonlyfield id='<%=id +"_time" %>' name='<%=form + "." + timeValue %>' style="width:70px;" ></h:readonlyfield>
    <input type="hidden" id="<%= id %>_hidden" timepicker="timepicker" value="" />
<%}%>
    
</span>
