/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import java.util.ArrayList;
import java.util.List;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * 用户一览 form。
 * 
 * @author fsb
 */
/*
 * 新規作成
 * DATE: 2012.11.09 NAME: fsb
 */
public class RPM00202Form extends AbstractForm {

    /**
     * 串行版本UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ユザID
     */
    private String userId = null;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 页面类型
     */
    private String pageType = null;

    /**
     * subForm1
     */
    private List<RPM0020201Dto> subForm1 = new ArrayList<RPM0020201Dto>();

    /**
     * ユザ情報設定 コントローラオブジェクト的构造。
     */
    public RPM00202Form() {
        super();
    }

    /**
     * ユザID的取得
     * 
     * @return ユザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユザID的设定
     * 
     * @param _userId ユザID
     */
    public void setUserId(String _userId) {
        this.userId = _userId;
    }

    /**
     * 用户名的取得
     * 
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名的设定
     * 
     * @param _userName 用户名
     */
    public void setUserName(String _userName) {
        this.userName = _userName;
    }

    /**
     * 页面类型的取得
     * 
     * @return 页面类型
     */
    public String getPageType() {
        return pageType;
    }

    /**
     * 页面类型的设定
     * 
     * @param _pageType 页面类型
     */
    public void setPageType(String _pageType) {
        this.pageType = _pageType;
    }

    /**
     * subForm1的取得
     * 
     * @return subForm1
     */
    public List<RPM0020201Dto> getSubForm1() {
        return subForm1;
    }

    /**
     * subForm1的设定
     * 
     * @param _subForm1 subForm1
     */
    public void setSubForm1(List<RPM0020201Dto> _subForm1) {
        this.subForm1 = _subForm1;
    }

}
