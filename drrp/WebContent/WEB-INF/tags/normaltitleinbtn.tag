<%@tag import="cn.com.prescription.framework.message.MessageUtils"%>
<%@tag pageEncoding="UTF-8" %>
<%@attribute name="value" type="java.lang.String" required="true" %>

<div id="<%=value %>_title" >
    <h2 class="normal_title_inbtn">
        <span class="title_area"><b><%=MessageUtils.getSimpleMessage("gamen_name_" + value) %></b></span>
    </h2>
</div>
