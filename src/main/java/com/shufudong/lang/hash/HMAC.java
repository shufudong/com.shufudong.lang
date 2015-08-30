package com.shufudong.lang.hash;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName: [中]HMAC
 * @Description: [中]hmac算法
 * @author [中]ShuFuDong
 * @date [中]2015年8月30日 下午3:15:01
 */
public class HMAC {

    /**
     * [中]根据给定密钥生成算法创建密钥
     * 
     * @param algorithm
     *            [中]密钥算法
     * @return [中]密钥
     * @throws RuntimeException
     *             [中]当 {@link java.security.NoSuchAlgorithmException} 发生时
     */
    private static byte[] getHmacKey(String algorithm) {
        // 初始化KeyGenerator
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        // 产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        // 获得密钥
        return secretKey.getEncoded();
    }

    /**
     * [中]获取 HmaMD5的密钥
     * 
     * @return [中]HmaMD5的密钥
     * @throws RuntimeException
     *             [中]当 {@link java.security.NoSuchAlgorithmException} 发生时
     */
    public static byte[] getHmaMD5key() {
        return getHmacKey("HmacMD5");
    }

    /**
     * [中]获取 HmaSHA的密钥
     * 
     * @return [中]HmaSHA的密钥
     * @throws RuntimeException
     *             [中]当 {@link java.security.NoSuchAlgorithmException} 发生时
     */
    public static byte[] getHmaSHAkey() {
        return getHmacKey("HmacSHA1");
    }

    /**
     * [中]获取 HmaSHA256的密钥
     * 
     * @return [中]HmaSHA256的密钥
     * @throws RuntimeException
     *             [中]当 {@link java.security.NoSuchAlgorithmException} 发生时
     */
    public static byte[] getHmaSHA256key() {
        return getHmacKey("HmacSHA256");
    }

    /**
     * [中]获取 HmaSHA384的密钥
     * 
     * @return [中]HmaSHA384的密钥
     * @throws RuntimeException
     *             [中]当 {@link java.security.NoSuchAlgorithmException} 发生时
     */
    public static byte[] getHmaSHA384key() {
        return getHmacKey("HmacSHA384");
    }

    /**
     * [中]获取 HmaSHA512的密钥
     * 
     * @return [中]HmaSHA384的密钥
     * @throws RuntimeException
     *             [中]当{@link java.security.NoSuchAlgorithmException} 发生时
     */
    public static byte[] getHmaSHA512key() {
        return getHmacKey("HmacSHA512");
    }

    /**
     * [中]转换密钥
     * 
     * @param key
     *            [中]二进制密钥
     * @param algorithm
     *            [中]密钥算法
     * @return [中]密钥
     */
    private static Key toKey(byte[] key, String algorithm) {
        // 生成密钥
        return new SecretKeySpec(key, algorithm);
    }

    /**
     * [中]使用HmacMD5消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacMD5(byte[] data, Key key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacMD5");
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return mac.doFinal(data);
    }

    /**
     * [中]使用HmacMD5消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacMD5(byte[] data, byte[] key) {
        Key k = toKey(key, "HmacMD5");
        return encodeHmacMD5(data, k);
    }

    /**
     * [中]使用HmacSHA消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA(byte[] data, Key key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return mac.doFinal(data);
    }

    /**
     * [中]使用HmacSHA消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA(byte[] data, byte[] key) {
        Key k = toKey(key, "HmacSHA1");
        return encodeHmacSHA(data, k);
    }

    /**
     * [中]使用HmacSHA256消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA256(byte[] data, Key key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return mac.doFinal(data);
    }

    /**
     * [中]使用HmacSHA256消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) {
        Key k = toKey(key, "HmacSHA256");
        return encodeHmacSHA256(data, k);
    }

    /**
     * [中]使用HmacSHA384消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA384(byte[] data, Key key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA384");
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return mac.doFinal(data);
    }

    /**
     * [中]使用HmacSHA384消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA384(byte[] data, byte[] key) {
        Key k = toKey(key, "HmacSHA384");
        return encodeHmacSHA384(data, k);
    }

    /**
     * [中]使用HmacSHA512消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA512(byte[] data, Key key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA512");
            mac.init(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return mac.doFinal(data);
    }

    /**
     * [中]使用HmacSHA512消息摘要算法计算消息摘要
     * 
     * @param data
     *            [中]做消息摘要的数据
     * @param key
     *            [中]密钥
     * @return [中]消息摘要(长度为16的字节数组)
     */
    public static byte[] encodeHmacSHA512(byte[] data, byte[] key) {
        Key k = toKey(key, "HmacSHA512");
        return encodeHmacSHA512(data, k);
    }

    private static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (byte b : data) {
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }
}
