/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.io.File;
import java.lang.Character.UnicodeBlock;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.exception.SystemException;

import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.UrlValidator;

/**
 * チェックユーティリティクラス。
 * <p>
 * 入力値などの値に対して評価を行うユーティリティクラス。
 * </p>
 * 
 * @author t.d.m
 */
public class CheckUtils {

    /**
     * CheckUtils 的构造。
     */
    private CheckUtils() {
    }

    /**
     * 指定された文字列がnullかどうかをチェックする。
     * <p>
     * 評価対象に指定された文字列がnullかどうかをチェックする。 長さ0の場合もnullと評価する。
     * </p>
     * 
     * @param _value 評価対象文字列
     * @return 評価結果 nullの場合は true、以外は false
     */
    public static boolean isEmpty(String _value) {
        if (_value == null || _value.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 指定された数値がnullかどうかをチェックする。
     * <p>
     * 評価対象に指定された数値がnullかどうかをチェックする。
     * </p>
     * 
     * @param _value 評価対象数値
     * @param _zeroFlg "0"許容标识 true:"0"許容する false:"0"許容しない
     * @return 評価結果 nullの場合は true、以外は false
     */
    public static boolean isEmpty(BigDecimal _value, boolean _zeroFlg) {
        if (_value == null) {
            return true;
        }
        if (!_zeroFlg) {
            if (new BigDecimal(0).compareTo(_value) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 指定された文字列がブランクかどうかをチェックする。
     * <p>
     * 評価対象に指定された文字列がブランクかどうかをチェックする。<br>
     * 評価対象に指定された文字列がnull、長さ0の文字列もしくは全て半角SPの場合にブランクと評価する。
     * </p>
     * 
     * @param _value 評価対象文字列
     * @return 評価結果 ブランクの場合は true、以外は false
     */
    public static boolean isBlank(String _value) {
        if (!isEmpty(_value)) {
            int length = _value.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(_value.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 指定された文字列が全て空白かどうかをチェックする。
     * <p>
     * 評価対象に指定された文字列が全て Whitespace かどうかをチェックする。<br>
     * Whitespaceの定義は {@link Character#isWhitespace(char)} に従う。
     * </p>
     * 
     * @param _value 評価対象文字列
     * @return 評価結果 空白の場合は true、以外は false
     */
    public static boolean isWhitespaces(String _value) {
        if (isEmpty(_value)) {
            return false;
        } else {
            int length = _value.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(_value.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 指定された文字列の全ての文字が数字かどうかをチェックする。
     * <p>
     * アラビア数字を対象に許可する。
     * </p>
     * 
     * @param _value 評価対象文字列
     * @return 評価結果 数字の場合は true、それ以外は false。null または空文字の場合は false。
     */
    public static boolean isDigits(String _value) {
        if (!isEmpty(_value)) {
            return org.apache.commons.lang.math.NumberUtils.isDigits(_value);
        }
        return false;
    }

    /**
     * 指定された文字列が数値として妥当かどうかをチェックする。
     * <p>
     * アラビア数字、カンマ、マイナス記号、小数点を含む数値を対象に、妥当かをチェックする。
     * </p>
     * 
     * @param _value 評価対象文字列
     * @return 評価結果 数値の場合は true、それ以外は false。null または空文字の場合は false。
     */
    public static boolean isNumber(String _value) {
        if (!isEmpty(_value)) {
            _value = _value.replaceAll(",", "");
            return org.apache.commons.lang.math.NumberUtils.isNumber(_value);
        }
        return false;
    }

    /**
     * 指定された数値が偶数かどうか判定する。
     * <p>
     * 評価対象は自然数のみとし 0 (ZERO) もしくはマイナス値が指定された場合は例外とする。 このメソッドでは 0 (ZERO)
     * </p>
     * 
     * @param _value 評価対象の自然数
     * @return 評価結果 偶数の場合は true、奇数の場合は false。
     */
    public static boolean isEven(int _value) {
        if (_value <= 0) {
            throw new IllegalArgumentException("検査対象の int にマイナスは指定できません。");
        }
        return (_value % 2 == 0);
    }

    /**
     * 数値が指定された最小値と最大値の範囲内かどうかをチェックする。
     * 
     * @param _value 評価対象値
     * @param _minvalue 最小値
     * @param _maxvalue 最大値
     * @return 評価結果 範囲内の場合は true、以外は false。
     */
    public static boolean isInRange(int _value, int _minvalue, int _maxvalue) {
        if (_value < _minvalue || _value > _maxvalue) {
            return false;
        }
        return true;
    }

    /**
     * 数値が指定された最小値と最大値の範囲内かどうかをチェックする。
     * 
     * @param _value 評価対象値
     * @param _minvalue 最小値
     * @param _maxvalue 最大値
     * @return 評価結果 範囲内の場合は true、以外は false。
     */
    public static boolean isInRange(long _value, long _minvalue, long _maxvalue) {
        if (_value < _minvalue || _value > _maxvalue) {
            return false;
        }
        return true;
    }

    /**
     * 数値が指定された最小値と最大値の範囲内かどうかをチェックする。
     * 
     * @param _value 評価対象値
     * @param _minvalue 最小値
     * @param _maxvalue 最大値
     * @return 評価結果 範囲内の場合は true、以外は false。
     */
    public static boolean isInRange(BigDecimal _value, BigDecimal _minvalue, BigDecimal _maxvalue) {
        if (_value == null || _minvalue == null || _maxvalue == null) {
            return false;
        }
        if (_value.compareTo(_minvalue) == -1 || _value.compareTo(_maxvalue) == 1) {
            return false;
        }
        return true;
    }

    /**
     * 日付が指定された最小日付と最大日付の範囲内かどうかをチェックする。
     * 
     * @param _value 評価対象値
     * @param _minvalue 最小値
     * @param _maxvalue 最大値
     * @param _format 日付書式
     * @return 評価結果 範囲内の場合は true、以外は false。
     */
    public static boolean isInRange(String _value, String _minvalue, String _maxvalue, String _format) {

        boolean ret = true;

        try {

            ret = isInRange(DateUtils.parse(_value, _format), DateUtils.parse(_minvalue, _format),
                DateUtils.parse(_maxvalue, _format));

        } catch (SystemException e) {
            // 日付妥当性を保てない場合はfalseを返却
            ret = false;
        }

        return ret;
    }

    /**
     * 日付が指定された最小日付と最大日付の範囲内かどうかをチェックする。
     * 
     * @param _value 評価対象値
     * @param _minvalue 最小値
     * @param _maxvalue 最大値
     * @return 評価結果 範囲内の場合は true、以外は false。
     */
    public static boolean isInRange(Date _value, Date _minvalue, Date _maxvalue) {

        if (_value.compareTo(_minvalue) == -1 || _value.compareTo(_maxvalue) == 1) {
            return false;
        }

        return true;
    }

    /**
     * 数値が指定された比較対象値未満かどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 以下の場合は true、以外は false。
     */
    public static boolean isLessThan(int _value1, int _value2) {
        return _value1 < _value2;
    }

    /**
     * 数値が指定された比較対象値未満かどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 以下の場合は true、以外は false。
     */
    public static boolean isLessThan(long _value1, long _value2) {
        return _value1 < _value2;
    }

    /**
     * 数値が指定された比較対象値未満かどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 以下の場合は true、以外は false。
     */
    public static boolean isLessThan(BigDecimal _value1, BigDecimal _value2) {
        if (_value1 == null || _value2 == null) {
            return false;
        }
        return (_value1.compareTo(_value2) == -1);
    }

    /**
     * 日付が指定された比較対象日付より前かどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 以下の場合は true、以外は false。
     */
    public static boolean isLessThan(Date _value1, Date _value2) {
        if (_value1 == null || _value2 == null) {
            return false;
        }
        return (_value1.compareTo(_value2) < 0);
    }

    /**
     * 数値が指定された比較対象値を超えるかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 超える場合は true、以外は false。
     */
    public static boolean isMoreThan(int _value1, int _value2) {
        return _value1 > _value2;
    }

    /**
     * 数値が指定された比較対象値を超えるかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 超える場合は true、以外は false。
     */
    public static boolean isMoreThan(long _value1, long _value2) {
        return _value1 > _value2;
    }

    /**
     * 数値が指定された比較対象値を超えるかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 超える場合は true、以外は false。
     */
    public static boolean isMoreThan(BigDecimal _value1, BigDecimal _value2) {
        if (_value1 == null || _value2 == null) {
            return false;
        }
        return (_value1.compareTo(_value2) == 1);
    }

    /**
     * 日付が指定された比較対象日付より後かどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 超える場合は true、以外は false。
     */
    public static boolean isMoreThan(Date _value1, Date _value2) {
        if (_value1 == null || _value2 == null) {
            return false;
        }
        return (_value1.compareTo(_value2) > 0);
    }

    /**
     * 数値が指定された比較対象値と等しいかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 等しい場合は true、以外は false。
     */
    public static boolean isEqual(int _value1, int _value2) {
        return _value1 == _value2;
    }

    /**
     * 数値が指定された比較対象値と等しいかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 等しい場合は true、以外は false。
     */
    public static boolean isEqual(long _value1, long _value2) {
        return _value1 == _value2;
    }

    /**
     * 数値が指定された比較対象値と等しいかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 等しい場合は true、以外は false。
     */
    public static boolean isEqual(BigDecimal _value1, BigDecimal _value2) {
        if (isEmpty(_value1, true) && isEmpty(_value2, true)) {
            return true;
        }
        if (isEmpty(_value1, true) && !isEmpty(_value2, true)) {
            return false;
        }
        if (!isEmpty(_value1, true) && isEmpty(_value2, true)) {
            return false;
        }
        return (_value1.compareTo(_value2) == 0);
    }

    /**
     * 文字列が指定された比較対象値と等しいかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 等しい場合は true、以外は false。
     */
    public static boolean isEqual(String _value1, String _value2) {
        if (isEmpty(_value1) && isEmpty(_value2)) {
            return true;
        }
        if (isEmpty(_value1) && !isEmpty(_value2)) {
            return false;
        }
        if (!isEmpty(_value1) && isEmpty(_value2)) {
            return false;
        }
        return _value1.equals(_value2);
    }

    /**
     * 文字列が指定された比較対象値と等しいかどうかをチェックする(画面表示区分比較の場合)。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 等しい場合は true、以外は false。
     */
    public static boolean isEqualGamenHyojiKubun(String _value1, String _value2) {
        if (isEmpty(_value1) && isEmpty(_value2)) {
            return true;
        }
        if (isEmpty(_value1) && !isEmpty(_value2)) {
            return false;
        }
        if (!isEmpty(_value1) && isEmpty(_value2)) {
            return false;
        }
        return StringUtils.lowerCase(_value1).equals(StringUtils.lowerCase(_value2));
    }

    /**
     * 日付が指定された比較対象値と等しいかどうかをチェックする。
     * 
     * @param _value1 評価対象値
     * @param _value2 比較対象値
     * @return 評価結果 等しい場合は true、以外は false。
     */
    public static boolean isEqual(Date _value1, Date _value2) {
        if (_value1 == null && _value2 == null) {
            return true;
        }
        if (_value1 == null && _value2 != null) {
            return false;
        }
        if (_value1 != null && _value2 == null) {
            return false;
        }
        return (_value1.compareTo(_value2) == 0);
    }

    /**
     * 文字列が指定された文字長（文字数）かどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _length 文字長
     * @return 評価結果 指定文字長未満の場合は true、それ以外は false。
     */
    public static boolean isMaxLength(String _value, int _length) {
        if (isEmpty(_value)) {
            return true;
        }
        return (!isMoreThanSize(_value, _length));
    }

    /**
     * 文字列が指定された文字長（文字数）未満かどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _size 文字長
     * @return 評価結果 指定文字長未満の場合は true、それ以外は false。
     */
    public static boolean isLessThanLength(String _value, int _size) {
        if (isEmpty(_value)) {
            return true;
        }
        return (_value.length() < _size);
    }

    /**
     * 文字列が指定された文字長（文字数）を超えるかどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _size 文字長
     * @return 評価結果 指定文字長を超えるの場合は true、それ以外は false。
     */
    public static boolean isMoreThanSize(String _value, int _size) {
        if (isEmpty(_value)) {
            return false;
        }
        return (_value.length() > _size);
    }

    /**
     * 文字列が指定された文字長（文字数）かどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _size 文字長
     * @return 評価結果 指定文字長の場合は true、それ以外は false。
     */
    public static boolean isJustSize(String _value, int _size) {
        if (isEmpty(_value)) {
            return false;
        }
        return (_value.length() == _size);
    }

    /**
     * 文字列が指定バイト数未満かどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _bytesize バイト数
     * @return 評価結果 指定バイト数未満の場合は true、それ以外は false。
     */
    public static boolean isLessThanByteSize(String _value, int _bytesize) {
        if (isEmpty(_value)) {
            return true;
        }
        return (StringUtils.getByteSize(_value) < _bytesize);
    }

    /**
     * 文字列が指定バイト数を超えるかどうかをチェックする。 </p>
     * 
     * @param _value 評価対象文字列
     * @param _bytesize バイト数
     * @return 評価結果 指定バイト数を超える場合は true、それ以外は false。
     */
    public static boolean isMoreThanByteSize(String _value, int _bytesize) {
        if (isEmpty(_value)) {
            return false;
        }
        return (StringUtils.getByteSize(_value) > _bytesize);
    }

    /**
     * 文字列が指定バイト数かどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _bytesize バイト数
     * @return 評価結果 指定バイト数の場合は true、それ以外は false。
     */
    public static boolean isJustByteSize(String _value, int _bytesize) {
        if (isEmpty(_value)) {
            return false;
        }
        return (StringUtils.getByteSize(_value) == _bytesize);
    }

    /**
     * 指定された数値の有効桁数をチェックする。
     * <p>
     * 整数部桁数、小数部桁数を指定し、評価対象となる数値が有効桁数を超えていないかをチェックする。<br>
     * 評価対象となる数値の文字列が null もしくは長さ 0 の文字列の場合、true
     * </p>
     * 
     * @param _value 評価対象となる数値の文字列
     * @param _integerSize 整数部桁数
     * @param _decimalSize 小数部桁数
     * @return の場合は true、それ以外は false。
     */
    public static boolean isSignificantFigures(String _value, int _integerSize, int _decimalSize) {
        if (isEmpty(_value)) {
            return true;
        }
        if (!isNumber(_value)) {
            throw new IllegalArgumentException("評価対象の文字列が数値ではありません。value=" + _value);
        }

        BigDecimal bdec = NumberUtils.abs(_value);
        BigDecimal intParts = bdec.setScale(0, RoundingMode.FLOOR);
        BigDecimal decParts = bdec.setScale(_decimalSize, RoundingMode.FLOOR);

        if (isMoreThanByteSize(intParts.toString(), _integerSize)) {
            return false;
        }
        if (bdec.compareTo(decParts) != 0) {
            return false;
        }

        return true;
    }

    /**
     * 指定された文字が全角かをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isFullWidth(char _value) {
        if (!isHalfWidth(_value)) {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字が全角かをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isFullWidth(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            // 1文字抽出
            char char_ = _value.charAt(i);
            // 全角文字チェック
            if (!isFullWidth(char_)) {
                // 改行は許す
                if (char_ != '\r' && char_ != '\n') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 指定された文字が半角かをチェックする。
     * <p>
     * 以下の文字を対象に許可する。<br>
     * <ul>
     * <li>Unicode BASIC LATIN</li>
     * <li>Unicode 半角 CJK punctuation</li>
     * <li>半角カタカナ</li>
     * </ul>
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isHalfWidth(char _value) {
        // BASIC LATIN
        if (UnicodeBlock.of(_value) == UnicodeBlock.BASIC_LATIN) {
            return true;
        }
        // 半角 CJK punctuation
        // ｡｢｣､
        if (_value >= 0xFF61 && _value <= 0xFF64) {
            return true;
        }
        // 半角カタカナ
        if (isHalfWidthKatakana(_value)) {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字が半角かをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isHalfWidth(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isHalfWidth(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字が全角ひらがなかをチェックする。
     * <p>
     * 以下の文字を対象に許可する。<br>
     * <ul>
     * <li>Unicode 0x3041から0x309E</li>
     * </ul>
     * ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん゛゜ゝゞ
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isFullWidthHiragana(char _value) {
        // かな
        if (_value >= 0x3041 && _value <= 0x309E) {
            return true;
        }
        // 中点・長音・ハイフン・マイナス は許可
        if (_value == '・' || _value == 'ー' || _value == '‐' || _value == '－') {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字列が全角ひらがなかをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isFullWidthHiragana(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isFullWidthHiragana(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字が全角カタカナかをチェックする。
     * <p>
     * 以下の文字を対象に許可する。<br>
     * <ul>
     * <li>Unicode 0x30A1から0x30FE</li>
     * </ul>
     * ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセソソゾタ ダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミ ムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ????・ーヽヽヾ
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isFullWidthKatakana(char _value) {
        if (_value >= 0x30A1 && _value <= 0x30FE) {
            return true;
        }
        // 中点・長音・ハイフン・マイナス は許可
        if (_value == '・' || _value == 'ー' || _value == '‐' || _value == '－') {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字列が全角カタカナかをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isFullWidthKatakana(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isFullWidthKatakana(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字が半角カタカナかをチェックする。
     * <p>
     * 以下の文字を対象に許可する。<br>
     * <ul>
     * <li>Unicode 0xFF65から0xFF9F</li>
     * </ul>
     * ･ｦｧｨｩｪｫｬｭｮｯｰｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾉﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝﾞﾟ
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isHalfWidthKatakana(char _value) {
        if (_value >= 0xFF65 && _value <= 0xFF9F) {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字が公営住宅管理系统用カタカナかをチェックする。
     * <p>
     * 以下の文字を対象に許可する。<br>
     * <ul>
     * <li>Unicode 0x0030から0x0039</li>0～9
     * <li>Unicode 0x0041から0x005A</li>A～Z
     * <li>Unicode 0xFF67から0xFF6F</li>ｧｨｩｪｫｬｭｮｯ(促音や拗音などの小さい文字)
     * <li>Unicode 0xFF71から0xFF9F</li>ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾉﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝﾞﾟ
     * </ul>
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isPubjyutakuKatakana(char _value) {

        // 0～9
        if (_value >= 0x0030 && _value <= 0x0039) {
            return true;
        }
        // A～Z
        if (_value >= 0x0041 && _value <= 0x005A) {
            return true;
        }
        // ｧｨｩｪｫｬｭｮｯ(促音や拗音などの小さい文字)
        if (_value >= 0xFF67 && _value <= 0xFF6F) {
            return true;
        }
        // ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾉﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜﾝﾞﾟ
        if (_value >= 0xFF71 && _value <= 0xFF9F) {
            return true;
        }

        // カナ文字の記号
        boolean resultFlag = false;
        for (char ch : StandardConstantsIF.KANA_KIGOU) {
            if (_value == ch) {
                resultFlag = true;
                break;
            }
        }
        if (resultFlag) {
            return true;
        }

        return false;
    }

    /**
     * 指定された文字列が公営住宅管理系统用半角カタカナかをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isPubjyutakuKatakana(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isPubjyutakuKatakana(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字列が半角カタカナかをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isHalfWidthKatakana(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isHalfWidthKatakana(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字が全角または半角のアルファベットかをチェックする。
     * <p>
     * 以下の文字を対象に許可する。<br>
     * <ul>
     * <li>Unicode 0x0041から0x005A</li>
     * <li>Unicode 0x0061から0x007A</li>
     * <li>Unicode 0xFF21から0xFF3A</li>
     * <li>Unicode 0xFF41から0xFF5A</li>
     * </ul>
     * ABCDEFGHIJKLMNOPQRSTUVWXYZ<br>
     * abcdefghijklmnopqrstuvwxyz<br>
     * ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ<br>
     * ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isAlphabet(char _value) {
        if ((_value >= 0x0041 && _value <= 0x005A) || (_value >= 0x0061 && _value <= 0x007A)) {
            return true;
        }
        if ((_value >= 0xFF21 && _value <= 0xFF3A) || (_value >= 0xFF41 && _value <= 0xFF5A)) {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字列が全角または半角のアルファベットかをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isAlphabet(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isAlphabet(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字がアルファベットの大文字かをチェックする。
     * <p>
     * 大文字、小文字が存在する文字を対象に大文字かどうかがチェックされる。
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isCapitalCharacter(char _value) {
        if (Character.isUpperCase(_value)) {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字列がアルファベットの大文字かをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isCapitalCharacter(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isCapitalCharacter(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字がアルファベットの小文字かをチェックする。
     * <p>
     * 大文字、小文字が存在する文字を対象に小文字かどうかがチェックされる。
     * </p>
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isSmallCharacter(char _value) {
        if (Character.isLowerCase(_value)) {
            return true;
        }
        return false;
    }

    /**
     * 指定された文字列がアルファベットの小文字かをチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isSmallCharacter(String _value) {
        if (isEmpty(_value)) {
            return false;
        }
        for (int i = 0; i < _value.length(); i++) {
            if (!isSmallCharacter(_value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 指定された文字列が正規表現にマッチしているかをチェックする。
     * 
     * @param _value 評価対象となる文字列
     * @param _regexp 正規表現
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean matchRegexp(String _value, String _regexp) {
        return GenericValidator.matchRegexp(_value, _regexp);
    }

    /**
     * 指定された文字列がe-mailアドレスとして妥当かをチェックする。
     * 
     * @param _value 評価対象となる文字列
     * @return 評価結果が妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isEmail(String _value) {
        return EmailValidator.getInstance().isValid(_value);
    }

    /**
     * 指定された文字列がURLとして妥当かをチェックする。
     * 
     * @param _value 評価対象となる文字列
     * @return 評価結果 妥当な場合は <code>true</code> 以外は <code>false</code>
     */
    public static boolean isUrl(String _value) {
        return new UrlValidator().isValid(_value);
    }

    /**
     * 指定された文字列が日付として妥当かつフォーマットに一致しているかどうかをチェックする。
     * 
     * @param _value 評価対象文字列
     * @param _format 日付時刻文字列の形式
     * @return 評価結果 妥当な場合は true、それ以外は false。null または空文字の場合は false。
     */
    public static boolean isFormatDate(String _value, String _format) {
        if (!isEmpty(_value) && !isEmpty(_format)) {
            if (_value.length() != _format.length()) {
                return false;
            }

            try {
                DateUtils.parse(_value, _format);
            } catch (SystemException e) {
                return false;
            }
            return true;
        }

        return false;
    }

    // 2010/03/29追加　author:toudaimei
    /**
     * 指定された文字列が英数字かチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 検査結果の真偽値
     */
    public static boolean isAlphaNum(String _value) {
        boolean result = true;
        if (!CheckUtils.isEmpty(_value)) {
            for (int i = 0; i < _value.length(); i++) {
                if (!CheckUtils.isAlphabet(_value.charAt(i)) && !CheckUtils.isDigits(String.valueOf(_value.charAt(i)))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 指定された文字列が英数字混在かチェックする。
     * 
     * @param _value 評価対象となる文字
     * @return 検査結果の真偽値
     */
    public static boolean isAlphaNumForPassword(String _value) {
        boolean result = true;
        boolean isAlpha = false;
        boolean isNum = false;
        if (!CheckUtils.isEmpty(_value)) {
            for (int i = 0; i < _value.length(); i++) {
                if (CheckUtils.isAlphabet(_value.charAt(i))) {
                    isAlpha = true;
                }
                if (CheckUtils.isDigits(String.valueOf(_value.charAt(i)))) {
                    isNum = true;
                }
            }
            if (!isAlpha || !isNum) {
                result = false;
            }
        }
        return result;
    }

    /**
     * ファイルがCSV形式がチェックします。
     * 
     * @param _logicalPath ブラウザで選択されたファイルパス
     * @param _physicalFile サーバー側で保存された物理ファイル
     * @return ファイルがCSV形式の場合 true.
     */
    public static boolean isCsvFile(String _logicalPath, File _physicalFile) {
        // 存在しない
        if (!_physicalFile.exists()) {
            return false;
        }
        // ファイルではない
        if (!_physicalFile.isFile()) {
            return false;
        }
        // 読み込めない
        if (!_physicalFile.canRead()) {
            return false;
        }
        // 拡張子が異なる
        if (!_logicalPath.toLowerCase().endsWith(".csv")) {
            return false;
        }
        // よしとする
        return true;
    }
}
