/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.rpa.action.form.RPA00301Form;
import cn.com.prescription.leshan.rpa.biz.RPA00301EntryLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00301InitLogic;

/**
 * 药品信息添加action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00301Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 药品信息form
     */
    private RPA00301Form rpa00301Form = new RPA00301Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00301Action() {
        super();
    }

    /**
     * 用户情報設定 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpa00301Form(new RPA00301Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00301Form, RPA00301InitLogic.class);
        return SUCCESS;
    }

    /**
     * 登録処理の相関チック的处理。
     * 
     * @return 返却結果
     * @throws Exception
     *             処理実行時に系统例外发生的情况
     */
    public void validateDoEntry() throws Exception {
        if (!CheckUtils.isEmpty(rpa00301Form.getPrice())
                        && !rpa00301Form.getPrice().matches("^(|\\+)?\\d+(\\.\\d+)?$")) {
            this.addFieldError(("rpa00301Form.price"), MessageUtils.getMessage("E30009", "价格"));
        }
    }

    /**
     * 用户情報設定 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00301Form, RPA00301EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 药品信息form的取得
     * 
     * @return 药品信息form
     */
    public RPA00301Form getRpa00301Form() {
        return rpa00301Form;
    }

    /**
     * 药品信息form的设定
     * 
     * @param __rpa00301Form 药品信息form
     */
    public void setRpa00301Form(RPA00301Form _rpa00301Form) {
        this.rpa00301Form = _rpa00301Form;
    }

}
