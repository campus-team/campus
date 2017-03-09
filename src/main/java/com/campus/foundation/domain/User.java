package com.campus.foundation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.campus.core.domain.IdEntity;

@Entity
@Table(name="user")
public class User extends IdEntity{
	
	private String userName;
	
	private String password;
	
	/** 用户类型  0:学生，1：教师，2：商家，99：系统管理员*/
	@Column(columnDefinition="int default 0")
	private Integer user_type;
	
	@Transient
	public String parseUser_type(){
		if(this.user_type==null) return "学生";
		switch (this.user_type.intValue()) {
			case 0: return "学生";
			case 1: return "教师";
			case 2: return "商家";
			case 99: return "系统管理员";
			default:return "无";
		}
	}
	
	@OneToOne(mappedBy="user")
	private Student student;
	
	@OneToOne(mappedBy="user")
	private Teacher teacher;
	
	@OneToOne(mappedBy="user")
	private Business business;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	
	
}
