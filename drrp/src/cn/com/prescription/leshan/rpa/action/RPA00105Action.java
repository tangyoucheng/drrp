/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00105Form;
import cn.com.prescription.leshan.rpa.biz.RPA00105InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00105UpdateLogic;

/**
 * 处方审核 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00105Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户一览的form
     */
    private RPA00105Form rpa00105Form = new RPA00105Form();

    /**
     * RPA00105Action 的构造。
     */
    public RPA00105Action() {
        super();
    }

    /**
     * 处方编辑 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        doDispatchEvent(rpa00105Form, RPA00105InitLogic.class);
        return SUCCESS;
    }

    /**
     * 处方编辑 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        doDispatchEvent(rpa00105Form, RPA00105UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 处方编辑 他页面返回的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doBack() throws Exception {
        // セッションからformを取得する。
        RPA00105Form form_ = (RPA00105Form) getSaveFormObject();
        if (form_ != null) {
            rpa00105Form = form_;
        }
        // 初期表示eventを実行する
        doDispatchEvent(rpa00105Form, RPA00105InitLogic.class);
        // 设定第一次审核标识
        rpa00105Form.setFirstReviewFlag("true");
        return SUCCESS;
    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPA00105Form getRpa00105Form() {
        return rpa00105Form;
    }

    /**
     * 用户一览的form的设定
     * 
     * @param _rpa00105Form 用户一览的form
     */
    public void setRpa00105Form(RPA00105Form _rpa00105Form) {
        this.rpa00105Form = _rpa00105Form;
    }

}
