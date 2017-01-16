package com.campus.foundation.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.core.dao.BaseDAO;
import com.campus.core.dao.CommonDAO;
import com.campus.core.dao.ICommonDAO;
import com.campus.foundation.domain.Student;
import com.campus.foundation.service.IStudentService;

/**
 * 学生服务
 * @author 刘汉升
 *
 */
@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

	@Resource(name="BaseDAO")
	private BaseDAO baseDao;
	
	private ICommonDAO dao;
	
	@PostConstruct
	public void init(){
		dao = new CommonDAO(Student.class,this.baseDao);
	}
	
	@Override
	public Student getObjById(long id) {
		return (Student)dao.getObjById(id);
	}

	@Override
	public boolean save(Student obj) {
		return dao.save(obj);
	}

	@Override
	public boolean delete(long id) {
		return dao.delete(id);
	}

	@Override
	public boolean update(Student obj) {
		return dao.update(obj);
	}

}
