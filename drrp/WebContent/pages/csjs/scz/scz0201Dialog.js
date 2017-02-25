//================================================
// 自動振分指示リンクのクリック処理
// 【入力】なし
// 【返却】なし
// 【作成】zyf
// 【更新】
// 【概要】
//================================================
(function($){
 	var box;
 	var result = [];
 	var applicationFrame = undefined;
 	var callback = function(){};
 	SCZ0201Dialog = function(){};
 	SCZ0201Dialog.data = [];
 	SCZ0201Dialog.createDialog = function(){
 		var parameter = "scz0201Form.imageWidth=" + encodeURI(SCZ0201Dialog.data.imageWidth)
		                + "&scz0201Form.imageHeight=" + encodeURI(SCZ0201Dialog.data.imageHeight)
		                + "&scz0201Form.imagePath=" + encodeURI(SCZ0201Dialog.data.imagePath)
		                + "&scz0201Form.imageName=''";
		var html = "<div id='SCZ0201Div' style='display:none;'>" +
			 	   "<iframe id='SCZ0201Frame' name='SCZ0201Frame' src='"+getContextPath('/scz/scz0201Action!doInit.do?') + parameter + "' class='model' height='100%' width='100%'  frameborder='0'></iframe>"+
				   "</div>";
		var boxy = new Boxy($(html), {
		    modal: true,
		    closeable: false,
			unloadOnHide: false
		});
		boxy.getCenter();
		boxy.resize(800, 610);
		return boxy;
	};
	SCZ0201Dialog.show = function(func){
		// Body部を非表示とする
		applicationFrame = $("#right").contents().find("body");
		applicationFrame.css("visibility", "hidden");
		box = this.createDialog();
		callback = func;
	};
	SCZ0201Dialog.hide = function(){
		// 隠していたBody部を表示
		if (applicationFrame != undefined) {
			applicationFrame.css("visibility", "visible");
		}
		box.hide();
		callback.call(this,result);
	};
	SCZ0201Dialog.setResult = function(data){
		result.data = data;
	};
	SCZ0201Dialog.getResult = function(){
		return result;
	};
	window.SCZ0201Dialog = SCZ0201Dialog;
})(jQuery);