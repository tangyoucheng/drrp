/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.leshan.rpa.action.form.RPA00109Form;
import cn.com.prescription.leshan.rpa.biz.RPA00109ExportLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00109InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00109SearchLogic;

/**
 * 处方统计 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00109Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方统计的form
     */
    private RPA00109Form rpa00109Form = new RPA00109Form();

    /**
     * RPA00109Action 的构造。
     */
    public RPA00109Action() {
        super();
    }

    /**
     * 处方信息统计 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00109Form, RPA00109InitLogic.class);
        // rpa00109Form.setPageStartRowNo(0);
        // doDispatchEvent(rpa00109Form, RPA00109SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 处方信息统计 检索的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doSearch() throws Exception {
        doDispatchEvent(rpa00109Form, RPA00109SearchLogic.class);
        return SUCCESS;
    }

    /**
     * 处方信息统计 导出的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doExport() throws Exception {
        doDispatchEvent(rpa00109Form, RPA00109ExportLogic.class);
        return SUCCESS;
    }

    /**
     * 处方统计的form的取得
     * 
     * @return 处方统计的form
     */
    public RPA00109Form getRpa00109Form() {
        return rpa00109Form;
    }

    /**
     * 处方统计的form的设定
     * 
     * @param _rpa00109Form 处方统计的form
     */
    public void setRpa00109Form(RPA00109Form _rpa00109Form) {
        this.rpa00109Form = _rpa00109Form;
    }

}
