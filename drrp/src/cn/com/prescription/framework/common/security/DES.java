/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.common.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * DESアルゴリズム
 * 
 * @author t.d.m
 */
public class DES {

    /** DESアルゴリズム */
    public static final String DES_ALGORITHM = "DES";
    /** DESede(TripleDES)アルゴリズム */
    public static final String DESEDE_ALGORITHM = "DESede";
    /** AESアルゴリズム */
    public static final String AES_ALGORITHM = "AES";
    /** Blowfishアルゴリズム */
    public static final String BLOWFISH_ALGORITHM = "Blowfish";
    /** RC2アルゴリズム */
    public static final String RC2_ALGORITHM = "RC2";
    /** RC4(ARCFOUR)アルゴリズム */
    public static final String RC4_ALGORITHM = "RC4";

    /** 対象アルゴリズム */
    public static final String SELECTED_ALGORITHM = DES_ALGORITHM;

    /**
     * キー生成<br>
     * 
     * @param key 生成元
     * @return Key
     */
    private static Key toKey(byte[] key) {
        return new SecretKeySpec(key, SELECTED_ALGORITHM);
    }

    /**
     * 復号化
     * 
     * @param data 複合対象
     * @param key 複合キー
     * @return 処理結果
     * @throws IOException 予期せぬ错误発生時
     * @throws NoSuchPaddingException 予期せぬ错误発生時
     * @throws NoSuchAlgorithmException 予期せぬ错误発生時
     * @throws InvalidKeyException 予期せぬ错误発生時
     * @throws BadPaddingException 予期せぬ错误発生時
     * @throws IllegalBlockSizeException 予期せぬ错误発生時
     */
    public static byte[] decrypt(byte[] data, String key) throws IOException, NoSuchAlgorithmException,
                    NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key k = toKey(BASE64.decryptBASE64(key));

        Cipher cipher = Cipher.getInstance(SELECTED_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * 暗号化
     * 
     * @param data 暗号対象
     * @param key 暗号キー
     * @return 処理結果
     * @throws NoSuchPaddingException 予期せぬ错误発生時
     * @throws NoSuchAlgorithmException 予期せぬ错误発生時
     * @throws InvalidKeyException 予期せぬ错误発生時
     * @throws BadPaddingException 予期せぬ错误発生時
     * @throws IllegalBlockSizeException 予期せぬ错误発生時
     * @throws IOException 予期せぬ错误発生時
     */
    public static byte[] encrypt(byte[] data, String key) throws IOException, NoSuchAlgorithmException,
                    NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key k = toKey(BASE64.decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(SELECTED_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * SEEDキー生成（ランダム）
     * 
     * @return ランダムSEEDキー
     * @throws IOException 予期せぬ错误発生時
     * @throws NoSuchAlgorithmException 予期せぬ错误発生時
     */
    public static String createKey() throws NoSuchAlgorithmException, IOException {
        return createKey(null);
    }

    /**
     * SEEDキー生成（指定）
     * 
     * @param seed seed
     * @return SEEDキー
     * @throws NoSuchAlgorithmException 予期せぬ错误発生時
     * @throws IOException 予期せぬ错误発生時
     */
    public static String createKey(String seed) throws NoSuchAlgorithmException, IOException {
        SecureRandom secureRandom = null;

        if (seed != null) {
            secureRandom = new SecureRandom(BASE64.decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(SELECTED_ALGORITHM);
        kg.init(secureRandom);

        SecretKey secretKey = kg.generateKey();

        return BASE64.encryptBASE64(secretKey.getEncoded());
    }
}
