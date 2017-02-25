/*
 * Copyright(c) 2016
 */

package cn.com.prescription.leshan.common.data.model;

/**
 * ?方?品一?信息表 モデルオブジェクト。
 *
 * @author gl
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
public class RpmPrescriptionDrug01Model extends RpmPrescriptionDrugModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 药品名称
     */
    private String drugName = null;

    /**
     * 厂商名称
     */
    private String manufacturerName = null;

    /**
     * 价格
     */
    private String price = null;

    /**
     * 单位
     */
    private String unit = null;

    /**
     * 药品名称的取得
     * 
     * @return 药品名称
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * 药品名称的设定
     * 
     * @param _drugName 药品名称
     */
    public void setDrugName(String _drugName) {
        this.drugName = _drugName;
    }

    /**
     * 厂商名称的取得
     * 
     * @return 厂商名称
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * 厂商名称的设定
     * 
     * @param _manufacturerName 厂商名称
     */
    public void setManufacturerName(String _manufacturerName) {
        this.manufacturerName = _manufacturerName;
    }

    /**
     * 价格的取得
     * 
     * @return 价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 价格的设定
     * 
     * @param _price 价格
     */
    public void setPrice(String _price) {
        this.price = _price;
    }

    /**
     * 单位的取得
     * 
     * @return 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 单位的设定
     * 
     * @param _unit 单位
     */
    public void setUnit(String _unit) {
        this.unit = _unit;
    }

}
