/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import cn.com.prescription.framework.common.security.BASE64;
import cn.com.prescription.framework.common.security.DES;
import cn.com.prescription.framework.exception.SystemException;

/**
 * セキュリティユーティリティクラス。
 * 
 * @author t.d.m
 */
public class AclUtils {

    /** 暗号化キー */
    private static final String PASSWORD_CRYPT_KEY = "_RP2016_";

    /**
     * AclUtils的构造
     */
    private AclUtils() {
        super();
    }

    /**
     * パースワード暗号化
     * 
     * @param _value パースワード
     * @return 暗号化後パースワード
     * @throws SystemException
     * @throws SystemException 予期せぬ错误発生時
     */
    public static String encryptPassword(String _value) throws SystemException {
        String encryptPassword = null;
        try {
            String key = DES.createKey(PASSWORD_CRYPT_KEY);
            StringBuffer tempStr = new StringBuffer();
            tempStr.append("{");
            tempStr.append(key);
            tempStr.append("}");
            byte[] bytes = DES.encrypt(_value.getBytes(), key);
            tempStr.append(BASE64.encryptBASE64(bytes));
            encryptPassword = tempStr.toString();
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
        return encryptPassword;

    }

    /**
     * パースワード復号化
     * 
     * @param _value 暗号化後パースワード
     * @return パースワード
     * @throws SystemException 予期せぬ错误発生時
     */
    public static String decryptPassword(String _value) throws SystemException {
        byte[] decryptBytes = null;
        try {
            String _key = _value.substring(_value.indexOf("{") + 1, _value.indexOf("}") - 1);
            decryptBytes = DES.decrypt(BASE64.decryptBASE64(_value.substring(_value.indexOf("}") + 1)), _key);
        } catch (Exception e) {
            throw new SystemException(e.getMessage());
        }
        return new String(decryptBytes);
    }
}
