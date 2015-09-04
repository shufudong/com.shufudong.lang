package com.shufudong.lang.util;

import com.shufudong.lang.exception.BaseRuntimeException;

/** 
 * @ClassName:   [中]XmlException 
 * @Description: [中]代表处理xml时抛出的异常.
 * @author       [中]ShuFuDong
 * @date         [中]2015年8月31日 上午12:12:24 
 */
public class XmlException extends BaseRuntimeException {
    
    private static final long serialVersionUID = -1732866460475427633L;
    
    public static final String XML_PAYLOAD_EMPTY = "xml.payload.empty";
    
    public static final String XML_ENCODE_ERROR = "xml.encoding.invalid";
    
    public static final String FILE_NOT_FOUND = "xml.file.not.found";
    
    public static final String XML_PARSE_ERROR = "xml.parse.error";
    
    public static final String XML_READ_ERROR = "xml.read.error";
    
    public static final String XML_VALIDATE_ERROR = "xml.validate.error";
    
    public static final String XML_TRANSFORM_ERROR = "xml.transform.error";
    
    /**
     * [中]构造一个异常, 指明异常的详细信息.
     * 
     * @param message
     *            [中]详细信息
     */
    public XmlException(String message) {
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
    public XmlException(String message, String code,
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
    public XmlException(Class<?> rise, String code, String name,
            Object[] arguments) {
        super(rise, code, name, arguments);
    }
    
    /** 
    * [中]构造一个异常
    * @param rise           [中]对象类
    * @param code           [中]错误编码
    * @param arguments      [中]错误信息参数数组
    */
    public XmlException(Class<?> rise,String code,Object[] arguments){
        super(rise,code,null,arguments);
    }

    /** 
    * [中]构造一个异常
    * @param rise       [中]对象类
    * @param e          [中]异常起因
    */
    public XmlException(Class<?> rise,Throwable e){
        super(rise,e);
    }
}
