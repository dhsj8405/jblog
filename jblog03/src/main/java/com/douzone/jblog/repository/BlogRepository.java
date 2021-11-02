package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

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


	public BlogVo findTitle(String id) {
		return sqlSession.selectOne("blog.selectTitle",id);
	}


	public void basic_update(BlogVo blogVo) {
		sqlSession.insert("blog.basicUpdate",blogVo);
	}

	public List<CategoryVo> findAllCategory(String id) {
		return sqlSession.selectList("blog.selectCategory",id);
	}


	public void insertCategory(CategoryVo categoryVo) {	
		sqlSession.insert("blog.insertCategory",categoryVo);

	}


	

}
