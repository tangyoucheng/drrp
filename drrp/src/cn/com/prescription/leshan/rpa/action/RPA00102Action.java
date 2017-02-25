/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00102Form;
import cn.com.prescription.leshan.rpa.biz.RPA00102InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00102SearchLogic;

/**
 * 处方一览 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00102Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方一览的form
     */
    private RPA00102Form rpa00102Form = new RPA00102Form();

    /**
     * RPA00102Action 的构造。
     */
    public RPA00102Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00102Form, RPA00102InitLogic.class);
        rpa00102Form.setPageStartRowNo(0);
        doDispatchEvent(rpa00102Form, RPA00102SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpa00102Form, RPA00102SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpa00102Form, RPA00102SearchLogic.class);
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
        RPA00102Form form_ = (RPA00102Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpa00102Form, RPA00102InitLogic.class);
        } else {
            rpa00102Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpa00102Form, RPA00102SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPA00102Form getRpa00102Form() {
        return rpa00102Form;
    }

    /**
     * 处方一览的form的设定
     * 
     * @param _rpa00102Form 处方一览的form
     */
    public void setRpa00102Form(RPA00102Form _rpa00102Form) {
        this.rpa00102Form = _rpa00102Form;
    }

}
