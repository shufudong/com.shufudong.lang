package com.shufudong.lang.hash;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.shufudong.lang.util.BytesToString;

/**
 * @ClassName: [中]MD5
 * @Description: [中]md5算法
 * @author [中]ShuFuDong
 * @date [中]2015年8月30日 下午3:34:52
 */
public class MD5 {

    /**
     * [中]将字节数组算出16进制MD5串
     * 
     * @param origin
     *            [中]字节数组
     * @return [中]16进制MD5字符串
     * @throws NoSuchAlgorithmException
     *             [中]当 {@link java.security.NoSuchAlgorithmException}发生时
     */
    public static String Encode16(byte[] origin)
            throws NoSuchAlgorithmException {
        String resultString = null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        resultString = BytesToString.byteArrayToHexString(md.digest(origin));
        return resultString;
    }

    /**
     * [中]将字符串算出16进制MD5串，字符串按系统编码进行编码
     * 
     * @param origin
     *            [中]字符串
     * @return [中]16进制MD5字符串
     * @throws NoSuchAlgorithmException
     */
    public static String Encode16(String origin)
            throws NoSuchAlgorithmException {
        return Encode16(origin.getBytes());
    }

    /**
     * [中]将输入流电池16禁止MD5字符串
     * 
     * @param inputStream
     *            [中]输入流
     * @return [中]MD5摘要字符串
     * @throws NoSuchAlgorithmException
     *             [中]当 {@link java.security.NoSuchAlgorithmException}发生时
     * @throws IOException
     *             [中]当 {@link java.io.IOException}发生时
     */
    public static String Encode16(InputStream inputStream)
            throws NoSuchAlgorithmException, IOException {
        String resultString = null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream mdfile = new DigestInputStream(inputStream, md);
        int len = 64 * 1024;
        byte buf[] = new byte[len];
        while (mdfile.read(buf, 0, len) != -1) {
        }
        resultString = BytesToString.byteArrayToHexString(md.digest());
        return resultString;
    }
}
