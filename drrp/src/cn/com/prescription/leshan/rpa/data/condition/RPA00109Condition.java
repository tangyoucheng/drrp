/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.data.condition;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.leshan.common.data.condition.RpmPrescriptionCondition;

/**
 * 处方统计检索画面のコンディション
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00109Condition extends RpmPrescriptionCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方年度
     */
    private String prescriptionYear;
    /**
     * 处方状态
     */
    private List<String> prescriptionStatusList = new ArrayList<String>();

    /**
     * 用户情報設定画面Condition的构造
     */
    public RPA00109Condition() {
        super();
    }

    /**
     * 处方年度的取得
     * @return 处方年度
     */
    public String getPrescriptionYear() {
        return prescriptionYear;
    }

    /**
     * 处方年度的设定
     * @param _prescriptionYear 处方年度
     */
    public void setPrescriptionYear(String _prescriptionYear) {
        this.prescriptionYear = _prescriptionYear;
    }

    /**
     * 处方状态的取得
     * @return 处方状态
     */
    public List<String> getPrescriptionStatusList() {
        return prescriptionStatusList;
    }

    /**
     * 处方状态的设定
     * @param __prescriptionStatusList 处方状态
     */
    public void setPrescriptionStatusList(List<String> _prescriptionStatusList) {
        this.prescriptionStatusList = _prescriptionStatusList;
    }

}