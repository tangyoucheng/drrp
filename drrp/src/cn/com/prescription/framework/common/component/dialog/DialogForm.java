/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.component.dialog;

import cn.com.prescription.framework.action.form.AbstractForm;

/**
 * ダイアログform.
 * 
 * @author bpchikazawa
 */
public class DialogForm extends AbstractForm {

    /** 串行版本号 */
    private static final long serialVersionUID = 1L;

    /** メッセージ */
    private String message = "";

    /** 「！」标识 */
    private Boolean isExclamation = false;

    /** 処理中标识 */
    private Boolean isPleaseWait = false;

    /** インフォメーション标识 */
    private Boolean isInfo = false;

    /** インフォメーション标识 */
    private Boolean isInfoId = false;

    /** AJAX通信異常标识 */
    private Boolean isAjaxConnectFailed = false;

    /** 登録完了标识 */
    private Boolean isComplete = false;

    /** 確認标识 */
    private Boolean isConfirm = false;

    /**
     * メッセージを取得します。
     * @return メッセージ
     */
    public final String getMessage() {
        return message;
    }

    /**
     * メッセージを設定します。
     * @param _message メッセージ
     */
    public final void setMessage(String _message) {
        message = _message;
    }

    /**
     * 「！」标识を取得します。
     * @return 「！」标识
     */
    public final Boolean getIsExclamation() {
        return isExclamation;
    }

    /**
     * 「！」标识を設定します。
     * @param _isExclamation 「！」标识
     */
    public final void setIsExclamation(Boolean _isExclamation) {
        isExclamation = _isExclamation;
    }

    /**
     * 処理中标识を取得します。
     * @return 処理中标识
     */
    public final Boolean getIsPleaseWait() {
        return isPleaseWait;
    }

    /**
     * 処理中标识を設定します。
     * @param _isPleaseWait 処理中标识
     */
    public final void setIsPleaseWait(Boolean _isPleaseWait) {
        isPleaseWait = _isPleaseWait;
    }

    /**
     * インフォメーション标识を取得します。
     * @return インフォメーション标识
     */
    public final Boolean getIsInfo() {
        return isInfo;
    }

    /**
     * インフォメーション标识を設定します。
     * @param _isInfo インフォメーション标识
     */
    public final void setIsInfo(Boolean _isInfo) {
        isInfo = _isInfo;
    }

    /**
     * インフォメーション标识を取得します。
     * @return インフォメーション标识
     */
    public Boolean getIsInfoId() {
        return isInfoId;
    }

    /**
     * インフォメーション标识を設定します。
     * @param isInfoId インフォメーション标识
     */
    public void setIsInfoId(Boolean isInfoId) {
        this.isInfoId = isInfoId;
    }

    /**
     * AJAX通信異常标识を取得します。
     * @return AJAX通信異常标识
     */
    public final Boolean getIsAjaxConnectFailed() {
        return isAjaxConnectFailed;
    }

    /**
     * AJAX通信異常标识を設定します。
     * @param _isAjaxConnectFailed AJAX通信異常标识
     */
    public final void setIsAjaxConnectFailed(Boolean _isAjaxConnectFailed) {
        isAjaxConnectFailed = _isAjaxConnectFailed;
    }

    /**
     * 登録完了标识を取得します。
     * @return 登録完了标识
     */
    public final Boolean getIsComplete() {
        return isComplete;
    }

    /**
     * 登録完了标识を設定します。
     * @param _isComplete 登録完了标识
     */
    public final void setIsComplete(Boolean _isComplete) {
        isComplete = _isComplete;
    }

    /**
     * 確認标识を取得します。
     * @return 確認标识
     */
    public final Boolean getIsConfirm() {
        return isConfirm;
    }

    /**
     * 確認标识を設定します。
     * @param _isConfirm 確認标识
     */
    public final void setIsConfirm(Boolean _isConfirm) {
        isConfirm = _isConfirm;
    }

}
