<!DOCTYPE html >
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.com.prescription.framework.common.component.dialog.DialogAction"%>
<%@ page import="cn.com.prescription.framework.biz.ActionInfo"%>
<%@ page import="cn.com.prescription.framework.biz.DbExclusiveChainInfo"%>
<%@ page import="cn.com.prescription.framework.biz.DbExclusiveChainListInfo"%>
<%@ include file="/pages/jsp/common/taglibs.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="content-style-type" content="text/css" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/public_default.css" />
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.4.2.js"></script> --%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_keycode.js"></script>
	<script type="text/javascript">
	    $(document).ready(function(){
	    	 init()
	    });
		<%-- Action指定をセッションから取得 --%>
		var okAction = '<s:property value="#session.OK_ACTION.url" escapeHtml="false"/>';
		var cancelAction = '<s:property value="#session.CANCEL_ACTION.url"/>';
		var nonAction = '<%=ActionInfo.NON_ACTION.getUrl()%>';
		<%-- DB排他後処理 --%>
		var fieldID = '<s:property value="#session.DbExclusiveChainInfo.fieldID" />';
		var okVal = '<s:property value="#session.DbExclusiveChainInfo.chainRecordDate" />';
		var cancelVal = '<s:property value="#session.DbExclusiveChainInfo.cancelRecordDate" />';
		<%-- セッションクリア --%>
		<% session.removeAttribute(DialogAction.OK_ACTION_SESSION_KEY); %>
		<% session.removeAttribute(DialogAction.CANCEL_ACTION_SESSION_KEY); %>
		<% session.removeAttribute(DbExclusiveChainInfo.SESSION_KEY); %>
		//================================================
		// ダイアログオープン時処理
		//================================================
		function init() {
			try {
			<s:if test="form.isConfirm">
				$("#OkCancel").show();
				$("#NO").focus();
			</s:if>
			<s:elseif test="!form.isPleaseWait">
				// ボタン表示エリアを表示
				if (cancelAction != '') {
					$("#OkCancel").show();
					$("#NO").focus();
				} else {
					$("#OkOnly").show();
					$("#OK").focus();
				}
			</s:elseif>
			}
			catch(e) {
			}

			// メッセージエリアに4行以上表示する場合
			if ($(".dialogMessage").size() > 0) {
				if ($(".dialogMessage").html().replace(/<br\s*\/?\s*>/gi, "\n").split("\n").length >4) {
					$(".dialogMessage").css("height", "150px");
					$(".dialogMessage").css("width", "380px");
					$(".dialogMessage").css("overflow", "auto");
				}
			}

		}
	</script>
	<style type="text/css">
	   * {
	       margin: 0;
	       padding: 0;
        }
        html, body {
            height: 100%;
            width: 100%;
        }
        img {
            margin-left: 25px;
            margin-right: 20px;
            vertical-align: middle;
        }
        .layoutTable_ {
            margin: 0;
            padding: 0;
        }
        .titleRow_ {
            background: #B4E6ED;
            color: black;
            font-weight: 700;
            height: 22px;
        }
	        .titleRow_ .layoutTable_ {
	            height: 22px;
	        }
        .message_spot, .messageArea_ {
            margin: 0;
            padding: 0;
            text-align: left;
            height: 180px;
        }
	        .messageArea_ .layoutTable_ {
	            height: 180px;
	        }
        .guideButtonRow_ {
            background: #B7D7EB;
            height: 60px;
        }
	        .guideButtonRow_ .layoutTable_ {
	            height: 60px;
	        }
		div.dialogMessage {
		}
	</style>
