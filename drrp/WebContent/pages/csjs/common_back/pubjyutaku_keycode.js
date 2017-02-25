// 调试
isDebug = false;



//================================================
//HTMLload是的处理
//【参数】
//【返回值】
//【做成】
//【更新】
//【概要】
//================================================
$(document).ready(function(){
	
	// --------------------------------------------------
	// 禁止邮件菜单查看
	// --------------------------------------------------
	$(document).bind("contextmenu",function(e){
		return isDebug;
	});
	
	// --------------------------------------------------
	// 禁止通过键盘刷新，前进，后退页面。
	// --------------------------------------------------
	$(document).keydown(function(e){
        // backspace
        var obj = e.target || e.srcElement;
        var jsType = obj.type || obj.getAttribute('type');
        var vReadOnly = obj.getAttribute('readonly');
        var vEnabled = obj.getAttribute('enabled');
        vReadOnly = (vReadOnly == null) ? false : vReadOnly;
        vEnabled = (vEnabled == null) ? true : vEnabled;
        var vCssClass = obj.getAttribute('class');
        if (vCssClass != null && (vCssClass.indexOf("readonlydata") >=0 )){
            obj.setAttribute("readonly",true,0); 
        }
        if (e.keyCode == 8) {
            if (jsType=="password" || jsType=="text" || jsType=="textarea") {
                if (vReadOnly==true || vReadOnly=="readonly" || vEnabled!=true) {
                    return isDebug;
                } else {
                    return true;
                }
            } else {
                return isDebug;
            }
        }
        
        // F5 或者 Ctrl + R
        if((e.keyCode==116) || (e.ctrlKey && e.keyCode==82)) {
            e.originalEvent.keyCode = 0;
            return isDebug;
        }
        // Enter
        if (e.keyCode==13) {
            if(e.srcElement.type!='submit' && e.srcElement.type!='textarea') {
                return isDebug;
            }
        }
        // Alt + ← 或者 Alt + →
        if (e.altKey) {
            if (e.keyCode == 37 || e.keyCode == 39) {
                return isDebug;
            }
        }
        // OK
        return true;
    });
	
});
