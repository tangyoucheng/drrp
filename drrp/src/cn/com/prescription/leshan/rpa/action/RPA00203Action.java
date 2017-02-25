/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00203Form;
import cn.com.prescription.leshan.rpa.biz.RPA00203DeleteLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00203InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00203UpdateLogic;

/**
 * 患者信息添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00203Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 患者信息form
     */
    private RPA00203Form rpa00203Form = new RPA00203Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00203Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00203Form(new RPA00203Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00203Form, RPA00203InitLogic.class);
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
        doDispatchEvent(rpa00203Form, RPA00203UpdateLogic.class);
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
        doDispatchEvent(rpa00203Form, RPA00203DeleteLogic.class);
        return SUCCESS;
    }

    /**
     * 患者信息form的取得
     * 
     * @return 患者信息form
     */
    public RPA00203Form getRpa00203Form() {
        return rpa00203Form;
    }

    /**
     * 患者信息form的设定
     * 
     * @param _rpa00203Form 患者信息form
     */
    public void setRpa00203Form(RPA00203Form _rpa00203Form) {
        this.rpa00203Form = _rpa00203Form;
    }

}
