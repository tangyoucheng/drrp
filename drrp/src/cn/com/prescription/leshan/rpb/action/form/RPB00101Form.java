/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpb.action.form;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 处方一览 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPB00101Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 门店ID
     */
    private String storeId = null;

    /**
     * 门店名
     */
    private String storeName = null;

    /**
     * 处方单前缀
     */
    private String rpCodePrefix = null;

    /**
     * 备注
     */
    private String notes = null;

    /**
     * 最终更新日期
     */
    private String lastUpdateDate = null;

    /**
     * 页面类型
     */
    private String pageType = null;

    /**
     * 二维码图片变更前
     */
    private String oldQrCodeImage;

    /**
     * 二维码图片变更前
     */
    private String newQrCodeImage;
    /**
     * 二维码变更后session
     */
    private List<CodeValueRecord> sessionImageDataSource;

    /**
     * 门店ID的取得
     * 
     * @return 门店ID
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * 门店ID的设定
     * 
     * @param __storeId 门店ID
     */
    public void setStoreId(String _storeId) {
        this.storeId = _storeId;
    }

    /**
     * 门店名的取得
     * 
     * @return 门店名
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 门店名的设定
     * 
     * @param __storeName 门店名
     */
    public void setStoreName(String _storeName) {
        this.storeName = _storeName;
    }

    /**
     * 处方单前缀的取得
     * 
     * @return 处方单前缀
     */
    public String getRpCodePrefix() {
        return rpCodePrefix;
    }

    /**
     * 处方单前缀的设定
     * 
     * @param __rpCodePrefix 处方单前缀
     */
    public void setRpCodePrefix(String _rpCodePrefix) {
        this.rpCodePrefix = _rpCodePrefix;
    }

    /**
     * 备注的取得
     * 
     * @return 备注
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 备注的设定
     * 
     * @param __notes 备注
     */
    public void setNotes(String _notes) {
        this.notes = _notes;
    }

    /**
     * 最终更新日期的取得
     * 
     * @return 最终更新日期
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * 最终更新日期的设定
     * 
     * @param __lastUpdateDate 最终更新日期
     */
    public void setLastUpdateDate(String _lastUpdateDate) {
        this.lastUpdateDate = _lastUpdateDate;
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
     * @param __pageType 页面类型
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

    /**
     * 二维码图片变更前的取得
     * 
     * @return 二维码图片变更前
     */
    public String getOldQrCodeImage() {
        return oldQrCodeImage;
    }

    /**
     * 二维码图片变更前的设定
     * 
     * @param _oldQrCodeImage 二维码图片变更前
     */
    public void setOldQrCodeImage(String _oldQrCodeImage) {
        this.oldQrCodeImage = _oldQrCodeImage;
    }

    /**
     * 二维码图片变更前的取得
     * 
     * @return 二维码图片变更前
     */
    public String getNewQrCodeImage() {
        return newQrCodeImage;
    }

    /**
     * 二维码图片变更前的设定
     * 
     * @param __newQrCodeImage 二维码图片变更前
     */
    public void setNewQrCodeImage(String _newQrCodeImage) {
        this.newQrCodeImage = _newQrCodeImage;
    }

    /**
     * 二维码变更后session的取得
     * 
     * @return 二维码变更后session
     */
    public List<CodeValueRecord> getSessionImageDataSource() {
        return sessionImageDataSource;
    }

    /**
     * 二维码变更后session的设定
     * 
     * @param __sessionImageDataSource 二维码变更后session
     */
    public void setSessionImageDataSource(List<CodeValueRecord> _sessionImageDataSource) {
        this.sessionImageDataSource = _sessionImageDataSource;
    }

}
