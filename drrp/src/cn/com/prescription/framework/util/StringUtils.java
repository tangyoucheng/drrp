/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.common.unique_id.Identifier;
import cn.com.prescription.framework.exception.SystemException;

/**
 * 文字列ユーティリティクラス。
 * 
 * @author t.d.m
 */
public final class StringUtils {

    /** 汎用数値フォーマッター */
    private static final NumberFormat FORMATTER_GENERIC_NUMBER = NumberFormat.getNumberInstance();

    /** 空文字列 **/
    public static final String EMPTY = "";

    /**
     * StringUtils 的构造。
     */
    private StringUtils() {
    }

    /**
     * defaultString
     * 
     * @param _text チェックを行う対象となる文字列
     * @return String 渡された文字列、null の場合には空の文字列
     */
    public static String defaultString(String _text) {
        return org.apache.commons.lang.StringUtils.defaultString(_text);
    }

    /**
     * defaultString
     * 
     * @param _value 対象数列
     * @return String 変換された値
     */
    public static String defaultString(int _value) {
        return String.valueOf(_value);
    }

    /**
     * defaultString
     * 
     * @param _value 対象数列
     * @return String 変換された値
     */
    public static String defaultString(BigDecimal _value) {
        return defaultString(_value, false);
    }

    /**
     * defaultString
     * 
     * @param _value 対象数列
     * @param _isEditComma カンマ编辑を行う場合 true
     * @return String 変換された値
     */
    public static String defaultString(BigDecimal _value, boolean _isEditComma) {
        if (_value == null) {
            return "";
        }
        return _isEditComma ? FORMATTER_GENERIC_NUMBER.format(_value) : _value.toPlainString();
    }

    /**
     * defaultString
     * 
     * @param _value 対象オブジェクト
     * @return String 変換された値
     */
    public static String defaultString(Object _value) {
        return _value == null ? "" : _value.toString();
    }

    /**
     * 文字列の対象文字を指定された文字で置き換える。
     * 
     * @param _text 対象文字列
     * @param _rep1 置換え対象の文字列
     * @param _rep2 置換え後の文字列
     * @return 置換え結果の文字列
     * @see org.apache.commons.lang.StringUtils#replace(String, String, String)
     */
    public static String replace(String _text, String _rep1, String _rep2) {
        return org.apache.commons.lang.StringUtils.replace(_text, _rep1, _rep2);
    }

    /**
     * 文字列内で指定された文字が最初に出現する位置のインデックスを返す。
     * <p>
     * 文字列内で指定された検索文字列が最初に出現する位置のインデックスを返す。<br>
     * 検索文字列が複数指定された場合は、いずれかの検索文字列が最初に出現する位置のインデックスるを返す。<br>
     * {@link String#indexOf(String)} を使用。
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, &quot;a&quot;) = -1
     * StringUtils.indexOf(&quot;aab&quot;, null) = -1
     * StringUtils.indexOf(&quot;&quot;, &quot;&quot;) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;) = 2
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;) = 1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;&quot;) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;aa&quot;, &quot;bb&quot;, &quot;cc&quot;) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;aaa&quot;, &quot;baa&quot;) = 2
     * </pre>
     * 
     * @param _text 検査対象の文字列
     * @param _search 検索する文字列
     * @return 検索文字列が検査対象の文字列内で最初に出現したインデックス番号<br>
     *         <code>-1</code>: 検索文字列が存在しないもしくは、検査対象の文字列、検索文字列がnullの場合
     */
    public static int indexOf(String _text, String... _search) {
        return org.apache.commons.lang.StringUtils.indexOfAny(_text, _search);
    }

    /**
     * 文字列内の指定されたインデックス以降で指定された文字が最初に出現する位置のインデックスを返す。
     * <p>
     * {@link String#indexOf(String)} を使用。
     * </p>
     * 
     * <pre>
     * StringUtils.indexOf(null, &quot;a&quot;, 2) = -1
     * StringUtils.indexOf(&quot;aab&quot;, null, 2) = -1
     * StringUtils.indexOf(&quot;&quot;, &quot;&quot;, 2) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 1) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 2) = 1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 1) = 2
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 2) = 5
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 1) = 1
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 2) = 4
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 1) = 0
     * StringUtils.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 2) = 0
     * </pre>
     * 
     * @param _text 検査対象の文字列
     * @param _search 検索する文字列
     * @param _start 検索開始位置のインデックス
     * @return 検索文字列が検査対象の文字列内の検索開始位置以降で最初に出現したインデックス番号<br>
     *         <code>-1</code>: 検索文字列が存在しないもしくは、検査対象の文字列、検索文字列がnullの場合
     */
    public static int indexOf(String _text, String _search, int _start) {
        return org.apache.commons.lang.StringUtils.indexOf(_text, _search, _start);
    }

