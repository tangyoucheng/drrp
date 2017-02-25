/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.security;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * BASE64変換.
 * 
 * @author nttdc
 */
public class BASE64 {

    /**
     * BASE64復号化
     * 
     * @param value 対象文字列
     * @return 処理結果
     * @throws IOException IO異常
     */
    public static byte[] decryptBASE64(String value) throws IOException {
        return (new BASE64Decoder()).decodeBuffer(value);
    }

    /**
     * BASE64暗号化
     * 
     * @param bytes 対象バイト
     * @return 処理結果
     */
    public static String encryptBASE64(byte[] bytes) {
        return (new BASE64Encoder()).encodeBuffer(bytes);
    }

}
