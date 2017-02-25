<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pages/jsp/common/taglibs.jsp" %>
<html>
<head>
<%-- <script src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.4.2.js" type="text/javascript" ></script> --%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">

		$(document).ready(function() {

			// 移動
			$("#next").submit();

		});

	</script>
	<title>Session Timeout</title>
</head>
<body>
<form id="next" name="next" target="_top" method="POST" action="<%=request.getContextPath()%>/pages/jsp/common/session_timeout_next.jsp">
	<input type="hidden" id="admin" name="admin" />
</form>
</body>
</html>
