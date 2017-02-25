/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import cn.com.prescription.framework.util.CheckUtils;

/**
 * メッセージリソース操作の抽象クラス。
 * <p>
 * メッセージリソース操作を行う抽象クラス。メッセージリソースごとに実装クラスを生成する。<br>
 * </p>
 * 
 * @author t.d.m
 */
public abstract class ResourceHandler {

    /**
     * configファイル
     */
    private String config = null;

    /**
     * フィールド名を記述
     */
    private Locale locale = Locale.getDefault();

    /**
     * ResourceHandler 的构造。
     */
    public ResourceHandler() {
        this.locale = Locale.getDefault();
        this.setConfig();
    }

    /**
     * ResourceHandler 的构造。
     * 
     * @param _locale ロケール
     */
    public ResourceHandler(Locale _locale) {
        this.locale = _locale;
        this.setConfig();
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
        java.util.ResourceBundle bundle = ResourceBundle.getBundle(config, locale);
        return bundle.getString(_messageKey);
    }

    /**
     * configファイルの設定を行う。
     * <p>
     * configファイルの設定を行う。実装クラスにてconfigファイルの設定を実装すること。
     * </p>
     */
    public abstract void setConfig();

    /**
     * configファイルの設定を行う。
     * 
     * @param _config configファイル
     */
    public void setConfig(String _config) {
        this.config = _config;
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
