/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpa.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 打印患者条形码信息 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPA00206Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 用户名
     */
    private String userName = null;

    /**
     * 手机
     */
    private String ceelNumber = null;

    /**
     * 条形码
     */
    private String barcodeFilePath = null;

    /**
     * 二维码
     */
    private String qrcodeFilePath = null;

    /**
     * 用户ID的取得
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID的设定
     * @param __userId 用户ID
     */
    public void setUserId(String _userId) {
        this.userId = _userId;
    }

    /**
     * 用户名的取得
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名的设定
     * @param _userName 用户名
     */
    public void setUserName(String _userName) {
        this.userName = _userName;
    }

    /**
     * 手机的取得
     * @return 手机
     */
    public String getCeelNumber() {
        return ceelNumber;
    }

    /**
     * 手机的设定
     * @param __ceelNumber 手机
     */
    public void setCeelNumber(String _ceelNumber) {
        this.ceelNumber = _ceelNumber;
    }

    /**
     * 条形码的取得
     * @return 条形码
     */
    public String getBarcodeFilePath() {
        return barcodeFilePath;
    }

    /**
     * 条形码的设定
     * @param __barcodeFilePath 条形码
     */
    public void setBarcodeFilePath(String _barcodeFilePath) {
        this.barcodeFilePath = _barcodeFilePath;
    }

    /**
     * 二维码的取得
     * @return 二维码
     */
    public String getQrcodeFilePath() {
        return qrcodeFilePath;
    }

    /**
     * 二维码的设定
     * @param __qrcodeFilePath 二维码
     */
    public void setQrcodeFilePath(String _qrcodeFilePath) {
        this.qrcodeFilePath = _qrcodeFilePath;
    }

}
