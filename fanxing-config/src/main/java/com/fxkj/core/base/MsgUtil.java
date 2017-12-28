package com.fxkj.core.base;

public class MsgUtil {
	private Boolean success;
	private Object rows;
	private String msg;

	public MsgUtil() {
		super();
	}

	public MsgUtil(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public MsgUtil(Boolean success, Object rows) {
		super();
		this.success = success;
		this.rows = rows;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
	

}
