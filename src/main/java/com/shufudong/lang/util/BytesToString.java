package com.shufudong.lang.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @ClassName: [中]BytesToString
 * @Description: [中]二进制数组转换成进制字符串
 * @author [中]ShuFuDong
 * @date [中]2015年8月30日 下午2:41:12
 */
public class BytesToString {

    /**
     * @Fields hexDigits : [中]16进制字母表
     */
    private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    /**
     * [中]转换字节数组为10进制字串
     * <p>
     * [中]使用本函数则返回加密结果的10进制数字字串，即全数字形式
     * </p>
     * 
     * @param b     [中]字节数组
     * @return      [中]10进制字串
     */
    public static String byteArrayToNumString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToNumString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * [中]转换字节数组为10进制字串
     * <p>
     * [中]使用本函数则返回加密结果的10进制数字字串，即全数字形式
     * </p>
     * 
     * @param b         [中]字节数组
     * @param pos       [中]开始位置
     * @param length    [中]长度
     * @return          [中]10进制字串
     */
    public static String byteArrayToNumString(byte[] b, int pos, int length) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = pos; i < pos + length; i++) {
            resultSb.append(byteToNumString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * [中]转换字节为10进制字符串
     * 
     * @param b [中]字节
     * @return  [中]10进制字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return "" + hexDigits[d1] + hexDigits[d2];
    }

    /**
     * [中]转换字节数组为16进制字串
     * <p>
     * [中]若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
     * </p>
     * 
     * @param b [中]字节数组
     * @return  [中]16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /** 
    * [中]转换字节数组为16进制字串
    * <p>
    * [中]若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
    * </p>
    * @param b          [中]字节数组
    * @param pos        [中]开始位置
    * @param length     [中]长度
    * @return           [中]16进制字串
    */
    public static String byteArrayToHexString(byte[] b, int pos, int length) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = pos; i < length + pos; i++) {
            resultSb.append(byteToHexString(b[i]));// 若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
        }
        return resultSb.toString();
    }
    
    /** 
    * [中]转换字节为16进制字符串
    * @param b      [中]字节
    * @return       [中]16进制字符串
    */
    private static String byteToNumString(byte b) {
        int _b = b;
        if (_b < 0) {
          _b = 256 + _b;
        }
        return String.valueOf(_b);
    }
    
    /** 
    * [中]16进字符串转换成字节数组
    * @param hexString  [中]hex字符串
    * @return           [中]16进制字串
    */
    public static byte[] hexStringToBytes(String hexString) {  
        if (hexString == null || hexString.equals("")) {  
            return null;  
        }  
        hexString = hexString.toLowerCase();
        int length = hexString.length() / 2;  
        char[] hexChars = hexString.toCharArray();  
        byte[] d = new byte[length];  
        for (int i = 0; i < length; i++) {  
            int pos = i * 2;  
            d[i] = (byte) (Arrays.binarySearch(hexDigits, hexChars[pos]) << 4 |
                    Arrays.binarySearch(hexDigits,hexChars[pos + 1]));  
        }  
        return d;  
    }
    
    /** 
    * [中]转换字符数组内非ASCII编码为%nn的结构字符串
    * @param b      [中]字节数组
    * @return       [中]非ASCII编码为%nn的结构字符串
    */
    public static String byteArrayToURLString(byte[] b) {
        byte ch = 0x00;
        int i = 0;
        if (b == null || b.length <= 0)
            return null;
        String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                          "A", "B", "C", "D", "E", "F"};
        StringBuffer out = new StringBuffer(b.length * 2);
        while (i < b.length) {
            // First check to see if we need ASCII or HEX
            if ((b[i] >= '0' && b[i] <= '9')
                || (b[i] >= 'a' && b[i] <= 'z')
                || (b[i] >= 'A' && b[i] <= 'Z') || b[i] == '$'
                || b[i] == '-' || b[i] == '_' || b[i] == '.'
                || b[i] == '!') {
                out.append((char) b[i]);
                i++;
            } else {
                out.append('%');
                ch = (byte) (b[i] & 0xF0); // Strip off high nibble
                ch = (byte) (ch >>> 4); // shift the bits down
                ch = (byte) (ch & 0x0F); // must do this is high order bit is
                // on!
                out.append(pseudo[ch]); // convert the nibble to a
                // String Character
                ch = (byte) (b[i] & 0x0F); // Strip off low nibble
                out.append(pseudo[ch]); // convert the nibble to a
                // String Character
                i++;
            }
        }
        String rslt = new String(out);
        return rslt;    
    }
    
    /** 
    * [中]将hex数据转换成input流
    * @param hexString  [中]hex字符串
    * @return           [中]input流
    */
    public static InputStream hexStringToInputStream(String hexString) {
        return new BufferedInputStream(new ByteArrayInputStream(hexStringToBytes(hexString)));
    }
    
    /** 
    * [中]读取文件内容
    * @param file       [中]文件路径
    * @return           [中]读取文件内容的16进制字符串
    */
    public static String fileArrayToHexString(File file) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buf = new byte[(int) file.length()];
            int off = 0;
            while (off < buf.length) {
                int i = input.read(buf, off, buf.length - off);
                if (i < 0) {
                    break;
                }
                off += i;
            }
            return BytesToString.byteArrayToHexString(buf);
        } catch (Exception e) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
}
