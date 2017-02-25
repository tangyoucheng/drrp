/**
 * @author tangdaiming
 */
(function($){
 	var box;
 	var applicationFrame = undefined;
 	var result = [];
 	var callback = function(){};
 	PubjyutakuLogin = function(){
	}
	PubjyutakuLogin.createDialog = function(){
		var html = "<div id='login' style='display:none;'>" +
			 	   "<iframe id='loginFrame' name='loginFrame' src='"+getContextPath("/scz/scz9902Action!doInit.do")+"' class='model' width='480' height='380' frameborder='0'></iframe>"+
				   "</div>";
		var boxy = new Boxy($(html), {
		    modal: true,
			unloadOnHide: false,
		    closeable: false,
		    afterShow: function(){
		    	$(window.frames["loginFrame"].document).find("#password").focus();
		    }
		});
		boxy.resize(480, 380);
		return boxy;
	}
	PubjyutakuLogin.show = function(func){
		// Body部を非表示とする
		applicationFrame = $("#right").contents().find("body");
		applicationFrame.css("visibility", "hidden");
		box = this.createDialog();
		callback = func;
	}
	PubjyutakuLogin.hide = function(){
		// 隠していたBody部を表示
		if (applicationFrame != undefined) {
			applicationFrame.css("visibility", "visible");
		}
		box.hide();
		callback.call(this,result);
	}
	PubjyutakuLogin.setResult = function(data){
		result.data = data;
	}
	PubjyutakuLogin.getResult = function(){
		return result;
	}
	window.PubjyutakuLogin = PubjyutakuLogin;
 })(jQuery);