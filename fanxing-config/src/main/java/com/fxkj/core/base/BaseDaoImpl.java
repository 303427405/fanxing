package com.fxkj.core.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.fxkj.core.utils.StringUtil;
import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDaoImpl<T> extends SqlMapClientDaoSupport {

	@Autowired
	public void setSqlMapClientBase(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	/**
	 * 实体分页 作用于整个系统的表格显示加分页
	 * 
	 * @param pageInfo
	 *            （分页实体）
	 * @param countEntityByStatementName
	 *            （查询总数的sql语句id）
	 * @param listEntityByStatementName
	 *            （查询记录的sql语句id）
	 * @param paramMap
	 *            （模糊查询条件）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected PageInfo<T> getPageByParamMap(PageInfo<T> pageInfo,
			String countEntityByStatementName,
			String listEntityByStatementName, Map<String, Object> paramMap) {
		Integer pageSize=(Integer)paramMap.get("pageSize");
		Integer pageNum=(Integer)paramMap.get("pageNum");
		String sort=(String)paramMap.get("sort");
		paramMap.put("pageNum", pageNum*pageSize);
		
		Long total = (Long) getSqlMapClientTemplate().queryForObject(
				countEntityByStatementName, paramMap);
		List<T> result = new ArrayList<T>();
		if (total != null && total.longValue() > 0) {
			result = getSqlMapClientTemplate().queryForList(
					listEntityByStatementName, paramMap);
		}
		pageInfo.setRows(result);
		pageInfo.setTotal(total);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNum);
		//pageInfo.setPageEnum(PageEnum.values());
		if(StringUtil.isNotEmpty(sort))
		   pageInfo.setSort(sort);
		return pageInfo;
	}

	/**
	 * 删除实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Integer deleteEntityById(BaseEntity obj, String sqlNameSpaceAndId) {
		return (Integer) getSqlMapClientTemplate().delete(sqlNameSpaceAndId,
				obj);
	}

	/**
	 * 批量删除实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param ids
	 * @return
	 */
	public Integer deleteEntityByIds(String sqlNameSpaceAndId, String ids) {
		return (Integer) getSqlMapClientTemplate().delete(sqlNameSpaceAndId,
				ids);
	}

	/**
	 * 修改实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Integer updateEntityById(BaseEntity obj, String sqlNameSpaceAndId) {
		return (Integer) getSqlMapClientTemplate().update(sqlNameSpaceAndId,
				obj);
	}

	/**
	 * 添加实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Integer addEntityById(BaseEntity obj, String sqlNameSpaceAndId) {
		return (Integer) getSqlMapClientTemplate().insert(sqlNameSpaceAndId,
				obj);
	}

	/**
	 * 获取实体（唯一）
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getEntityById(BaseEntity obj, String sqlNameSpaceAndId) {
		return (T) getSqlMapClientTemplate().queryForObject(sqlNameSpaceAndId,
				obj);
	}

}
