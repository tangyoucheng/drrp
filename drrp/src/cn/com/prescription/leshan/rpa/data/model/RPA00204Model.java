/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.data.model;

import java.sql.Timestamp;

import cn.com.prescription.leshan.common.data.model.RpmPatientModel;

/**
 * 患者情报检索画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00204Model extends RpmPatientModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方创建日
     */
    private Timestamp createDatePrescription = null;

    /**
     * 处方种类
     */
    private String prescriptionType = null;

    /**
     * 处方内容
     */
    private String contents = null;

    /**
     * 处方图片信息
     */
    private String fileContents = null;

    /**
     * 处方金额
     */
    private String price = null;

    /**
     * ログイン画面モデル 的构造。
     */
    public RPA00204Model() {
        super();
    }

    /**
     * 处方创建日的取得
     * 
     * @return 处方创建日
     */
    public Timestamp getCreateDatePrescription() {
        return createDatePrescription;
    }

    /**
     * 处方创建日的设定
     * 
     * @param __createDatePrescription 处方创建日
     */
    public void setCreateDatePrescription(Timestamp _createDatePrescription) {
        this.createDatePrescription = _createDatePrescription;
    }

    /**
     * 处方种类的取得
     * 
     * @return 处方种类
     */
    public String getPrescriptionType() {
        return prescriptionType;
    }

    /**
     * 处方种类的设定
     * 
     * @param _prescriptionType 处方种类
     */
    public void setPrescriptionType(String _prescriptionType) {
        this.prescriptionType = _prescriptionType;
    }

    /**
     * 处方内容的取得
     * 
     * @return 处方内容
     */
    public String getContents() {
        return contents;
    }

    /**
     * 处方内容的设定
     * 
     * @param _contents 处方内容
     */
    public void setContents(String _contents) {
        this.contents = _contents;
    }

    /**
     * 处方图片信息的取得
     * 
     * @return 处方图片信息
     */
    public String getFileContents() {
        return fileContents;
    }

    /**
     * 处方图片信息的设定
     * 
     * @param _fileContents 处方图片信息
     */
    public void setFileContents(String _fileContents) {
        this.fileContents = _fileContents;
    }

    /**
     * 处方金额的取得
     * @return 处方金额
     */
    public String getPrice() {
        return price;
    }

    /**
     * 处方金额的设定
     * @param _price 处方金额
     */
    public void setPrice(String _price) {
        this.price = _price;
    }

}
