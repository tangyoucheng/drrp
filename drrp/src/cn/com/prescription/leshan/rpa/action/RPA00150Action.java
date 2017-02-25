/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00150Form;
import cn.com.prescription.leshan.rpa.biz.RPA00150InitLogic;

/**
 * 处方手写录入 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00150Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户一览的form
     */
    private RPA00150Form rpa00150Form = new RPA00150Form();

    /**
     * RPA00150Action 的构造。
     */
    public RPA00150Action() {
        super();
    }

    /**
     * 处方编辑 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00150Form, RPA00150InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPA00150Form getRpa00150Form() {
        return rpa00150Form;
    }

    /**
     * 用户一览的form的设定
     * 
     * @param _rpa00150Form 用户一览的form
     */
    public void setRpa00150Form(RPA00150Form _rpa00150Form) {
        this.rpa00150Form = _rpa00150Form;
    }

}
