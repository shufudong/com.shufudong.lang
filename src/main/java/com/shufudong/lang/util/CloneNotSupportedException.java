package com.shufudong.lang.util;

import com.shufudong.lang.exception.BaseRuntimeException;

/** 
 * @ClassName:   [中]CloneNotSupportedException 
 * @Description: [中]当<code>ObjectUtil.clone</code>方法被调用时，如果被复制的对象不支持该操作，则抛出该异常。
 * <p>注意，和<code>java.lang.CloneNotSupportedException</code>不同，该异常从<code>RuntimeException</code>派生。</p>
 * @author       [中]ShuFuDong
 * @date         [中]2015年2月8日 下午11:00:03 
 */
public class CloneNotSupportedException extends BaseRuntimeException {

    private static final long serialVersionUID = -4325043378708941044L;
    
    /**
     * [中]构造一个异常, 指明异常的详细信息.
     * 
     * @param message
     *            [中]详细信息
     */
    public CloneNotSupportedException(String message) {
        super(message);
    }

    /**
     * [中]构造一个异常, 指明引起这个异常的起因.
     * 
     * @param message
     *            [中]详细信息
     * @param cause
     *            [中]异常的起因
     */
    public CloneNotSupportedException(String message, String code,
            Throwable cause) {
        super(message, code, cause);
    }

    /** 
    * [中]构造一个异常
    * @param rise           [中]对象类
    * @param code           [中]错误编码
    * @param name           [中]错误名
    * @param arguments      [中]错误信息参数数组
    */
    public CloneNotSupportedException(Class<?> rise, String code, String name,
            Object[] arguments) {
        super(rise, code, name, arguments);
    }
    
    /** 
    * [中]构造一个异常
    * @param rise           [中]对象类
    * @param code           [中]错误编码
    * @param arguments      [中]错误信息参数数组
    */
    public CloneNotSupportedException(Class<?> rise,String code,Object[] arguments){
        super(rise,code,null,arguments);
    }
    
    /** 
    * [中]构造一个异常
    * @param rise       [中]对象类
    * @param e          [中]异常的起因
    */
    public CloneNotSupportedException(Class<?> rise,Throwable cause){
        super(rise,cause);
    }
}
