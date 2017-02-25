/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpm.action.form.RPM00202Form;
import cn.com.prescription.leshan.rpm.biz.RPM00202InitLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00202SearchLogic;

/**
 * 用户情報設定 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00202Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户一览的form
     */
    private RPM00202Form rpm00202Form = new RPM00202Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00202Action() {
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
        doDispatchEvent(rpm00202Form, RPM00202InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpm00202Form, RPM00202SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpm00202Form, RPM00202SearchLogic.class);
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
        RPM00202Form form_ = (RPM00202Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpm00202Form, RPM00202InitLogic.class);
        } else {
            rpm00202Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpm00202Form, RPM00202SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 用户一览的formを取得する。
     * 
     * @return 用户一览的form
     */
    public RPM00202Form getRpm00202Form() {
        return rpm00202Form;
    }

    /**
     * 用户一览的formを設定する。
     * 
     * @param _rpm00202Form 用户一览的form
     */
    public void setRpm00202Form(RPM00202Form _rpm00202Form) {
        this.rpm00202Form = _rpm00202Form;
    }

}
