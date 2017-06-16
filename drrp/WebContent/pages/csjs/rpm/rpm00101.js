// デバッグ
isDebug = false;

var doubleFlg = false;
//================================================
//  初期化初期化。
// 【 入力 】 なし
// 【返却値】なし
// 【作成者】tyc
// 【作成日】2013/06/29
// 【更新者】tyc
// 【更新日】2013/06/29
// 【 概要 】なし
//================================================
$(document).ready(function() {
    localStorage.setItem("chikuFlg", "");
	// --------------------------------------------------
	// 右クリック菜单禁止
	// --------------------------------------------------
	$(document).bind("contextmenu",function(e){
		return isDebug;
	});
	
	// --------------------------------------------------
	// キーボードによる再読込、戻る、進む 禁止
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
        
        // F5 または Ctrl + R
        if((e.keyCode==116) || (e.ctrlKey && e.keyCode==82)) {
            e.originalEvent.keyCode = 0;
            return isDebug;
        }
//        // Enter
//        if (e.keyCode==13) {
//            if(e.srcElement.type!='submit' && e.srcElement.type!='textarea') {
//                return isDebug;
//            }
//        }
        // Alt + ← または Alt + →
        if (e.altKey) {
            if (e.keyCode == 37 || e.keyCode == 39) {
                return isDebug;
            }
        }
        // OK
        return true;
    });
	
	$('#loginUserId').focus();
	doubleFlg = false;
	
	//用户ID初期化
	setDefaultState();
});

//=================================================
//用户ID初期化
//【 入力 】なし
//【返却値】なし
//【作成者】gl
//【作成日】2016/09/29
//=================================================
function setDefaultState(){
    var loginUser;
    var loginUserPassword;

    if(document.rpm00101Form.loginUserId && document.rpm00101Form.loginUserId.length > 1) {
        loginUser = document.rpm00101Form.loginUserId[0];
    } else {
        loginUser = document.rpm00101Form.loginUserId;
    }
    var usercd = getCookie("rp_user_id");
    if(loginUser && loginUser.value == "" && usercd != null){
        loginUser.value = usercd;
    }

    if(document.rpm00101Form.loginUserPwd.length > 1) {
        loginUserPassword = document.rpm00101Form.loginUserPwd[0];
    } else {
        loginUserPassword = document.rpm00101Form.loginUserPwd;
    }

    var storecd = getCookie("rp_store_code");
    if(storecd != null && storecd.length > 0){
        $("#loginStoreCode").val(storecd)
    }

    if(loginUser && loginUser.value == ""){
        loginUser.focus();
    }else{
        loginUserPassword.focus();
    }
}


// =================================================
// 登录二重送信チェック
// 【 入力 】なし
// 【返却値】なし
// 【作成者】gl
// 【作成日】2013/04/11
// =================================================
function login() {
	if (!doubleFlg){
		loadStorage();
		// 登录二重送信制御 5秒後クリア
		setTimeout("release()", 5000);
		doubleFlg = true;
		
        // 把当前用户保存到cookie中
	    var loginUser;
	    if(document.rpm00101Form.loginUserId && document.rpm00101Form.loginUserId.length > 1) {
	        loginUser = document.rpm00101Form.loginUserId[0];
	    }
	    else {
	        loginUser = document.rpm00101Form.loginUserId;
	    }
	    var loginUserCd = null;
	    if (loginUser){
	        loginUserCd = loginUser.value;
	    }
        if (loginUser && loginUserCd != null && loginUserCd.length > 0){
            var period = new Date()
            period.setTime(period.getTime() + 604800000);
            // HTTP protocol judgment!!
            var bSecure = false;
            if (window.location.protocol.indexOf( "https") != -1){
                bSecure = true;
            }
            setCookie("rp_user_id", loginUserCd, period,null,null,bSecure);
        }
        
        // 把当前店铺编码保存到cookie中
	    var loginStoreCode = $("#loginStoreCode").val();
        if (loginStoreCode != null && loginStoreCode.length > 0){
            var period = new Date()
            period.setTime(period.getTime() + 604800000);
            // HTTP protocol judgment!!
            var bSecure = false;
            if (window.location.protocol.indexOf( "https") != -1){
                bSecure = true;
            }
            setCookie("rp_store_code", loginStoreCode, period,null,null,bSecure);
        }
        
		return true;
	} else {
		return false;
	}
}

//=================================================
// load localStorage
//【 入力 】なし
//【返却値】なし
//【作成者】gl
//【作成日】2013/04/11
//=================================================
function loadStorage() {
	var flg = localStorage.getItem("flg"); 
	if (flg) {
		$("#flg").val(flg);
	}

}

//=================================================
// 登录二重送信制御クリア
//【 入力 】なし
//【返却値】なし
//【作成者】gl
//【作成日】2013/10/30
//=================================================
function release() {
	doubleFlg = false;
}
