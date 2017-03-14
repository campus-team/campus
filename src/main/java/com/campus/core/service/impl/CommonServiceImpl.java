package com.campus.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.campus.core.dao.BaseDAO;
import com.campus.core.dao.CommonDAO;
import com.campus.core.dao.ICommonDAO;
import com.campus.core.service.ICommonService;
import com.campus.foundation.domain.Student;

public class CommonServiceImpl<T> implements ICommonService<T>{
	
	@Resource(name="BaseDAO")
	protected BaseDAO baseDao;
	
	protected ICommonDAO dao;
	
	@PostConstruct
	public void init(){
		Type type = this.getClass().getGenericSuperclass(); //获取当前类的超类也就是 CommonServiceImpl
		if(type instanceof ParameterizedType){ // 判断这个超类是不是参数化类型的
	    	Type paramtype1 = ((ParameterizedType)type).getActualTypeArguments()[0]; // 获取第一个参数
		    Class<T> clazz = (Class<T>)paramtype1;
		    dao = new CommonDAO(clazz,this.baseDao);
	    }
	}
	
	@Override
	public T getObjById(long id) {
		return (T)dao.getObjById(id);
	}

	@Override
	public boolean save(T obj) {
		return dao.save(obj);
	}

	@Override
	public boolean delete(long id) {
		return dao.delete(id);
	}

	@Override
	public boolean update(T obj) {
		return dao.update(obj);
	}
	
	@Override
	public T getObjByProperty(String paramName, Object paramValue) {
		return (T) dao.getObjByProperty(paramName, paramValue);
	}
}
