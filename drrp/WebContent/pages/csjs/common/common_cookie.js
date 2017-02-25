/**
 * @fileoverview クッキーに対するアクセス関数群。<br/>
 * <br/>
 * <strong>サンプルコード</strong> </a><br/>
 * <br/>
 * <table border="1">
 *   <tr>
 *     <th>
 *       サンプル（.htmlファイル）
 *     </th>
 *   </tr>
 *   <tr>
 *     <td>
 *       <font size="-1">
<pre>
  ・
  ・
  ・
  &lt;script src=&quot;csjs/im_cookie.js&quot;&gt;&lt;/script&gt;

  &lt;script language=&quot;javascript&quot;&gt;
    // クッキーに &quot;ABC&quot; というキーで &quot;XYZ&quot; というデータを保存
    setCookie(&quot;ABC&quot;, &quot;XYZ&quot;);
    
    // クッキーから &quot;ABC&quot; というキーに該当するデータを取得
    var sABC = getCookie(&quot;ABC&quot;);
    
    // クッキーから &quot;ABC&quot; というキーに該当するデータを削除
    removeCookie(&quot;ABC&quot;);
  &lt;/script&gt;
  ・
  ・
  ・
</pre>
 *       </font>
 *     </td>
 *   </tr>
 * </table>
 *
 */

/**
 * 指定のデータをクッキーに保存します。<br/>
 * <br/>
 *
 * @param {String} sName 参照名称
 * @param {String} sValue 値
 * @param {Date} dExpires [option] 利用期限 (省略可能)
 * @param {String} sDomain [option] 有効ドメイン (省略可能)
 * @param {String} sPath [option] 設定可能階層 (省略可能)
 * @param {Boolean} bSecure [option] セキュリティの有無 (省略可能)
 */
function setCookie(sName, sValue, dExpires, sDomain, sPath, bSecure) {
	var sStr = "";

	/* cock */
	sStr += sName;
	sStr += "=";
	sStr += escape(sValue);
	if(dExpires != null){ sStr += "; expires=" + dExpires.toGMTString(); }
	if(sDomain != null){ sStr += "; domain=" + sDomain; }
	if(sPath != null){ sStr += "; path=" + sPath; }
	if(bSecure == true){ sStr += "; secure"; }

	/* bake */
	document.cookie = sStr;
}

/**
 * クッキーの値を取得します。<br/>
 * <br/>
 * 指定参照名称に該当するクッキー内に保存された値を返却します。<br/>
 * 指定参照名称で値を取得できなかった場合には、null 値が返されます。<br/>
 *
 * @param {String} sName 参照名称
 * @return 参照名称に対応した値
 * @type String
 */
function getCookie(sName) {
	var nLen = ("" + sName).length + 2;
	var sCookie = " " + document.cookie;
	var nStart = sCookie.indexOf(" " + sName + "=");
	var nEnd = sCookie.indexOf(";", nStart + nLen);

	/* name is nothing */
	if(nStart == -1){ return null; }

	/* search end point */
	if(nEnd == -1){ nEnd = sCookie.length; }

	/* eat and return */
	return unescape(sCookie.substring(nStart + nLen, nEnd));
}

/**
 *  クッキーの値を消去します。<br/>
 * <br/>
 * クッキー内に保存されているデータのうち、指定の参照名に該当するデータを消去します。<br/>
 * 実際には、クッキーの有効期限を過去に設定し直す事によって、
 * その値自体を無効とし、表面上見えない状態にしているだけです。
 *
 * @param {String} sName 参照名称
 * @param {String} sPath [option] 階層 (省略可能)
 */
function removeCookie(sName, sPath) {
	var dExpires = new Date();
	var sValue = getCookie(sName);

	// HTTP protocol judgment!!
	var bSecure = false;
	if (window.location.protocol.indexOf( "https") != -1){
		bSecure = true;
	}

	/* set expire to past */
	setCookie(sName, sValue, dExpires, null, sPath, bSecure);
}

/**
 * @private
 * return names.
 *  [Output] nothing
 *  [detail] get a collection(Array).
 *           A element is cookie-id in collection.
 */
function getCookieNames(){
	var sCookie = " " + document.cookie;

	if(sCookie.length > 2){
		var array = new Array();
		var startPoint = 0;
		while(true){
			var headPoint = sCookie.indexOf(" ", startPoint);
			if(headPoint == -1){ break; }
			var tailPoint = sCookie.indexOf("=", headPoint);
			var name = sCookie.substring(headPoint + 1, tailPoint);
			array[array.length] = name;
			startPoint = sCookie.indexOf(";", tailPoint);
			if(startPoint == -1){ break; }
		}
		return array;
	}
	else{
		return new Array();
	}
}

/* End of File */