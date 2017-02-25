/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.biz;

import java.io.Serializable;

/**
 * 排他错误時の処理継続情報保持Bean.
 * 
 * @author bpchikazawa
 */
public class DbExclusiveChainInfo implements Serializable {

    /** セッションキー */
    public static final String SESSION_KEY = "DbExclusiveChainInfo";

    /** 串行版本号 */
    private static final long serialVersionUID = 3483944024190993834L;

    /** 最終更新日時保持フィールドID */
    private String fieldID = "";

    /** 処理続行時に上書きする最終更新日時 */
    private String chainRecordDate = "";

    /** 処理キャンセル時に上書きする最終更新日時 */
    private String cancelRecordDate = "";

    /**
     * コンストラクタ.
     */
    public DbExclusiveChainInfo() {
        super();
    }

    /**
     * コンストラクタ.
     * 
     * @param _fieldID 最終更新日時保持フィールドID
     * @param _chainRecordDate 処理続行時に上書きする最終更新日時
     * @param _cancelRecordDate 処理キャンセル時に上書きする最終更新日時
     */
    public DbExclusiveChainInfo(String _fieldID, String _chainRecordDate, String _cancelRecordDate) {
        this.setFieldID(_fieldID);
        this.setChainRecordDate(_chainRecordDate);
        this.setCancelRecordDate(_cancelRecordDate);
    }

    /**
     * @return 最終更新日時保持フィールドID
     */
    public final String getFieldID() {
        return fieldID;
    }

    /**
     * @param _fieldID 最終更新日時保持フィールドID
     */
    public final void setFieldID(String _fieldID) {
        fieldID = _fieldID;
    }

    /**
     * @return 処理続行時に上書きする最終更新日時
     */
    public final String getChainRecordDate() {
        return chainRecordDate;
    }

    /**
     * @param _chainRecordDate 処理続行時に上書きする最終更新日時
     */
    public final void setChainRecordDate(String _chainRecordDate) {
        chainRecordDate = _chainRecordDate;
    }

    /**
     * @return 処理キャンセル時に上書きする最終更新日時
     */
    public final String getCancelRecordDate() {
        return cancelRecordDate;
    }

    /**
     * @param _cancelRecordDate 処理キャンセル時に上書きする最終更新日時
     */
    public final void setCancelRecordDate(String _cancelRecordDate) {
        cancelRecordDate = _cancelRecordDate;
    }

}
