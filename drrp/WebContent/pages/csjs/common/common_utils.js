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

//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以 arg2的精确结果
CommonUtilJs.accMul = function (arg1,arg2) {
  var m=0,s1=arg1.toString(),s2=arg2.toString();
  try{m+=s1.split(".")[1].length}catch(e){}
  try{m+=s2.split(".")[1].length}catch(e){}
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

//给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg){
  return accMul(arg, this);
}

//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
CommonUtilJs.accDiv = function (arg1,arg2){
  var t1=0,t2=0,r1,r2;
  try{t1=arg1.toString().split(".")[1].length}catch(e){}
  try{t2=arg2.toString().split(".")[1].length}catch(e){}
  with(Math){
      r1=Number(arg1.toString().replace(".",""))
      r2=Number(arg2.toString().replace(".",""))
      return (r1/r2)*pow(10,t2-t1);
  }
}
//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
CommonUtilJs.accAdd = function (arg1,arg2){
  var r1,r2,m;
  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
  m=Math.pow(10,Math.max(r1,r2))
  return (arg1*m+arg2*m)/m
  }

//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg){
  return accAdd(arg,this);
}

String.prototype.trim=function(){
   return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim=function(){
   return this.replace(/(^\s*)/g,"");
}
String.prototype.rtrim=function(){
   return this.replace(/(\s*$)/g,"");
}

