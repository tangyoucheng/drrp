/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.data.condition;

import java.util.ArrayList;
import java.util.List;

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
public class RPA00102Condition extends RpmPatientCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方创建者
     */
    private String prescriptionCreateUserId = null;

    /**
     * 处方状态
     */
    private List<String> prescriptionStatus = new ArrayList<String>();

    /**
     * 用户情報設定画面Condition的构造
     */
    public RPA00102Condition() {
        super();
    }

    /**
     * 处方创建者的取得
     * @return 处方创建者
     */
    public String getPrescriptionCreateUserId() {
        return prescriptionCreateUserId;
    }

    /**
     * 处方创建者的设定
     * @param _prescriptionCreateUserId 处方创建者
     */
    public void setPrescriptionCreateUserId(String _prescriptionCreateUserId) {
        this.prescriptionCreateUserId = _prescriptionCreateUserId;
    }

    /**
     * 处方状态的取得
     * @return 处方状态
     */
    public List<String> getPrescriptionStatus() {
        return prescriptionStatus;
    }

    /**
     * 处方状态的设定
     * @param __prescriptionStatus 处方状态
     */
    public void setPrescriptionStatus(List<String> _prescriptionStatus) {
        this.prescriptionStatus = _prescriptionStatus;
    }

}