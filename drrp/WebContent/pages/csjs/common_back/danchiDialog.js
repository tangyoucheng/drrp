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
 	DanchiDialog = function(){};
 	DanchiDialog.data = [];
 	DanchiDialog.createDialog = function(){
 		var parameter = "";
 		var parameter = "danchiForm.shokanCd=" + encodeURI(DanchiDialog.data.shokanCode)
//		                + "&danchiForm.kyokaCd=" + encodeURI(DanchiDialog.data.kyokaCd)
//		                + "&danchiForm.gakkoKubunCd=" + encodeURI(DanchiDialog.data.gakkoKubunCd)
//		                + "&danchiForm.kateiCd=" + encodeURI(DanchiDialog.data.kateiCd)
//		                + "&danchiForm.kaikoKamokuNo=" + encodeURI(DanchiDialog.data.kaikoKamokuNo)
//		                + "&danchiForm.bunkatuKamokuNo=" + encodeURI(DanchiDialog.data.bunkatuKamokuNo)
//		                + "&danchiForm.gakkaCd=" + encodeURI(DanchiDialog.data.gakkaCd)
		                + "&danchiForm.areaCd=" + encodeURI(DanchiDialog.data.areaCode);
		var html = "<div id='danchiDiv' style='display:none;'>" +
			 	   "<iframe id='danchiFrame' name='danchiFrame' src='"+getContextPath('/common/danchiAction!doSearch.do?') + parameter + "' class='model' height='100%' width='100%'  frameborder='0'></iframe>"+
				   "</div>";
		var boxy = new Boxy($(html), {
		    modal: true,
		    closeable: false,
			unloadOnHide: false
		});
		boxy.resize(330, 600);
		return boxy;
	};
 	DanchiDialog.show = function(func){
		// Body部を非表示とする
		applicationFrame = $("#right").contents().find("body");
		applicationFrame.css("visibility", "hidden");
		box = this.createDialog();
		callback = func;
	};
 	DanchiDialog.hide = function(){
		// 隠していたBody部を表示
		if (applicationFrame != undefined) {
			applicationFrame.css("visibility", "visible");
			// ウォーターマークで入力規則を表示
			$(window.frames.document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
				if($(this).val()==''){
					$(this).css({"color":"#888"}).val(this.getAttribute("grayMessage"));
				}
				
			});
			$(window.top.right.document).find("input[class*=grayTips], textarea[class*=grayTips]").each(function(){
				if($(this).val()==''){
					$(this).css({"color":"#888"}).val(this.getAttribute("grayMessage"));
				}
				
			});
		}
		box.hide();
		callback.call(this,result);
	};
 	DanchiDialog.setResult = function(data){
		result.data = data;
	};
 	DanchiDialog.getResult = function(){
		return result;
	};
	window.DanchiDialog = DanchiDialog;
})(jQuery);