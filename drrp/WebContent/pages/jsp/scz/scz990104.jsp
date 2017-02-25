<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<script type="text/javascript">
function start() {

	//定数定義
	var MAIN_WINDOW_URL ='<%=request.getContextPath()%>/pages/jsp/scz/scz9901.jsp';
	var MODE = 1;
	//メインウィンドウオープン処理 //
	var MAIN_WINDOW_NAME = "MainWindow" + Math.round(Math.random()*10000);
	var MAIN_WINDOW_STYLE = "toolbar=no,location=no,directories=no,fullscreen=no," +
			"menubar=no,resizable=yes," +
			"status=yes,scrollbars=yes,titlebar=yes";
	
	
	var wMainWin = window.open(MAIN_WINDOW_URL,MAIN_WINDOW_NAME, MAIN_WINDOW_STYLE);
	wMainWin.moveTo(0, 0);
	wMainWin.resizeTo(screen.availWidth, screen.availHeight);
	if (wMainWin != undefined && wMainWin != null) {
		window.opener = null;
		window.open('','_self');
		window.self.close(); 
	}

}
start();
</script>
</head>
<body>
マイ菜单画面のウィンドウが自動的に開かない場合は<a href="javascript:void(0);" onclick="start();">ここをクリック</a>して下さい。
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.4.2.js"></script> --%>
<script type="text/javascript" src="${ctx}/pages/csjs/lib/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_keycode.js"></script>
</body>
</html>