<%@tag import="cn.com.prescription.framework.util.NumberUtils"%>
<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@tag import="cn.com.prescription.framework.util.CheckUtils"%>
<%@tag import="cn.com.prescription.framework.util.NumberUtils"%>
<%@attribute name="id" type="java.lang.String" required="true" %>
<%@attribute name="form" type="java.lang.String" required="true" %>
<%@attribute name="tabDiv" type="java.lang.String" required="false" %>
<%@attribute name="tabDivName" type="java.lang.String" required="false" %>
<%@attribute name="showTabIndex" type="java.lang.String" required="false" %>
<%@attribute name="tabWidth" type="java.lang.String" required="false" %>

<%
    String[] tabsDivName = tabDivName.split(",");
    if(CheckUtils.isEmpty(tabWidth)){
        tabWidth = "width:120px;";
    } else {
        tabWidth = "width:" + tabWidth + ";";
    }
    int selectedTabIndex = NumberUtils.toInt(showTabIndex);

    request.setAttribute("idTabsIndexName",form + ".idTabsIndex");

%>
<table id="<%=id %>" style="margin-top:10px;margin-bottom:10px; width: 100%;background:#D1F1FF;" >
    <tr>
        <td style="border: 2px solid #aaaaaa;">
              <ul style="background:#CEE2F5; text-align: center;">
                  <%for (int i = 0; i < tabsDivName.length; i++) {%>
                      <%if(selectedTabIndex == i){%>
                            <li style="<%=tabWidth %>list-style:none;float:left;border-right: 2px solid #aaaaaa;background:#B7D7EB; color:#000000; padding:5px 13px;" ><b><%=tabsDivName[i] %></b></li>
                      <%} else {%>
                            <li style="<%=tabWidth %>list-style:none;float:left;border-right: 2px solid #aaaaaa;background:#FFFFFF; color:#000000; padding:5px 13px;"><b><%=tabsDivName[i] %></b></li>
                      <%} %>
                  <%}%>
              </ul>
              <s:hidden id="labelTabsIndexName" name="%{#request.idTabsIndexName}" />
        </td>
    </tr>
</table>
<script type="text/javascript">
    $(document).ready(function(){
        
        var tabsDiv = '<%=tabDiv%>'.split(",");
        var selectedTabIndex = parseInt('<%=showTabIndex%>');
        for ( var i = 0; i < tabsDiv.length; i++) {
            if (selectedTabIndex == i) {
                $("." + tabsDiv[i]).show();
            } else {
                $("." + tabsDiv[i]).hide();
    
            }
        }
    });
</script>
