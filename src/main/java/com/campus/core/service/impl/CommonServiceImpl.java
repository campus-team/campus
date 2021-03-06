package com.campus.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.campus.core.dao.BaseDAO;
import com.campus.core.dao.CommonDAO;
import com.campus.core.dao.ICommonDAO;
import com.campus.core.query.IPageObject;
import com.campus.core.query.PageObject;
import com.campus.core.query.QueryObject;
import com.campus.core.service.ICommonService;

public class CommonServiceImpl<T> implements ICommonService<T>{
	
	@Resource(name="BaseDAO")
	protected BaseDAO baseDao;
	
	protected ICommonDAO dao;
	
	protected Class<T> clazz;
	
	@PostConstruct
	public void init(){
		Type type = this.getClass().getGenericSuperclass(); //获取当前类的超类也就是 CommonServiceImpl
		if(type instanceof ParameterizedType){ // 判断这个超类是不是参数化类型的
	    	Type paramtype1 = ((ParameterizedType)type).getActualTypeArguments()[0]; // 获取第一个参数
		    this.clazz = (Class<T>)paramtype1;
		    dao = new CommonDAO(this.clazz, this.baseDao);
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

	@Override
	public List<T> query(String queryStr, Map params, int begin, int max){
		return (List<T>)dao.query(queryStr, params, begin, max);
	}
	
	@Override
	public List<T> listAll() {
		QueryObject qo = new QueryObject(clazz);
		return (List<T>)dao.query(qo.getQueryHqlStr(), qo.getParams(), -1, -1);
	}
	
	public IPageObject list(QueryObject qo) {
		qo.setClazz(clazz);
		PageObject po = new PageObject();
		int totalRows = dao.queryTotalRows(qo.getQueryHqlStr_forTotalRows(), qo.getParams()); // 总记录数
		if(totalRows > 0){
			po.setTotalRows(totalRows);
			int totalPages = (totalRows - 1) / qo.getPageRows(); // 总页数
			int currentPage = qo.getCurrentPage() > totalPages ? totalPages : qo.getCurrentPage();
			if(currentPage < 1){
				currentPage = 1;
			}
			qo.setCurrentPage(currentPage);
			List resultList = dao.query(qo.getQueryHqlStr(), qo.getParams(), qo.getBegin(), qo.getMax());
			po.setTotalRows(totalRows);
			po.setResultList(resultList);
			po.setCurrentPage(currentPage);
			po.setTotalPages(totalPages);
		}
		return po;
	}
}
