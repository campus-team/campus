package com.campus.core.dao;


/**
 * 共同的数据操作类
 * @author 刘汉升
 *
 */
public class CommonDAO implements ICommonDAO {

	protected Class<?> entityClass;
	
	private BaseDAO baseDAO;
	
	public CommonDAO(Class<?> clazz, BaseDAO baseDAO) {
		this.entityClass = clazz;
		this.baseDAO = baseDAO;
	}
	
	@Override
	public Object getObjById(long id) {
		return this.baseDAO.get(entityClass, id);
	}

	@Override
	public boolean save(Object obj) {
		return this.baseDAO.save(obj);
	}

	@Override
	public boolean delete(long id) {
		return this.baseDAO.delete(entityClass, id);
	}

	@Override
	public boolean update(Object obj) {
		return this.baseDAO.update(obj);
	}

	@Override
	public Object getObjByProperty(String paramName, Object paramValue) {
		return this.baseDAO.getObjByProperty(entityClass, paramName, paramValue);
	}

}
