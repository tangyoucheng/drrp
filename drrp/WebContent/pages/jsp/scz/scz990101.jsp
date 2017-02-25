<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%--
/*
 * 门户
 *
 *
 * 新規作成
 * DATE: 2016/03/09 NAME: tyc
 */
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="cn.com.prescription.framework.message.MessageUtils"%>
<%@page import="cn.com.prescription.leshan.common.LeshanConstantsIF"%>
<%@page import="cn.com.prescription.framework.util.CheckUtils"%>
<%@page import="cn.com.prescription.framework.util.ServiceUtils"%>
<%@include file="/pages/jsp/common/taglibs.jsp" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
  <link rel="stylesheet" type="text/css" href="${ctx}/pages/css/public_common.css"></link>
    <script type="text/javascript">
        function getContextPath(path) {
            return "${ctx}" + (path ? path : "");
        }
    </script>
    <title></title>
</head>
<!-- <body bgcolor="#FFFFFF" onload="readCookie();"> -->
<body bgcolor="#FFFFFF" >
<s:form name="scz990101Form" action="scz990101Action" namespace="/scz" method="post" theme="simple">
<%-- ヘッダー --%>
<div id="wrap_top" >
    <div id="header" style="height:91px;width: auto;">
<!--         <span class="header_inner"> -->
    
                <div  id="sitemname" style="text-align: left;">
                    <%=MessageUtils.getSimpleMessage("title.system")%>
                </div>
                <div id="username">
                    <g:property value="scz990101Form.userName"/>
                </div>
                
                <!-- メイン菜单 -->
                <div class="idVerticalTabs scrollable vertical" >
                    <ul id="mainMenuCtrl" class="items">
                    
                        <s:if test="scz990101Form.subForm.size <= 1" >
                            <table class="item">
                                <tr>
                                    <td style="height: 30px;">&nbsp;</td>
                                </tr>
                            </table>
                        </s:if>
                        
                        <s:iterator status="mainMenuData" value="scz990101Form.subForm" >
                    
                                <table class="item">
                                    <tr>
                                        <s:iterator status="rowMenuData" value="top" >
                                            <td>
                                                <a id="menuTop_${mainMenuData.index}_${rowMenuData.index}" href="#_${mainMenuData.index}_${rowMenuData.index}" onclick="return selectMenu('${menuId}',this)"><g:property value="menuName"/></a>
                                            </td>
                                        </s:iterator>
                                    </tr>
                                </table>

                        </s:iterator>
                        
                      </ul>
                </div>
                
<!--                 <div id="actions"> -->
<!--                     <a class="prevPage" title="上一页" onclick="doPrev(this)" href="#up"></a> -->
<!--                     <a class="nextPage" title="下一页" onclick="doNext(this)" style="margin-top: 24px;" href="#down"></a> -->
<!--                 </div> -->
    
                
                <div class="gnavi_area" style="width: auto">
                    <table>
                        <tr>
                            <td>
                                <a href="javascript:void(0);" id="logoutId" title="注销"  onclick="return loginOut();"><img src="${ctx}/pages/img/logout.png" width="50px" alt=""/></a>
                        </tr>
                    </table>
                </div>
<!--         </span> -->
    </div>
</div>
<s:hidden name="hdnMenuId" value="Z"/>
<s:hidden name="fontSize" value="14"/>
<s:hidden id="scz990101HidUserId" name="scz990101Form.userId" />
<s:hidden name="scz990101Form.cookieFlg" />
</s:form>
<s:form action="scz990102Action" namespace="/scz" method="post" target="left" theme="simple">
  <s:hidden name="scz990102Form.parentMenuId" value=""/>
</s:form>
<%-- <script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.4.2.js"></script> --%>
<script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${ctx}/pages/csjs/common/jquery.idTabs.js"></script>
<script type="text/javascript" src="${ctx}/pages/csjs/common/tools.scrollable-1.1.2.js"></script>
<script type="text/javascript" src="${ctx}/pages/csjs/scz/scz990101.js"></script>
<script type="text/javascript" src="${ctx}/pages/csjs/common/pubjyutaku_keycode.js"></script>
</body>
</html>
