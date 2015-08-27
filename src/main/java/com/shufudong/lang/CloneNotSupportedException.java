package com.shufudong.lang;

import com.shufudong.lang.exception.ChainedRuntimeException;

/** 
 * @ClassName:   [中]CloneNotSupportedException 
 * @Description: [中]当<code>ObjectUtil.clone</code>方法被调用时，如果被复制的对象不支持该操作，则抛出该异常。
 * <p>注意，和<code>java.lang.CloneNotSupportedException</code>不同，该异常从<code>RuntimeException</code>派生。</p>
 * @author       [中]Maven 
 * @date         [中]2015年8月27日 下午11:00:03 
 */
public class CloneNotSupportedException extends ChainedRuntimeException {

    private static final long serialVersionUID = -4325043378708941044L;
    
    /** 
    * Description:   [中]构造一个空的异常.
    */
    public CloneNotSupportedException() {
        super();
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明异常的详细信息.
    * @param message [中]详细信息
    */
    public CloneNotSupportedException(String message) {
        super(message);
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明引起这个异常的起因.
    * @param cause   [中]异常的起因
    */
    public CloneNotSupportedException(Throwable cause) {
        super(cause);
    }
    
    /** 
    * Description:   [中]构造一个异常, 指明引起这个异常的起因.
    * @param message [中]详细信息
    * @param cause   [中]异常的起因
    */
    public CloneNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

}
