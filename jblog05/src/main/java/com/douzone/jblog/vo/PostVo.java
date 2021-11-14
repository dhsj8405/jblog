package com.douzone.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String contents;
	private String regDateTime;
	private Long categoryNo;
	private String categoryName;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(String regDateTime) {
		this.regDateTime = regDateTime;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", regDateTime=" + regDateTime
				+ ", categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}
	
	
}
