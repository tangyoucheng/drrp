/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpm.action.form.RPM00303Form;
import cn.com.prescription.leshan.rpm.biz.RPM00303DeleteLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00303InitLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00303UpdateLogic;

/**
 * 角色编辑action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00303Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色编辑的form
     */
    private RPM00303Form rpm00303Form = new RPM00303Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00303Action() {
        super();
    }

    /**
     * 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpm00303Form, RPM00303InitLogic.class);
        return SUCCESS;
    }

    /**
     * 更新角色信息う。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        doDispatchEvent(rpm00303Form, RPM00303UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 删除角色信息。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doDelete() throws Exception {
        doDispatchEvent(rpm00303Form, RPM00303DeleteLogic.class);
        return SUCCESS;
    }

    /**
     * 角色编辑的form的取得
     * 
     * @return 角色编辑的form
     */
    public RPM00303Form getRpm00303Form() {
        return rpm00303Form;
    }

    /**
     * 角色编辑的form的设定
     * 
     * @param _rpm00303Form 角色编辑的form
     */
    public void setRpm00303Form(RPM00303Form _rpm00303Form) {
        this.rpm00303Form = _rpm00303Form;
    }

}
