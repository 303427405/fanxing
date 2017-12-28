package com.fxkj.enums;

public enum MediaEnum {

	IMAGE(0, "image"), VOICE(1, "voice"), VIDEO(2, "video"), THUMB(3, "thumb");
	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private MediaEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(int code) {
		for (MediaEnum m : MediaEnum.values()) {
			if (m.getCode() == code) {
				return m.getDesc();
			}
		}
		return null;
	}

	public static int getCode(String desc) {
		for (MediaEnum m : MediaEnum.values()) {
			if (m.getDesc().equals(desc)) {
				return m.getCode();
			}
		}
		return -1;
	}
}
