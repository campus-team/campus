package com.campus.core.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.campus.foundation.domain.Student;

/**
 * 最底层用于操作数据库的类
 * @author 刘汉升
 *
 */
@Repository("BaseDAO")
public class BaseDAO {

	@Autowired  
    private SessionFactory  sessionFactory;
	
	private Session session;
	
	public Object get(Class<?> clazz, Serializable id){
		if(id==null){
			return null;
		}
		return getSession().get(clazz, id);
	}
	
	public boolean save(Object obj){
		try{
			getSession().persist(obj);
			return true;
		}catch(HibernateException e){
			return false;
		}
	}
	
	public boolean delete(Class<?> clazz, Serializable id){
		try{
			Object obj = get(clazz, id);
			getSession().delete(obj);
			return true;
		}catch(HibernateException e){
			return false;
		}
	}
	
	public boolean update(Object obj){
		try{
			getSession().merge(obj);
			return true;
		}catch(HibernateException e){
			return false;
		}
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
