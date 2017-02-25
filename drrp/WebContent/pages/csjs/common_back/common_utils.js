function CommonUtilJs(){
}

//
// 数値カンマ编辑（削除）
//
CommonUtilJs.delComma = function(value) {
 var patern = /,/g;
 var val = "";
 if (value != undefined  && value != null) {
	 val = value.replace(patern, "");
 }
 return val;
};

//
// 数値カンマ编辑（追加）
//
CommonUtilJs.addComma = function(value) {
 var patern = /^([\+\-]?\d+)(\d{3})([\,\d+]*)(\.\d+)?$/;
 var rep = "";
 var val = "";
 if (value != undefined  && value != null) {
	 val = String(value);
	 while (rep != val) {
		 rep = val;
		 val = rep.replace(patern, "$1,$2$3$4");
	 }
 }
 return val;
};

//=========================================================================
//日付フォーマット编辑
//【 入力 】文字列
//【返却値】スラッシュ编辑した日付文字列
//=========================================================================
CommonUtilJs.addDateFormat = function(value) {
 var before = value;
 var after = "";

 if (before.match("^\\d{8}$")) {
     after += before.substr( 0, 4 );
     after += "/";
     after += before.substr( 4, 2 );
     after += "/";
     after += before.substr( 6 );
 } else if (before.match("^\\d{6}$")) {
     after += before.substr( 0, 4 );
     after += "/";
     after += before.substr( 4 );
 } else {
     after = before;
 }
 return after;
};

//=========================================================================
//日付フォーマット编辑（スラッシュ除去）
//【 入力 】文字列
//【返却値】スラッシュ除去した日付文字列
//=========================================================================
CommonUtilJs.delDateFormat = function(value) {

 var before = value;
 var after = "";
 var parts = before.split("/");

 if ( parts.length == 3 ) {
     var tmp1 = "00".concat(parts[1]);
     var tmp2 = "00".concat(parts[2]);
     after = parts[0].concat( tmp1.substr(tmp1.length - 2) ).concat( tmp2.substr(tmp2.length - 2) );
 } else if ( parts.length == 2 ) {
     var tmp1 = "00".concat(parts[1]);
     after = parts[0].concat( tmp1.substr(tmp1.length - 2) );
 } else {
     after = before;
 }

 return after;
};

//=========================================================================
// 指定された文字列は整数であるかどうかをチェックする
//【 入力 】文字列
//【返却値】判定結果
//=========================================================================
CommonUtilJs.isInteger = function(value) {
	if (value.match(/[^0-9]/g) || parseInt(value, 10) + "" != value) {
		return false;
	} else {
		return true;
	}
};

//=========================================================================
//指定された文字列の前の0を削除する
//【 入力 】文字列
//【返却値】結果返却
//=========================================================================
CommonUtilJs.trimLeft0 = function(value) {
	var result = "";
	if (value.length > 1) {
		result = value.replace(/^0*/, ""); 
	} else {
		result = value;
	}
	if (result == "") {
		result = "0";
	}
	return result;
};
