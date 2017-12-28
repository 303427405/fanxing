package com.fxkj.core.base;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fxkj.core.common.CustomDateSerializer;

/**
 * 系统基础实体。每一个实体继承些类（ccuu 仅供显示不参与业务）
 * 
 * @author AdministratorAug 10, 2014
 * 
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;

	private Integer id;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpdateDate() {
		return updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
