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
public class DbExclusiveChainListInfo implements Serializable {

    /** セッションキー */
    public static final String SESSION_KEY = "DbExclusiveChainListInfo";

    /** 串行版本号 */
    private static final long serialVersionUID = 3483944024190993834L;
    
    /** 項目名 */
    private String name = "";
    
    /** 本番データ */
    private String data = "";
    
    /** 仮入力 */
    private String kariData = "";

    /**
     * コンストラクタ.
     */
    public DbExclusiveChainListInfo() {
        super();
    }

    /**
     * コンストラクタ.
     * 
     * @param _fieldID 最終更新日時保持フィールドID
     * @param _chainRecordDate 処理続行時に上書きする最終更新日時
     * @param _cancelRecordDate 処理キャンセル時に上書きする最終更新日時
     */
    public DbExclusiveChainListInfo(String _name, String _data, String _kariData) {
        this.setName(_name);
        this.setData(_data);
        this.setKariData(_kariData);
    }

    /**
     * 項目名的取得。
     * 
     * @return 項目名
     */
    public String getName() {
        return name;
    }

    /**
     * 項目名的设定。
     * 
     * @param _name 項目名
     */
    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * 本番データ的取得。
     * 
     * @return 本番データ
     */
    public String getData() {
        return data;
    }

    /**
     * 本番データ的设定。
     * 
     * @param _data 本番データ
     */
    public void setData(String _data) {
        this.data = _data;
    }

    /**
     * 仮入力的取得。
     * 
     * @return 仮入力
     */
    public String getKariData() {
        return kariData;
    }

    /**
     * 仮入力的设定。
     * 
     * @param _kariData 仮入力
     */
    public void setKariData(String _kariData) {
        this.kariData = _kariData;
    }

}
