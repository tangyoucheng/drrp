/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpc.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpc.action.form.RPC00102Form;
import cn.com.prescription.leshan.rpc.biz.RPC00102InitLogic;
import cn.com.prescription.leshan.rpc.biz.RPC00102SearchLogic;

/**
 * 门诊情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPC00102Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 门诊一览的form
     */
    private RPC00102Form rpc00102Form = new RPC00102Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPC00102Action() {
        super();
    }

    /**
     * 门诊情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00102Form(new RPC00102Form());
        // 门诊情報設定eventを実行する
        doDispatchEvent(rpc00102Form, RPC00102InitLogic.class);
        return SUCCESS;
    }

    /**
     * 门诊情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpc00102Form, RPC00102SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 门诊情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpc00102Form, RPC00102SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 駐車場検索一覧 戻る処理的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doBack() throws Exception {
        // セッションからformを取得する。
        RPC00102Form form_ = (RPC00102Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpc00102Form, RPC00102InitLogic.class);
        } else {
            rpc00102Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpc00102Form, RPC00102SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 门诊一览的form的取得
     * 
     * @return 门诊一览的form
     */
    public RPC00102Form getRpc00102Form() {
        return rpc00102Form;
    }

    /**
     * 门诊一览的form的设定
     * 
     * @param _rpc00102Form 门诊一览的form
     */
    public void setRpc00102Form(RPC00102Form _rpc00102Form) {
        this.rpc00102Form = _rpc00102Form;
    }

}
