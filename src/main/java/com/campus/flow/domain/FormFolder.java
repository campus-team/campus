package com.campus.flow.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.campus.core.domain.IdEntity;

/**
 * 表单文件夹
 * @author 刘汉升
 *
 */
@Entity
@Table(name="df_formfolder")
public class FormFolder extends IdEntity{
	
	// 表单文件夹名称
	private String name;
	// 序号
	private Integer sequence;
	// 父文件夹
	@ManyToOne
	private FormFolder parent;
	// 子文件夹
	@OneToMany(mappedBy="parent")
	private List<FormFolder> children = new ArrayList<FormFolder>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public FormFolder getParent() {
		return parent;
	}
	public void setParent(FormFolder parent) {
		this.parent = parent;
	}
	public List<FormFolder> getChildren() {
		return children;
	}
	public void setChildren(List<FormFolder> children) {
		this.children = children;
	}
	
	
}
