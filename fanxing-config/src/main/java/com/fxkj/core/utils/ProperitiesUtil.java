package com.fxkj.core.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProperitiesUtil {
	private static Logger logger = LoggerFactory
			.getLogger(ProperitiesUtil.class);
	
	

	/* 私有构造方法，防止被实例化 */
	private ProperitiesUtil() {
	}

	/* 此处使用一个内部类来维护单例 */
	private static class SingletonFactory {
		private static ProperitiesUtil instance = new ProperitiesUtil();
	}

	/* 获取实例 */
	public static ProperitiesUtil getInstance() {
		return SingletonFactory.instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}


	/**
	 * 根据指定的path路径和key得到后面的value
	 * 
	 * @param path
	 * @param key
	 * @return
	 */
	synchronized static public String getValue(String path, String key) {
		InputStream is = ProperitiesUtil.class.getResourceAsStream(path);
		if (is != null) {
			try {
				Properties dbProps = new Properties();
				dbProps.load(is);
				return dbProps.getProperty(key);
			} catch (IOException io) {
				logger.error("读取指定path下的properities文件失败！" + io.getMessage());
				return null;
			}
		} else {
			logger.error("不能读取指定path下的properities文件. " + path
					+ "请确保指定path下的properties文件在CLASSPATH指定的路径中");
			return null;
		}

	}

	/**
	 * 得到系统中Properities文件中指定key的value
	 * 
	 * @param key
	 * @return
	 */
	synchronized static public String getValue(String key) {
		try {
			InputStream in = ProperitiesUtil.class
					.getResourceAsStream("/config/system/system.properties");
			InputStream is = new BufferedInputStream(in);
			Properties dbProps = new Properties();
			dbProps.load(is);
			return dbProps.getProperty(key);
		} catch (IOException io) {
			logger.error("系统中Properities文件读取失败:" + io.getMessage());
			return null;
		}

	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			System.out.println(ProperitiesUtil.getInstance().getValue(
					"/config/system/system.properties", "appointLocalSql"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
