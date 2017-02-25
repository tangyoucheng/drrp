/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpm.action.form.RPM00501Form;
import cn.com.prescription.leshan.rpm.biz.RPM00501EntryLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00501InitLogic;

/**
 * 角色添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00501Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 推广信息添加的form
     */
    private RPM00501Form rpm00501Form = new RPM00501Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00501Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpm00501Form(new RPM00501Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpm00501Form, RPM00501InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        doDispatchEvent(rpm00501Form, RPM00501EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 推广信息添加的form的取得
     * 
     * @return 角色添加的form
     */
    public RPM00501Form getRpm00501Form() {
        return rpm00501Form;
    }

    /**
     * 推广信息添加的form的设定
     * 
     * @param _rpm00301Form 角色添加的form
     */
    public void setRpm00501Form(RPM00501Form _rpm00501Form) {
        this.rpm00501Form = _rpm00501Form;
    }

}
