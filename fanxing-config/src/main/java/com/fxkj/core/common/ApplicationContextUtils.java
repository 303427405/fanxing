package com.fxkj.core.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	public static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtils.context = context;
	}

	public static <T> T getBean(String beanId, Class<T> clazz) {
		return context.getBean(beanId, clazz);
	}

	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * 获取bean
	 * @param bean
	 * @param activeProfiles 运行环境（qa,dev）
	 * @return
	 */
	public static Object getBean(String bean,String activeProfiles) {
		ApplicationContext app = getApplicationContext(activeProfiles);
		return app.getBean(bean);
	}

	/**
	 *	获取ApplicationContext
	 * @param activeProfiles 运行环境（qa,dev）
	 * @return
	 */
	public static ApplicationContext getApplicationContext(String activeProfiles) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles(activeProfiles);
		ctx.load(new String[] {
				"classpath*:config/jdbc/*/applicationContext*.xml",
				"classpath*:config/spring/*.xml" });
		ctx.refresh();
		Assert.notNull(ctx, "ApplicationContext 配置出错了");
		return ctx;
	}
}
