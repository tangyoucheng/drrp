/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00205Form;
import cn.com.prescription.leshan.rpa.biz.RPA00205ExportLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00205InitLogic;

/**
 * 患者信息导出添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00205Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 患者信息form
     */
    private RPA00205Form rpa00205Form = new RPA00205Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00205Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpa00205Form(new RPA00205Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00205Form, RPA00205InitLogic.class);
        return SUCCESS;
    }

    /**
     * 用户情報設定 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doExport() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00205Form, RPA00205ExportLogic.class);
        return SUCCESS;
    }

    /**
     * 患者信息form的取得
     * 
     * @return 患者信息form
     */
    public RPA00205Form getRpa00205Form() {
        return rpa00205Form;
    }

    /**
     * 患者信息form的设定
     * 
     * @param __rpa00205Form 患者信息form
     */
    public void setRpa00205Form(RPA00205Form _rpa00205Form) {
        this.rpa00205Form = _rpa00205Form;
    }

}
