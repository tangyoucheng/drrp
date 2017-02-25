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
 * スタイル指定可能な <select> タグ.
 * 
 * @author bpchikazawa
 */
public class SelectTag extends ComponentTagSupport {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** 名称 */
    private String name = "";
    /** 初期選択値フィールド */
    private String key = "";
    /** データソース */
    private String list = "";
    /** リストキー */
    private String listKey = "";
    /** リスト値 */
    private String listValue = "";
    /** OnChangeスクリプト */
    private String onChange = "";

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
        return new Select(_valuestack);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        // コンポーネント取得
        Select component_ = (Select) this.getComponent();
        // パラメータセット
        component_.setName(this.name);
        component_.setKey(this.key);
        component_.setList(this.list);
        component_.setListKey(this.listKey);
        component_.setListValue(this.listValue);
        component_.setOnChange(this.onChange);
    }

    /**
     * @param _name 名称
     */
    public final void setName(String _name) {
        name = _name;
    }

    /**
     * @param _key 初期選択値フィールド
     */
    public final void setKey(String _key) {
        key = _key;
    }

    /**
     * @param _list データソース
     */
    public final void setList(String _list) {
        list = _list;
    }

    /**
     * @param _listKey リストキー
     */
    public final void setListKey(String _listKey) {
        listKey = _listKey;
    }

    /**
     * @param _listValue リスト値
     */
    public final void setListValue(String _listValue) {
        listValue = _listValue;
    }

    /**
     * @param _onChange OnChangeスクリプト
     */
    public final void setOnChange(String _onChange) {
        onChange = _onChange;
    }

}
