/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.action.validator;

import java.util.Locale;

import cn.com.prescription.framework.common.resource.ResourceHandler;

/**
 * バリデータメッセージリソース操作クラス。
 * 
 * @author t.d.m
 */
public final class ValidatorResourceHandler extends ResourceHandler {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = -4571389565563895536L;

    /**
     * ValidatorResourceHandler 的构造。
     */
    private ValidatorResourceHandler() {
        super();
    }

    /**
     * ValidatorResourceHandler 的构造。
     * 
     * @param _locale ロケール
     */
    private ValidatorResourceHandler(Locale _locale) {
        super(_locale);
    }

    /**
     * ValidatorResourceHandler 取得する。
     * 
     * @return ValidatorResourceHandler
     */
    public static ValidatorResourceHandler create() {
        return new ValidatorResourceHandler();
    }

    /**
     * ValidatorResourceHandler 取得する。
     * 
     * @param _locale ロケール
     * @return ValidatorResourceHandler
     */
    public static ValidatorResourceHandler create(Locale _locale) {
        return new ValidatorResourceHandler(_locale);
    }

    /**
     * configファイルの設定を行う。
     */
    @Override
    public void setConfig() {
        setConfig("cn.com.prescription.framework.action.validator.i18n");
    }

}
