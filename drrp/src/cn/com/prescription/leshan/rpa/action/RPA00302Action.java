/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00302Form;
import cn.com.prescription.leshan.rpa.biz.RPA00302InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00302SearchLogic;

/**
 * 药品一览 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00302Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 药品一览的form
     */
    private RPA00302Form rpa00302Form = new RPA00302Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00302Action() {
        super();
    }

    /**
     * 药品一览 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00302Form(new RPA00302Form());
        // 药品一览eventを実行する
        doDispatchEvent(rpa00302Form, RPA00302InitLogic.class);
        return SUCCESS;
    }

    /**
     * 药品一览的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpa00302Form, RPA00302SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 药品一览的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpa00302Form, RPA00302SearchLogic.class);
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
        RPA00302Form form_ = (RPA00302Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpa00302Form, RPA00302InitLogic.class);
        } else {
            rpa00302Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpa00302Form, RPA00302SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 药品一览的form的取得
     * 
     * @return 药品一览的form
     */
    public RPA00302Form getRpa00302Form() {
        return rpa00302Form;
    }

    /**
     * 药品一览的form的设定
     * 
     * @param _rpa00302Form 药品一览的form
     */
    public void setRpa00302Form(RPA00302Form _rpa00302Form) {
        this.rpa00302Form = _rpa00302Form;
    }

}
