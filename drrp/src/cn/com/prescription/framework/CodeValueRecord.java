/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework;

import java.io.Serializable;

/**
 * コード値レコード
 * 
 * @author ztg
 */
public class CodeValueRecord implements Serializable {

    /**
     * 串行版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * レコードのコード
     */
    private String recordCode = null;

    /**
     * レコードの値
     */
    private String recordValue = null;

    /**
     * コンストラクタ.
     */
    public CodeValueRecord() {
        super();
    }

    /**
     * コンストラクタ.
     * 
     * @param _recordCode コード
     * @param _recordValue 表記
     */
    public CodeValueRecord(String _recordCode, String _recordValue) {
        super();
        recordCode = _recordCode;
        recordValue = _recordValue;
    }

	/**
	 * レコードのコードを取得します。
	 * @return レコードのコード
	 */
	public String getRecordCode() {
	    return recordCode;
	}

	/**
	 * レコードのコードを設定します。
	 * @param recordCode レコードのコード
	 */
	public void setRecordCode(String recordCode) {
	    this.recordCode = recordCode;
	}

	/**
	 * レコードの値を取得します。
	 * @return レコードの値
	 */
	public String getRecordValue() {
	    return recordValue;
	}

	/**
	 * レコードの値を設定します。
	 * @param recordValue レコードの値
	 */
	public void setRecordValue(String recordValue) {
	    this.recordValue = recordValue;
	}

}
