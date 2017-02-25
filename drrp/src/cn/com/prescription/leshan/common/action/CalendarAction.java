/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.common.action;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.util.DateUtils;
import cn.com.prescription.framework.util.NumberUtils;
import cn.com.prescription.leshan.common.LeshanConstantsIF;
import cn.com.prescription.leshan.common.action.form.CalendarForm;

/**
 * 日历
 * 
 * @author nttdata
 */
/*
 * 新規作成 DATE: 2011.08.18 NAME: nttdata
 */
public class CalendarAction extends AbstractAction {

    /** 串行版本UID */
    private static final long serialVersionUID = 1244479542447823404L;

    /** form */
    private CalendarForm form = new CalendarForm();

    /**
     * CalendarAction 的构造。
     */
    public CalendarAction() {
        super();
    }

    /**
     * 初期化処理
     * 
     * @return 返却結果
     * @throws Exception 初期化処理で、業務チェック例外以外の例外が発生した場合。
     */
    public String doInit() throws Exception {
        // 終了
        return SUCCESS;
    }

    /**
     * 日历のオプション取得処理
     * 
     * @return 返却結果
     * @throws Exception 初期化処理で、業務チェック例外以外の例外が発生した場合。
     */
    public String doGetOptions() throws Exception {

        String sysYear = DateUtils.format(DateUtils.getSysDate(), StandardConstantsIF.DATE_FORMAT_YYYY);
        int minDate = NumberUtils.toInt(sysYear) - LeshanConstantsIF.KYOTU_CALENDAR_HYOJI_YEAR_SU.intValue();
        if (minDate >= 1868) {
            form.setMinDate(minDate + "/01/01");
        } else {
            form.setMinDate("1868/10/23");
        }
        form.setYearRange("-" + LeshanConstantsIF.KYOTU_CALENDAR_HYOJI_YEAR_SU + ":+20");
        // 終了
        return SUCCESS;
    }

    /**
     * 系统日付取得処理
     * 
     * @return 返却結果
     * @throws Exception 初期化処理で、業務チェック例外以外の例外が発生した場合。
     */
    public String doGetSystemDate() throws Exception {

        String sysdate = DateUtils.format(DateUtils.getSysDate(), StandardConstantsIF.DATE_FORMAT_YYYY_MM_DD);
        form.setSystemDate(sysdate);
        // 終了
        return SUCCESS;
    }

    /**
     * @return form
     */
    public CalendarForm getForm() {
        return form;
    }

    /**
     * @param _form form
     */
    public void setForm(CalendarForm _form) {
        form = _form;
    }

}
