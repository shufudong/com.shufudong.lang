package com.shufudong.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

/** 
 * @ClassName:   [中]ExceptionUtil 
 * @Description: [中]处理异常的工具类。
 * @author       [中]ShuFuDong
 * @date         [中]2015年8月27日 下午11:03:01 
 */
public class ExceptionUtil {
    
    /** 
    * @Description:     [中]取得异常的stacktrace字符串。
    * @param throwable  [中]异常
    * @return           [中]字符串
    */
    public static String getStackTrace(Throwable throwable) {
        StringWriter buffer = new StringWriter();
        PrintWriter out = new PrintWriter(buffer);
        throwable.printStackTrace(out);
        out.flush();
        return buffer.toString();
    }
}
