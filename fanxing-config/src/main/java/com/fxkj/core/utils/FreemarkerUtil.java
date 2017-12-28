package com.fxkj.core.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.Assert;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerUtil {

	/**
	 * 
	 * @param name
	 * @param env
	 * @return
	 * @throws TemplateModelException
	 */
	public static TemplateModel getVariable(String name, Environment env)
			throws TemplateModelException {
		Assert.hasText(name);
		Assert.notNull(env);
		return env.getVariable(name);
	}

	/**
	 * 设置变量
	 * @param name
	 * @param value
	 * @param env
	 * @throws TemplateException
	 */
	public static void setVariable(String name, Object value, Environment env)
			throws TemplateException {
		Assert.hasText(name);
		Assert.notNull(env);
		if (value instanceof TemplateModel) {
			env.setVariable(name, (TemplateModel) value);
		} else {
			env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
		}
	}
	
	/**
	 * 设置变量
	 * @param variables
	 * @param env
	 * @throws TemplateException
	 */
	public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateException {
		Assert.notNull(variables);
		Assert.notNull(env);
		for (Entry<String, Object> entry : variables.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			setVariable(name, value, env);
		}
	}

}
