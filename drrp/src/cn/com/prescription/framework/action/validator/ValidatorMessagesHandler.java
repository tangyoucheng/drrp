/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.validator;

import java.util.Locale;

import cn.com.prescription.framework.common.resource.ResourceHandler;

/**
 * 基盤メッセージリソース操作クラス。
 * <p>
 * 基本入力チェック错误メッセージ的取得<br>
 * </p>
 * 
 * @author t.d.m
 */
public final class ValidatorMessagesHandler extends ResourceHandler {

    /**
     * 串行版本号
     * 
     * @since 1.0
     */
    private static final long serialVersionUID = -6343504888100997972L;

    /**
     * FrameworkMessageResourceHandler 的构造。
     * 
     * @since 1.0
     */
    private ValidatorMessagesHandler() {
        super();
    }

    /**
     * FrameworkMessageResourceHandler 的构造。
     * 
     * @param _locale ロケール
     * @since 1.0
     */
    private ValidatorMessagesHandler(Locale _locale) {
        super(_locale);
    }

    /**
     * ValidatorMessagesHandler 取得する。
     * 
     * @return ValidatorMessagesHandler
     */
    public static ValidatorMessagesHandler create() {
        return new ValidatorMessagesHandler();
    }

    /**
     * 指定されたロケールで ValidatorMessagesHandler 取得する。
     * 
     * @param _locale ロケール
     * @return ValidatorMessagesHandler
     */
    public static ValidatorMessagesHandler create(Locale _locale) {
        return new ValidatorMessagesHandler(_locale);
    }

    /**
     * configファイルの設定を行う。
     */
    @Override
    public void setConfig() {
        setConfig("cn.com.prescription.framework.action.validator.message");
    }

}
