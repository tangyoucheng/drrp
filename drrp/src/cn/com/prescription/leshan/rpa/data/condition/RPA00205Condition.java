/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.data.condition;

import cn.com.prescription.leshan.common.data.condition.RpmPatientCondition;

/**
 * 患者情报检索画面のコンディション
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00205Condition extends RpmPatientCondition {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 导出项目
     */
    private String exportItems = null;

    /**
     * 用户情報設定画面Condition的构造
     */
    public RPA00205Condition() {
        super();
    }

    /**
     * 导出项目的取得
     * @return 导出项目
     */
    public String getExportItems() {
        return exportItems;
    }

    /**
     * 导出项目的设定
     * @param _exportItems 导出项目
     */
    public void setExportItems(String _exportItems) {
        this.exportItems = _exportItems;
    }
}