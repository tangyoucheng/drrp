/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.event;

import java.util.Collection;

import cn.com.prescription.framework.action.form.StandartForm;
import cn.com.prescription.framework.biz.ActionInfo;

import org.intra_mart.framework.base.event.EventResult;

/**
 * 標準のevent処理結果.
 * 
 * @author nttdc
 */
public class StandardEventResult implements EventResult {

    /** 串行版本号 */
    private static final long serialVersionUID = -5524048408820374404L;

    /** form */
    private StandartForm form = null;

    /** ダウンロード标识 */
    private Boolean isDownload = false;

    /** ダウンロードパス */
    private String downloadPath = "";

    /** ダウンロードファイル名 */
    private String downloadFile = "";

    /** 登録完了ダイアログ标识 */
    private Boolean isCompleteDialog = false;

    /** メッセージ */
    private Collection<String> messages = null;

    /** action */
    private ActionInfo actionInfo = null;

    /**
     * @return form
     */
    public StandartForm getForm() {
        return form;
    }

    /**
     * @param _form form
     */
    public void setForm(StandartForm _form) {
        this.form = _form;
    }

    /**
     * @param _isDownload ダウンロード開始标识
     */
    public final void setIsDownload(Boolean _isDownload) {
        isDownload = _isDownload;
    }

    /**
     * @return ダウンロード開始标识
     */
    public final Boolean getIsDownload() {
        return isDownload;
    }

    /**
     * @param _downloadPath ダウンロードファイルパス
     */
    public final void setDownloadPath(String _downloadPath) {
        downloadPath = _downloadPath;
    }

    /**
     * @return ダウンロードファイルパス
     */
    public final String getDownloadPath() {
        return downloadPath;
    }

    /**
     * @param _downloadFile ダウンロードファイル名
     */
    public final void setDownloadFile(String _downloadFile) {
        downloadFile = _downloadFile;
    }

    /**
     * @return ダウンロードファイル名
     */
    public final String getDownloadFile() {
        return downloadFile;
    }

    /**
     * @return 登録完了ダイアログ标识
     */
    public final Boolean getIsCompleteDialog() {
        return isCompleteDialog;
    }

    /**
     * @param _isCompleteDialog 登録完了ダイアログ标识
     */
    public final void setIsCompleteDialog(Boolean _isCompleteDialog) {
        isCompleteDialog = _isCompleteDialog;
    }

    /**
     * @return メッセージ
     */
    public final Collection<String> getMessages() {
        return messages;
    }

    /**
     * @param _messages メッセージ
     */
    public final void setMessages(Collection<String> _messages) {
        messages = _messages;
    }

    /**
     * @return action
     */
    public final ActionInfo getActionInfo() {
        return actionInfo;
    }

    /**
     * @param _actionInfo action
     */
    public final void setActionInfo(ActionInfo _actionInfo) {
        actionInfo = _actionInfo;
    }

}
