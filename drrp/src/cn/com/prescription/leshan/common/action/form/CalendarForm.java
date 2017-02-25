/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.common.action.form;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 日历form
 * 
 * @author tyc
 */
public class CalendarForm extends AbstractForm {

    /** serialVersionUID */
    private static final long serialVersionUID = -290638759896761522L;

    /** 系统日付 */
    private String systemDate = null;

    /** 年の選択範囲 */
    private String yearRange = null;

    /** 日历最小値 */
    private String minDate = null;

    /**
     * CalendarForm的构造。
     */
    public CalendarForm() {
        super();
    }

    /**
     * 系统日付を取得します。
     * 
     * @return 系统日付
     */
    public String getSystemDate() {
        return systemDate;
    }

    /**
     * 系统日付を設定します。
     * 
     * @param systemDate 系统日付
     */
    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    /**
     * 年の選択範囲を取得する。
     * 
     * @return 年の選択範囲
     */
    public String getYearRange() {
        return yearRange;
    }

    /**
     * 年の選択範囲を設定する。
     * 
     * @param yearRange 年の選択範囲
     */
    public void setYearRange(String yearRange) {
        this.yearRange = yearRange;
    }

    /**
     * 日历最小値を取得する。
     * 
     * @return 日历最小値
     */
    public String getMinDate() {
        return minDate;
    }

    /**
     * 日历最小値を設定する。
     * 
     * @param minDate 日历最小値
     */
    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

}
