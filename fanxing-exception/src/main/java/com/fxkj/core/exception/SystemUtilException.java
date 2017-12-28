package com.fxkj.core.exception;

/**
 * 系统错误 异常类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SystemUtilException extends RuntimeException {
	
	public SystemUtilException() {
		super();
	}

	public SystemUtilException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SystemUtilException(String arg0) {
		super(arg0);
	}

	public SystemUtilException(Throwable arg0) {
		super(arg0);
	}

}
