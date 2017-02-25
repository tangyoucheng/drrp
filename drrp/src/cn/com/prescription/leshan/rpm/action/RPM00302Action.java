/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpm.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpm.action.form.RPM00302Form;
import cn.com.prescription.leshan.rpm.biz.RPM00302InitLogic;
import cn.com.prescription.leshan.rpm.biz.RPM00302SearchLogic;

/**
 * 角色添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPM00302Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色添加的form
     */
    private RPM00302Form rpm00302Form = new RPM00302Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPM00302Action() {
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
        doDispatchEvent(rpm00302Form, RPM00302InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doPage() throws Exception {
        doDispatchEvent(rpm00302Form, RPM00302SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpm00302Form, RPM00302SearchLogic.class);
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
        RPM00302Form form_ = (RPM00302Form) getSaveFormObject();
        if (form_ == null) {
            // 初期表示eventを実行する
            doDispatchEvent(rpm00302Form, RPM00302InitLogic.class);
        } else {
            rpm00302Form = form_;
            // 検索eventを実行する
            doDispatchEvent(rpm00302Form, RPM00302SearchLogic.class);
        }
        return SUCCESS;
    }

    /**
     * 角色添加的form的取得
     * 
     * @return 角色添加的form
     */
    public RPM00302Form getRpm00302Form() {
        return rpm00302Form;
    }

    /**
     * 角色添加的form的设定
     * 
     * @param _rpm00302Form 角色添加的form
     */
    public void setRpm00302Form(RPM00302Form _rpm00302Form) {
        this.rpm00302Form = _rpm00302Form;
    }

}
