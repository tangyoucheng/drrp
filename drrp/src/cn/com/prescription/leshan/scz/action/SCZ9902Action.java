/*
 * Copyright(c) 2011 
 */

package cn.com.prescription.leshan.scz.action;

import cn.com.prescription.leshan.scz.action.form.SCZ9902Form;
import cn.com.prescription.leshan.scz.biz.SCZ9902ConfirmLogic;
import cn.com.prescription.leshan.scz.biz.SCZ9902InitLogic;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.message.MessageUtils;
import cn.com.prescription.framework.util.CheckUtils;

/**
 * 画面ロック機能 action。
 * 
 * @author t.d.m
 */
/*
 * 新規作成
 * DATE: 2010.05.26 NAME: t.d.m
 */
public class SCZ9902Action extends AbstractAction {

    /**
     * SCZ9902Action 的构造。
     */
    public SCZ9902Action() {
        super();
    }

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录form
     */
    private SCZ9902Form scz9902Form = new SCZ9902Form();

    /**
     * 画面ロック機能 初期表示的处理。
     * 
     * @return 返回结果
     * @throws Exception 处理时发生的例外
     */
    @Override
    public String doInit() throws Exception {

        // 初期表示eventを実行する
        doDispatchEvent(scz9902Form, SCZ9902InitLogic.class);
        return SUCCESS;
    }

    /**
     * 画面ロック機能 確認的处理。
     * 
     * @return 返回结果
     * @throws Exception 处理时发生的例外
     */
    public String doConfirm() throws Exception {

        // 引継情報の入力チェック
        if (CheckUtils.isEmpty(scz9902Form.getPassword())) {
            scz9902Form.setMessage(MessageUtils.getMessage("E30001", "密码"));
            this.addFieldError("", MessageUtils.getMessage("E30001", "密码"));
        } else {
            // 確認eventを実行する
            doDispatchEvent(scz9902Form, SCZ9902ConfirmLogic.class);
        }

        return SUCCESS;
    }

    /**
     * 登录formを取得します。
     * 
     * @return 登录form
     */
    public SCZ9902Form getScz9902Form() {
        return scz9902Form;
    }

    /**
     * 登录formを設定します。
     * 
     * @param scz9902Form 登录form
     */
    public void setScz9902Form(SCZ9902Form scz9902Form) {
        this.scz9902Form = scz9902Form;
    }

}
