package com.campus.core.service;

/**
 * 通用的服务接口   调用ICommonDAO实现底层操作
 * @author 刘汉升
 *
 * @param <T>
 */
public abstract interface ICommonService<T> {

	/**
	 * 根据主键id获取相应实体对象
	 * @param id
	 * @return 实体对象
	 */
	public abstract T getObjById(long id);
	
	/**
	 * 插入实体对象
	 * @param obj
	 * @return 是否成功
	 */
	public abstract boolean save(T obj);
	
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
	public abstract boolean update(T obj);
	
	/**
	 * 根据实体的某个属性获取实体对象
	 * @param paramName  属性名
	 * @param paramValue  属性值
	 * @return
	 */
	public abstract T getObjByProperty(String paramName, Object paramValue);
}
