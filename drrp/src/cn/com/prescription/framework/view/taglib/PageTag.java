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
 * ページ切替カスタムタグ.
 * 
 * @author nttdc
 */
public class PageTag extends ComponentTagSupport {

    /** 串行版本号 */
    private static final long serialVersionUID = -6002576324387156112L;

    /** スタイル */
    private String styleClass;

    /** テーマ */
    private String theme;

    /** インクルード */
    private String includes;

    /** 名称 */
    private String name;

    /** unique标识 */
    private boolean uniqueFlag;

    /**
     * @return テーマ
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param _theme テーマ
     */
    public void setTheme(String _theme) {
        this.theme = _theme;
    }

    /**
     * @return インクルード
     */
    public String getIncludes() {
        return includes;
    }

    /**
     * @param _includes インクルード
     */
    public void setIncludes(String _includes) {
        this.includes = _includes;
    }

    /**
     * @return スタイル
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param _styleClass スタイル
     */
    public void setStyleClass(String _styleClass) {
        this.styleClass = _styleClass;
    }

    /**
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param _name 名称
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * unique标识を取得します。
     * 
     * @return unique标识
     */
    public boolean getUniqueFlag() {
        return uniqueFlag;
    }

    /**
     * unique标识を設定します。
     * 
     * @param uniqueFlag unique标识
     */
    public void setUniqueFlag(boolean uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
    }

    /**
     * Bean取得
     * 
     * @param _stack スタック
     * @param _request リクエスト
     * @param _response レスポンス
     * @return Bean
     */
    @Override
    public Component getBean(ValueStack _stack, HttpServletRequest _request, HttpServletResponse _response) {
        return new Pages(_stack);
    }

    /**
     * パラメータ設定.
     */
    @Override
    protected void populateParams() {
        super.populateParams();

        Pages pages = (Pages) component;
        // pages.setPageStartRowNo(pageStartRowNo);
        pages.setIncludes(includes);
        // pages.setRecordCount(recordCount);
        // pages.setPageSize(pageSize);
        pages.setStyleClass(styleClass);
        pages.setTheme(theme);
        pages.setName(name);
        pages.setUniqueFlag(uniqueFlag);

    }

}
