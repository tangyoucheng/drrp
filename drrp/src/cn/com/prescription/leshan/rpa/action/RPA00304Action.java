/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00304Form;
import cn.com.prescription.leshan.rpa.biz.RPA00304InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00304SearchLogic;

/**
 * 药品情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00304Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 药品一览的form
     */
    private RPA00304Form rpa00304Form = new RPA00304Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00304Action() {
        super();
    }

    /**
     * 药品一览 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00304Form(new RPA00304Form());
        // 药品情報設定eventを実行する
        doDispatchEvent(rpa00304Form, RPA00304InitLogic.class);
        return SUCCESS;
    }

    /**
     * 药品一览 分页的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpa00304Form, RPA00304SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 药品一览 检索的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpa00304Form, RPA00304SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 药品一覧 戻る処理的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doBack() throws Exception {
        // セッションからformを取得する。
        RPA00304Form form_ = (RPA00304Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpa00304Form, RPA00304InitLogic.class);
        } else {
            rpa00304Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpa00304Form, RPA00304SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 药品一览的form的取得
     * 
     * @return 药品一览的form
     */
    public RPA00304Form getRpa00304Form() {
        return rpa00304Form;
    }

    /**
     * 药品一览的form的设定
     * 
     * @param _rpa00304Form 药品一览的form
     */
    public void setRpa00304Form(RPA00304Form _rpa00304Form) {
        this.rpa00304Form = _rpa00304Form;
    }

}
