<% response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); %>
<!DOCTYPE HTML >
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pages/jsp/common/taglibs.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/pages/css/public_default.css" />
<%-- 	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.4.2.js"></script> --%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_keycode.js"></script>
	<style type="text/css">
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
            margin-left: auto;
            margin-right: auto;
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
	</style>
	<script type="text/javascript">


	    window.history.forward(1); 
	    
		$(document).ready(function() {
			$("#moveLoginUrl").click(function(){
                window.top.location.href = "<%=request.getContextPath()%>/login";
			});
		});

	</script>
	<title></title>
</head>
<body>
    <br /><br /><br /><br /><br />
	<table align="center" width="500px;">
	    <tr>
	        <td>
			<div class="titleRow_">
				<table class="layoutTable_" align="center"><tr><td>
					<span>
						系统错误
					</span>
				</td></tr></table>
			</div>
			<div class="message_spot"><div class="messageArea_">
					<table class="layoutTable_" align="center">
					<tr>
					    <td>
					        <img src="<%=request.getContextPath()%>/pages/img/err.gif" />
					    </td>
					    <td>
						    <% if (request.getParameter("msg") != null && request.getParameter("msg") != "")  {%>
							<span><%=request.getParameter("msg")%></span>
							<%} else { %>
							<span>访问出错了。<br/>请与系统管理员联络。</span>
							<%} %>
					    </td>
					</tr>
					</table>
			</div></div>
			<div class="guideButtonRow_">
				<div >
					<table class="layoutTable_" >
						<tr >
							<td >
							<input type="button" id="moveLoginUrl" value="去登录画面" style="width:160px;" />
							</td>
						</tr>
					</table>
				</div>
		</div>
	        </td>
	    </tr>
	</table>
</body>
</html>