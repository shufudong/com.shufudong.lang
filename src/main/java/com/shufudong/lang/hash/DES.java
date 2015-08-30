package com.shufudong.lang.hash;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.shufudong.lang.util.BytesToString;

/**
 * @ClassName: [中]DES
 * @Description: [中]DES加解密
 * @author [中]ShuFuDong
 * @date [中]2015年8月30日 下午2:40:06
 */
public class DES {

    /**
     * [中]解密
     * 
     * @param string
     *            [中]密文
     * @param key
     *            [中]密匙
     * @return [中]原文
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String decode16(String string, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        return BytesToString.byteArrayToHexString(decode(string.getBytes(),
                key.getBytes()));
    }

    /**
     * [中]解密
     * 
     * @param string
     *            [中]密文
     * @param key
     *            [中]密匙
     * @return [中]原文
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String decode(String string, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        return new String(decode(string.getBytes(), key.getBytes()));
    }

    /**
     * [中]解密
     * 
     * @param string
     *            [中]密文
     * @param key
     *            [中]密匙
     * @return [中]原文
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decode(byte[] string, byte[] key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(string);

    }

    /**
     * [中]加密
     * 
     * @param string
     *            [中]原文
     * @param key
     *            [中]密匙
     * @return [中]密文
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *             [中]设定文件
     */
    public static String encode16(String string, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        return BytesToString.byteArrayToHexString(encode(string.getBytes(),
                key.getBytes()));
    }

    /**
     * [中]加密
     * 
     * @param string
     *            [中]原文
     * @param key
     *            [中]密匙
     * @return [中]密文
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *             [中]设定文件
     */
    public static String encode(String string, String key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        return new String(encode(string.getBytes(), key.getBytes()));
    }

    /**
     * [中]加密
     * 
     * @param string
     *            [中]原文
     * @param key
     *            [中]密匙
     * @return [中]密文
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     *             [中]设定文件
     */
    public static byte[] encode(byte[] string, byte[] key)
            throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(string);
    }
}
