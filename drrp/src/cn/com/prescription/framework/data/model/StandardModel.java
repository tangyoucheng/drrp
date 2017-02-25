/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.data.model;

import java.io.Serializable;

import cn.com.prescription.framework.action.form.StandartForm;

/**
 * 標準のモデル.
 * 
 * @author nttdc
 */
public class StandardModel extends StandartForm implements Serializable {

    /** 串行版本号 */
    private static final long serialVersionUID = 5867294152297807432L;

    /**
     * 学校スキーマ
     */
    private String gakkouSchema = null;

    /**
     * 学校スキーマ的取得。
     * 
     * @return 学校スキーマ
     */
    public String getGakkouSchema() {
        return gakkouSchema;
    }

    /**
     * 学校スキーマ的设定。
     * 
     * @param _gakkouSchema 学校スキーマ
     */
    public void setGakkouSchema(String _gakkouSchema) {
        this.gakkouSchema = _gakkouSchema;
    }
}
