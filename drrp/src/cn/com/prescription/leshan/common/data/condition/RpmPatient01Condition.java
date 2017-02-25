/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */

package cn.com.prescription.leshan.common.data.condition;

/**
 * 患者基本信息表Condition。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmPatient01Condition extends RpmPatientCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方ID
     */
    private String prescriptionId = null;

    /**
     * 患者基本信息表Condition 的构造。
     */
    public RpmPatient01Condition() {
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
     * @param _prescriptionId 处方ID
     */
    public void setPrescriptionId(String _prescriptionId) {
        this.prescriptionId = _prescriptionId;
    }

}