    /**
     * 文字列内で指定された文字が最後に出現する位置のインデックスを返す。
     * <p>
     * 文字列内で指定された検索文字列が最後に出現する位置のインデックスを返す。<br>
     * {@link String#lastIndexOf(String)} を使用。
     * </p>
     * 
     * @param _text 検査対象の文字列
     * @param _search 検索する文字列
     * @return 検索文字列が検査対象の文字列内で最後に出現したインデックス番号<br>
     *         <code>-1</code>: 検索文字列が存在しないもしくは、検査対象の文字列、検索文字列がnullの場合
     */
    public static int lastIndexOf(String _text, String... _search) {
        return org.apache.commons.lang.StringUtils.lastIndexOfAny(_text, _search);
    }

    /**
     * lastIndexOf
     * 
     * @param _text 対象文字列
     * @param _search 検索文字列
     * @param _start スタート値
     * @return int
     */
    public static int lastIndexOf(String _text, String _search, int _start) {
        return org.apache.commons.lang.StringUtils.lastIndexOf(_text, _search, _start);
    }

    /**
     * startWith
     * 
     * @param _text 対象文字列
     * @param _prefix 比較文字列
     * @return boolean 判定結果
     */
    public static boolean startWith(String _text, String _prefix) {
        return org.apache.commons.lang.StringUtils.startsWith(_text, _prefix);
    }

    /**
     * startWith
     * 
     * @param _text 対象文字列
     * @param _prefix 比較文字列
     * @return boolean 判定結果
     */
    public static boolean endsWith(String _text, String _prefix) {
        return org.apache.commons.lang.StringUtils.endsWith(_text, _prefix);
    }

    /**
     * contains
     * 
     * @param _text 対象文字列
     * @param _search 検索文字列
     * @return boolean 判定結果
     */
    public static boolean contains(String _text, String... _search) {
        // 指定された検索文字列のいずれかが存在するか
        return (indexOf(_text, _search) >= 0);
    }

