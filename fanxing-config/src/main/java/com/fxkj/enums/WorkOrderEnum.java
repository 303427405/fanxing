package com.fxkj.enums;

public enum WorkOrderEnum {
	
	ACCEPTED(0, "已受理"), HANDLE(1, "正在处理"), COMPLETE(2, "已完成"), CANCELED(3, "已取消");
	private int code;
	private String desc;
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	private WorkOrderEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(int code) {
		for (WorkOrderEnum e : WorkOrderEnum.values()) {
			if (e.getCode() == code) {
				return e.getDesc();
			}
		}
		return null;
	}

	public static int getCode(String desc) {
		for (WorkOrderEnum e : WorkOrderEnum.values()) {
			if (e.getDesc().equals(desc)) {
				return e.getCode();
			}
		}
		return -1;
	}

}
