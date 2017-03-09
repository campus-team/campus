package com.campus.foundation.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.campus.core.domain.IdEntity;

@Entity
@Table(name="user_business")
public class Business extends IdEntity{

	@OneToOne(cascade = CascadeType.REMOVE)
	private User user;
	
	private String name;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
