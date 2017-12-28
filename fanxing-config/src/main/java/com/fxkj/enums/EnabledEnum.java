package com.fxkj.enums;

public enum EnabledEnum {
	ENABLED(1, "启用"), DISABLE(0, "禁用");
	private int code;
	private String desc;
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	private EnabledEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(int code) {
		for (EnabledEnum e : EnabledEnum.values()) {
			if (e.getCode() == code) {
				return e.getDesc();
			}
		}
		return null;
	}

	public static int getCode(String desc) {
		for (EnabledEnum e : EnabledEnum.values()) {
			if (e.getDesc().equals(desc)) {
				return e.getCode();
			}
		}
		return -1;
	}

}
