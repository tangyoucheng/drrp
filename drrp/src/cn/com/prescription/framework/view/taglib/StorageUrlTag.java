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
 * イメージタグ.
 * 
 * @author bpchikazawa
 */
public class StorageUrlTag extends ComponentTagSupport {

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
        return new StorageUrl(_valuestack, _request, _response);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        // コンポーネント取得
        StorageUrl component_ = (StorageUrl) this.getComponent();
        // パラメータセット
        component_.setStorageKey(this.storageKey);
    }

    /** storageKey属性 */
    protected String storageKey = "";

    /**
     * @param _storageKey storageKey属性
     */
    public final void setStorageKey(String _storageKey) {
        storageKey = _storageKey;
    }

}
