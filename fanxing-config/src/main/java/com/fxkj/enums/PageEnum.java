package com.fxkj.enums;

/**
 * 页面选择条数
 * @author haiyangtao
 *
 */
public enum PageEnum {
	TWENTY(20, 20), FIFTY(50, 50), ONE_HUNDRED(100, 100);
	private int code;
	private int size;

	public int getCode() {
		return code;
	}

	public int getSize() {
		return size;
	}

	private PageEnum(int code, int size) {
		this.code = code;
		this.size = size;
	}

}
