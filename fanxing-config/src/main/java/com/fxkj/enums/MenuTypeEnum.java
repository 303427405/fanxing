package com.fxkj.enums;

public enum MenuTypeEnum {

	MENU(1, "菜单"), BUTTON(2, "按钮");
	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	private MenuTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDesc(int code) {
		for (MenuTypeEnum m : MenuTypeEnum.values()) {
			if (m.getCode() == code) {
				return m.getDesc();
			}
		}
		return null;
	}

	public static int getCode(String desc) {
		for (MenuTypeEnum m : MenuTypeEnum.values()) {
			if (m.getDesc().equals(desc)) {
				return m.getCode();
			}
		}
		return -1;
	}
}
