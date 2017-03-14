package com.campus.core.dao;
/**
 * 通用的数据访问对象接口
 * @author 刘汉升
 *
 */
public abstract interface ICommonDAO {

	/**
	 * 根据主键id获取相应实体对象
	 * @param id
	 * @return 实体对象
	 */
	public abstract Object getObjById(long id);
	
	/**
	 * 插入实体对象
	 * @param obj
	 * @return 是否成功
	 */
	public abstract boolean save(Object obj);
	
	/**
	 * 根据主键id删除实体
	 * @param id
	 * @return 是否成功
	 */
	public abstract boolean delete(long id);
	
	/**
	 * 更新实体对象
	 * @param obj
	 * @return 是否成功
	 */
	public abstract boolean update(Object obj);
	
	/**
	 * 根据实体的某个属性获取实体对象
	 * @param paramName  属性名
	 * @param paramValue  属性值
	 * @return
	 */
	public abstract Object getObjByProperty(String paramName, Object paramValue);
	
}
