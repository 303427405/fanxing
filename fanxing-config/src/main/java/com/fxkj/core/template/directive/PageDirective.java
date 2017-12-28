package com.fxkj.core.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fxkj.core.common.Commons;
import com.fxkj.enums.PageEnum;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.utility.DeepUnwrap;

public class PageDirective extends AbstractBaseDirective {

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		Integer pageSize = (Integer) DeepUnwrap.unwrap((TemplateModel) params
				.get("pageSize"));
		Long total = (Long) DeepUnwrap.unwrap((TemplateModel) params
				.get("total"));
		Integer pageNum = (Integer) DeepUnwrap.unwrap((TemplateModel) params
				.get("pageNum"));
		if (pageSize == null || pageSize < PageEnum.values()[0].getCode())
			pageSize = PageEnum.values()[0].getCode();
		if (pageNum == null || pageNum < 1) {
			pageNum = 1;
		} else {
			pageNum = pageNum + 1;
		}
		if (total == null || total < 1)
			total = 1L;
		// 总页数
		Integer pageTotal = total.intValue() % pageSize == 0 ? total.intValue()
				/ pageSize : total.intValue() / pageSize + 1;
		List<Integer> pageSplit = new ArrayList<Integer>();
		if (pageTotal >= pageNum) {
				for (int i = pageNum; i <= pageTotal; i++) {
					if(pageSplit.size()<Commons.PAGE_SPLIT)
						pageSplit.add(i);
				}
		}
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pageSplit", pageSplit);
		map.put("pageNum", pageNum);
		map.put("isFirst", pageNum == 1);
		map.put("isLast", pageNum == pageTotal);
		map.put("pageTotal", pageTotal);
		map.put("total", total);
		map.put("startShow", (pageNum-1)*pageSize+1);
		map.put("endShow", pageNum*pageSize>total?total:pageNum*pageSize);
		map.put("hasNext", pageSplit.get(pageSplit.size()-1)==pageTotal?false:true);
		setLocalValue(map, env, body);
	}

}
