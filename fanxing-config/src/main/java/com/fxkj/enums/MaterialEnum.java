package com.fxkj.enums;

/**
 * 素材
 * @author Administrator
 *
 */
public enum MaterialEnum {

	TXT(0, "文本"), PHOTO(1, "图片"), NEWS(2, "图文");
	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private MaterialEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(int code) {
		for (MaterialEnum m : MaterialEnum.values()) {
			if (m.getCode() == code) {
				return m.getDesc();
			}
		}
		return null;
	}

	public static int getCode(String desc) {
		for (MaterialEnum m : MaterialEnum.values()) {
			if (m.getDesc().equals(desc)) {
				return m.getCode();
			}
		}
		return -1;
	}

}
