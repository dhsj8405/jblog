package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;
	
	@NotEmpty
	private String id;
	
	@NotEmpty
	@Length(min=4, max=16)
	private String password;
	
	private String joinDate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", id=" + id + ", password=" + password + ", joinDate=" + joinDate + "]";
	}

	
}
