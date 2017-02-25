/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpm.action.form.RPM00401Form;
import cn.com.prescription.leshan.rpm.biz.RPM00401EntryLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00401InitLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00401SearchLogic;

/**
 * 患者信息导出添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00401Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 患者信息form
     */
    private RPM00401Form rpm00401Form = new RPM00401Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00401Action() {
        super();
    }

    /**
     * 用户菜单权限设定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpm00401Form(new RPM00401Form());
        // 用户菜单权限设定eventを実行する
        doDispatchEvent(rpm00401Form, RPM00401InitLogic.class);
        doDispatchEvent(rpm00401Form, RPM00401SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户菜单权限设定 检索的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        // 用户菜单权限设定eventを実行する
        doDispatchEvent(rpm00401Form, RPM00401SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户菜单权限设定 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        // 用户菜单权限设定eventを実行する
        doDispatchEvent(rpm00401Form, RPM00401EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 患者信息form的取得
     * 
     * @return 患者信息form
     */
    public RPM00401Form getRpm00401Form() {
        return rpm00401Form;
    }

    /**
     * 患者信息form的设定
     * 
     * @param __rpm00401Form 患者信息form
     */
    public void setRpm00401Form(RPM00401Form _rpm00401Form) {
        this.rpm00401Form = _rpm00401Form;
    }

}
