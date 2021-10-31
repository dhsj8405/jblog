package com.douzone.jblog.vo;

public class BlogVo {
	private String title;
	private String logo;
	private String id;
	private String categoryDesc;
	private String categoryName;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "BlogVo [title=" + title + ", logo=" + logo + ", id=" + id + ", categoryDesc=" + categoryDesc
				+ ", categoryName=" + categoryName + "]";
	}
	

	
}
