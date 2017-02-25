
//================================================
//checked処理
//【入力】なし
//【返却】なし
//【作成】l.z.q
//【更新】
//【概要】
//================================================
function ischecked(Index) {
	$("#nextUrl").val($("#chohyoId_" + Index).val());
	
	// パラメタ設定
	var params = {
		"scz0301Form.nextUrl" : $("#chohyoId_" + Index).val()
	};

	// パラメタ設定
	$.ajax ({
		data : params,
		url : "scz0301Json!doNext.do",
		success : function(result) {
		},
		error : function() {
			window.top.showAjaxConnectFailed();
			return;
		}
	});
	
}

//================================================
//checked処理
//【入力】なし
//【返却】なし
//【作成】l.z.q
//【更新】
//【概要】
//================================================
function goNext() {
	if($("#nextUrl").val() == null || $("#nextUrl").val() == ""){
		// 错误メーセージがありません TOTO
		window.top.showAjaxMessageById('E30001,選択');
		return false;
	}
	window.top.right.location.href = $("#nextUrl").val();
}

