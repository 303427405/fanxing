package com.fxkj.core.base;

public interface BaseDao {

	/**
	 * 删除实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Integer deleteEntityById(BaseEntity obj, String sqlNameSpaceAndId);

	/**
	 * 批量删除实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param ids
	 * @return
	 */
	public Integer deleteEntityByIds(String sqlNameSpaceAndId, String ids);

	/**
	 * 修改实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Integer updateEntityById(BaseEntity obj, String sqlNameSpaceAndId);

	/**
	 * 添加实体
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Integer addEntityById(BaseEntity obj, String sqlNameSpaceAndId);

	/**
	 * 获取实体（唯一）
	 * 
	 * @param sqlNameSpaceAndId
	 * @param obj
	 * @return
	 */
	public Object getEntityById(BaseEntity obj, String sqlNameSpaceAndId);

}
