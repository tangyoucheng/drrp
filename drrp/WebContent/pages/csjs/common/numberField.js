
var doing = "";
function disableLimit(){doing = "1";}
function enableLimit(){doing = "";}
// =========================================================================
// 入力内容を制約する
// 【 入力 】文字列と最大サイズ
// 【返却値】なし
// =========================================================================
function inputLimit(_object,_integerSize,_decimalSize,_commaFlg,minusFlg) {
    if (doing != ""){
        return;
    };
// var dataValue = CommonUtilJs.delComma(_object.value);
    var dataValue = _object.value; 
    var minusSign = "";
    if (minusFlg) {
        minusSign = "-?";
    }
    doing = "1";
    if (dataValue.length > 0) {
        var strValidate = "";
        var strZero = "";
        eval("strValidate = \/^"+minusSign+"(\\d{1,"+_integerSize+"}(\\.\\d{0,"+_decimalSize+"})?)?$\/");
        if (!strValidate.test(dataValue)) {
            var _position = getPosition(_object);
            _object.value = dataValue.substring(0, _position - 1) + dataValue.substring(_position);
            setCursorPos(_object, _position - 1);
        }    
    }
    doing = "";
}

// --------------------------------------------------
// 数値フォマト
// --------------------------------------------------
function numberFormat(_object,_commaFlg,_rpadFlg,_decimalSize) {
    doing = "1";
    var numberData = _object.val();
    if(_commaFlg){
        numberData = CommonUtilJs.delComma(numberData);
    }
    while  ( numberData.length>1 &  numberData[0]=="0" &  numberData[1]!="."){
        numberData=numberData.substring(1);
    }
    while  ( numberData.length>2 &  numberData[0]=="-" &  numberData[1]=="0" &  numberData[2]!="."){
        numberData=numberData[0]+numberData.substring(2);
    }
    
    while  (numberData.indexOf('.')>0 &   numberData[numberData.length-1]=="0"){
        numberData=numberData.substring(0,numberData.length-1);
    }
    
    if (numberData =="-" | numberData =="-." ){
        numberData = "";
    }
    if (numberData =="-0"){
        numberData = "0";
    }
    if (numberData[0]=="."){
        numberData = "0"+numberData;
    }
    if(numberData.length>0){
        if(_rpadFlg&&_decimalSize!=""&&_decimalSize!=null){
            if(numberData.indexOf('.')<0){
                numberData = numberData + ".";
                for( var i = 0 ; i < parseInt(_decimalSize) ; i ++ ){
                    numberData = numberData + "0";
                }
            }else{
                var rpadSize = parseInt(_decimalSize)-(numberData.length-1-numberData.indexOf('.'));
                for( var i = 0 ; i < rpadSize ; i ++ ){
                    numberData = numberData + "0";
                }
            }
        }
        else if (numberData.indexOf('.') + 1 == numberData.length) {
            numberData = numberData.substring(0, numberData.indexOf('.'));
        }
    }
    if(_commaFlg){
        _object.val(CommonUtilJs.addComma(numberData));
    }else{
        _object.val(numberData);
    }
    doing = "";
}

// --------------------------------------------------
// 数値カンマ编辑（削除）
// --------------------------------------------------
function deleteComma(_object,_commaFlg) {
    doing = "1";
    if(_commaFlg){
        _object.val(CommonUtilJs.delComma(_object.val()));
    }
    doing = "";
}

// =========================================================================
// カソール位置的取得
// 【 入力 】入力項目
// 【返却値】カソール位置
// =========================================================================
function getPosition(_object) {
    var result = 0;
    if (_object.selectionStart) { 
        result = _object.selectionStart;
    } else { // IE
        var rng;
        if (_object.tagName == "TEXTAREA") {
            rng = event.srcElement.createTextRange();
            rng.moveToPoint(event.x, event.y);
        } else {
            rng = document.selection.createRange();
        }
        rng.moveStart("character", -event.srcElement.value.length);
        result = rng.text.length;
    }
    return result;
}

// =========================================================================
// カソール位置を再設定する
// 【 入力 】入力項目とカソール位置
// 【返却値】なし
// =========================================================================
function setCursorPos(_object, _position) {
    var rng = _object.createTextRange();
    rng.moveStart('character', _position);
    rng.collapse(true);
    rng.select();
}

// =========================================================================
// コピー内容をチックする
// 【 入力 】コピー内容
// 【返却値】成功：true;失敗：false
// =========================================================================
function inputValidate(_value,_integerSize,_decimalSize,_commaFlg,minusFlg) {
    doing = "1";
    var minusSign = "";
    if (minusFlg) {
        minusSign = "-?";
    }
    if (_value.length > 0) {
        var strValidate = "";
        var strZero = "";
        if(_commaFlg){
            _integerSize = parseInt(_integerSize) - 2;
            if(_decimalSize != "" && _decimalSize != null){
                eval("strValidate = \/^"+minusSign+"[0-9]\\,?((\\d\\,?){0,"+_integerSize+"})?\\d?(\\.\\d{0,"+_decimalSize+"})?$\/");
                eval("strZero = \/^"+minusSign+"[0](\\.\\d{0,"+_decimalSize+"})?$\/");
            }else{
                eval("strValidate = \/^"+minusSign+"[0-9]\\,?((\\d\\,?){0,"+_integerSize+"})?\\d?$\/");
                eval("strZero = /^"+minusSign+"[0]?$/");
            }
        }else{
            _integerSize = parseInt(_integerSize) - 1;
            if(_decimalSize != "" && _decimalSize != null){
                eval("strValidate = \/^"+minusSign+"[0-9]\\d{0,"+_integerSize+"}(\\.\\d{0,"+_decimalSize+"})?$\/");
                eval("strZero = \/^"+minusSign+"[0](\\.\\d{0,"+_decimalSize+"})?$\/");
            }else{
                eval("strValidate = \/^"+minusSign+"[0-9]\\d{0,"+_integerSize+"}$\/");
                eval("strZero = /^"+minusSign+"[0]?$/");
            }
        }
        if (!strValidate.test(_value) && !strZero.test(_value)) {
            return false;
        } else {
            return true;
        }
    }
    doing = "";
}

// =========================================================================
// 空格を削除する
// 【 入力 】入力文字
// 【返却値】空格を削除した文字
// =========================================================================
function del_LRSpace(str) {
    str = str.replace(/(^\s*)|(\s*$)/g, "");
    return str;
}

// =========================================================================
// 団地IDをフォーマットする
// 【 入力 】入力文字（XXXXXX）
// 【返却値】フォーマットしたの団地ID（XXX.XXX）
// =========================================================================
function danchiIdLostFocus(str) {
    var regExp = /(([0-9]|[a-z]|[A-Z])[0-9]{2}){2}$/;
    if (regExp.test(str.value)) {
    $("#"+str.id).val(str.value.substring(0, 3) + "." + str.value.substring(3, 6));
    }
}


// =========================================================================
// 団地IDのフォーマットを削除する
// 【 入力 】入力文字（XXX.XXX）
// 【返却値】フォーマットしたの団地ID（XXXXXX）
// =========================================================================
function danchiIdOnFocus(str) {
    if (str.value.length == 7) {
        $("#"+str.id).val(str.value.substring(0, 3) + str.value.substring(4, 7));
    }
}

// =========================================================================
// 団地IDは、数字以外の入力値をクリアする
// 【 入力 】入力文字（a-zA-z）
// 【返却値】フォーマットしたの団地ID（0-9）
// =========================================================================
function danchiClear(str) {
    $("#"+str.id).val(str.value.replace(/\D/g,''));
}
