/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 角色一览form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM00502Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推广信息ID
     */
    private String adId = null;

    /**
     * 推广信息名
     */
    private String adName = null;

    /**
     * 页面类型
     */
    private String pageType = null;

    /**
     * subForm1
     */
    private List<RPM0050201Dto> subForm1 = new ArrayList<RPM0050201Dto>();

    /**
     * 推广信息IDを取得します。
     * @return 推广信息ID
     */
    public String getAdId() {
        return adId;
    }

    /**
     * 推广信息IDを設定します。
     * @param _adId 推广信息ID
     */
    public void setAdId(String _adId) {
        this.adId = _adId;
    }

    /**
     * 推广信息名を取得します。
     * @return 推广信息名
     */
    public String getAdName() {
        return adName;
    }

    /**
     * 推广信息名を設定します。
     * @param _adName 推广信息名
     */
    public void setAdName(String _adName) {
        this.adName = _adName;
    }

    /**
     * 页面类型を取得します。
     * @return 页面类型
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * 页面类型を設定します。
     * @param _pageType 页面类型
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

    /**
     * subForm1を取得します。
     * @return subForm1
     */
    public List<RPM0050201Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1を設定します。
     * @param _subForm1 subForm1
     */
    public void setSubForm1(List<RPM0050201Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }


}
