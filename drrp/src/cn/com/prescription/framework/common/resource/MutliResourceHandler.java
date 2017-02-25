/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.resource;

import java.text.MessageFormat;
import java.util.Locale;

import cn.com.prescription.framework.util.CheckUtils;

/**
 * メッセージリソース操作の抽象クラス。
 * <p>
 * メッセージリソース操作を行う抽象クラス。メッセージリソースごとに実装クラスを生成する。<br>
 * </p>
 * 
 * @author t.d.m
 */
public abstract class MutliResourceHandler {

    /**
     * configファイル
     */
    private String[] config = null;

    /**
     * メッセージ
     */
    CombinedResourceBundle bundle = null;

    /**
     * フィールド名を記述
     */
    private Locale locale = Locale.getDefault();

    /**
     * ResourceHandler 的构造。
     */
    public MutliResourceHandler() {
        this.locale = Locale.getDefault();
        // this.setConfig();
    }

    /**
     * ResourceHandler 的构造。
     * 
     * @param _locale ロケール
     */
    public MutliResourceHandler(Locale _locale) {
        this.locale = _locale;
    }

    /**
     * 指定されたメッセージキーと置換パラメータからメッセージキー付きのメッセージ的取得。
     * <p>
     * 指定されたメッセージキーと置換パラメータからメッセージキー付きのメッセージ的取得。<br>
     * フォーマット: [メッセージキ－]メッセージ内容
     * </p>
     * 
     * @param _messageKey メッセージキー
     * @param _params 置換パラメータ
     * @return メッセージキー付きのメッセージ
     */
    public String getMessage(String _messageKey, String... _params) {

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(_messageKey);
        builder.append("] ");
        builder.append(getSimpleMessage(_messageKey, _params));

        return builder.toString();
    }

    /**
     * 指定されたメッセージキーと置換パラメータからメッセージキーなしのメッセージ的取得。
     * <p>
     * 指定されたメッセージキーと置換パラメータからメッセージキーなしのメッセージ的取得。<br>
     * 指定されたメッセージキーに該当するメッセージが存在しなかった場合は、<br>
     * <code>#messagekey[locale]</code> が生成される。
     * </p>
     * 
     * @param _messageKey メッセージキー
     * @param _params 置換パラメータ
     * @return メッセージキーなしのメッセージ
     */
    public String getSimpleMessage(String _messageKey, String... _params) {
        String pattern = getPattern(_messageKey);
        if (!CheckUtils.isEmpty(pattern)) {
            return MessageFormat.format(pattern, (Object[]) _params);
        }
        // patternが取得できない場合
        StringBuilder builder = new StringBuilder();
        builder.append("#");
        builder.append(_messageKey);
        builder.append("[");
        builder.append(locale.toString());
        builder.append("]");

        if (_params.length > 0) {
            builder.append(" ");
            for (int i = 0; i < _params.length; i++) {
                builder.append(_params[i]);
                builder.append(", ");
            }
            builder.setLength(builder.length() - 2);
        }

        return builder.toString();
    }

    /**
     * configファイルからメッセージキーに該当するメッセージパターン的取得。
     * 
     * @param _messageKey メッセージキー
     * @return メッセージパターン
     */
    public String getPattern(String _messageKey) {
        return bundle.getString(_messageKey);
    }

    /**
     * configファイルの設定を行う。
     * 
     * @param _config configファイル
     */
    public void setConfig(String _config) {
        this.config = new String[1];
        this.config[0] = _config;
        bundle = new CombinedResourceBundle(config, locale);
    }

    /**
     * configファイルの設定を行う。
     * 
     * @param _config configファイル
     */
    public void setConfig(String[] _config) {
        this.config = new String[_config.length];
        System.arraycopy(_config, 0, this.config, 0, _config.length);
        bundle = new CombinedResourceBundle(config, locale);
    }

    /**
     * locale 的设定。
     * 
     * @param _locale locale
     */
    public void setLocale(Locale _locale) {
        this.locale = _locale;
    }
}
