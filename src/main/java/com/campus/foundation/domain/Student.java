package com.campus.foundation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.campus.core.domain.IdEntity;

/**
 * 学生实体
 * @author 刘汉升
 *
 */
@Entity
@Table(name="student")
public class Student extends IdEntity{

	/** 学号 */
	private String number;
	/** 姓名 */
	private String name;
	/** 性别   true为男  false为女(默认为男) */
	@Column(columnDefinition="bit default true")
	private Boolean sex;
	/** 年龄 */
	private Integer age;
	/** 手机号 */
	private String phone;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