    /**
     * containsIgnoreCase
     * 
     * @param _text 対象文字列
     * @param _search 検索文字列
     * @return boolean 判定結果
     */
    public static boolean containsIgnoreCase(String _text, String... _search) {

        // 指定された検索文字列のいずれかが大／小文字の区別なく存在するか
        if (_search == null || _search.length == 0) {
            return false;
        } else {
            for (int i = 0; i < _search.length; i++) {
                if (indexOf(_text.toUpperCase(), _search[i].toUpperCase()) >= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 概要をここに記述。
     * <p>
     * 詳細をここに記述<br>
     * </p>
     * 
     * <pre>
     * StringUtils.containsNone(null, &quot;x&quot;, &quot;y&quot;, &quot;z&quot;) = true
     * StringUtils.containsNone(&quot;abab&quot;, null) = true
     * StringUtils.containsNone(&quot;&quot;, &quot;x&quot;, &quot;y&quot;, &quot;z&quot;) = true
     * StringUtils.containsNone(&quot;abab&quot;, &quot;&quot;) = true
     * StringUtils.containsNone(&quot;abab&quot;, &quot;xyz&quot;) = true
     * StringUtils.containsNone(&quot;abab&quot;, &quot;ba&quot;) = false
     * StringUtils.containsNone(&quot;abab&quot;, &quot;baa&quot;) = true
     * StringUtils.containsNone(&quot;abab&quot;, &quot;x&quot;, &quot;y&quot;, &quot;z&quot;) = true
     * StringUtils.containsNone(&quot;abaz&quot;, &quot;x&quot;, &quot;y&quot;, &quot;z&quot;) = false
     * </pre>
     * 
     * @param _text 対象文字列
     * @param _invalidString 検索文字列
     * @return boolean 判定結果
     * @since 1.0
     */
    public static boolean containsNone(String _text, String... _invalidString) {

        if (_invalidString != null && _invalidString.length != 0) {
            for (int i = 0; i < _invalidString.length; i++) {
                if (indexOf(_text, _invalidString[i]) >= 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * substring
     * 
     * @param _text 対象文字列
     * @param _start 開始値
     * @return String 抽出文字列
     */
    public static String substring(String _text, int _start) {
        return org.apache.commons.lang.StringUtils.substring(_text, _start);
    }

    /**
     * substring
     * 
     * @param _text 対象文字列
     * @param _start 開始値
     * @param _end 終了値
     * @return String 抽出文字列
     */
    public static String substring(String _text, int _start, int _end) {
        return org.apache.commons.lang.StringUtils.substring(_text, _start, _end);
    }

    /**
     * 概要をここに記述。
     * <p>
     * 詳細をここに記述<br>
     * </p>
     * 
     * <pre>
     * StringUtils.substringBetween(null, *)            = null
     * StringUtils.substringBetween(&quot;&quot;, &quot;&quot;)             = &quot;&quot;
     * StringUtils.substringBetween(&quot;&quot;, &quot;tag&quot;)          = null
     * StringUtils.substringBetween(&quot;tagabctag&quot;, null)  = null
     * StringUtils.substringBetween(&quot;tagabctag&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtils.substringBetween(&quot;tagabctag&quot;, &quot;tag&quot;) = &quot;abc&quot;
     * </pre>
     * 
     * @param _text 対象文字列
     * @param _tag タグ文字列
     * @return String
     */
    public static String substringBetween(String _text, String _tag) {
        return org.apache.commons.lang.StringUtils.substringBetween(_text, _tag);
    }

    /**
     * substringBetween
     * 
     * @param _text 対象文字列
     * @param _open _open
     * @param _close _close
     * @return String
     */
    public static String substringBetween(String _text, String _open, String _close) {
        return org.apache.commons.lang.StringUtils.substringBetween(_text, _open, _close);
    }

    /**
     * left
     * 
     * @param _text 対象文字列
     * @param _length レングス
     * @return String
     */
    public static String left(String _text, int _length) {
        return org.apache.commons.lang.StringUtils.left(_text, _length);
    }

    /**
     * leftpad全角半角対応
     * 
     * @param _text 対象文字列
     * @param _length レングス
     * @return String
     */
    public static String leftPad1(String _text, int _length, String _separator) {

        if (_text == null) {
            _text = "";
        }
        while (getStringLength(_text) < _length) {
            _text = _separator + _text;
        }
        return _text;
    }

    /**
     * left
     * 
     * <pre>
     * StringUtils.left(null, *)      = null
     * StringUtils.left(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.left(&quot;abc&quot;, &quot;a&quot;)   = &quot;&quot;
     * StringUtils.left(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
     * StringUtils.left(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
     * StringUtils.left(&quot;abc&quot;, &quot;d&quot;)   = &quot;abc&quot;
     * StringUtils.left(&quot;abc&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtils.left(&quot;abc&quot;, null)  = &quot;abc&quot;
     * </pre>
     * 
     * @param _text 対象文字列
     * @param _separator セパレータ
     * @return String
     */
    public static String left(String _text, String _separator) {
        return org.apache.commons.lang.StringUtils.substringBefore(_text, _separator);
    }

    /**
     * right
     * 
     * @param _text 対象文字列
     * @param _length レングス
     * @return String
     */
    public static String right(String _text, int _length) {
        return org.apache.commons.lang.StringUtils.right(_text, _length);

    }

    /**
     * right
     * 
     * <pre>
     * StringUtils.right(null, *)      = null
     * StringUtils.right(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.right(*, null)      = &quot;&quot;
     * StringUtils.right(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
     * StringUtils.right(&quot;abcba&quot;, &quot;b&quot;) = &quot;cba&quot;
     * StringUtils.right(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
     * StringUtils.right(&quot;abc&quot;, &quot;d&quot;)   = &quot;&quot;
     * StringUtils.right(&quot;abc&quot;, &quot;&quot;)    = &quot;abc&quot;
     * </pre>
     * 
     * @param _text 対象文字列
     * @param _separator セパレータ
     * @return String
     */
    public static String right(String _text, String _separator) {
        return org.apache.commons.lang.StringUtils.substringAfter(_text, _separator);
    }

    /**
     * mid
     * 
     * @param _text 対象文字列
     * @param _start 開始値
     * @param _length レングス
     * @return String
     */
    public static String mid(String _text, int _start, int _length) {
        return org.apache.commons.lang.StringUtils.mid(_text, _start, _length);
    }

    /**
     * mid
     * 
     * <pre>
     * StringUtils.mid(&quot;wx[b]yz&quot;, &quot;[&quot;, &quot;]&quot;) = &quot;b&quot;
     * StringUtils.mid(null, *, *)          = null
     * StringUtils.mid(*, null, *)          = null
     * StringUtils.mid(*, *, null)          = null
     * StringUtils.mid(&quot;&quot;, &quot;&quot;, &quot;&quot;)          = &quot;&quot;
     * StringUtils.mid(&quot;&quot;, &quot;&quot;, &quot;]&quot;)         = null
     * StringUtils.mid(&quot;&quot;, &quot;[&quot;, &quot;]&quot;)        = null
     * StringUtils.mid(&quot;yabcz&quot;, &quot;&quot;, &quot;&quot;)     = &quot;&quot;
     * StringUtils.mid(&quot;yabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * StringUtils.mid(&quot;yabczyabcz&quot;, &quot;y&quot;, &quot;z&quot;)   = &quot;abc&quot;
     * </pre>
     * 
     * @param _text 対象文字列
     * @param _openTag 開始タグ
     * @param _closeTag 終了タグ
     * @return String
     */
    public static String mid(String _text, String _openTag, String _closeTag) {
        return org.apache.commons.lang.StringUtils.substringBetween(_text, _openTag, _closeTag);
    }

    /**
     * 文字列を分割文字で分割し配列の文字列に変換する。
     * <p>
     * 指定された分割文字で文字列を配列に分割する。<br>
     * 分割文字で囲まれた長さ0の文字列も対象となる。
     * </p>
     * 
     * <pre>
     *   StringUtils.split(null, ',')   = null
     *   StringUtils.split(&quot;&quot;, ',')   = []
     *   StringUtils.split(&quot;a,b,c&quot;, ',')   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     *   StringUtils.split(&quot;a,b,,c&quot;, ',')   = [&quot;a&quot;, &quot;b&quot;, &quot;&quot;, &quot;c&quot;]
     * </pre>
     * 
     * @param _text 分割対象の文字列
     * @param _separator 分割する文字
     * @return 分割後の配列の文字列
     */
    public static String[] split(String _text, String _separator) {
        return org.apache.commons.lang.StringUtils.splitPreserveAllTokens(_text, _separator);
    }

    /**
     * 文字列を分割文字で分割し配列の文字列に変換する。
     * <p>
     * 指定された分割文字で文字列を配列に分割する。<br>
     * 分割文字で囲まれた長さ0の文字列を対象とするかどうかを指定できる。
     * </p>
     * 
     * <pre>
     *   StringUtils.split(&quot;a,b,,c&quot;, ',', true)  = [&quot;a&quot;, &quot;b&quot;, &quot;&quot;, &quot;c&quot;]
     *   StringUtils.split(&quot;a,b,,c&quot;, ',', false) = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
     * </pre>
     * 
     * @param _text 分割対象の文字列
     * @param _separator 分割する文字
     * @param _preserveAllTokens 分割したトークン全てを対象とするか、長さ0の場合は排除するかを指定
     *            <ul>
     *            <li><code>true</code>: 全てを対象</li>
     *            <li><code>false</code>: 長さ0のトークンは排除</li>
     *            </ul>
     * @return 分割後の配列の文字列
     */
    public static String[] split(String _text, String _separator, boolean _preserveAllTokens) {
        if (_preserveAllTokens) {
            return org.apache.commons.lang.StringUtils.splitPreserveAllTokens(_text, _separator);
        } else {
            return org.apache.commons.lang.StringUtils.split(_text, _separator);
        }
    }

    /**
     * 配列内の要素を連結する。区切り文字なし。
     * 
     * <pre>
     *   StringUtils.join(null, ',')                = null
     *   StringUtils.join([], ',')                  = &quot;&quot;
     *   StringUtils.join([null], ',')              = &quot;&quot;
     *   StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], ',')     = &quot;abc&quot;
     *   StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;&quot;, &quot;c&quot;], ',') = &quot;abc&quot;
     * </pre>
     * 
     * @param _array 配列
     * @return 連結結果の文字列
     */
    public static String join(Object[] _array) {
        return org.apache.commons.lang.StringUtils.join(_array);
    }

    /**
     * 配列内の要素を指定された区切り文字で連結する。
     * 
     * <pre>
     *   StringUtils.join(null, ',')                = null
     *   StringUtils.join([], ',')                  = &quot;&quot;
     *   StringUtils.join([null], ',')              = &quot;&quot;
     *   StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], ',')     = &quot;a,b,c&quot;
     *   StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;c&quot;], null)    = &quot;abc&quot;
     *   StringUtils.join([&quot;a&quot;, &quot;b&quot;, &quot;&quot;, &quot;c&quot;], ',') = &quot;a,b,,c&quot;
     * </pre>
     * 
     * @param _array 配列
     * @param _separator 区切り文字
     * @return 連結結果の文字列
     */
    public static String join(Object[] _array, char _separator) {
        return org.apache.commons.lang.StringUtils.join(_array, _separator);
    }

    /**
     * repeat
     * 
     * @param _text 対象文字列
     * @param _repeat 繰り返し数
     * @return String
     */
    public static String repeat(String _text, int _repeat) {
        return org.apache.commons.lang.StringUtils.repeat(_text, _repeat);
    }

    /**
     * leftPad
     * 
     * @param _text 対象文字列
     * @param _size サイズ
     * @return String
     */
    public static String leftPad(String _text, int _size) {
        return org.apache.commons.lang.StringUtils.leftPad(_text, _size);
    }

    /**
     * leftPad
     * 
     * @param _text 対象文字列
     * @param _size サイズ
     * @param _padStr 埋め込み文字列
     * @return String
     */
    public static String leftPad(String _text, int _size, String _padStr) {
        return org.apache.commons.lang.StringUtils.leftPad(_text, _size, _padStr);
    }

    /**
     * rightPad
     * 
     * @param _text 対象文字列
     * @param _size サイズ
     * @return String
     */
    public static String rightPad(String _text, int _size) {
        return org.apache.commons.lang.StringUtils.rightPad(_text, _size);
    }

    /**
     * rightPad
     * 
     * @param _text 対象文字列
     * @param _size サイズ
     * @param _padStr 埋め込み文字列
     * @return String
     */
    public static String rightPad(String _text, int _size, String _padStr) {
        return org.apache.commons.lang.StringUtils.rightPad(_text, _size, _padStr);
    }

    /**
     * rightPad1全角半角対応
     * 
     * @param _text 対象文字列
     * @param _length レングス
     * @return String
     */
    public static String rightPad1(String _text, int _length, String _separator) {

        if (_text == null) {
            _text = " ";
        }
        if (_text == "") {
            _text = " ";
        }
        if (_separator == null) {
            _separator = " ";
        }
        if (_separator == "") {
            _separator = " ";
        }
        String text_ = _text;
        while (getStringLength(text_) < _length) {
            text_ = text_ + _separator;
        }
        return text_;
    }

    /**
     * trim
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String trim(String _text) {
        return org.apache.commons.lang.StringUtils.trim(_text);
    }

    /**
     * trimToNull
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String trimToNull(String _text) {
        return org.apache.commons.lang.StringUtils.trimToNull(_text);
    }

    /**
     * deleteWhitespace
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String deleteWhitespace(String _text) {
        return org.apache.commons.lang.StringUtils.deleteWhitespace(_text);
    }

    /**
     * upperCase
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String upperCase(String _text) {
        return org.apache.commons.lang.StringUtils.upperCase(_text);
    }

    /**
     * lowerCase
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String lowerCase(String _text) {
        return org.apache.commons.lang.StringUtils.lowerCase(_text);
    }

    /**
     * swapCase
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String swapCase(String _text) {
        return org.apache.commons.lang.StringUtils.swapCase(_text);
    }

    /**
     * capitalize
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String capitalize(String _text) {
        return org.apache.commons.lang.StringUtils.capitalize(_text);
    }

    /**
     * capitalize
     * 
     * @param _text 対象文字列
     * @param _separator セパレータ
     * @return String
     */
    public static String capitalize(String _text, char _separator) {

        if (CheckUtils.isEmpty(_text)) {
            return _text;
        } else {
            String[] splits = split(_text, String.valueOf(_separator));
            StringBuilder sb = new StringBuilder(_text.length());
            for (int i = 0; i < splits.length; i++) {
                sb.append(org.apache.commons.lang.StringUtils.capitalize(splits[i]));
            }
            return sb.toString();
        }
    }

    /**
     * reverse
     * 
     * <pre>
     * StringUtils.reverse(null)  = null
     * StringUtils.reverse(&quot;&quot;)    = &quot;&quot;
     * StringUtils.reverse(&quot;bat&quot;) = &quot;tab&quot;
     * </pre>
     * 
     * @param _text 対象文字列
     * @return String
     */
    public static String reverse(String _text) {
        return org.apache.commons.lang.StringUtils.reverse(_text);
    }

    /**
     * reverseDelimited
     * 
     * <pre>
     * StringUtils.reverseDelimited(null, *)      = null
     * StringUtils.reverseDelimited(&quot;&quot;, *)        = &quot;&quot;
     * StringUtils.reverseDelimited(&quot;a.b.c&quot;, 'x') = &quot;a.b.c&quot;
     * StringUtils.reverseDelimited(&quot;a.b.c&quot;, &quot;.&quot;) = &quot;c.b.a&quot;
     * StringUtils.reverseDelimited(&quot;ab.cd.ef&quot;, &quot;.&quot;) = &quot;c.b.a&quot; // TODO
     * </pre>
     * 
     * @param _text 対象文字列
     * @param _separator セパレータ
     * @return String
     */
    public static String reverseDelimited(String _text, char _separator) {
        return org.apache.commons.lang.StringUtils.reverseDelimited(_text, _separator);
    }

    /**
     * abbreviate
     * 
     * <pre>
     * StringUtils.abbreviate(null, *) = null
     * StringUtils.abbreviate(&quot;&quot;, 4) = &quot;&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 6) = &quot;abc...&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 7) = &quot;abcdefg&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 8) = &quot;abcdefg&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 4) = &quot;a...&quot;
     * StringUtils.abbreviate(&quot;abcdefg&quot;, 3) = IllegalArgumentException
     * 
     * @param _text 対象文字列
     * @param _length レングス
     * @return String
     */
    public static String abbreviate(String _text, int _length) {
        return org.apache.commons.lang.StringUtils.abbreviate(_text, _length);
    }

    /**
     * 文字列のバイト数的取得。
     * <p>
     * 登录用户のエンコードで文字列のバイト数的取得。
     * </p>
     * 
     * @param _value 対象文字列
     * @return 文字列のバイト数
     * @since 1.0
     */
    public static int getByteSize(String _value) {

        // String encode = null;

        /*
         * try { encode = ServiceUtils.getCurrentUserInfo().getEncoding(); } catch (ServiceSystemException e) {
         * e.printStackTrace(); }
         */

        return getByteSize(_value, "UTF-8");
    }

    /**
     * 文字列のバイト数的取得。
     * <p>
     * 指定されたエンコードで文字列のバイト数的取得。
     * </p>
     * 
     * @param _value 対象文字列
     * @param _encode エンコード
     * @return 文字列のバイト数
     * @since 1.0
     */
    public static int getByteSize(String _value, String _encode) {
        int size = 0;

        if (!CheckUtils.isEmpty(_encode)) {
            try {
                size = _value.getBytes(_encode).length;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            size = _value.getBytes().length;
        }

        return size;
    }

    /**
     * オブジェクトの値を文字列で出力する。
     * <p>
     * ログなどでオブジェクト内容を出力する場合に利用する。<br>
     * {@link Object#toString} メソッドを利用すると配列形式など実際の値が出力されないオブジェクトの内容を出力する。 <br>
     * </p>
     * 
     * @param _object 出力対象のオブジェクト
     * @return オブジェクト内容
     */
    public static String objectToString(Object _object) {

        StringBuilder sb = new StringBuilder();

        if (_object == null) {
            return "null";
        }

        if (_object.getClass().isArray()) {
            List<Object> list = null;
            try {
                list = Arrays.asList((Object[]) _object);
            } catch (ClassCastException e) {
                int arrayLength = Array.getLength(_object);
                list = new ArrayList<Object>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    list.add(Array.get(_object, i));
                }
            }
            sb.append("[");
            for (Object item : list) {
                sb.append(item);
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]");
        } else {
            sb.append(_object);
        }

        return sb.toString();
    }

    /**
     * 指定されたパターンを使用してMessageFormatを生成し、指定された置換え文字列をフォーマットする。
     * 
     * @param _pattern パターン文字列
     * @param _replaces 置換え文字列
     * @return フォーマット後文字列
     */
    public static String messageFormat(String _pattern, Object... _replaces) {
        return MessageFormat.format(_pattern, _replaces);
    }

    /**
     * 文字列のバイト切り出し.
     * 
     * @param _data 処理対象となる文字列
     * @param _length カットしたいバイト数
     * @param _encoding 文字エンコーディング("EUC-JP", "UTF-8" etc...)
     * @return カットされた文字列
     * @throws SystemException 予期せぬ例外発生時
     */
    public static final String trunc(final String _data, final int _length, final String _encoding)
                    throws SystemException {
        try {
            CharsetEncoder ce = Charset.forName(_encoding).newEncoder();
            if (_length >= ce.maxBytesPerChar() * _data.length()) {
                return _data;
            }
            CharBuffer cb = CharBuffer.wrap(new char[Math.min(_data.length(), _length)]);
            _data.getChars(0, Math.min(_data.length(), cb.length()), cb.array(), 0);
            return trunc(ce, cb, _length).toString();
        } catch (RuntimeException e) {
            throw new SystemException(e);
        }
    }

    /**
     * 文字列のバイト切り出し.
     * 
     * @param ce エンコーダ。
     * @param in 処理対象となる文字列のバッファ
     * @param capacity カットしたいバイト数
     * @return in にて渡されたバッファを指定バイト数で flip() したもの
     * @throws SystemException 予期せぬ例外発生時
     */
    public static final CharBuffer trunc(final CharsetEncoder ce, final CharBuffer in, final int capacity)
                    throws SystemException {
        try {
            if (capacity >= ce.maxBytesPerChar() * in.limit()) {
                return in;
            }
            final ByteBuffer out = ByteBuffer.allocate(capacity);
            ce.reset();
            CoderResult cr = in.hasRemaining() ? ce.encode(in, out, true) : CoderResult.UNDERFLOW;
            if (cr.isUnderflow()) {
                cr = ce.flush(out);
            }
            return (CharBuffer) in.flip();
        } catch (RuntimeException e) {
            throw new SystemException(e);
        }
    }

    /**
     * 文字列が半角から全角に変換する 半角範囲：33-126；全角範囲：65281-65374；全角-半角=65248；
     * 
     * @param 文字列
     * @return 変換後の文字列
     */
    public static String toUpper(String _input) {

        // 空文字の場合
        if (_input == null) {
            return "";
        }

        char[] c = _input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            // スペースの場合
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            // スペース以外の場合
            if (c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 文字列が全角から半角に変換する 半角範囲：33-126；全角範囲：65281-65374；全角-半角=65248；
     * 
     * @param 文字列
     * @return 変換後の文字列
     */
    public static String toLower(String _input) {

        // 空文字の場合
        if (_input == null) {
            return "";
        }

        char[] c = _input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            // スペースの場合
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            // スペース以外の場合
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 文字列が全角から半角に変換する 半角範囲：33-126；全角範囲：65281-65374；全角-半角=65248；
     * 
     * @param 文字列
     * @return 変換後の文字列
     */
    public static int getStringLength(String _input) {
        if (_input == null) {

            return 0;
        }
        try {
            byte b[] = utf8ToSjis(_input).getBytes(StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS);
            return b.length;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 文字列を分割文字で分割し配列の文字列に変換する。
     * 
     * @param _input 分割対象の文字列
     * @param rule 分割rule
     * @return 分割後の配列
     */
    public static String[] split(String _input, int[] rule) {
        String[] retAry = null;
        if (_input == null || rule == null) {
            return retAry;
        }
        int strRuleLeng = 0;
        int[] subLength = new int[rule.length + 1];
        subLength[0] = 0;
        for (int i = 0; i < rule.length; i++) {
            strRuleLeng += rule[i];
            subLength[i + 1] = strRuleLeng;
        }
        if (strRuleLeng != _input.length()) {
            return retAry;
        }
        retAry = new String[rule.length];
        for (int i = 0; i < rule.length; i++) {
            retAry[i] = _input.substring(subLength[i], subLength[i + 1]);
        }

        return retAry;
    }

    /**
     * 引数の文字列(Shift_JIS)を、UTF-8にエンコードする。
     * 
     * @param value 変換対象の文字列
     * @return エンコードされた文字列
     */
    public static String sjisToUtf8(String value) throws SystemException {
        try {
            byte[] srcStream = value.getBytes(StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS);
            byte[] destStream = (new String(srcStream, StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS))
                .getBytes(StandardConstantsIF.KYOTU_ENCODE_UTF8);
            value = new String(destStream, StandardConstantsIF.KYOTU_ENCODE_UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
        return value;
    }

    /**
     * 引数の文字列(UTF-8)を、Shift_JISにエンコードする。
     * 
     * @param value 変換対象の文字列
     * @return エンコードされた文字列
     */
    public static String utf8ToSjis(String value) throws SystemException {
        try {
            byte[] srcStream = value.getBytes(StandardConstantsIF.KYOTU_ENCODE_UTF8);
            value = new String(srcStream, StandardConstantsIF.KYOTU_ENCODE_UTF8);
            byte[] destStream = value.getBytes(StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS);
            value = new String(destStream, StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
        return value;
    }

    /**
     * 引数の文字列を、エンコードする。
     * 
     * @param value 変換対象の文字列
     * @param src 変換前の文字コード
     * @param dest 変換後の文字コード
     * @return エンコードされた文字列
     */
    private static String convert(String value, String src, String dest) {
        Map<String, String> conversion = createConversionMap(src, dest);
        char oldChar;
        char newChar;
        String key;
        for (Iterator<String> itr = conversion.keySet().iterator(); itr.hasNext();) {
            key = itr.next();
            oldChar = toChar(key);
            newChar = toChar(conversion.get(key));
            value = value.replace(oldChar, newChar);
        }
        return value;
    }

    /**
     * エンコード情報を作成する
     * 
     * @param src 変換前の文字コード
     * @param dest 変換後の文字コード
     * @return エンコードされた文字列
     */
    private static Map<String, String> createConversionMap(String src, String dest) {
        Map<String, String> conversion = new HashMap<String, String>();
        if ((src.equals(StandardConstantsIF.KYOTU_ENCODE_UTF8))
                        && (dest.equals(StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS))) {
            // －（全角マイナス）
            conversion.put("U+FF0D", "U+2212");
            // ～（全角チルダ）
            conversion.put("U+FF5E", "U+301C");
            // ￠（セント）
            conversion.put("U+FFE0", "U+00A2");
            // ￡（ポンド）
            conversion.put("U+FFE1", "U+00A3");
            // ￢（ノット）
            conversion.put("U+FFE2", "U+00AC");
            // ―（全角マイナスより少し幅のある文字）
            conversion.put("U+2015", "U+2014");
            // ∥（半角パイプが2つ並んだような文字）
            conversion.put("U+2225", "U+2016");

        } else if ((src.equals(StandardConstantsIF.KYOTU_ENCODE_SHIFT_JIS))
                        && (dest.equals(StandardConstantsIF.KYOTU_ENCODE_UTF8))) {
            // －（全角マイナス）
            conversion.put("U+2212", "U+FF0D");
            // ～（全角チルダ）
            conversion.put("U+301C", "U+FF5E");
            // ￠（セント）
            conversion.put("U+00A2", "U+FFE0");
            // ￡（ポンド）
            conversion.put("U+00A3", "U+FFE1");
            // ￢（ノット）
            conversion.put("U+00AC", "U+FFE2");
            // ―（全角マイナスより少し幅のある文字）
            conversion.put("U+2014", "U+2015");
            // ∥（半角パイプが2つ並んだような文字）
            conversion.put("U+2016", "U+2225");

        }
        return conversion;
    }

    /**
     * 16進表記の文字的取得。
     * 
     * @param value 変換対象の文字列
     * @return 16進表記の文字
     */
    private static char toChar(String value) {
        return (char) Integer.parseInt(value.trim().substring("U+".length()), 16);
    }

    /**
     * substringB
     * 
     * @param _text 対象文字列(全半角対応)
     * @param _begin 開始位置
     * @param _length レングス
     * @param _fillLength 空白で埋める
     * @return String
     */
    public static String substringB(String _text, int _begin, int _length, boolean _fillLength) {
        String _result = "";
        int _tempPos = (_begin) / 2;
        while (_text.length() > _tempPos && getStringLength(left(_text, _tempPos + 1)) <= _begin) {
            _tempPos = _tempPos + 1;
        }
        if (_begin < 2) {
            _tempPos = 0;
        }
        while (_text.length() > _tempPos
                        && (getStringLength(_result) + getStringLength(mid(_text, _tempPos, 1)) <= _length)) {
            _result = _result + mid(_text, _tempPos, 1);
            _tempPos = _tempPos + 1;
        }
        if (_fillLength) {
            while (getStringLength(_result) != _length) {
                _result = _result + " ";
            }
        }

        return _result;
    }

    /**
     * leftB
     * 
     * @param _text 対象文字列(全半角対応)
     * @param _length レングス
     * @param _fillLength 空白で埋める
     * @return String
     */

    public static String leftB(String _text, int _length, boolean _fillLength) {
        return substringB(_text, 0, _length, _fillLength);
    }

    /**
     * rightB(一旦利用しないてください)
     * 
     * @param _text 対象文字列(全半角対応)
     * @param _length レングス
     * @param _fillLength 空白で埋める
     * @return String
     */

    public static String rightB(String _text, int _length, boolean _fillLength) {
        int startPos_ = 0;
        if (getStringLength(_text) > _length) {
            startPos_ = getStringLength(_text) - _length;
        }
        return substringB(_text, startPos_, _length, _fillLength);
    }

    /**
     * キーを作成する。
     * 
     * @return キー
     * @throws SystemException
     */
    public static String getUniqueId() throws SystemException {
        String uniqueid;

        try {
            Identifier identifier = new Identifier();
            uniqueid = identifier.get();
        } catch (IOException e) {
            throw new SystemException(e);
        }
        return uniqueid;
    }

    /**
     * <p>
     * 機能:format param to %param%
     * </p>
     * 
     * @param param
     * @return String
     */
    public static String getLikeParameter(String param) {
        String result = null;
        if (CheckUtils.isEmpty(param)) {
            result = "%%";
        } else {
            result = "%" + param + "%";
        }
        return result;
    }
}
