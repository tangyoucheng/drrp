/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpc.action.form.RPC00103Form;
import cn.com.prescription.leshan.rpc.biz.RPC00103DeleteLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00103InitLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00103UpdateLogic;

/**
 * 初诊信息添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPC00103Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 初诊信息form
     */
    private RPC00103Form rpc00103Form = new RPC00103Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPC00103Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00103Form(new RPC00103Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpc00103Form, RPC00103InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpc00103Form, RPC00103UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定 删除的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doDelete() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpc00103Form, RPC00103DeleteLogic.class);
        return SUCCESS;
    }

    /**
     * 初诊信息form的取得
     * 
     * @return 初诊信息form
     */
    public RPC00103Form getRpc00103Form() {
        return rpc00103Form;
    }

    /**
     * 初诊信息form的设定
     * 
     * @param _rpc00103Form 初诊信息form
     */
    public void setRpc00103Form(RPC00103Form _rpc00103Form) {
        this.rpc00103Form = _rpc00103Form;
    }

}
