package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	public void createBlog(String id) {
		blogRepository.create(id);
	}

	public void createCategory(String id) {
		blogRepository.insertBasicCategory(id);

	}

	public BlogVo getContents(String id) {
		
		return blogRepository.getTitle(id);
	}

}
