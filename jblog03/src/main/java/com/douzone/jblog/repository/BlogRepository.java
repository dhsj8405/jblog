package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	
	public void create(String id) {
		sqlSession.insert("blog.create",id);
	}


	public void insertBasicCategory(String id) {
		sqlSession.insert("blog.basicCategory",id);
	}


	public BlogVo getTitle(String id) {
		return sqlSession.selectOne("selectTitle",id);
	}

}
