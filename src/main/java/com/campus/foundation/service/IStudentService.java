package com.campus.foundation.service;

import com.campus.foundation.domain.Student;

public abstract interface IStudentService {
	
	public abstract Student getObjById(long id);
	
	public abstract boolean save(Student obj);
	
	public abstract boolean delete(long id);
	
	public abstract boolean update(Student obj);
}
