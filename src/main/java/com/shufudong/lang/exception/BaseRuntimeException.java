package com.shufudong.lang.exception;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @ClassName: [中]BaseRuntimeException
 * @Description: [中]异常基类，继承自RunTimeExcetpion.
 * @author [中]ShuFuDong
 * @date [中]2015年9月4日 上午12:56:49
 */
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -2291119144488238470L;

    public final static String UNKNOWN_EXCEPTION = "UNKNOWN";

    private Class<?> rise;
    private String name;
    private Object[] arguments;
    private String code;

    /**
     * 生成异常
     * 
     * @param rise
     *            对象类
     * @param code
     *            错误编码
     * @param name
     *            错误名
     */
    public BaseRuntimeException(Class<?> rise, String code, String name) {
        this(rise, code, name, null);
    }

    /**
     * 生成异常
     * 
     * @param rise
     *            对象类
     * @param e
     *            其他异常
     */
    public BaseRuntimeException(Class<?> rise, Throwable e) {
        this(rise, BaseException.UNKNOWN_EXCEPTION, e.getLocalizedMessage(),
                null);
    }

    /**
     * 生成异常
     * 
     * @param rise
     *            对象类
     * @param code
     *            错误编码
     * @param name
     *            错误名
     * @param arguments
     *            错误说明数组
     */
    public BaseRuntimeException(Class<?> rise, String code, String name,
            Object[] arguments) {
        this.rise = rise;
        this.code = code;
        this.name = name;
        this.arguments = arguments;
    }

    /**
     * 生成异常
     * 
     * @param code
     *            错误编码
     */
    public BaseRuntimeException(String code) {
        this.code = code;
    }

    /**
     * 生成异常
     * 
     * @param code
     *            错误编码
     * @param name
     *            错误名
     */
    public BaseRuntimeException(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 生成异常
     * 
     * @param code
     *            错误编码
     * @param e
     *            异常
     */
    public BaseRuntimeException(String code, Throwable e) {
        super(e);
        this.code = code;
    }

    /**
     * 生成异常
     * 
     * @param code
     *            错误编码
     * @param name
     *            错误名
     * @param e
     *            异常
     */
    public BaseRuntimeException(String code, String name, Throwable e) {
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
