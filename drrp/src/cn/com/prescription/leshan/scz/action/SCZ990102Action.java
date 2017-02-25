/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.action;

import cn.com.prescription.leshan.scz.action.form.SCZ990102Form;
import cn.com.prescription.leshan.scz.biz.SCZ990102InitLogic;
import cn.com.prescription.framework.action.AbstractAction;

/**
 * 门户画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.04 NAME: kourei
 */
public class SCZ990102Action extends AbstractAction {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录form
     */
    private SCZ990102Form scz990102Form = new SCZ990102Form();

    /**
     * 初期処理的处理
     * 
     * @return 返回结果
     * @throws Exception 处理时发生的例外
     */
    @Override
    public String doInit() throws Exception {

        doDispatchEvent(scz990102Form, SCZ990102InitLogic.class);

        return SUCCESS;
    }

    /**
     * 登录formを取得します。
     * 
     * @return 登录form
     */
    public SCZ990102Form getScz990102Form() {
        return scz990102Form;
    }

    /**
     * 登录formを設定します。
     * 
     * @param scz990102Form 登录form
     */
    public void setScz990102Form(SCZ990102Form scz990102Form) {
        this.scz990102Form = scz990102Form;
    }

}
