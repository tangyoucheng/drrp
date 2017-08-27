/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpc.action.form.RPC00101Form;
import cn.com.prescription.leshan.rpc.biz.RPC00101EntryLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00101InitLogic;

/**
 * 门诊信息添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPC00101Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 门诊信息form
     */
    private RPC00101Form rpc00101Form = new RPC00101Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPC00101Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpc00101Form(new RPC00101Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpc00101Form, RPC00101InitLogic.class);
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
        doDispatchEvent(rpc00101Form, RPC00101EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 门诊信息form的取得
     * @return 门诊信息form
     */
    public RPC00101Form getRpc00101Form() {
        return rpc00101Form;
    }

    /**
     * 门诊信息form的设定
     * @param __rpc00101Form 门诊信息form
     */
    public void setRpc00101Form(RPC00101Form _rpc00101Form) {
        this.rpc00101Form = _rpc00101Form;
    }

}
