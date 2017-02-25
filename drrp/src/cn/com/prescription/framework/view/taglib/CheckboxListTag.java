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
 * チェックボックスリストタグ
 * 
 * @date 2011/10/11
 * @author bpchikazawa
 */
public class CheckboxListTag extends ComponentTagSupport {

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
    /** カラム・カウント */
    private String columnCount = "";
    /** cssErrorClass */
    private String cssErrorClass = "";

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
        return new CheckboxList(_valuestack);
    }

    /**
     * パラメータ設定.
     */

    @Override
    protected void populateParams() {
        super.populateParams();

        CheckboxList component_ = (CheckboxList) component;
        component_.setName(this.name);
        component_.setKey(this.key);
        component_.setList(this.list);
        component_.setListKey(this.listKey);
        component_.setListValue(this.listValue);
        component_.setColumnCount(columnCount);
        component_.setCssErrorClass(cssErrorClass);

    }

    /**
     * 名称的设定。
     * @param name 名称
     */
    public void setName(String name) {
        this.name = findString(name);
    }

    /**
     * 初期選択値フィールド的设定。
     * @param key 初期選択値フィールド
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * データソース的设定。
     * @param list データソース
     */
    public void setList(String list) {
        this.list = list;
    }

    /**
     * リストキー的设定。
     * @param listKey リストキー
     */
    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    /**
     * リスト値的设定。
     * @param listValue リスト値
     */
    public void setListValue(String listValue) {
        this.listValue = listValue;
    }

    /**
     * カラム・カウント的设定。
     * @param columnCount カラム・カウント
     */
    public void setColumnCount(String columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * カラム・カウント的设定。
     * @param cssErrorClass カラム・カウント
     */
    public void setCssErrorClass(String cssErrorClass) {
        this.cssErrorClass = cssErrorClass;
    }
}