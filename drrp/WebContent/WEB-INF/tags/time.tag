<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="form" type="java.lang.String" required="true" %>
<%@attribute name="hour" type="java.lang.String" required="false" %>
<%@attribute name="minute" type="java.lang.String" required="false" %>
<%@attribute name="mustFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="readonlyFlg" type="java.lang.Boolean" required="false" %>
<%@attribute name="disabledFlg" type="java.lang.Boolean" required="false" %>

<%
if(disabledFlg == null) {
    disabledFlg = Boolean.FALSE;
}
if(mustFlg == null) {
    mustFlg = Boolean.FALSE;
}
if(readonlyFlg == null) {
    readonlyFlg = Boolean.FALSE;
}

%>

<span group="<%= id %>" >

<%if (disabledFlg) {%>
    <h:disabledfield id='<%=id +"_hour" %>' value='<%=form + "." + hour %>' style="width:20px;text-align:right;"></h:disabledfield>
    時
    <h:disabledfield id='<%=id +"_minute" %>' value='<%=form + "." + minute %>' style="width:20px;text-align:right;"></h:disabledfield>
    分
    
<%} else if (readonlyFlg) {%>
    <h:readonlyfield id='<%=id +"_hour" %>' name='<%=form + "." + hour %>' style="width:20px;text-align:right;"></h:readonlyfield>
    時
    <h:readonlyfield id='<%=id +"_minute" %>' name='<%=form + "." + minute %>' style="width:20px;text-align:right;"></h:readonlyfield>
    分
    
<%} else if (mustFlg) {%>
    <h:numberField id='<%=id +"_hour" %>' name='<%=form + "." + hour %>' width="20px" integerSize="2" mustFlg="true"/>
    時
    <h:numberField id='<%=id +"_minute" %>' name='<%=form + "." + minute %>' width="20px" integerSize="2" mustFlg="true"/>
    分
    
<%--     <input type="hidden" id="<%= id %>_hidden" timepicker="timepicker" value="" /> --%>

<%} else if (!disabledFlg && !readonlyFlg && !mustFlg) {%>
    <h:numberField id='<%=id +"_hour" %>' name='<%=form + "." + hour %>' width="20px" integerSize="2" />
    時
    <h:numberField id='<%=id +"_minute" %>' name='<%=form + "." + minute %>' width="20px" integerSize="2"/>
    分
        
<%--     <input type="hidden" id="<%= id %>_hidden" timepicker="timepicker" value="" /> --%>

<%}%>
    
</span>
