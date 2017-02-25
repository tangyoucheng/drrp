/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 处方手写录入 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00150Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 页面种类（编辑，只读）
     */
    private String pageType;
    /**
     * 页面种类（编辑，只读）的取得
     * @return 页面种类（编辑，只读）
     */
    public String getPageType() {
        return pageType;
    }
    /**
     * 页面种类（编辑，只读）的设定
     * @param _pageType 页面种类（编辑，只读）
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

}
