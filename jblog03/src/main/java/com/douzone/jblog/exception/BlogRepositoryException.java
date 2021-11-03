package com.douzone.jblog.exception;

public class BlogRepositoryException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public BlogRepositoryException() {
		super("SiteRepositoryException Occurs");
	}

	public BlogRepositoryException(String message) {
		super(message);
	}
}
