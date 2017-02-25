<%@tag import="cn.com.prescription.framework.common.session.UserSessionUtils"%>
<%@tag pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="Content-Script-Type" content="text/javascript"/>
<meta http-equiv="Content-Style-Type" content="text/css"/>
<title></title>

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery-1.12.4.js"></script>


<!-- 基本スタイルシート -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/css/public_default.css" />


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/pages/csjs/lib/jquery_ui/jquery-ui.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_ui/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/lib/jquery_ui/jquery.ui.datepicker-zh.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/pubjyutaku_keycode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/numberField.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/common_utils.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/pages/csjs/common/tagFile.js"></script> --%>

<!-- 基本スクリプトファイル -->
<script type="text/javascript">
    function getContextPath(path) {
        return "<%=request.getContextPath()%>" + (path ? path : "");
    }
<%--     var currentUser = ("<%=UserSessionUtils.getUserId()%>"); --%>
//     if (currentUser != window.top.head.document.scz990101Form["scz990101HidUserId"].value) {
//         ログアウト処理を行う
//         $.ajax ({
//             url : getContextPath('/sczJsonData/scz990101Json!doLoginOut.do'),
//             success : function(result) {
                
//             },
//             error : function() {
                
//             }
//         });
//     }

//     window.history.forward(1); 
    
    if (window.attachEvent) {
        // Internet Explorer /Opera 場合
        window.attachEvent("onload", function() {
            if (window.top.right != undefined && window.top.right.document.readyState == 'complete') {
                
                // 二重送信制御
                $(window.top.left.document).find("input[id=leftMenuDblClickFlag]").val(false);
                
            }
        });
        // Internet Explorer /Opera 場合
//         window.attachEvent("onbeforeunload", function() {
//             history.go(0);
            
//         });
    } else {
        // Mozilla/Firefox 場合
        window.addEventListener("load", function() {
            if (window.top.right.document.readyState == 'complete') {
                
                // 二重送信制御
                $(window.top.left.document).find("input[id=leftMenuDblClickFlag]").val(false);
                
            }
        }, true);
    }
</script>


