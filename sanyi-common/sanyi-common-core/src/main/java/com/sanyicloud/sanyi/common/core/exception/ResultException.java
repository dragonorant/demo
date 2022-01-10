package com.sanyicloud.sanyi.common.core.exception;

import com.sanyicloud.sanyi.common.core.enums.ExceptionEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResultException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public ResultException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ResultException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public ResultException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public ResultException(ExceptionEnum exceptionEnum){
		super(exceptionEnum.getEMsg());
		this.msg = exceptionEnum.getEMsg();
		this.code = exceptionEnum.getCode();
	}

	public ResultException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}