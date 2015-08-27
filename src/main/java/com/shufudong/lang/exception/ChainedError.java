/**   
* @Title: 		[中]ChainedError.java 
* @Package 		[中]com.shufudong.lang.exception 
* @Description: [中]可嵌套的异常.
* @author 		[中]ShuFuDong
* @date 		[中]2015年8月27日 下午10:36:29 
* @version 		[中]V1.0   
*/ 
package com.shufudong.lang.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/** 
 * @ClassName: 	    [中]ChainedError 
 * @Description:    [中]可嵌套的异常.
 * @author          [中]ShuFuDong
 * @date            [中]2015年8月27日 下午10:36:29 
 */
public class ChainedError extends Error implements ChainedThrowable {
    
    private static final long serialVersionUID = 3762250833760368436L;
    private final ChainedThrowable delegate = new ChainedThrowableDelegate(this);
    private Throwable cause;
    
    /**
    * Description: 	[中]构造一个空的异常.
    */
    public ChainedError() {
        super();
    }
    
    /** 
    * Description:      [中]构造一个异常, 指明异常的详细信息.
    * @param message    [中]详细信息
    */
    public ChainedError(String message) {
        super(message);
    }
    
    /** 
    * Description:      [中]构造一个异常, 指明引起这个异常的起因.
    * @param cause      [中]异常的起因
    */
    public ChainedError(Throwable cause) {
        super((cause == null) ? null : cause.getMessage());
        this.cause = cause;
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明引起这个异常的起因.
    * @param message [中]详细信息
    * @param cause   [中]异常的起因
    */
    public ChainedError(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }
    
    /* (non-Javadoc)
    * <p>Description: [中]取得引起这个异常的起因.</p> 
    * @return         [中]异常的起因.
    * @see java.lang.Throwable#getCause()
    */
    @Override
    public Throwable getCause() {
        return cause;
    }
    
    /* (non-Javadoc)
    * Description: [中]打印调用栈到标准错误.
    * @see java.lang.Throwable#printStackTrace()
    */
    @Override
    public void printStackTrace() {
        delegate.printStackTrace();
    }
    
    /* (non-Javadoc)
    * Description:      [中]打印调用栈到指定输出流.
    * @param stream     [中]输出字节流.
    * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
    */
    @Override
    public void printStackTrace(PrintStream stream) {
        delegate.printStackTrace(stream);
    }
    
    /* (non-Javadoc)
    * Description:  [中]打印调用栈到指定输出流.
    * @param writer [中]输出字符流.
    * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
    */
    @Override
    public void printStackTrace(PrintWriter writer) {
        delegate.printStackTrace(writer);
    }

    
    /* (non-Javadoc)
    * Description:  [中]打印异常的调用栈, 不包括起因异常的信息.
    * @param writer [中]打印到输出流
    * @see com.shufudong.lang.exception.ChainedThrowable#printCurrentStackTrace(java.io.PrintWriter)
    */
    @Override
    public void printCurrentStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
    }

}
