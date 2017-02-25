/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.action;

import cn.com.prescription.leshan.scz.action.form.SCZ9901Form;
import cn.com.prescription.leshan.scz.biz.SCZ9901InitLogic;
import cn.com.prescription.framework.action.AbstractAction;

/**
 * SCZ9901 一般门户画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.04 NAME: kourei
 */
public class SCZ9901Action extends AbstractAction {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录form
     */
    private SCZ9901Form scz9901Form = new SCZ9901Form();

    /**
     * 初期処理的处理
     * 
     * @return 返回结果
     * @throws Exception 处理时发生的例外
     */
    @Override
    public String doInit() throws Exception {

        doDispatchEvent(scz9901Form, SCZ9901InitLogic.class);

        return SUCCESS;
    }

    /**
     * 登录formを取得します。
     * 
     * @return 登录form
     */
    public SCZ9901Form getScz9901Form() {
        return scz9901Form;
    }

    /**
     * 登录formを設定します。
     * 
     * @param scz9901Form 登录form
     */
    public void setScz9901Form(SCZ9901Form scz9901Form) {
        this.scz9901Form = scz9901Form;
    }

}
