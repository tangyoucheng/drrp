/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.scz.action;

import cn.com.prescription.leshan.scz.action.form.SCZ990101Form;
import cn.com.prescription.leshan.scz.biz.SCZ990101InitLogic;
import cn.com.prescription.framework.action.AbstractAction;
import cn.com.prescription.framework.common.session.UserSessionUtils;

/**
 * SCZ990101Action 门户画面 action。
 * 
 * @author kourei
 */
/*
 * 新規作成
 * DATE: 2010.03.04 NAME: kourei
 */
public class SCZ990101Action extends AbstractAction {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录form
     */
    private SCZ990101Form scz990101Form = new SCZ990101Form();

    /**
     * 初期処理的处理
     * 
     * @return 返回结果
     * @throws Exception 处理时发生的例外
     */
    @Override
    public String doInit() throws Exception {

        doDispatchEvent(scz990101Form, SCZ990101InitLogic.class);
        return SUCCESS;
    }

    /**
     * ログアウト処理的处理
     * 
     * @return 登录form
     * @throws Exception 处理时发生的例外
     */
    public String doLoginOut() throws Exception {

        // 登録用户のセッション情報クリア
        UserSessionUtils.clearUserSessionInfo();
        // セッション破棄
        this.request.getSession().invalidate();

        return null;
    }

    /**
     * 登录formを取得します。
     * 
     * @return 登录form
     */
    public SCZ990101Form getScz990101Form() {
        return scz990101Form;
    }

    /**
     * 登录formを設定します。
     * 
     * @param scz990101Form 登录form
     */
    public void setScz990101Form(SCZ990101Form scz990101Form) {
        this.scz990101Form = scz990101Form;
    }

}
