package com.campus.flow.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.campus.core.domain.IdEntity;
import com.campus.foundation.domain.User;

/**
 * 表单模板
 * @author 刘汉升
 *
 */
@Entity
@Table(name="df_form")
public class Form extends IdEntity{

	// 生成数据库表的名称
	private String name;
	// 显示的名称
    private String displayName;
    // 文档的类型
    //private String type;
    // 创造者
    @OneToOne
    private User creator;
    // 原始的html表单
    private String originalHtml;
    // 解析后的html
    private String parseHtml;
    // 字段数量
    private Integer fieldNum = 0;
    // 表单文件夹
    @ManyToOne
    private FormFolder form_folder;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getOriginalHtml() {
		return originalHtml;
	}
	public void setOriginalHtml(String originalHtml) {
		this.originalHtml = originalHtml;
	}
	public String getParseHtml() {
		return parseHtml;
	}
	public void setParseHtml(String parseHtml) {
		this.parseHtml = parseHtml;
	}
	public Integer getFieldNum() {
		return fieldNum;
	}
	public void setFieldNum(Integer fieldNum) {
		this.fieldNum = fieldNum;
	}
	public FormFolder getForm_folder() {
		return form_folder;
	}
	public void setForm_folder(FormFolder form_folder) {
		this.form_folder = form_folder;
	}
    
    
    
    
}
