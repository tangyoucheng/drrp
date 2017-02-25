/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.leshan.common.action;

import cn.com.prescription.leshan.common.action.form.MenuBootForm;
import cn.com.prescription.leshan.common.biz.MenuBootLogic;
import cn.com.prescription.framework.action.AbstractAction;

/**
 * 菜单起動時処理
 * 
 * @author bpchikazawa
 */
public class MenuBootAction extends AbstractAction {

    /** serialVersionUID */
    private static final long serialVersionUID = 1244479542447823404L;

    /** form */
    private MenuBootForm form = new MenuBootForm();

    /**
     * 菜单起動時処理
     * 
     * @return 返却結果
     * @throws Exception 初期化処理で、業務チェック例外以外の例外が発生した場合。
     */
    @Override
    public String doInit() throws Exception {
        // 実行
        doDispatchEvent(this.form, MenuBootLogic.class);
        // 終了
        return SUCCESS;
    }

    /**
     * @return form
     */
    public MenuBootForm getForm() {
        return form;
    }

    /**
     * @param _form form
     */
    public void setForm(MenuBootForm _form) {
        form = _form;
    }

}
