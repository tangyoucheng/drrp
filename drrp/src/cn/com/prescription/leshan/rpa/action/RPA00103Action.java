/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.rpa.action.form.RPA0010301Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00103Form;
import cn.com.prescription.leshan.rpa.biz.RPA00103DeleteLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00103InitLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00103UpdateLogic;

/**
 * 处方编辑 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00103Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户一览的form
     */
    private RPA00103Form rpa00103Form = new RPA00103Form();

    /**
     * RPA00103Action 的构造。
     */
    public RPA00103Action() {
        super();
    }

    /**
     * 处方编辑 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00103Form, RPA00103InitLogic.class);
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

        if (CheckUtils.isEqual("1", rpa00103Form.getPrescriptionType())
                        && CheckUtils.isEmpty(rpa00103Form.getFileContents())) {
            this.addFieldError((""), MessageUtils.getMessage("E30001", "处方图片"));
        }

        if (CheckUtils.isEqual("2", rpa00103Form.getPrescriptionType())
                        && CheckUtils.isEmpty(rpa00103Form.getPrescriptionContent())) {
            this.addFieldError(("rpa00103Form.prescriptionContent"), MessageUtils.getMessage("E30001", "处方内容"));
        }
        if (rpa00103Form.getSubForm1() != null && !rpa00103Form.getSubForm1().isEmpty()) {
            for (int i = 0; i < rpa00103Form.getSubForm1().size(); i++) {
                RPA0010301Dto rpa0010301Dto = rpa00103Form.getSubForm1().get(i);
                if (!rpa0010301Dto.getQuantity().matches("^(|\\+)?\\d+(\\.\\d+)?$")) {
                    this.addFieldError(("rpa00103Form.subForm1[" + i + "].quantity"),
                        MessageUtils.getMessage("E30009", "药品清单第" + (i + 1) + "行的数量"));
                }
            }
        }

    }

    /**
     * 处方编辑 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doUpdate() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00103Form, RPA00103UpdateLogic.class);
        return SUCCESS;
    }

    /**
     * 处方编辑 删除的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doDelete() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00103Form, RPA00103DeleteLogic.class);
        return SUCCESS;
    }

    /**
     * 用户一览的form的取得
     * 
     * @return 用户一览的form
     */
    public RPA00103Form getRpa00103Form() {
        return rpa00103Form;
    }

    /**
     * 用户一览的form的设定
     * 
     * @param _rpa00103Form 用户一览的form
     */
    public void setRpa00103Form(RPA00103Form _rpa00103Form) {
        this.rpa00103Form = _rpa00103Form;
    }

}
