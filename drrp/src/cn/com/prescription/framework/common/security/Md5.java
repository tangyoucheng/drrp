package cn.com.prescription.framework.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.com.prescription.framework.exception.SystemException;

/**
 * MD5で暗号化する。
 */
/*
 * 新規作成
 * DATE: 2012.02.15 NAME: y.h
 */
public class Md5 {
    /**
     *  MD5で暗号化する
     * @param  _str 暗号化対象文字列
     * @return result　暗号化文字列
     * @throws SystemException 系统错误
     */
    public static String digestMd5(String _str) throws SystemException {
    	
        if (_str == null || _str.length() == 0) {   
        	throw new SystemException();   
        }

        byte[] hash = null ;

        try{
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(_str.getBytes());
		    hash = md.digest();
        }catch(NoSuchAlgorithmException e){
        	throw new SystemException();
        }

        return bytesToHexString(hash);   
    }

    /**
     *  バイト配列を16進数文字列へ変換する
     * @param  _hash バイト配列
     * @return hexString 16進数文字列
     */
    private static String bytesToHexString(byte []_hash) {   
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < _hash.length; i++) {   
            if ((0xff & _hash[i]) < 0x10) {   
                hexString.append("0" + Integer.toHexString((0xFF & _hash[i])));   
            } else {   
                hexString.append(Integer.toHexString(0xFF & _hash[i]));   
            }   
        }   
           
        return hexString.toString();   
    }  
    
}
