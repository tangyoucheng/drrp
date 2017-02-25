/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.prescription.framework.util.StringUtils;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * サブミットタグ.
 * 
 * @author bpchikazawa
 */
public class SubmitTag extends ComponentTagSupport {

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
        return new Submit(_valuestack, _request, _response);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        // コンポーネント取得
        Submit component_ = (Submit) this.getComponent();
        // パラメータセット
        component_.setId(this.id);
        component_.setName(this.name);
        component_.setTitle(this.title);
        component_.setValue(this.value);
        component_.setCssClass(this.cssClass);
        component_.setCssStyle(this.cssStyle);
        component_.setTabindex(this.tabindex);
        component_.setDisabled(this.disabled);
        if (StringUtils.contains(this.onclick, "showAjaxConfirm")) {
            component_.setOnclick(this.onclick);
        } else {
            component_.setOnclick("doClearWaterMark();" + this.onclick); // 2012/04/09 　ウォーターマーク処理追加
        }
        component_.setNamespace(this.namespace);
        component_.setAction(this.action);
        component_.setMethod(this.method);
    }

    /** name属性 */
    private String name = "";
    /** title属性 */
    private String title = "";
    /** value属性 */
    private String value = "";
    /** cssClass属性 */
    private String cssClass = "";
    /** cssStyle属性 */
    private String cssStyle = "";
    /** tabindex属性 */
    private String tabindex = "";
    /** disabled属性 */
    private String disabled = "";
    /** onclick属性 */
    private String onclick = "";

    /** namespace属性 */
    private String namespace = "";
    /** action属性 */
    private String action = "";
    /** method属性 */
    private String method = "";

    /**
     * @param _name name属性
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _title title属性
     */
    public final void setTitle(String _title) {
        title = _title;
    }

    /**
     * @param _value value属性
     */
    public final void setValue(String _value) {
        this.value = _value;
    }

    /**
     * @param _cssClass cssClass属性
     */
    public final void setCssClass(String _cssClass) {
        cssClass = _cssClass;
    }

    /**
     * @param _cssStyle cssStyle属性
     */
    public final void setCssStyle(String _cssStyle) {
        cssStyle = _cssStyle;
    }

    /**
     * @param _tabindex tabindex属性
     */
    public final void setTabindex(String _tabindex) {
        tabindex = _tabindex;
    }

    /**
     * @param _disabled _disabled属性
     */
    public final void setDisabled(String _disabled) {
        disabled = _disabled;
    }

    /**
     * @param _onclick onclick属性
     */
    public final void setOnclick(String _onclick) {
        onclick = _onclick;
    }

    /**
     * @param _namespace namespace属性
     */
    public final void setNamespace(String _namespace) {
        namespace = _namespace;
    }

    /**
     * @param _action action属性
     */
    public final void setAction(String _action) {
        action = _action;
    }

    /**
     * @param _method method属性
     */
    public final void setMethod(String _method) {
        method = _method;
    }

}
