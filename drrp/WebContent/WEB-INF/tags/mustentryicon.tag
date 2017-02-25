<%@tag pageEncoding="UTF-8" %>
<%@attribute name="style" type="java.lang.String" required="false" %>
<%if (style==null){style = "";}%>
<img src="<%=request.getContextPath()%>/pages/img/icon_mustentry.gif" alt="必须" style="<%=style%>" class="icon_mustentry"/>