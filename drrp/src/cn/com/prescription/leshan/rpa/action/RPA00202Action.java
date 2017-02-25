/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00202Form;
import cn.com.prescription.leshan.rpa.biz.RPA00202InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00202SearchLogic;

/**
 * 用户情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00202Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户一览的form
     */
    private RPA00202Form rpa00202Form = new RPA00202Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00202Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00202Form(new RPA00202Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00202Form, RPA00202InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpa00202Form, RPA00202SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpa00202Form, RPA00202SearchLogic.class);
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
        RPA00202Form form_ = (RPA00202Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpa00202Form, RPA00202InitLogic.class);
        } else {
            rpa00202Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpa00202Form, RPA00202SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPA00202Form getRpa00202Form() {
        return rpa00202Form;
    }

    /**
     * 用户一览的form的设定
     * 
     * @param _rpa00202Form 用户一览的form
     */
    public void setRpa00202Form(RPA00202Form _rpa00202Form) {
        this.rpa00202Form = _rpa00202Form;
    }

}
