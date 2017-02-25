/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00104Form;
import cn.com.prescription.leshan.rpa.biz.RPA00104InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00104UpdateLogic;

/**
 * 处方调配 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00104Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方调配的form
     */
    private RPA00104Form rpa00104Form = new RPA00104Form();

    /**
     * RPA00104Action 的构造。
     */
    public RPA00104Action() {
        super();
    }

    /**
     * 处方调配 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00104Form, RPA00104InitLogic.class);
        return SUCCESS;
    }

    /**
     * 处方调配 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00104Form, RPA00104UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 处方调配的form的取得
     * 
     * @return 处方调配的form
     */
    public RPA00104Form getRpa00104Form() {
        return rpa00104Form;
    }

    /**
     * 处方调配的form的设定
     * 
     * @param _rpa00104Form 处方调配的form
     */
    public void setRpa00104Form(RPA00104Form _rpa00104Form) {
        this.rpa00104Form = _rpa00104Form;
    }

}
