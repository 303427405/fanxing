package com.fxkj.core.base;

import java.util.ArrayList;
import java.util.List;

import com.fxkj.core.common.Commons;
import com.fxkj.enums.PageEnum;

/**
 * 分页返回前台页面参数的封装
 * 
 *
 * @param <T>
 */
public class PageInfo<T> {

	private Long total=0L;
	private List<T> rows=new ArrayList<T>();

	private Integer pageSize = Commons.PAGE_SIZE;

	private Integer pageNum;

	private PageEnum[] pageEnum=PageEnum.values();

	private String sort;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public PageEnum[] getPageEnum() {
		return pageEnum;
	}

	public void setPageEnum(PageEnum[] pageEnum) {
		this.pageEnum = pageEnum;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
