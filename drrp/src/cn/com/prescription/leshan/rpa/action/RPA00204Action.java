/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00204Form;
import cn.com.prescription.leshan.rpa.biz.RPA00204InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00204SearchLogic;

/**
 * 用户情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00204Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户一览的form
     */
    private RPA00204Form rpa00204Form = new RPA00204Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00204Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00204Form(new RPA00204Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00204Form, RPA00204InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpa00204Form, RPA00204SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpa00204Form, RPA00204SearchLogic.class);
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
        RPA00204Form form_ = (RPA00204Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpa00204Form, RPA00204InitLogic.class);
        } else {
            rpa00204Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpa00204Form, RPA00204SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPA00204Form getRpa00204Form() {
        return rpa00204Form;
    }

    /**
     * 用户一览的form的设定
     * 
     * @param _rpa00204Form 用户一览的form
     */
    public void setRpa00204Form(RPA00204Form _rpa00204Form) {
        this.rpa00204Form = _rpa00204Form;
    }

}
