/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00201Form;
import cn.com.prescription.leshan.rpa.biz.RPA00201EntryLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00201InitLogic;

/**
 * 患者信息添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00201Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 患者信息form
     */
    private RPA00201Form rpa00201Form = new RPA00201Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00201Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpa00201Form(new RPA00201Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00201Form, RPA00201InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00201Form, RPA00201EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 患者信息form的取得
     * @return 患者信息form
     */
    public RPA00201Form getRpa00201Form() {
        return rpa00201Form;
    }

    /**
     * 患者信息form的设定
     * @param __rpa00201Form 患者信息form
     */
    public void setRpa00201Form(RPA00201Form _rpa00201Form) {
        this.rpa00201Form = _rpa00201Form;
    }

}
