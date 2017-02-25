<%--
/*
 * 用户登录页面
 *
 * DATE: 2016/03/16 
 */
--%>
<%@page import="cn.com.prescription.framework.message.MessageUtils"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/pages/jsp/common/taglibs.jsp" %>
<!DOCTYPE html><html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title><%=MessageUtils.getSimpleMessage("title.system")%></title>
  <script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.12.4.min.js"></script>
<%--   <script type="text/javascript" src="${ctx}/pages/csjs/common/pubjyutaku_keycode.js"></script> --%>
  <script type="text/javascript" src="${ctx}/pages/csjs/common/common_cookie.js"></script>
  <script type="text/javascript" src="${ctx}/pages/csjs/rpm/rpm00101.js"></script>
  <link type="text/css" href="${ctx}/pages/css/rpm/rpm00101.css" rel="stylesheet" ></link>
</head>

<body id="login">
<s:form action="loginAction" name="rpm00101Form" namespace="/rpm" method="post" validate="true">
    <div id="login_body">
        <div class="login_area">
        <table class="login_table">
            <tr>
                <td colspan="3" ><div class="login_info"><s:fielderror escape="false"/><s:actionerror escape="false"/><s:actionmessage escape="false"/></div></td>
            </tr>
            <tr>
                <td style="text-align:right;">用户ID</td>
                <td><h:mustentryicon/></td>
                <td><s:textfield id="loginUserId" name="rpm00101Form.userId" cssErrorClass="error" size="28" maxlength="50" cssClass="mustdata" cssErrorStyle="ime-mode:disabled;" cssStyle="ime-mode:disabled;"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">密码</td>
                <td><h:mustentryicon/></td>
                <td><s:password id="loginUserPwd" name="rpm00101Form.password" cssErrorClass="error" size="28" maxlength="50" showPassword="true" cssClass="mustdata"/></td>
            </tr>
            <tr>
                <td style="text-align:right;">分店</td>
                <td></td>
                <td><s:select name="rpm00101Form.storeCode" list="rpm00101Form.storeCodeDataSource" listKey="recordCode" listValue="recordValue" cssStyle="width:195px;" cssErrorClass="error" /></td>
            </tr>
        </table>
        </div>
        
        <div style="margin-top: 10px;">
        <table align="center">
            <tr>
                 <td><s:hidden id="flg" name="rpm00101Form.flg"/>
                     <input id="loginBtn" type="image" name="method:doLogin" alt="登录" src="${ctx}/pages/img/login_btn_bg.gif" onmousemove="this.src='${ctx}/pages/img/login_btn_bg_ov.gif'" onmouseout="this.src='${ctx}/pages/img/login_btn_bg.gif'" value="登录"  onclick="return login();"/>
                 </td>
            </tr>
        </table>
        </div>
    </div>
</s:form> 
</body>
</html>