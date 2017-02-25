/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.rpa.action.form.RPA00303Form;
import cn.com.prescription.leshan.rpa.biz.RPA00303DeleteLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00303InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00303UpdateLogic;

/**
 * 药品信息编辑action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00303Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 药品信息编辑form
     */
    private RPA00303Form rpa00303Form = new RPA00303Form();

    /**
     * SCM0301Action 的构造。
     */
    public RPA00303Action() {
        super();
    }

    /**
     * 药品信息编辑 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // this.setRpa00303Form(new RPA00303Form());
        // 药品编辑eventを実行する
        doDispatchEvent(rpa00303Form, RPA00303InitLogic.class);
        return SUCCESS;
    }

    /**
     * 登録処理の相関チック的处理。
     * 
     * @return 返却結果
     * @throws Exception
     *             処理実行時に系统例外发生的情况
     */
    public void validateDoUpdate() throws Exception {
        if (!CheckUtils.isEmpty(rpa00303Form.getPrice())
                        && !rpa00303Form.getPrice().matches("^(|\\+)?\\d+(\\.\\d+)?$")) {
            this.addFieldError(("rpa00303Form.price"), MessageUtils.getMessage("E30009", "价格"));
        }
    }

    /**
     * 药品信息编辑 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        // 药品编辑eventを実行する
        doDispatchEvent(rpa00303Form, RPA00303UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 药品信息编辑 删除的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doDelete() throws Exception {
        // 药品编辑eventを実行する
        doDispatchEvent(rpa00303Form, RPA00303DeleteLogic.class);
        return SUCCESS;
    }

    /**
     * 药品信息编辑form的取得
     * 
     * @return 药品信息编辑form
     */
    public RPA00303Form getRpa00303Form() {
        return rpa00303Form;
    }

    /**
     * 药品信息编辑form的设定
     * 
     * @param _rpa00303Form 药品信息编辑form
     */
    public void setRpa00303Form(RPA00303Form _rpa00303Form) {
        this.rpa00303Form = _rpa00303Form;
    }

}
