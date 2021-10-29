package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	
	public void create(String id) {
		sqlSession.insert("blog.create",id);
	}


	public void basicCategory(String id) {
		sqlSession.insert("blog.basicCategory",id);
	}

}
