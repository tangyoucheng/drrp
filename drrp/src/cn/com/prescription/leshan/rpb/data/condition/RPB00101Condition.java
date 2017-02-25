/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpb.data.condition;

import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;

/**
 * 处方情报检索画面のコンディション
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPB00101Condition extends RpmPatientCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方创建日
     */
    private String prescriptionCreateDate = null;

    /**
     * 用户情報設定画面Condition的构造
     */
    public RPB00101Condition() {
        super();
    }

    /**
     * 处方创建日的取得
     * 
     * @return 处方创建日
     */
    public String getPrescriptionCreateDate() {
        return prescriptionCreateDate;
    }

    /**
     * 处方创建日的设定
     * 
     * @param _prescriptionCreateDate 处方创建日
     */
    public void setPrescriptionCreateDate(String _prescriptionCreateDate) {
        this.prescriptionCreateDate = _prescriptionCreateDate;
    }

}