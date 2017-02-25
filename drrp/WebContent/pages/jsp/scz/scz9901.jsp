<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="cn.com.prescription.framework.message.MessageUtils"%>
<%@page import="cn.com.prescription.leshan.common.LeshanConstantsIF"%>
<%@include file="/pages/jsp/common/taglibs.jsp" %>

<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <title><%=MessageUtils.getSimpleMessage("title.system")%></title>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.12.4.js"></script>
    <!-- Bootstrap-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/bootstrap.dialog.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/dialog.js"></script>

    <!-- 基本スタイルシート -->
    <script type="text/javascript" src="${ctx}/pages/csjs/common/dialog.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/pages/css/public_default.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/scz/scz9901.css" />
    <!-- スクリプトファイル -->
    <script type="text/javascript">
        var maxTime = <%=LeshanConstantsIF.KYOTU_GAMEN_LOCK%>;
        
        function getContextPath(path) {
            return "<%= request.getContextPath() %>" + (path ? path : "");
        }
    </script>
    <script type="text/javascript" src="${ctx}/pages/csjs/scz/scz9901.js"></script>
    <script type="text/javascript" src="${ctx}/pages/csjs/common/pubjyutaku_keycode.js"></script>
</head>
<body bgcolor="#F7F7F7" onload="reinitIframe()">

<input type="hidden" id="scz9901AdUrl" name="scz9901Form.adUrl"  value="${scz9901Form.adUrl }">

<!-- Modal -->
<div class="modal fade in" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog  modal-lg" role="document" style="">
        <div class="modal-content" >
            <div class="modal-header" style="display: none;height: 30px;padding-top: 0px;padding-right: 5px;">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="line-height:0.7;filter:alpha(opacity=100);color:red;opacity:0.9"><span aria-hidden="true"><font size="24px;">&times;</font></span></button>
            </div>
            <div class="modal-body scroll-wrapper">
                <iframe id="iframe" name="popupFrame" frameborder="0" width="100%" src="" height="98%" scrolling="auto"></iframe> 
            </div>
        </div>
    </div>
</div>

<table id="frameLayoutTable" border="0" cellpadding="0" cellspacing="0" style="border: solid 1px #b2aaba;margin: 5px auto 0px auto;background-color: #FFFFFF;">
    <tr>
      <td colspan="2">
        <iframe id="head" name="head" src="<s:url action="scz990101Action" namespace="/scz" method="doInit"/>" scrolling="no" frameborder="0" width="100%" height="98px"></iframe> 
      </td>
    </tr>
    <tr>
      <td valign="top">                                   
        <iframe id="left" name="left" src="" scrolling="no" frameborder="0" width="205px" height="580px"></iframe>  
      </td>
      <td>
        <iframe id="right" name="right" src=""  frameborder="0" width="785px" scrolling="auto"  height="580px" style="overflow-y: active;"></iframe> 
      </td>
   </tr>
</table>



</body>
</html>