/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpb.data.model;

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
public class RPB00101Model extends RpmPatientModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方ID
     */
    private String prescriptionId = null;

    /**
     * 处方创建日
     */
    private Timestamp createDatePrescription = null;

    /**
     * ログイン画面モデル 的构造。
     */
    public RPB00101Model() {
        super();
    }

    /**
     * 处方ID的取得
     * @return 处方ID
     */
    public String getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * 处方ID的设定
     * @param __prescriptionId 处方ID
     */
    public void setPrescriptionId(String _prescriptionId) {
        this.prescriptionId = _prescriptionId;
    }

    /**
     * 处方创建日的取得
     * @return 处方创建日
     */
    public Timestamp getCreateDatePrescription() {
        return createDatePrescription;
    }

    /**
     * 处方创建日的设定
     * @param _createDatePrescription 处方创建日
     */
    public void setCreateDatePrescription(Timestamp _createDatePrescription) {
        this.createDatePrescription = _createDatePrescription;
    }

}
