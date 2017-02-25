<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="cn.com.prescription.leshan.common.LeshanConstantsIF"%>
<%@page import="cn.com.prescription.framework.util.CheckUtils"%>
<%@page import="cn.com.prescription.framework.util.ServiceUtils"%>
<%@include file="/pages/jsp/common/taglibs.jsp"%>
<%--
/*
 * Copyright(c) 2011 
 */
/*
 * 门户画面
 *
 * @author tyc
 * @since 1.0
 *
 * 新規作成
 * DATE: 2010/05/02 NAME: gl
 */
--%>
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
</head>
<body>
<s:form action="scz990102Action" name="scz990102Form" namespace="/scz" method="post" theme="simple">
<%-- 菜单枠 --%>
<div class="menuLarge" id="menu">
    <%-- 菜单 --%>
    <div class="menuLargeInner">
        <%-- 菜单タイトル --%>
        <s:property value="scz990102Form.title"/>
        
        <s:iterator value="scz990102Form.menuMiddleList" status="menuMiddleStatus">
            
            <s:if test='menuType == "1"'>
                <a <s:if test="#menuMiddleStatus.first">id="menuMiddleInitId"</s:if> href="javascript:void(0);" class="menuMiddle"><s:property value="menuName"/></a>
                <span>
                <s:iterator value="menuLinkList" status="menuLinkStatus">
                    <a <s:if test="#menuMiddleStatus.first && #menuLinkStatus.first">id="menuLinkInitId"</s:if> href="javascript:void(0);" class="menuLink"
                        onclick="goNextPage(this,'<s:property value="url"/>','<s:property value="menuId"/>');"> 
                        <s:property value="menuName" escapeHtml="false" />
                    </a>
                </s:iterator>
                </span>
            </s:if>
            <s:elseif  test='menuType == "0"'>
                <a <s:if test="#menuMiddleStatus.first">id="menuLinkInitId"</s:if> href="javascript:void(0);" class="menuLink" style="margin-left: 0px;"
                    onclick="goNextPage(this,'<s:property value="url"/>','<s:property value="menuId"/>');"> 
                    <s:property value="menuName" escapeHtml="false" />
                </a>
            </s:elseif>
        </s:iterator>
    </div>
</div>
<input id="leftMenuDblClickFlag" type="hidden">
<s:hidden name="scz990102Form.title" />
<s:hidden id="parentMenuId" name="scz990102Form.parentMenuId" />
</s:form>
<form name="submitForm" method="post" target="right">
  <input type="hidden" name="menuId" value=""/>
  <input type="hidden" name="url" value=""/>
</form>
<%-- <script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.4.2.js"></script> --%>
<script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${ctx}/pages/csjs/scz/scz990102.js"></script>
<script type="text/javascript" src="${ctx}/pages/csjs/common/pubjyutaku_keycode.js"></script>
</body>
</html>
