/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 药品一览 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00304Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 药品称
     */
    private String drugName = null;

    /**
     * 厂商名称
     */
    private String manufacturerName = null;

    /**
     * 页面类型
     */
    private String pageType = null;

    /**
     * subForm1
     */
    private List<RPA0030401Dto> subForm1 = new ArrayList<RPA0030401Dto>();

    /**
     * 药品称的取得
     * 
     * @return 药品称
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * 药品称的设定
     * 
     * @param _drugName 药品称
     */
    public void setDrugName(String _drugName) {
        this.drugName = _drugName;
    }

    /**
     * 厂商名称的取得
     * 
     * @return 厂商名称
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * 厂商名称的设定
     * 
     * @param _manufacturerName 厂商名称
     */
    public void setManufacturerName(String _manufacturerName) {
        this.manufacturerName = _manufacturerName;
    }

    /**
     * 页面类型的取得
     * 
     * @return 页面类型
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * 页面类型的设定
     * 
     * @param _pageType 页面类型
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

    /**
     * subForm1的取得
     * 
     * @return subForm1
     */
    public List<RPA0030401Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1的设定
     * 
     * @param _subForm1 subForm1
     */
    public void setSubForm1(List<RPA0030401Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }

}
