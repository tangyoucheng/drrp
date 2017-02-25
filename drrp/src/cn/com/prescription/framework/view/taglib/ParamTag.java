/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * パラメータタグ.
 * 
 * @author bpchikazawa
 */
public class ParamTag extends ComponentTagSupport {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /**
     * タグ生成コンポーネント
     * 
     * @param _valuestack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     * @return コンポーネント
     */
    @Override
    public Component getBean(ValueStack _valuestack, HttpServletRequest _request, HttpServletResponse _response) {
        return new Param(_valuestack, _request, _response);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        // コンポーネント取得
        Param component_ = (Param) this.getComponent();
        // パラメータセット
        component_.setName(this.name);
        component_.setValue(this.value);
    }

    /** name属性 */
    private String name = "";
    /** value属性 */
    private String value = "";

    /**
     * @param _name name属性
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _value value属性
     */
    public final void setValue(String _value) {
        value = _value;
    }

}
