package com.shufudong.lang.util;

import com.shufudong.lang.exception.BaseRuntimeException;

/**
 * @ClassName: [中]ClassInstantiationException
 * @Description: [中]代表实例化类时失败的异常。
 * @author [中]ShuFuDong
 * @date [中]2015年2月27日 下午10:57:52
 */
public class ClassInstantiationException extends BaseRuntimeException {

    private static final long serialVersionUID = 7054896282777928820L;

    /**
     * [中]构造一个异常, 指明异常的详细信息.
     * 
     * @param message
     *            [中]详细信息
     */
    public ClassInstantiationException(String message) {
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
    public ClassInstantiationException(String message, String code,
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
    public ClassInstantiationException(Class<?> rise, String code, String name,
            Object[] arguments) {
        super(rise, code, name, arguments);
    }
    
    /** 
    * [中]构造一个异常
    * @param rise           [中]对象类
    * @param code           [中]错误编码
    * @param arguments      [中]错误信息参数数组
    */
    public ClassInstantiationException(Class<?> rise,String code,Object[] arguments){
        super(rise,code,null,arguments);
    }
}
