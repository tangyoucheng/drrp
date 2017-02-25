/*
 * Copyright(c) 2016 
 */

package cn.com.prescription.leshan.rpm.action.form;

import java.util.List;

import cn.com.prescription.framework.CodeValueRecord;
import cn.com.prescription.framework.action.form.AbstractForm;
import cn.com.prescription.framework.common.session.UserSessionInfo;

/**
 * 登录画面form。
 * 
 * @author tyc
 */
/*
 * 新規作成
 * DATE: 2016.03.20 NAME: tyc
 */
public class RPM00101Form extends AbstractForm {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId = null;

    /**
     * 密码
     */
    private String password = null;

    /**
     * 用户情報
     */
    private UserSessionInfo userInfo = null;

    /**
     * 错误
     */
    private String errMsg = null;

    /**
     * 次画面ID
     */
    private String gamenId = null;

    /**
     * 登录标识
     */
    private String flg = null;

    /**
     * 地区管理員标识
     */
    private String chikuFlg = null;

    /**
     * クライアントIPアドレス
     */
    // private String ipAddr = null;
    /**
     * 店铺代码
     */
    private String storeCode;
    /**
     * 店铺代码DataSource
     */
    private List<CodeValueRecord> storeCodeDataSource;

    /**
     * 登录画面的构造。
     */
    public RPM00101Form() {
        super();
    }

    /**
     * 用户ID的取得
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID的设定
     * @param _userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 密码的取得
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码的设定
     * @param _password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户情報的取得
     * @return 用户情報
     */
    public UserSessionInfo getUserInfo() {
        return userInfo;
    }

    /**
     * 用户情報的设定
     * @param _userInfo 用户情報
     */
    public void setUserInfo(UserSessionInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 错误的取得
     * @return 错误
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 错误的设定
     * @param _errMsg 错误
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 次画面ID的取得
     * @return 次画面ID
     */
    public String getGamenId() {
        return gamenId;
    }

    /**
     * 次画面ID的设定
     * @param _gamenId 次画面ID
     */
    public void setGamenId(String gamenId) {
        this.gamenId = gamenId;
    }

    /**
     * 登录标识的取得
     * @return 登录标识
     */
    public String getFlg() {
        return flg;
    }

    /**
     * 登录标识的设定
     * @param __flg 登录标识
     */
    public void setFlg(String _flg) {
        this.flg = _flg;
    }

    /**
     * 地区管理員标识的取得
     * @return 地区管理員标识
     */
    public String getChikuFlg() {
        return chikuFlg;
    }

    /**
     * 地区管理員标识的设定
     * @param __chikuFlg 地区管理員标识
     */
    public void setChikuFlg(String _chikuFlg) {
        this.chikuFlg = _chikuFlg;
    }

    /**
     * クライアントIPアドレス的取得
     * @return クライアントIPアドレス
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * クライアントIPアドレス的设定
     * @param _storeCode クライアントIPアドレス
     */
    public void setStoreCode(String _storeCode) {
        this.storeCode = _storeCode;
    }

    /**
     * 店铺代码DataSource的取得
     * @return 店铺代码DataSource
     */
    public List<CodeValueRecord> getStoreCodeDataSource() {
        return storeCodeDataSource;
    }

    /**
     * 店铺代码DataSource的设定
     * @param _storeCodeDataSource 店铺代码DataSource
     */
    public void setStoreCodeDataSource(List<CodeValueRecord> _storeCodeDataSource) {
        this.storeCodeDataSource = _storeCodeDataSource;
    }

}
