/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 推广信息添加添加dto。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM00501Form extends AbstractForm {

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
     * 推广信息url
     */
    private String adUrl = null;
    
    /**
     * 推广信息状态
     */
    private String adStatus = null;

    /**
     * 是否选中
     */
    private boolean checkedFlag = false;
    
    /**
     * 推广信息备注
     */
    private String notes = null;

    /**
     * 推广信息IDを取得します。
     * @return 推广信息ID
     */
    public String getAdId() {
        return adId;
    }

    /**
     * 推广信息IDを設定します。
     * @param __adId 推广信息ID
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
     * @param __adName 推广信息名
     */
    public void setAdName(String _adName) {
        this.adName = _adName;
    }

    /**
     * 推广信息urlを取得します。
     * @return 推广信息url
     */
    public String getAdUrl() {
        return adUrl;
    }

    /**
     * 推广信息urlを設定します。
     * @param __adUrl 推广信息url
     */
    public void setAdUrl(String _adUrl) {
        this.adUrl = _adUrl;
    }

    /**
     * 推广信息状态を取得します。
     * @return 推广信息状态
     */
    public String getAdStatus() {
        return adStatus;
    }

    /**
     * 推广信息状态を設定します。
     * @param __adStatus 推广信息状态
     */
    public void setAdStatus(String _adStatus) {
        this.adStatus = _adStatus;
    }

    /**
     * 是否选中を取得します。
     * @return 是否选中
     */
    public boolean isCheckedFlag() {
        return checkedFlag;
    }

    /**
     * 是否选中を設定します。
     * @param _checkedFlag 是否选中
     */
    public void setCheckedFlag(boolean _checkedFlag) {
        this.checkedFlag = _checkedFlag;
    }

    /**
     * 推广信息备注を取得します。
     * @return 推广信息备注
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 推广信息备注を設定します。
     * @param _notes 推广信息备注
     */
    public void setNotes(String _notes) {
        this.notes = _notes;
    }

}
