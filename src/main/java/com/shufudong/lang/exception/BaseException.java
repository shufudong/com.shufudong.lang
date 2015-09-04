package com.shufudong.lang.exception;

import java.text.MessageFormat;
import java.util.Locale;


/** 
 * @ClassName:   [中]BaseException 
 * @Description: [中]异常基类，继承自Excetpion.
 * @author       [中]ShuFuDong
 * @date         [中]2015年9月4日 上午12:56:23 
 */
public class BaseException extends Exception {
    
    private static final long serialVersionUID = -3276905877752908908L;

    public final static String UNKNOWN_EXCEPTION = "UNKNOWN";
    
    private Class<?> rise;
    private String name;
    private String code;
    private Object[] arguments;

    /**
     * [中]生成异常
     * 
     * @param rise      [中]对象类
     * @param code      [中]错误编码
     * @param name      [中]错误名
     */
    public BaseException(Class<?> rise, String code, String name) {
        this(rise, code, name, null);
    }

    /**
     * [中]生成异常
     * 
     * @param rise      [中]对象类
     * @param e         [中]其他异常
     */
    public BaseException(Class<?> rise, Throwable e) {
        this(rise, BaseException.UNKNOWN_EXCEPTION, e.getLocalizedMessage(), null);
    }

    /**
     * [中]生成异常
     * 
     * @param rise      [中]对象类
     * @param code      [中]错误编码
     * @param name      [中]错误名
     * @param arguments [中]错误信息参数数组
     */
    public BaseException(Class<?> rise, String code, String name, Object[] arguments) {
        this.rise = rise;
        this.code = code;
        this.name = name;
        this.arguments = arguments;
    }
    
    /** 
    * [中]生成异常
    * @param rise       [中]对象类
    * @param e          [中]异常
    * @param code       [中]错误编码
    * @param name       [中]错误名
    * @param arguments  [中]错误信息参数数组
    */
    public BaseException(Class<?> rise,Throwable e,String code,String name,Object[] arguments){
        super(e);
        this.rise = rise;
        this.code = code;
        this.name = name;
        this.arguments = arguments;
    }

    /**
     * [中]生成异常
     * 
     * @param code      [中]错误编码
     */
    public BaseException(String code) {
        this.code = code;
    }

    /**
     * [中]生成异常
     * 
     * @param code      [中]错误编码
     * @param name      [中]错误名
     */
    public BaseException(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * [中]生成异常
     * 
     * @param code      [中]错误编码
     * @param e         [中]异常
     */
    public BaseException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    /**
     * [中]生成异常
     * 
     * @param code      [中]错误编码
     * @param name      [中]错误名
     * @param e         [中]异常
     */
    public BaseException(String code, String name, Throwable e) {
        super(e);
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"code\":" + code + ",\"info\":\"" + getMessage() + "\"}";
    }

    public String getCode() {
        return code;
    }

    public Class<?> getRise() {
        return this.rise;
    }

    @Override
    public String getMessage() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public String getLocalizedMessage(Locale locale) {

        String m = null;
        String rtv = null;
        try {
            m = name;
            if (m != null && m.length() > 0 && arguments != null) {
                MessageFormat form = new MessageFormat(m);
                rtv = form.format(arguments);
            } else if (m != null && m.length() > 0 && arguments == null) {
                rtv = m;
            }
        } catch (Throwable t) {
            rtv = name;// rise.getName()+ "." +
            for (int i = 0; arguments != null && i < arguments.length; i++) {
                rtv += i + ":" + arguments[i].toString();
            }
        }
        return rtv;
    }
}
