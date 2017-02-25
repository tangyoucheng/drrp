/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpm.action.form.RPM00301Form;
import cn.com.prescription.leshan.rpm.biz.RPM00301EntryLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00301InitLogic;

/**
 * 角色添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00301Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色添加的form
     */
    private RPM00301Form rpm00301Form = new RPM00301Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00301Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpm00301Form(new RPM00301Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpm00301Form, RPM00301InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        doDispatchEvent(rpm00301Form, RPM00301EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 角色添加的form的取得
     * 
     * @return 角色添加的form
     */
    public RPM00301Form getRpm00301Form() {
        return rpm00301Form;
    }

    /**
     * 角色添加的form的设定
     * 
     * @param _rpm00301Form 角色添加的form
     */
    public void setRpm00301Form(RPM00301Form _rpm00301Form) {
        this.rpm00301Form = _rpm00301Form;
    }

}
