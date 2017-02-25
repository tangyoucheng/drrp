/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.validator;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.framework.util.StringUtils;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.util.ValidatorUtils;

/**
 * フィールドチェッククラス。
 * <p>
 * 本クラスで提供するバリデーション機能は以下に定義する。詳細は {@link ValidatorManager} を参照。
 * </p>
 * 
 * @author t.d.m
 * @since 1.0
 */
public class FieldChecks {

    /**
     * コンストラクタ
     */
    public FieldChecks() {
    }

    /**
     * 単項目バリデータの必須チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateRequired(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        return !CheckUtils.isEmpty(value);
    }

    /**
     * 単項目バリデータの半角チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateHalfWidth(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isHalfWidth(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータの全角チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateFullWidth(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isFullWidth(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータの英字チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateAlphabet(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isAlphabet(value)) {
            result = false;
        }
        return result;
    }

    /**
     * 単項目バリデータの英数字チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateAlphaNum(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            for (int i = 0; i < value.length(); i++) {
                if (!CheckUtils.isAlphabet(value.charAt(i)) && !CheckUtils.isDigits(String.valueOf(value.charAt(i)))) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの英数混在チェックを行う。
     * <p>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateAlphaNumMix(Object _bean, Field _field) throws ValidatorException {
        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {

            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "regExp"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータの英字（大文字）チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateAlphaCapitalChar(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            for (int i = 0; i < value.length(); i++) {
                if (!(CheckUtils.isAlphabet(value.charAt(i)) && CheckUtils.isCapitalCharacter(value.charAt(i)))) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの英数字（大文字）チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateAlphaNumCapitalChar(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            for (int i = 0; i < value.length(); i++) {
                if (!(CheckUtils.isAlphabet(value.charAt(i)) && CheckUtils.isCapitalCharacter(value.charAt(i)))
                                && !CheckUtils.isDigits(String.valueOf(value.charAt(i)))) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの数値チェックを行う。
     * <p>
     * 単項目バリデータの数値チェックを行う。 符号、桁区切り、小数点を含め数値として妥当かどうかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateNumeric(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.isNumber(value);
        }

        return result;
    }

    /**
     * 単項目バリデータの数字チェックを行う。
     * <p>
     * 単項目バリデータの数字チェックを行う。 全角、半角の数字のみを許可する。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateDigits(Object _bean, Field _field) { // throws ValidatorFailedException {

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isDigits(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータの半角カタカナチェックを行う。(公営住宅管理系统用)
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateHankakuKana(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isBlank(value)) {
            value = StringUtils.replace(value, " ", "");
            if (!CheckUtils.isHalfWidthKatakana(value)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの半角カタカナチェックを行う。(半角の英字、スペース、-のみを許可する。)
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateAlphaKana(Object _bean, Field _field) throws ValidatorException {

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isBlank(value)) {
            value = value.replaceAll("[A-Za-z- ]", "");
            if (!CheckUtils.isEmpty(value) && !CheckUtils.isHalfWidthKatakana(value)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの全角ひらがなチェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateZenkakuKana(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isFullWidthHiragana(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータの大文字チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateCapitalChar(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isCapitalCharacter(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータの小文字チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateSmallChar(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !CheckUtils.isSmallCharacter(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータのint型チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateInteger(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            Object intValue = GenericTypeValidator.formatInt(value);
            if (intValue == null) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータのlong型チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateLong(Object _bean, Field _field) {

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            Object intValue = GenericTypeValidator.formatLong(value);
            if (intValue == null) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータのshort型チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateShort(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            Object intValue = GenericTypeValidator.formatShort(value);
            if (intValue == null) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータのdouble型チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateDouble(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            Object intValue = GenericTypeValidator.formatDouble(value);
            if (intValue == null) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータのfloat型チェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateFloat(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            Object intValue = GenericTypeValidator.formatFloat(value);
            if (intValue == null) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータのメールアドレス形式のチェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateMailAddress(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value) && !GenericValidator.isEmail(value)) {
            result = false;
        }

        return result;
    }

    /**
     * 単項目バリデータのURL形式のチェックを行う。
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateUrl(Object _bean, Field _field) {
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        return GenericValidator.isUrl(value);
    }

    /**
     * 単項目バリデータの日付時刻形式のチェックを行う。
     * <p>
     * 単項目バリデータの日付時刻形式のチェックを行う。 バリデーション設定ファイルに定義されたdateFormatの日付時刻書式を元に行う。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateDateFormat(Object _bean, Field _field) throws ValidatorException {

        String varValueDateFormat = _field.getVarValue("dateFormat");
        if (CheckUtils.isEmpty(varValueDateFormat)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "dateFormat"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            Object dateValue = GenericTypeValidator.formatDate(value, varValueDateFormat, true);
            if (dateValue == null) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの入力禁止文字チェックを行う。
     * <p>
     * 単項目バリデータの入力禁止文字チェックを行う。 以下の入力禁止文字が含まれていないことをチェックする。
     * <ul>
     * <li>バリデーションルールファイルに定義された入力禁止文字</li>
     * <li>バリデーション設定ファイルのforbiddenCharの入力禁止文字</li>
     * </ul>
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     */
    public boolean validateForbiddenChar(Object _bean, Field _field) {

        // 入力禁止対象文字の取得
        String varValueForbiddenChar = ValidatorResourceHandler.create().getPattern("forbiddenChar");

        // 検査対象プロパティ値取得
        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        if (!CheckUtils.isEmpty(value) && !CheckUtils.isEmpty(varValueForbiddenChar)) {
            for (char fobiddenChar_ : varValueForbiddenChar.toCharArray()) {
                if (StringUtils.indexOf(value, String.valueOf(fobiddenChar_)) > -1) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 単項目バリデータの入力許可文字チェックを行う。
     * <p>
     * 単項目バリデータの入力許可文字チェックを行う。<br>
     * バリデーション定義ファイルのallowedCharに定義された入力許可文字のみが含まれていることをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateAllowedChar(Object _bean, Field _field) throws ValidatorException {

        String varValueAllowedChar = _field.getVarValue("allowedChar");
        if (CheckUtils.isEmpty(varValueAllowedChar)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "allowedChar"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        if (!CheckUtils.isEmpty(value)) {

            char[] valueChar = value.toCharArray();

            for (char target : valueChar) {
                if (StringUtils.indexOf(varValueAllowedChar, String.valueOf(target)) == -1) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 単項目バリデータの正規表現チェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateRegExp(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "regExp"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータのバイト数チェックを行う。
     * <p>
     * 単項目バリデータのバイト数チェックを行う。<br>
     * バリデーション定義ファイルのjustByteSizeに定義されたバイト数に一致チするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateJustByteSize(Object _bean, Field _field) throws ValidatorException {

        String varValueJustByteSize = _field.getVarValue("justByteSize");
        if (CheckUtils.isEmpty(varValueJustByteSize) || !CheckUtils.isNumber(varValueJustByteSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "justByteSize"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.isJustByteSize(value, NumberUtils.toInt(varValueJustByteSize));
        }

        return result;
    }

    /**
     * 単項目バリデータの桁数チェックを行う。
     * <p>
     * 単項目バリデータの桁数チェックを行う。<br>
     * バリデーション定義ファイルのjustSizeに定義された桁数に一致チするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateJustSize(Object _bean, Field _field) throws ValidatorException {

        String varValueJustSize = _field.getVarValue("justSize");
        if (CheckUtils.isEmpty(varValueJustSize) || !CheckUtils.isNumber(varValueJustSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "justSize"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.isJustSize(value, NumberUtils.toInt(varValueJustSize));
        }

        return result;
    }

    /**
     * 単項目バリデータの最大バイト数チェックを行う。
     * <p>
     * 単項目バリデータの最大バイト数チェックを行う。<br>
     * バリデーション定義ファイルのvalidateMaxByteSizeに定義された最大バイト数以下かをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMaxByteSize(Object _bean, Field _field) throws ValidatorException {

        String varValueMaxByteSize = _field.getVarValue("maxByteSize");
        if (CheckUtils.isEmpty(varValueMaxByteSize) || !CheckUtils.isNumber(varValueMaxByteSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "maxByteSize"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = (CheckUtils.isLessThanByteSize(value, NumberUtils.toInt(varValueMaxByteSize)) || CheckUtils
                .isJustByteSize(value, NumberUtils.toInt(varValueMaxByteSize)));
        }

        return result;
    }

    /**
     * 単項目バリデータの最大文字数チェックを行う。
     * <p>
     * 単項目バリデータの最大文字数チェックを行う。<br>
     * バリデーション定義ファイルのvalidateMaxSizeに定義された最大文字数以下かをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMaxSize(Object _bean, Field _field) throws ValidatorException {

        String varValueMaxSize = _field.getVarValue("maxSize");
        if (CheckUtils.isEmpty(varValueMaxSize) || !CheckUtils.isNumber(varValueMaxSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "maxSize"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = (CheckUtils.isLessThanLength(value, NumberUtils.toInt(varValueMaxSize)) || CheckUtils.isJustSize(
                value, NumberUtils.toInt(varValueMaxSize)));
        }

        return result;
    }

    /**
     * 単項目バリデータの最小バイト数チェックを行う。
     * <p>
     * 単項目バリデータの最小バイト数チェックを行う。<br>
     * バリデーション定義ファイルのminByteSizeに定義された最小バイト数以上かをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMinByteSize(Object _bean, Field _field) throws ValidatorException {

        String varValueMinByteSize = _field.getVarValue("minByteSize");
        if (CheckUtils.isEmpty(varValueMinByteSize) || !CheckUtils.isNumber(varValueMinByteSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "minByteSize"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = (CheckUtils.isMoreThanByteSize(value, NumberUtils.toInt(varValueMinByteSize)) || CheckUtils
                .isJustByteSize(value, NumberUtils.toInt(varValueMinByteSize)));
        }

        return result;
    }

    /**
     * 単項目バリデータの最小文字数チェックを行う。
     * <p>
     * 単項目バリデータの最小文字数チェックを行う。<br>
     * バリデーション定義ファイルのminByteSizeに定義された最小文字数以上かをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMinSize(Object _bean, Field _field) throws ValidatorException {

        String varValueMinSize = _field.getVarValue("minSize");
        if (CheckUtils.isEmpty(varValueMinSize) || !CheckUtils.isNumber(varValueMinSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "minSize"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = (CheckUtils.isMoreThanSize(value, NumberUtils.toInt(varValueMinSize)) || CheckUtils.isJustSize(
                value, NumberUtils.toInt(varValueMinSize)));
        }

        return result;
    }

    /**
     * 単項目バリデータの有効桁数チェックを行う。
     * <p>
     * 単項目バリデータの有効桁数チェックを行う。<br>
     * バリデーション定義ファイルのintegerSize、decimalSizeに定義された整数桁、小数桁で有効かどうかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateSignificantFigures(Object _bean, Field _field) throws ValidatorException {

        String varValueIntegerSize = _field.getVarValue("integerSize");
        if (CheckUtils.isEmpty(varValueIntegerSize) || !CheckUtils.isNumber(varValueIntegerSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "integerSize"));
        }
        String varValueDecimalSize = _field.getVarValue("decimalSize");
        if (CheckUtils.isEmpty(varValueDecimalSize) || !CheckUtils.isNumber(varValueDecimalSize)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "decimalSize"));
        }

        String value = NumberUtils.abs(ValidatorUtils.getValueAsString(_bean, _field.getProperty())).toString();

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.isSignificantFigures(value, NumberUtils.toInt(varValueIntegerSize),
                NumberUtils.toInt(varValueDecimalSize));
        }

        return result;
    }

    /**
     * 単項目バリデータの最大値チェックを行う。
     * <p>
     * 単項目バリデータの最大値チェックを行う。 バリデーション定義ファイルのmaxValueに定義された最大値以下かをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMaxValue(Object _bean, Field _field) throws ValidatorException {

        String varValueMaxValue = _field.getVarValue("maxValue");
        if (CheckUtils.isEmpty(varValueMaxValue) || !CheckUtils.isNumber(varValueMaxValue)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "maxValue"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            if (!CheckUtils.isNumber(value)) {
                result = false;
            } else {
                BigDecimal bdecValue = NumberUtils.toBigDecimal(value);
                BigDecimal bdecMaxValue = NumberUtils.toBigDecimal(varValueMaxValue);

                if (bdecValue.compareTo(bdecMaxValue) == 1) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの最小値チェックを行う。
     * <p>
     * 単項目バリデータの最小値チェックを行う。 バリデーション定義ファイルのminValueに定義された最小値以上かをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMinValue(Object _bean, Field _field) throws ValidatorException {

        String varValueMinValue = _field.getVarValue("minValue");
        if (CheckUtils.isEmpty(varValueMinValue) || !CheckUtils.isNumber(varValueMinValue)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "minValue"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            if (!CheckUtils.isNumber(value)) {
                result = false;
            } else {
                BigDecimal bdecValue = NumberUtils.toBigDecimal(value);
                BigDecimal bdecMinValue = NumberUtils.toBigDecimal(varValueMinValue);

                if (bdecValue.compareTo(bdecMinValue) == -1) {
                    result = false;
                }
            }
        }

        return result;
    }

    /**
     * 日付項目の関連チェックを行う。
     * <p>
     * 2つの日付項目間での関連チェックを行う。<br>
     * 同一コントロールオブジェクト内の項目間のみチェック可能。ヘッダ部と明細部などは行えない。<br>
     * 指定可能な比較内容を以下に示す。<br>
     * <br>
     * <table border=1 cellspacing=3 cellpadding=0 summary="">
     * <tr bgcolor="#ccccff">
     * <td>ジェネレータの記号</td>
     * <td>validation-configの設定</td>
     * <td>比較演算子</td>
     * </tr>
     * <tr>
     * <td>＜</td>
     * <td>lt</td>
     * <td>&lt;</td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td>≦</td>
     * <td>le</td>
     * <td>&lt;=</td>
     * </tr>
     * <tr>
     * <td>＝</td>
     * <td>eq</td>
     * <td>==</td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td>≠</td>
     * <td>ne</td>
     * <td>!=</td>
     * </tr>
     * <tr>
     * <td>≧</td>
     * <td>ge</td>
     * <td>&gt;=</td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td>＞</td>
     * <td>gt</td>
     * <td>&gt;</td>
     * </tr>
     * </table>
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     * @since 1.0
     */
    public boolean validateCompareDate(Object _bean, Field _field) throws ValidatorException {

        String varValueDateFormat = _field.getVarValue("dateFormat");
        if (CheckUtils.isEmpty(varValueDateFormat)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "dateFormat"));
        }

        String varValueCompareProperty = _field.getVarValue("compareProperty");
        if (CheckUtils.isEmpty(varValueCompareProperty)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "compareProperty"));
        }

        String varValueCompareOperator = _field.getVarValue("compareOperator");
        if (CheckUtils.isEmpty(varValueCompareOperator)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "compareOperator"));
        }
        int index = StringUtils.lastIndexOf(_field.getProperty(), ".");
        if (index >= 0) {
            String varValueComparePropertyIndexed = _field.getVarValue("comparePropertyIndexed");
            if (!CheckUtils.isEmpty(varValueComparePropertyIndexed)
                            && Boolean.parseBoolean(varValueComparePropertyIndexed)) {
                varValueCompareProperty = StringUtils.substring(_field.getProperty(), 0, index + 1)
                                + varValueCompareProperty;
            }
        }

        boolean result = true;

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        String compareValue = ValidatorUtils.getValueAsString(_bean, varValueCompareProperty);

        if (!CheckUtils.isEmpty(value) && !CheckUtils.isEmpty(compareValue)) {
            try {
                Date dateValue = DateUtils.parse(value, varValueDateFormat);
                Date dateCompareValue = DateUtils.parse(compareValue, varValueDateFormat);

                if (CheckUtils.isEqual(varValueCompareOperator, "lt") && dateValue.compareTo(dateCompareValue) > -1) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "le")
                                && dateValue.compareTo(dateCompareValue) > 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "eq")
                                && dateValue.compareTo(dateCompareValue) != 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "ne")
                                && dateValue.compareTo(dateCompareValue) == 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "ge")
                                && dateValue.compareTo(dateCompareValue) < 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "gt")
                                && dateValue.compareTo(dateCompareValue) < 1) {
                    result = false;
                }

            } catch (SystemException e) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 数値項目の関連チェックを行う。
     * <p>
     * 2つの数値項目間での関連チェックを行う。<br>
     * 同一コントロールオブジェクト内の項目間のみチェック可能。ヘッダ部と明細部などは行えない。<br>
     * 指定可能な比較内容は {@link FieldChecks#validateCompareDate(Object, Field)} と同様。<br>
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     * @since 1.0
     */
    public boolean validateCompareDecimal(Object _bean, Field _field) throws ValidatorException {

        String varValueCompareProperty = _field.getVarValue("compareProperty");
        if (CheckUtils.isEmpty(varValueCompareProperty)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "compareProperty"));
        }

        String varValueCompareOperator = _field.getVarValue("compareOperator");
        if (CheckUtils.isEmpty(varValueCompareOperator)) {
            throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
                _field.getProperty(), "compareOperator"));
        }
        int index = StringUtils.lastIndexOf(_field.getProperty(), ".");
        if (index >= 0) {
            String varValueComparePropertyIndexed = _field.getVarValue("comparePropertyIndexed");
            if (!CheckUtils.isEmpty(varValueComparePropertyIndexed)
                            && Boolean.parseBoolean(varValueComparePropertyIndexed)) {
                varValueCompareProperty = StringUtils.substring(_field.getProperty(), 0, index + 1)
                                + varValueCompareProperty;
            }
        }

        boolean result = true;

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());
        String compareValue = ValidatorUtils.getValueAsString(_bean, varValueCompareProperty);

        if (!CheckUtils.isEmpty(value) && !CheckUtils.isEmpty(compareValue)) {
            try {
                BigDecimal decimalValue = NumberUtils.toBigDecimal(value);
                BigDecimal decimalCompareValue = NumberUtils.toBigDecimal(compareValue);

                if (CheckUtils.isEqual(varValueCompareOperator, "lt")
                                && decimalValue.compareTo(decimalCompareValue) > -1) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "le")
                                && decimalValue.compareTo(decimalCompareValue) > 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "eq")
                                && decimalValue.compareTo(decimalCompareValue) != 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "ne")
                                && decimalValue.compareTo(decimalCompareValue) == 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "ge")
                                && decimalValue.compareTo(decimalCompareValue) < 0) {
                    result = false;

                } else if (CheckUtils.isEqual(varValueCompareOperator, "gt")
                                && decimalValue.compareTo(decimalCompareValue) < 1) {
                    result = false;
                }

            } catch (NumberFormatException e) {
                result = false;
            }
        }

        return result;
    }

    /**
     * 単項目バリデータの電話番号チェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateTell(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            varValueRegExp = "^\\d[\\d-]*\\d$";
            // throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
            // _field.getProperty(), "regExp"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータの携帯電話番号チェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateMobile(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            varValueRegExp = "^\\d{2,4}-\\d{2,4}-\\d{4}$";
            // throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
            // _field.getProperty(), "regExp"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータの郵便番号チェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validatePost(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            varValueRegExp = "^[0-9]{3}-?[0-9]{4}$";
            // throw new ValidatorException(ValidatorMessagesHandler.create().getSimpleMessage("E0000004",
            // _field.getProperty(), "regExp"));
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータの団地IDチェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateDanchiId(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            varValueRegExp = "^(([a-zA-Z0-9]){1}([0-9]){2}).?(([a-zA-Z0-9]){1}([0-9]){2})";
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータのブロックIDチェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateBlockId(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            varValueRegExp = "^(([a-zA-Z0-9]){1}([0-9]){2}).?(([a-zA-Z0-9]){1}([0-9]){2})";
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }

    /**
     * 単項目バリデータのブロックIDチェックを行う。
     * <p>
     * 単項目バリデータの正規表現チェックを行う。 <br>
     * バリデーション定義ファイルのregExpに定義された正規表現にマッチするかをチェックする。
     * </p>
     * 
     * @param _bean バリデーション対象のプロパティを持つJavaBeans
     * @param _field 検査オブジェクト
     * @return 検査結果の真偽値
     * @throws ValidatorException 検査実行時に例外が発生した場合スロー
     */
    public boolean validateJyutakuBango(Object _bean, Field _field) throws ValidatorException {

        String varValueRegExp = _field.getVarValue("regExp");
        if (CheckUtils.isEmpty(varValueRegExp)) {
            varValueRegExp = "^([a-zA-Z0-9]){1}([0-9]){2}";
        }

        String value = ValidatorUtils.getValueAsString(_bean, _field.getProperty());

        boolean result = true;
        if (!CheckUtils.isEmpty(value)) {
            result = CheckUtils.matchRegexp(value, varValueRegExp);
        }

        return result;
    }
}
