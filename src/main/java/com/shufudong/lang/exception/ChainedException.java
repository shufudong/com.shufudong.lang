package com.shufudong.lang.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/** 
 * @ClassName:   [中]ChainedException 
 * @Description: [中]可嵌套的异常.
 * @author       [中]ShuFuDong
 * @date         [中]2015年2月27日 下午10:49:03 
 */
public class ChainedException extends Exception implements ChainedThrowable {

    private static final long serialVersionUID = 8924945492399183157L;
    private final ChainedThrowable delegate = new ChainedThrowableDelegate(this);
    private Throwable cause;
    
    /** 
    * Description:   [中]构造一个空的异常.
    */
    public ChainedException() {
        super();
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明异常的详细信息.
    * @param message [中]详细信息
    */
    public ChainedException(String message) {
        super(message);
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明引起这个异常的起因.
    * @param cause   [中]异常的起因
    */
    public ChainedException(Throwable cause) {
        super((cause == null) ? null : cause.getMessage());
        this.cause = cause;
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明引起这个异常的起因.
    * @param message [中]详细信息
    * @param cause   [中]异常的起因
    */
    public ChainedException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }
    
    /* (non-Javadoc)
    * Description: [中]取得引起这个异常的起因.
    * @return      [中]异常的起因.
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
    * Description:  [中]打印调用栈到指定输出流.
    * @param stream [中]输出字节流.
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
     * Description:     [中]打印异常的调用栈, 不包括起因异常的信息.
     * @param writer    [中]打印到输出流
     * @see com.shufudong.lang.exception.ChainedThrowable#printCurrentStackTrace(java.io.PrintWriter)
     */
    @Override
    public void printCurrentStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
    }
}
