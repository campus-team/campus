package com.campus.core.dao;

public abstract interface ICommonDAO {

	public abstract Object getObjById(long id);
	
	public abstract boolean save(Object obj);
	
	public abstract boolean delete(long id);
	
	public abstract boolean update(Object obj);
	
}
