/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * SCZ9901Form メイン画面form。
 * 
 * @author kourei
 */
/*
 * 新規作成 DATE: 2010.03.10 NAME: kourei 更新 DATE: $Date:: 0000-00-00 00:00:00 $ NAME: $Author$
 */
public class SCZ9901Form extends AbstractForm {

    private static final long serialVersionUID = 1L;

    /**
     * ページカラーパターン
     */
    private String pageColorPattern = null;

    /**
     * 推广信息URL
     */
    private String adUrl = null;

    /**
     * 登录form 的构造。
     */
    public SCZ9901Form() {
        super();
    }

    /**
     * ページカラーパターン的取得
     * @return ページカラーパターン
     */
    public String getPageColorPattern() {
        return pageColorPattern;
    }

    /**
     * ページカラーパターン的设定
     * @param __pageColorPattern ページカラーパターン
     */
    public void setPageColorPattern(String _pageColorPattern) {
        this.pageColorPattern = _pageColorPattern;
    }

    /**
     * 推广信息URL的取得
     * @return 推广信息URL
     */
    public String getAdUrl() {
        return adUrl;
    }

    /**
     * 推广信息URL的设定
     * @param _adUrl 推广信息URL
     */
    public void setAdUrl(String _adUrl) {
        this.adUrl = _adUrl;
    }
}
