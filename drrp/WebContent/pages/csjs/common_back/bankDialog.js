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
 	BankDialog = function(){};
 	BankDialog.data = [];
 	BankDialog.createDialog = function(){
 		var parameter = "";
 		var parameter = encodeURI(BankDialog.data.JSURL)
		                + "&bankForm.bankCode=" + encodeURI(BankDialog.data.jsBankCode);
//		                + "&bankForm.gakkoKubunCd=" + encodeURI(BankDialog.data.gakkoKubunCd)
//		                + "&bankForm.kateiCd=" + encodeURI(BankDialog.data.kateiCd)
//		                + "&bankForm.kaikoKamokuNo=" + encodeURI(BankDialog.data.kaikoKamokuNo)
//		                + "&bankForm.bunkatuKamokuNo=" + encodeURI(BankDialog.data.bunkatuKamokuNo)
//		                + "&bankForm.gakkaCd=" + encodeURI(BankDialog.data.gakkaCd)
//		                + "&bankForm.gakunen=" + encodeURI(BankDialog.data.gakunen);
		var html = "<div id='bankDiv' style='display:none;'>" +
			 	   "<iframe id='bankFrame' name='bankFrame' src='"+ parameter + "' class='model' height='100%' width='100%'  frameborder='0'></iframe>"+
				   "</div>";
		var boxy = new Boxy($(html), {
		    modal: true,
		    closeable: false,
			unloadOnHide: false
		});
		boxy.resize(480, 600);
		return boxy;
	};
 	BankDialog.show = function(func){
		// Body部を非表示とする
		applicationFrame = $("#right").contents().find("body");
		applicationFrame.css("visibility", "hidden");
		box = this.createDialog();
		callback = func;
	};
 	BankDialog.hide = function(){
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
 	BankDialog.setResult = function(data){
		result.data = data;
	};
 	BankDialog.getResult = function(){
		return result;
	};
	window.BankDialog = BankDialog;
})(jQuery);