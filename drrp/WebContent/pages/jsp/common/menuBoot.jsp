<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pages/jsp/common/taglibs.jsp" %>
<html>
<head>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.4.2.min.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_keycode.js"></script>
</head>

<body onload="javascript:location.replace('<s:property value="form.url" />');">
    <img style="margin-top: 10px;margin-left: 260px;" src="<%=request.getContextPath()%>/pages/img/loadingAnimation.gif" />
</body>
</html>

