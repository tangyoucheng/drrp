//================================================
//  初期化時、データ的设定。
//  【入力】 なし
//  【返却】 なし
//  【作成】 
//  【更新】
//  【概要】
//================================================
$(document).ready(function() {

});

var tempJsDanchiCd = "";
// ================================================
// 団地コード変更しているがの判断項目
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setDanchiTemp(_codeId) {

	tempJsDanchiCd = $("[id='" + _codeId + "']").val();
}

// ================================================
// 団地コードより該当の団地名的取得
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setDanchiMei(_jimushoId, _shichoId, _codeId, _nameId, _touCodeId,
		_touNameId, _kariFlg,_hasiFlg, _kengenNashiFlg) {
	var jsJimusho = '';
	var jsShicho = '';
	if (_jimushoId!="" && $("[id='" + _jimushoId + "']").val() != undefined) {
		jsJimusho = $("[id='" + _jimushoId + "']").val();
	}
	if (_shichoId!="" && $("[id='" + _shichoId + "']").val() != undefined) {
		jsShicho = $("[id='" + _shichoId + "']").val();
	}
	if (_kariFlg == null || _kariFlg == undefined || _kariFlg == "") {
		_kariFlg = "0";
	}

	if (_hasiFlg == true){
		_hasiFlg = "1";
	}
	if (_hasiFlg == null || _hasiFlg == undefined || _hasiFlg == "") {
		_hasiFlg = "0";
	}
	var jsDanchiCd = $("[id='" + _codeId + "']").val();
	if (tempJsDanchiCd != jsDanchiCd) {
		if (jsDanchiCd != null && jsDanchiCd.length > 0) {
			// パラメタ設定
			var params = {
				"danchiForm.jimushoCd" : jsJimusho,
				"danchiForm.shichoCd" : jsShicho,
				"danchiForm.danchiCd" : jsDanchiCd,
				"danchiForm.kariFlg" : _kariFlg,
				"danchiForm.hasiFlg" : _hasiFlg,
                "danchiForm.kengenNashiFlg" : _kengenNashiFlg
			};
			$
					.ajax({
						data : params,
						url : getContextPath('/commonJsonData/danchiJson!doGetDanchiMei.do'),
						success : function(result) {
							var jsDanchiMei = result.danchiForm.danchiMei;
							var jsDanchiExistFlg = result.danchiForm.danchiExistFlg;
							if (jsDanchiExistFlg) {
								$("[id='" + _nameId + "']").val(jsDanchiMei);
							} else {
								$("[id='" + _nameId + "']").val('');
								$("[id='" + _codeId + "']").val('');
							}
						},
						error : function() {
							window.top.showAjaxConnectFailed();
						}
					});
		} else {
			$("[id='" + _nameId + "']").val('');
		}

		// 棟の設定
		setTouInfo(_codeId, _touCodeId, _touNameId);
	}
}