</head>
<body >
	<s:form method="post" theme="simple" validate="false">
		<%-- 标题 --%>
		<div class="titleRow_">
			<table class="layoutTable_"><tr><td style="text-align: center;width: 570px;">
				<span>
					<s:if test="form.isExclamation">警告信息</s:if>
					<s:if test="form.isPleaseWait">処理中</s:if>
					<s:if test="form.isInfo">信息内容</s:if>
					<s:if test="form.isAjaxConnectFailed">通信错误</s:if>
					<s:if test="form.isComplete">処理完了</s:if>
					<s:if test="form.isConfirm">确认信息</s:if>
				</span>
			</td></tr></table>
		</div>

		<%--信息区域 --%>
		<div class="message_spot"><div class="messageArea_">
			<%-- 应用程序错误 --%>
			<s:if test="form.isExclamation">
				<table class="layoutTable_">
				<tr><td align="center">
				  <table><tr>
				      <td><img src="<%=request.getContextPath()%>/pages/img/exclametion.gif" /></td>
				      <td style="padding-right:20px;text-align: left;word-break:break-all;">
					      <div class="dialogMessage"><s:property value="form.message" escapeHtml="false" /></div>
                      </td></tr>
                  </table>
				</td></tr>
				</table>
			</s:if>
			<%-- 処理中プログレス --%>
			<s:if test="form.isPleaseWait">
				<div style="text-align:center">
					<br/><br/><br/><br/>
					<span>处理中。请稍等。</span><br/>
					<img src="<%=request.getContextPath()%>/pages/img/loadingAnimation.gif"/>
				</div>
			</s:if>
			<%-- 処理完了メッセージ --%>
			<s:if test="form.isComplete">
				<table class="layoutTable_"><tr><td>
					<img src="<%=request.getContextPath()%>/pages/img/info.gif" />
				</td><td style="padding-right:20px;word-break:break-all;">
					<div class="dialogMessage"><s:property value="form.message" escapeHtml="false" /></div>
				</td></tr></table>
			</s:if>
			<%-- 信息内容 --%>
			<s:if test="form.isInfo || form.isInfoId">
				<table class="layoutTable_"><tr><td>
					<img src="<%=request.getContextPath()%>/pages/img/info.gif" />
				</td><td style="padding-right:20px;word-break:break-all;">
					<div class="dialogMessage"><s:property value="form.message" escapeHtml="false" /></div>
				</td></tr></table>
			</s:if>
			<%-- Ajax通信错误 --%>
			<s:if test="form.isAjaxConnectFailed">
				<table class="layoutTable_"><tr><td>
					<img src="<%=request.getContextPath()%>/pages/img/exclametion.gif" />
				</td><td style="padding-right:20px;word-break:break-all;">
					<div>和服务器通信失败。</div>
				</td></tr></table>
			</s:if>
			<%-- 确认信息 --%>
			<s:if test="form.isConfirm">
				<table class="layoutTable_"><tr><td>
					<img src="<%=request.getContextPath()%>/pages/img/info.gif" />
				</td><td style="padding-right:20px;word-break:break-all;">
					<div class="dialogMessage"><s:property value="form.message" escapeHtml="false" /></div>
				</td></tr></table>
			</s:if>
		</div></div>

		<%-- 按钮操作区域 --%>
		<div class="guideButtonRow_">
			<div id="OkOnly" style="display:none;">
				<table class="layoutTable_"><tr><td style="text-align: center;width: 570px;">
					<input type="button" id="OK" value="OK" onclick="window.top.clickYes(okAction, nonAction, fieldID, okVal);" style="width:100px;" />
				</td></tr></table>
			</div>
			<div id="OkCancel" style="display:none;">
			    <table class="layoutTable_"><tr><td style="text-align: right;;width: 275px;">
                    <input type="button" id="YES" value="OK" onclick="window.top.clickYes(okAction, nonAction, fieldID, okVal);" style="width:100px;" />
                </td><td style="width: 20px;">&nbsp;</td><td style="text-align: left;;width: 275px;">
                    <input type="button" id="NO" value="取消" onclick="window.top.clickNo(cancelAction, nonAction, fieldID, cancelVal);" style="width:100px;" />
                </td></tr></table>
			</div>
		</div>
	</s:form>
</body>
</html>