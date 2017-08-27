/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpc.action.form.RPC00104Form;
import cn.com.prescription.leshan.rpc.biz.RPC00104EntryLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00104InitLogic;

/**
 * 复诊信息添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPC00104Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 复诊信息form
     */
    private RPC00104Form rpc00104Form = new RPC00104Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPC00104Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpc00104Form(new RPC00104Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpc00104Form, RPC00104InitLogic.class);
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
        doDispatchEvent(rpc00104Form, RPC00104EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 复诊信息form的取得
     * @return 复诊信息form
     */
    public RPC00104Form getRpc00104Form() {
        return rpc00104Form;
    }

    /**
     * 复诊信息form的设定
     * @param __rpc00104Form 复诊信息form
     */
    public void setRpc00104Form(RPC00104Form _rpc00104Form) {
        this.rpc00104Form = _rpc00104Form;
    }

}
