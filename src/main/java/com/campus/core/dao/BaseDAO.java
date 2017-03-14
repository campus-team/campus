package com.campus.core.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campus.core.domain.IdEntity;
import com.campus.core.tools.CommUtil;

/**
 * 最底层用于操作数据库的类
 * @author 刘汉升
 *
 */
@Repository("BaseDAO")
@Transactional
public class BaseDAO {

	private static final Logger log = LoggerFactory.getLogger(BaseDAO.class);
	
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
			log.error("数据库保存失败：(class:{})",obj.getClass());
			return false;
		}
	}
	
	public boolean delete(Class<?> clazz, Serializable id){
		try{
			Object obj = get(clazz, id);
			getSession().delete(obj);
			return true;
		}catch(HibernateException e){
			log.error("数据删除失败：(class:{},id:{})",clazz.getClass(),id);
			return false;
		}
	}
	
	public boolean update(Object obj){
		try{
			getSession().merge(obj);
			return true;
		}catch(HibernateException e){
			log.error("数据更新失败：(class:{},id:{})", obj.getClass(),((IdEntity)obj).getId());
			return false;
		}
	}
	
	public Object getObjByProperty(final Class<?> clazz, final String paramName, final Object paramValue){
		session = getSession();
		String className = clazz.getName();
		StringBuffer hql = new StringBuffer("select obj from ");
		hql.append(className);
		hql.append(" obj ");
		Query query = null;
		if(CommUtil.isNotNull(paramName) && CommUtil.isNotNull(paramValue)){
			hql.append(" where obj."+paramName+" = :"+paramName);
			query = session.createQuery(hql.toString());
			query.setParameter(paramName, paramValue);
		}else{
			query = session.createQuery(hql.toString());
		}
		List result_list = query.list();
		if(result_list!=null && result_list.size()==1){
			return result_list.get(0);
		}
		return null;
	}
	
	public List query(final String queryStr, final Map params, final int begin, final int max){
		session = getSession();
		Query query = session.createQuery(queryStr);
		if(params!=null){
			Iterator entrySetIterator = params.entrySet().iterator();
			while(entrySetIterator.hasNext()){
				Map.Entry entry = (Map.Entry)entrySetIterator.next();
				String key = entry.getKey().toString();
				Object value = entry.getValue();
				query.setParameter(key, value);
			}
		}
		if(begin>=0 && max>=0){
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		return query.list();
	}
	
	public int queryTotalRows(final String queryStr, final Map params){
		session = getSession();
		Query query = session.createQuery(queryStr);
		if(params!=null){
			Iterator entrySetIterator = params.entrySet().iterator();
			while(entrySetIterator.hasNext()){
				Map.Entry entry = (Map.Entry)entrySetIterator.next();
				String key = entry.getKey().toString();
				Object value = entry.getValue();
				query.setParameter(key, value);
			}
		}
		return ((Number)query.uniqueResult()).intValue();
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
