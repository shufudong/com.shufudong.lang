 package com.shufudong.lang.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;

/** 
 * @ClassName:      [中]ChainedThrowable 
 * @Description:    [中]实现此接口的异常, 是由另一个异常引起的.
 * @author          [中]ShuFuDong
 * @date            [中]2015年2月27日 下午9:50:51 
 */
public interface ChainedThrowable extends Serializable {
    
    /** 
    * [中]取得异常的起因.
    * @return Throwable [中]异常的起因.
    */
    Throwable getCause();
    
    /** 
    * [中]打印调用栈到标准错误.
    */
    void printStackTrace();
    
    /** 
    * [中]打印调用栈到指定输出流.
    * @param stream [中]输出字节流.
    */
    void printStackTrace(PrintStream stream);
    
    /** 
    * [中]打印调用栈到指定输出流.
    * @param writer [中]输出字符流.
    */
    void printStackTrace(PrintWriter writer);
    
    /** 
    * [中]打印异常的调用栈, 不包括起因异常的信息.
    * @param writer [中]打印到输出流
    */
    void printCurrentStackTrace(PrintWriter writer);
}