// ================================================
// 団地のオートコンプリート機能
// 【入力】なし
// 【返却】なし
// 【作成】syugyokuhi
// 【更新】
// 【概要】
// ================================================
function autocompleteDanchiName(_jimushoId, _shichoId, _danchiCodeId,
		_danchiNameId, _touCodeId, _touNameId, _kariFlg,_hasiFlg, _kengenNashiFlg) {

	var jsJimusho = '';
	var jsShicho = '';
	if (_jimushoId!="" &&  $("[id='" + _jimushoId + "']").val() != undefined) {
		jsJimusho = $("[id='" + _jimushoId + "']").val();
	}
	if (_shichoId!="" &&  $("[id='" + _shichoId + "']").val() != undefined) {
		jsShicho = $("[id='" + _shichoId + "']").val();
	}

	if (_kariFlg == null || _kariFlg == undefined || _kariFlg == "") {
		_kariFlg = "0";
	}

	if (_hasiFlg == true){
		_hasiFlg = "1";
	}
	if (_hasiFlg == null || _hasiFlg == undefined || _hasiFlg == "") {
		_hasiFlg = "0";
	}
	$("[id='" + _danchiNameId + "']")
			.autocomplete(
					{
						// autoFocus: true,
						position : {
							my : "right top",
							at : "right bottom",
							collision : "fit"
						},
						source : function(request, response) {

							var params = {
								"danchiForm.jimushoCd" : jsJimusho,
								"danchiForm.shichoCd" : jsShicho,
								"danchiForm.DanchiMei" : $(
										"[id='" + _danchiNameId + "']").val(),
								"danchiForm.kariFlg" : _kariFlg,
								"danchiForm.hasiFlg" : _hasiFlg,
				                "danchiForm.kengenNashiFlg" : _kengenNashiFlg
							};
							$
									.ajax({
										url : getContextPath('/commonJsonData/danchiJson!doGetDanchiInfo.do'),
										cache : false,
										data : params,
										success : function(data) {

											autocompleteCloseFlag = false;
											response(data.danchiForm.subForm);
										}
									});
						},
						search : function(event, ui) {
							$("[id='" + _danchiCodeId + "']").val("").change();

							// 棟の設定
							setTouInfo(_danchiCodeId, _touCodeId, _touNameId);
						},
						select : function(event, ui) {
							$("[id='" + _danchiNameId + "']").val(
									ui.item.recordValue);
							$("[id='" + _danchiCodeId + "']").val(
									ui.item.recordCode).change();

							// 棟の設定
							setTouInfo(_danchiCodeId, _touCodeId, _touNameId);
							return false;
						},
						change : function() {
							if ($("[id='" + _danchiNameId + "']").val()
									.replace(/\s/g, '').length == 0) {
								$("[id='" + _danchiNameId + "']").val("");
								$("[id='" + _danchiCodeId + "']").val("")
										.change();

								// 棟の設定
								setTouInfo(_danchiCodeId, _touCodeId,
										_touNameId);
							}
						},
						close : function(event) {
							// $("[id='" + _danchiNameId + "']").blur();
						}
					}).data("autocomplete")._renderItem = function(ul, item) {
		ul.removeClass("ui-danchi-autocomplete");
		ul.addClass("ui-danchi-autocomplete");
		return $("<li></li>").data("item.autocomplete", item).append(
				"<a>" + item.recordValue + "</a>").appendTo(ul);
	};
}

// ================================================
// 画面を閉じる
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function doCheckDanchiCode(_danchiCodeObject, _danchiNameObject) {
	if ($("[id='" + _danchiCodeObject + "']").val() == '') {
		$("[id='" + _danchiNameObject + "']").val('');
	}
	if ($("[id='" + _danchiNameObject + "']").val() == '') {
		$("[id='" + _danchiCodeObject + "']").val('');
	}
}

// ================================================
// 棟情報の設定
// 【入力】なし
// 【返却】なし
// 【作成】
// 【更新】
// 【概要】
// ================================================
function setTouInfo(_danchiCodeId, _touCodeId, _touNameId) {

	// 棟の設定
	if (_touCodeId != null && _touNameId != null && _touCodeId != ''
			&& _touNameId != '') {

		if (_touCodeId.length == _touNameId.length) {

			for ( var i = 0; i < _touCodeId.length; i++) {
				if (_touCodeId[i] != null && _touCodeId[i].length > 0) {
					$("[id='" + _touCodeId[i] + "']").val('');
				}
				if (_touNameId[i] != null && _touNameId[i].length > 0) {
					$("[id='" + _touNameId[i] + "']").val('');

					if ($("[id='" + _danchiCodeId + "']").val() == null
							|| $("[id='" + _danchiCodeId + "']").val().length == 0) {
						// 棟リストをクリアする
						$("[id='" + _touNameId[i] + "']").empty();
					} else {
						doGetTouList(_danchiCodeId, new Array(_touNameId[i]));
					}
				}
			}
		}
	}
}