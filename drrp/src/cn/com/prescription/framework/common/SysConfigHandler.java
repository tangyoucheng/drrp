/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common;

import java.util.Locale;

import cn.com.prescription.framework.common.resource.ResourceHandler;

/**
 * 系统設定情報
 * 
 * @author nttdc
 */
public class SysConfigHandler extends ResourceHandler {

    /** 串行版本号 */
    private static final long serialVersionUID = -3142544888171919226L;

    /** デフォルトロケール：唯一のインスタンス */
    private static final SysConfigHandler instance = new SysConfigHandler();

    /**
     * FrameworkMessageResourceHandler 的构造。
     */
    private SysConfigHandler() {
        super();
    }

    /**
     * FrameworkMessageResourceHandler 的构造。
     * 
     * @param _locale ロケール
     */
    private SysConfigHandler(Locale _locale) {
        super(_locale);
    }

    /**
     * MessagesHandler 取得する。
     * 
     * @return MessagesHandler
     */
    public static SysConfigHandler create() {
        return instance;
    }

    /**
     * 指定されたロケールで MessagesHandler 取得する。
     * 
     * @param _locale ロケール
     * @return MessagesHandler
     */
    public static SysConfigHandler create(Locale _locale) {
        return new SysConfigHandler(_locale);
    }

    /**
     * configファイルの設定を行う。
     */
    @Override
    public void setConfig() {
        setConfig("sys_config");
    }

}
