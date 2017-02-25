<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="form" type="java.lang.String" required="true" %>
<%@attribute name="tabDivId" type="java.lang.String" required="false" %>
<%@attribute name="tabDivName" type="java.lang.String" required="false" %>
<%@attribute name="tabWidth" type="java.lang.String" required="false" %>

<%
    String[] tabsDivId = tabDivId.split(",");
    String[] tabsDivName = tabDivName.split(",");
    if(CheckUtils.isEmpty(tabWidth)){
        tabWidth = "";
    } else {
        tabWidth = "width:" + tabWidth + ";";
    }

    request.setAttribute("idTabsIndexName",form + ".idTabsIndex");
%>
<table id="<%=id %>" class="idTabs">
    <tr>
        <td style="border: 2px solid #aaaaaa;">
              <ul>
                  <%for (int i = 0; i < tabsDivId.length; i++) {%>
                        <li style="<%=tabWidth %>border-right: 2px solid #aaaaaa"><a href="#<%=tabsDivId[i] %>" onclick="setIdTabsIndex('idTabsIndexName','<%=i %>');"><b><%=tabsDivName[i] %></b></a></li>
                  <%}%>
              </ul>
              <s:hidden id="idTabsIndexName" name="%{#request.idTabsIndexName}" />
        </td>
    </tr>
</table>
