var unSerialize = "labelTabsIndexName,serializeInfo,serializeFlg";
/**
 * @author tangdaiming
 */
var StringBuffer = function() {
	this.init();
};

StringBuffer.prototype = {
	buffer : null,
	init : function() {
		this.buffer = [];
	},
	append : function(obj) {
		this.buffer.push(obj);
		return this;
	},
	remove : function(idx) {
		if (this.items() > idx) {
			this.buffer = this.buffer.remove(idx);
		}
		return this;
	},
	items : function() {
		return this.buffer.length;
	},
	clear : function() {
		this.init();
		return this;
	},
	toString : function() {
		return this.buffer.join("");
	}
};

var HashMap = function() {
};
HashMap.prototype = {
	add : function(key, value) {
		this[key] = value
	},
	get : function(key) {
		return this[key]
	},
	contains : function(key) {
		return this.Get(key) == null ? false : true
	},
	remove : function(key) {
		delete this[key]
	}
}

var gInfo = {
	KeyType : {
		'text' : 1,
		'textarea' : 2,
//		'hidden' : 4,
		'checkbox' : 4,
		'radio' : 8,
		'select-one' : 16,
		'select-multiple' : 43,
		'file' : 64,
		'password' : 128

	}
};

var gUtil = {
	F_PTN_TEXT : (gInfo.KeyType['text'] | gInfo.KeyType['textarea']
//			| gInfo.KeyType['hidden'] | gInfo.KeyType['file'] | gInfo.KeyType['password']),
			| gInfo.KeyType['file'] | gInfo.KeyType['password']),
	F_PTN_CHECK : (gInfo.KeyType['checkbox'] | gInfo.KeyType['radio']),
	F_PTN_SELECT : (gInfo.KeyType['select-one'] | gInfo.KeyType['select-multiple']),
	F_PTN_LINK : (gInfo.KeyType['hidden']),

	isElement : function(obj) {
		return obj && obj.nodeType == 1;
	},
	isArray : function(obj) {
		return obj
				&& obj != window
				&& (obj instanceof Array || (typeof obj.length == "number" && typeof obj.item == "function"));
	},
	isFunction : function(obj) {
		return typeof obj == "function";
	},
	isString : function(obj) {
		return typeof obj == "string";
	},

	idObj : function(obj) {
		var resElm = null;
		if (typeof obj == "string") {
			if (document.getElementById) {
				resElm = document.getElementById(obj);
			} else if (document.all) {
				resElm = document.all(obj);
			}
		} else if (typeof(obj) == 'object') {
			resElm = obj;
		}
		return resElm;
	},
	serialize : function(baseNode) {
		var res = [];
		if (this.isElement(baseNode)) {
			var nodeMax = baseNode.elements.length;
			var chkParam = [];
			var param = null;
			var unSerAry = unSerialize.split(",");

			for (var idx = 0; idx < nodeMax; idx++) {
				var wkElm = baseNode.elements[idx];
				if (wkElm.id) {
		            var flg = false;
				    for (var i = 0; i < unSerAry.length; i++) {
				        if (unSerAry[i] == wkElm.id) {
				      
				            flg = true;
				            break;
				        }
                    }
				    if(wkElm.calssName =="hasDatepicker" ){flg = true;}
				    if (flg == true) {
				        continue;
				    }
					var vals = this.getElementValue(wkElm);
					if (!chkParam[vals.id]) {
						chkParam[vals.id] = 0;
					}
					if (vals.value) {
						if (this.isArray(vals.value)) {
							for (var i = 0; i < vals.value.length; i++) {
								param = new StringBuffer();
								param.append(vals.id).append('=');
								param.append(encodeURIComponent(vals.value[i]));
								chkParam[vals.id]++;
								res.push(param.toString());
							}
						} else if (this.isString(vals.value)) {
							param = new StringBuffer();
							param.append(vals.id).append('=');
							param.append(encodeURIComponent(vals.value));
							chkParam[vals.id]++;
							res.push(param.toString());
						}
					}
				}
			}
			for (prop in chkParam) {
				if (chkParam[prop] === 0) {
					res.push(prop + '=');
				}
			}
		}
		return GetCrc32(res.join('&'));
	},

	/**
	 * 指定Elementに、指定値的设定関数<BR>
	 *  [text],[textarea],[hidden]はvalueにセット<BR>
	 *  [checkbox],[radio]はvalueの値が合致すればture、しなければfalse<BR>
	 *  [select-one]はvalueが合致するOptionを選択状態<BR>
	 *  [div],[span]はinnerHTMLにセット
	 * @param obj      ElementObject or ID
	 * @param value    設定する値
	 * @return -1:Elementが見つからない<BR>
	 *         0:正常終了
	 **/
	setElementValue : function(obj, value) {
		var res = -1;
		var elm = gUtil.idObj(obj);
		if (elm) {
			var nPtn = this.checkTypePtn(elm.type);
			if (nPtn & this.F_PTN_TEXT) { //  Text Pattern
				elm.value = value;
			} else if (nPtn & this.F_PTN_CHECK) { //  CheckBox Pattern
				elm.checked = (elm.value == value) ? true : false;
			} else if (nPtn & this.F_PTN_SELECT) { //  SelectBox Pattern
				for (var idx = 0; idx < elm.options.length; idx++) {
					if (elm.options[idx].value == value) {
						elm.selectedIndex = idx;
						break;
					}
				}
			} else {
				//  DIV and SPANの時は直接中に入れる
				if (elm.tagName.toUpperCase() == 'DIV'
						|| elm.tagName.toUpperCase() == 'SPAN') {
					elm.innerHTML = value;
				}
			}
			res = 0;
		}
		return res;
	},

	/**
	 * 指定Elementから値を取り出す関数<BR>
	 *  [text],[textarea],[hidden],[checkbox],[radio],[select-one]はvalueの値<BR>
	 *  [div],[span]はinnerHTMLの値を取得
	 * @param obj      ElementObject or ID
	 * @return 取得結果
	 **/
	getElementValue : function(obj) {
		var res = null;
		var elm = gUtil.idObj(obj);
		if (elm) {
			var nPtn = this.checkTypePtn(elm.type);
			res = new Object();
			res.type = elm.type;
			res.name = elm.name;
			res.id = elm.id;
			if (nPtn & this.F_PTN_TEXT) {
				res.value = elm.value;
			} else if (nPtn & this.F_PTN_CHECK) {
				if (elm.checked) {
					res.value = elm.value;
				} else {
					res.value = null;
				}
			} else if (nPtn & this.F_PTN_SELECT) {
				if (nPtn & gInfo.KeyType['select-one']) {
					if (elm.selectedIndex != -1){
						res.value = elm.value + ":" + elm.options[elm.selectedIndex].text;
					} else {
						res.value = elm.value + ":" + "";
					}
				} else {
					res.value = [];
					var length = elm.length || 0;
					for (var i = 0; i < length; i++) {
						var opt = elm.options[i];
						if (opt.selected) {
							res.value.push((opt.value) ? opt.value : opt.text);
						}
					}
				}
			}
			//  DIV and SPANの時は直接中身を取得
			if (elm.tagName.toUpperCase() == 'DIV'
					|| elm.tagName.toUpperCase() == 'SPAN') {
				res.type = elm.tagName.toUpperCase();
				res.value = elm.innerHTML;
			}
		}
		return res;
	},

	/**
	 * Typeに対応する、判定用のパターンを返す
	 * @param tp     Type
	 * @return 判定用パターン
	 **/
	checkTypePtn : function(tp) {
		var result = 0;
		if (typeof tp === "string") {
			for (var prop in gInfo.KeyType) {
				if (prop == tp) {
					result |= gInfo.KeyType[prop];
				}
			}
		}
		return result;
	}

};



function GetCrc32(Instr)  
{
   // if(typeof(window.Crc32Table)!="undefined"){
    	//return;  
    window.Crc32Table=new Array(256);  
    //}
    var i,j;  
    var Crc;  
    for(i=0; i<256; i++)  
    {  
        Crc=i;  
        for(j=0; j<8; j++)  
        {  
            if(Crc & 1)  
                Crc=((Crc >> 1)& 0x7FFFFFFF) ^ 0xEDB88320;  
            else  
                Crc=((Crc >> 1)& 0x7FFFFFFF);  
        }  
        Crc32Table[i]=Crc;  
    }  
    
    if (typeof Instr != "string") Instr = "" + Instr;  
    Crc=0xFFFFFFFF;  
    for(i=0; i<Instr.length; i++)  
        Crc=((Crc >> 8)&0x00FFFFFF) ^ Crc32Table[(Crc & 0xFF)^ Instr.charCodeAt(i)];  
    Crc ^=0xFFFFFFFF;  
    return Crc;  
}
