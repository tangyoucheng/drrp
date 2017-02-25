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
 * パンくずリストタグ.
 * 
 * @author bpchikazawa
 */
public class MenuNavigationTag extends ComponentTagSupport {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** 画面名 */
    private String gamenName = "";

    /**
     * @param _gamenName 画面名
     */
    public final void setGamenName(String _gamenName) {
        gamenName = _gamenName;
    }

    /**
     * タグ生成コンポーネント
     * 
     * @param _valuestack スタック
     * @param _httpservletrequest リクエスト
     * @param _httpservletresponse レスポンス
     * @return コンポーネント
     */
    @Override
    public Component getBean(ValueStack _valuestack, HttpServletRequest _httpservletrequest,
                    HttpServletResponse _httpservletresponse) {
        return new MenuNavigation(_valuestack);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        // コンポーネント取得
        MenuNavigation component_ = (MenuNavigation) this.getComponent();
        // 画面名
        component_.setGamenName(this.gamenName);
    }

}
