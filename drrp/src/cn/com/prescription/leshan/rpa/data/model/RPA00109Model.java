/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.data.model;

import java.math.BigDecimal;

import cn.com.prescription.leshan.common.data.model.RpmPrescriptionModel;

/**
 * 处方统计检索画面のモードル
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00109Model extends RpmPrescriptionModel {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 最大年
     */
    private int maxYear = 0;

    /**
     * 最小年
     */
    private int minYear = 0;

    /**
     * 一月件数
     */
    private BigDecimal janCount = null;

    /**
     * 二月件数
     */
    private BigDecimal febCount = null;

    /**
     * 三月件数
     */
    private BigDecimal marCount = null;

    /**
     * 一季度件数
     */
    private BigDecimal firstQuarterCount = null;

    /**
     * 四月件数
     */
    private BigDecimal aprCount = null;

    /**
     * 五月件数
     */
    private BigDecimal mayCount = null;

    /**
     * 六月件数
     */
    private BigDecimal juneCount = null;

    /**
     * 二季度件数
     */
    private BigDecimal secondQuarterCount = null;

    /**
     * 七月件数
     */
    private BigDecimal julyCount = null;

    /**
     * 八月件数
     */
    private BigDecimal augCount = null;

    /**
     * 九月件数
     */
    private BigDecimal sepCount = null;

    /**
     * 三季度件数
     */
    private BigDecimal thirdQuarterCount = null;

    /**
     * 十月件数
     */
    private BigDecimal octCount = null;

    /**
     * 十一月件数
     */
    private BigDecimal novCount = null;

    /**
     * 十二月件数
     */
    private BigDecimal decCount = null;

    /**
     * 四季度件数
     */
    private BigDecimal fourthQuarterCount = null;

    /**
     * 年度件数
     */
    private BigDecimal yearCount = null;

    /**
     * ログイン画面モデル 的构造。
     */
    public RPA00109Model() {
        super();
    }

    /**
     * 最大年的取得
     * 
     * @return 最大年
     */
    public int getMaxYear() {
        return maxYear;
    }

    /**
     * 最大年的设定
     * 
     * @param _maxYear 最大年
     */
    public void setMaxYear(int _maxYear) {
        this.maxYear = _maxYear;
    }

    /**
     * 最小年的取得
     * 
     * @return 最小年
     */
    public int getMinYear() {
        return minYear;
    }

    /**
     * 最小年的设定
     * 
     * @param _minYear 最小年
     */
    public void setMinYear(int _minYear) {
        this.minYear = _minYear;
    }

    /**
     * 一月件数的取得
     * 
     * @return 一月件数
     */
    public BigDecimal getJanCount() {
        return janCount;
    }

    /**
     * 一月件数的设定
     * 
     * @param __janCount 一月件数
     */
    public void setJanCount(BigDecimal _janCount) {
        this.janCount = _janCount;
    }

    /**
     * 二月件数的取得
     * 
     * @return 二月件数
     */
    public BigDecimal getFebCount() {
        return febCount;
    }

    /**
     * 二月件数的设定
     * 
     * @param __febCount 二月件数
     */
    public void setFebCount(BigDecimal _febCount) {
        this.febCount = _febCount;
    }

    /**
     * 三月件数的取得
     * 
     * @return 三月件数
     */
    public BigDecimal getMarCount() {
        return marCount;
    }

    /**
     * 三月件数的设定
     * 
     * @param __marCount 三月件数
     */
    public void setMarCount(BigDecimal _marCount) {
        this.marCount = _marCount;
    }

    /**
     * 一季度件数的取得
     * 
     * @return 一季度件数
     */
    public BigDecimal getFirstQuarterCount() {
        return firstQuarterCount;
    }

    /**
     * 一季度件数的设定
     * 
     * @param __firstQuarterCount 一季度件数
     */
    public void setFirstQuarterCount(BigDecimal _firstQuarterCount) {
        this.firstQuarterCount = _firstQuarterCount;
    }

    /**
     * 四月件数的取得
     * 
     * @return 四月件数
     */
    public BigDecimal getAprCount() {
        return aprCount;
    }

    /**
     * 四月件数的设定
     * 
     * @param __aprCount 四月件数
     */
    public void setAprCount(BigDecimal _aprCount) {
        this.aprCount = _aprCount;
    }

    /**
     * 五月件数的取得
     * 
     * @return 五月件数
     */
    public BigDecimal getMayCount() {
        return mayCount;
    }

    /**
     * 五月件数的设定
     * 
     * @param __mayCount 五月件数
     */
    public void setMayCount(BigDecimal _mayCount) {
        this.mayCount = _mayCount;
    }

    /**
     * 六月件数的取得
     * 
     * @return 六月件数
     */
    public BigDecimal getJuneCount() {
        return juneCount;
    }

    /**
     * 六月件数的设定
     * 
     * @param __juneCount 六月件数
     */
    public void setJuneCount(BigDecimal _juneCount) {
        this.juneCount = _juneCount;
    }

    /**
     * 二季度件数的取得
     * 
     * @return 二季度件数
     */
    public BigDecimal getSecondQuarterCount() {
        return secondQuarterCount;
    }

    /**
     * 二季度件数的设定
     * 
     * @param __secondQuarterCount 二季度件数
     */
    public void setSecondQuarterCount(BigDecimal _secondQuarterCount) {
        this.secondQuarterCount = _secondQuarterCount;
    }

    /**
     * 七月件数的取得
     * 
     * @return 七月件数
     */
    public BigDecimal getJulyCount() {
        return julyCount;
    }

    /**
     * 七月件数的设定
     * 
     * @param __julyCount 七月件数
     */
    public void setJulyCount(BigDecimal _julyCount) {
        this.julyCount = _julyCount;
    }

    /**
     * 八月件数的取得
     * 
     * @return 八月件数
     */
    public BigDecimal getAugCount() {
        return augCount;
    }

    /**
     * 八月件数的设定
     * 
     * @param __augCount 八月件数
     */
    public void setAugCount(BigDecimal _augCount) {
        this.augCount = _augCount;
    }

    /**
     * 九月件数的取得
     * 
     * @return 九月件数
     */
    public BigDecimal getSepCount() {
        return sepCount;
    }

    /**
     * 九月件数的设定
     * 
     * @param __sepCount 九月件数
     */
    public void setSepCount(BigDecimal _sepCount) {
        this.sepCount = _sepCount;
    }

    /**
     * 三季度件数的取得
     * 
     * @return 三季度件数
     */
    public BigDecimal getThirdQuarterCount() {
        return thirdQuarterCount;
    }

    /**
     * 三季度件数的设定
     * 
     * @param __thirdQuarterCount 三季度件数
     */
    public void setThirdQuarterCount(BigDecimal _thirdQuarterCount) {
        this.thirdQuarterCount = _thirdQuarterCount;
    }

    /**
     * 十月件数的取得
     * 
     * @return 十月件数
     */
    public BigDecimal getOctCount() {
        return octCount;
    }

    /**
     * 十月件数的设定
     * 
     * @param __octCount 十月件数
     */
    public void setOctCount(BigDecimal _octCount) {
        this.octCount = _octCount;
    }

    /**
     * 十一月件数的取得
     * 
     * @return 十一月件数
     */
    public BigDecimal getNovCount() {
        return novCount;
    }

    /**
     * 十一月件数的设定
     * 
     * @param __novCount 十一月件数
     */
    public void setNovCount(BigDecimal _novCount) {
        this.novCount = _novCount;
    }

    /**
     * 十二月件数的取得
     * 
     * @return 十二月件数
     */
    public BigDecimal getDecCount() {
        return decCount;
    }

    /**
     * 十二月件数的设定
     * 
     * @param __decCount 十二月件数
     */
    public void setDecCount(BigDecimal _decCount) {
        this.decCount = _decCount;
    }

    /**
     * 四季度件数的取得
     * 
     * @return 四季度件数
     */
    public BigDecimal getFourthQuarterCount() {
        return fourthQuarterCount;
    }

    /**
     * 四季度件数的设定
     * 
     * @param __fourthQuarterCount 四季度件数
     */
    public void setFourthQuarterCount(BigDecimal _fourthQuarterCount) {
        this.fourthQuarterCount = _fourthQuarterCount;
    }

    /**
     * 年度件数的取得
     * 
     * @return 年度件数
     */
    public BigDecimal getYearCount() {
        return yearCount;
    }

    /**
     * 年度件数的设定
     * 
     * @param __yearCount 年度件数
     */
    public void setYearCount(BigDecimal _yearCount) {
        this.yearCount = _yearCount;
    }

}
