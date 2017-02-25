/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00206Form;
import cn.com.prescription.leshan.rpa.biz.RPA00206InitLogic;

/**
 * 打印患者信息导出添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00206Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 打印患者信息form
     */
    private RPA00206Form rpa00206Form = new RPA00206Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00206Action() {
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
        doDispatchEvent(rpa00206Form, RPA00206InitLogic.class);
        return SUCCESS;
    }

    /**
     * 打印患者信息form的取得
     * 
     * @return 打印患者信息form
     */
    public RPA00206Form getRpa00206Form() {
        return rpa00206Form;
    }

    /**
     * 打印患者信息form的设定
     * 
     * @param __rpa00206Form 打印患者信息form
     */
    public void setRpa00206Form(RPA00206Form _rpa00206Form) {
        this.rpa00206Form = _rpa00206Form;
    }

}
