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
 * ダウンロード開始タグ.
 * 
 * @author bpchikazawa
 */
public class DownloadTag extends ComponentTagSupport {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

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
        return new Download(_valuestack);
    }

}
