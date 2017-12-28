package com.fxkj.core.common;

import java.io.File;

import com.fxkj.enums.PageEnum;


public class Commons {
	
	/**
	 * 手机注册时用户所属组织
	 */
	public static String ANONYMOUSUSER_ORGCODE="00_anonymousUser";

	/**
	 * redis存入url的key
	 */
	public static byte[] URLBYTE = "metadataSourceUrl".getBytes();
	/**
	 * redis存入btn的key
	 */
	public static byte[] BTNBYTE = "metadataSourceBtn".getBytes();

	/**
	 * 国家编码（用于判断用户是不是全国用户）
	 */
	public static String COUNTRYCODE = "00";

	/**
	 * 系统默认密码
	 */
	public final static String PASSWORD_DEFAULT = "123456";

	/**
	 * 快捷方式树名称样式
	 */
	public final static String SHORTCUT_TREE_NAME_STYLE = "<i class='iconfont %s'></i>&nbsp";

	/**
	 * 创建快捷方式的总数
	 */
	public final static int SHORTCUT_COUNT = 5;

	/**
	 * 系统默认每页显示条数
	 */
	public final static Integer PAGE_SIZE = PageEnum.values()[0].getCode();

	/**
	 * 页数分割
	 */
	public final static int PAGE_SPLIT = 5;

	/**
	 * 系统默认允许上传多少兆的图片
	 */
	public final static double UPLOADIMGSIZE = 5;

	/**
	 * 允许上传图片类型
	 */
	public final static String UPLOADIMGTYPE = ".jpg;.jpeg;.gif;.png";

	/**
	 * 用户头像路径
	 */
	public final static String OPERATOR_HEAD_IMG_PATH = "img" + File.separator
			+ "headPhoto" + File.separator;

	/**
	 * 用户头像大小
	 */
	public final static Double OPERATOR_HEAD_IMG_size = 5.0;

	/**
	 * 素材图片路径
	 */
	public final static String MATERIAL_IMG_PATH = "img" + File.separator
			+ "material" + File.separator;
	/**
	 * 素材图片大小
	 */
	public final static Double MATERIAL_IMG_size = 0.5;

	/**
	 * 公众号logo路径
	 */
	public final static String WECHAT_LOGO_IMG_PATH = "img" + File.separator
			+ "wechat_logo" + File.separator;

	/**
	 * 公众号logo大小
	 */
	public final static Double WECHAT_LOGO_IMG_Size = 5.0;
	
	
	/**
	 * 商品图片路径
	 */
	public final static String PRODUCT_IMG_PATH = "img" + File.separator
			+ "product" + File.separator;
	

	/**
	 * 商品图片大小
	 */
	public final static Double PRODUCT_IMG_SIZE = 0.2;
	

	/**
	 * 上传图片(裁减)时存在map里的key
	 */
	public final static String IMG_KEY_COMPRESS = "c";
	/**
	 * 上传图片(原图)时存在map里的key
	 */
	public final static String IMG_KEY_ORIGINAL = "o";

}
