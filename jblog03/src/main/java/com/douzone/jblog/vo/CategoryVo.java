package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String description;
	private String blogId;
	private Long postCount;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}

	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public Long getpostCount() {
		return postCount;
	}
	public void setpostCount(Long postCount) {
		this.postCount = postCount;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", blogId=" + blogId + ", postCount="
				+ postCount + "]";
	}
	

}
