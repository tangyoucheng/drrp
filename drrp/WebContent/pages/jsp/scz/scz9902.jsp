<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
/*
 * Copyright(c) 2011 
 */
/*
 * 画面ロック機能
 *
 * @author t.d.m
 * @since 1.0
 *
 * 新規作成
 * DATE: 2010/05/26 NAME: t.d.m
 */
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/pages/jsp/common/taglibs.jsp" %>
<%@page import="cn.com.prescription.framework.message.MessageUtils"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <link rel="stylesheet" type="text/css" href="${ctx}/pages/css/public_default.css" />
    <title><s:text name="title.system"/></title>
</head>
<body>
<s:form action="scz9902Action" namespace="/scz" method="post" theme="simple" validate="true">
<div id="dialogue_s" style="margin-top: 10px;">
    <h2 class="normal_title" style="text-align: left;height: 18px;padding-left: 10px;">ロック解除</h2>
    <div class="message_spot">
        一定時間操作されなかったため、画面がロックされました。<br/>復帰するためには密码を入力し、再登录してください。
    </div>
    <br/>
    <br/>
    <div class="dialogue_inner_inner">
        <table class="entry_dialogue_table">
            <tr>
                <td style="text-align:right;padding:10px 0px;">
                    用户名
                </td>
                <td></td>
                <td style="text-align:left;padding:10px 0px;">
                    <s:textfield name="scz9902Form.userName" size="33" cssClass="readonlydata" readonly="true"></s:textfield>
                </td>
            </tr>
            <tr>
                <td class="entry_name_titlelong" style="text-align:right;padding-top: 5px;padding-bottom: 5px;">
                    密码
                </td>
                <td style="width: 35px;padding:5px 4px;"><h:mustentryicon/></td>
                <td style="text-align:left;">
                    <s:password id="password" name="scz9902Form.password" size="35" maxlength="100" cssClass="mustdata"></s:password>
                </td>
            </tr>
        </table>
    </div>
    <br/>
    <br/>
    <div class="guide_btn_area">
        <table class="guide_btn_table">
            <tr>
                <td><a href="javascript:void(0);" class="login_subbtn" id="login"></a></td>
                <td><a href="javascript:void(0);" class="head_logout_subbtn" id="logout"></a></td>
            </tr>
        </table>
    </div>
</div><!-- dialogue_s -->
</s:form>
<%-- <script src="${ctx}/pages/csjs/lib/jquery-1.4.2.js" type="text/javascript" ></script> --%>
<script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.12.4.min.js"></script>
<script type="text/javascript">

// デバッグ
isDebug = false;

$(document).ready(function(){
    
    // --------------------------------------------------
    // 右クリック菜单禁止
    // --------------------------------------------------
    $(document).bind("contextmenu",function(e){
        return isDebug;
    });
    
    $(document).keydown(function(e) {
        // backspace
        var obj = e.target || e.srcElement;
        var jsType = obj.type || obj.getAttribute('type');
        var vReadOnly = obj.getAttribute('readonly');
        var vEnabled = obj.getAttribute('enabled');
        vReadOnly = (vReadOnly == null) ? false : vReadOnly;
        vEnabled = (vEnabled == null) ? true : vEnabled;
        if (e.keyCode == 8) {
            if (jsType=="password" || jsType=="text" || jsType=="textarea") {
                if (vReadOnly==true || vEnabled!=true) {
                    return isDebug;
                } else {
                    return true;
                }
            } else {
                return isDebug;
            }
        }
        
        // F5 または Ctrl + R
        if((e.keyCode==116) || (e.ctrlKey && e.keyCode==82)) {
            e.originalEvent.keyCode = 0;
            return isDebug;
        }
        // Enter
        if (e.keyCode == 13) {
            $('form').each(function() {
                e.preventDefault();
            });
        }
        // Alt + ← または Alt + →
        if (e.altKey) {
            if (e.keyCode == 37 || e.keyCode == 39) {
                return isDebug;
            }
        }
        // OK
        return true;
    });
    
    $("#password").focus();
    $('input:last').keydown(function(e){
        if(e.keyCode==9) {$('input:first').focus(); return false;}
    });
    if (window.top.right != undefined) {
        // 業務系画面
        $("#logout").click(function(){window.top.location.replace('${ctx}' +'/loginAction.do');});
    } else {
        // 基盤系画面
        //$("#logout").click(function(){window.top.location.href = '/koumu/systemManager/scz040102Action!doLoginOut.do';});
    }
    $("#login").click(function(){
        var param = {
            "scz9902Form.password" : $("#password").val()
        };
        $.ajax({
            url: "scz9902Json!doConfirm.do",
            type: "post",
            dataType: "json",
            data: param,
            success: function(result){
                if (result.scz9902Form.message != "") {
                    // 業務错误時の処理               
                    window.top.showAjaxMessage(result.scz9902Form.message);
                } else {
                    var data = new Object();
                    data.resultFlg = true;
                    window.parent.PubjyutakuLogin.setResult(data);
                    window.parent.PubjyutakuLogin.hide();
                }
            },
            error: function(result){
                alert($("#sessionTimeoutMessage").val());
                $("#logout").click();
            }
        });
    });

//  $("#logout").click(function(){
//      var data = new Object();
//      data.resultFlg = false;
//      window.parent.PubjyutakuLogin.setResult(data);
//      window.parent.PubjyutakuLogin.hide();
//  });
});
</script>
<textarea style="display:none" id="sessionTimeoutMessage"><%= MessageUtils.getSimpleMessage("E00003") %></textarea>
</body>
</html>
