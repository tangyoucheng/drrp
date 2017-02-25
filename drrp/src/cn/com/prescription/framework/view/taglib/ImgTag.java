/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.view.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * イメージタグ.
 * 
 * @author bpchikazawa
 */
public class ImgTag extends StorageUrlTag {

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
        return new Img(_valuestack, _request, _response);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        // コンポーネント取得
        Img component_ = (Img) this.getComponent();
        // パラメータセット
        component_.setId(this.id);
        component_.setName(this.name);
        component_.setSrc(this.src);
        component_.setStorageKey(this.storageKey);
        component_.setAlt(this.alt);
        component_.setCssClass(this.cssClass);
        component_.setCssStyle(this.cssStyle);
        component_.setOnclick(this.onclick);
    }

    /** name属性 */
    private String name = "";
    /** src属性 */
    private String src = "";
    /** alt属性 */
    private String alt = "";
    /** cssClass属性 */
    private String cssClass = "";
    /** cssStyle属性 */
    private String cssStyle = "";
    /** onclick属性 */
    private String onclick = "";

    /**
     * @param _name name属性
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _src src属性
     */
    public final void setSrc(String _src) {
        src = _src;
    }

    /**
     * @param _alt alt属性
     */
    public final void setAlt(String _alt) {
        alt = _alt;
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
     * @param _onclick onclick属性
     */
    public final void setOnclick(String _onclick) {
        onclick = _onclick;
    }

}
