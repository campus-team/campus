package com.campus.core.service;

import java.util.List;
import java.util.Map;

import com.campus.core.query.IPageObject;
import com.campus.core.query.QueryObject;

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
	
	/**
	 * 查询对象列表
	 * @param queryStr 查询的hql
	 * @param params 参数值对
	 * @param begin 记录开始的位置
	 * @param max  取出记录条数
	 * @return
	 */
	public abstract List query(String queryStr, Map params, int begin, int max);
	
	/**
	 * 查询所有的结果
	 * @return
	 */
	public abstract List<T> listAll();
	
	/**
	 * 根据查询对象查询结果
	 * @param qo 查询对象
	 * @return 返回页面对象
	 */
	public IPageObject list(QueryObject qo);
}
