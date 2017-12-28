package com.fxkj.core.template.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fxkj.core.utils.FreemarkerUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public abstract class AbstractBaseDirective implements TemplateDirectiveModel {
	
	
	protected void setLocalValue(String name, Object value, Environment env, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		TemplateModel sourceVariable = FreemarkerUtil.getVariable(name, env);
		FreemarkerUtil.setVariable(name, value, env);
		body.render(env.getOut());
		FreemarkerUtil.setVariable(name, sourceVariable, env);
	}
	
	
	protected void setLocalValue(Map<String, Object> variables, Environment env, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map<String, Object> sourceVariables = new HashMap<String, Object>();
		for (String name : variables.keySet()) {
			TemplateModel sourceVariable = FreemarkerUtil.getVariable(name, env);
			sourceVariables.put(name, sourceVariable);
		}
		FreemarkerUtil.setVariables(variables, env);
		body.render(env.getOut());
		FreemarkerUtil.setVariables(sourceVariables, env);
	}

}
