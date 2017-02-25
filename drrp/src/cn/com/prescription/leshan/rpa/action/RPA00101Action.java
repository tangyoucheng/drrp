/*
 * Copyright(c) 2016 
 */
package cn.com.prescription.leshan.rpa.action;

import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;
import cn.com.prescription.leshan.rpa.action.form.RPA0010101Dto;
import cn.com.prescription.leshan.rpa.action.form.RPA00101Form;
import cn.com.prescription.leshan.rpa.biz.RPA00101EntryLogic;
import cn.com.prescription.leshan.rpa.biz.RPA00101InitLogic;

/**
 * 处方添加 action。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.07 NAME: fsb
 */
public class RPA00101Action extends AbstractAction {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 处方添加的form
     */
    private RPA00101Form rpa00101Form = new RPA00101Form();

    /**
     * RPA00101Action 的构造。
     */
    public RPA00101Action() {
        super();
    }

    /**
     * 处方添加 初期表示的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doInit() throws Exception {
        this.setRpa00101Form(new RPA00101Form());
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00101Form, RPA00101InitLogic.class);
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

        if (CheckUtils.isEqual("1", rpa00101Form.getPrescriptionType())
                        && CheckUtils.isEmpty(rpa00101Form.getFileContents())) {
            this.addFieldError((""), MessageUtils.getMessage("E30001", "处方图片"));
        }

        if (CheckUtils.isEqual("2", rpa00101Form.getPrescriptionType())
                        && CheckUtils.isEmpty(rpa00101Form.getPrescriptionContent())) {
            this.addFieldError(("rpa00101Form.prescriptionContent"), MessageUtils.getMessage("E30001", "处方内容"));
        }
        if (rpa00101Form.getSubForm1() != null && !rpa00101Form.getSubForm1().isEmpty()) {
            for (int i = 0; i < rpa00101Form.getSubForm1().size(); i++) {
                RPA0010101Dto rpa0010101Dto = rpa00101Form.getSubForm1().get(i);
                if (!rpa0010101Dto.getQuantity().matches("^(|\\+)?\\d+(\\.\\d+)?$")) {
                    this.addFieldError(("rpa00101Form.subForm1[" + i + "].quantity"),
                        MessageUtils.getMessage("E30009", "药品清单第" + (i + 1) + "行的数量"));
                }
            }
        }

    }

    /**
     * 处方添加 登録的处理。
     * 
     * @return 返却結果
     * @throws Exception 処理実行時に系统例外发生的情况
     */
    public String doEntry() throws Exception {
        // 用户情報設定eventを実行する
        doDispatchEvent(rpa00101Form, RPA00101EntryLogic.class);
        return SUCCESS;
    }

    /**
     * 处方添加的form的取得
     * 
     * @return 处方添加的form
     */
    public RPA00101Form getRpa00101Form() {
        return rpa00101Form;
    }

    /**
     * 处方添加的form的设定
     * 
     * @param _rpa00101Form 处方添加的form
     */
    public void setRpa00101Form(RPA00101Form _rpa00101Form) {
        this.rpa00101Form = _rpa00101Form;
    }

}
