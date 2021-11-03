package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.BlogRepositoryException;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	
	public void create(String id) throws BlogRepositoryException{
		sqlSession.insert("blog.create",id);
	}


	public void insertBasicCategory(String id) throws BlogRepositoryException{
		sqlSession.insert("blog.basicCategory",id);
	}


	public BlogVo findTitle(String id) throws BlogRepositoryException{
		return sqlSession.selectOne("blog.selectTitle",id);
	}


	public void basic_update(BlogVo blogVo) throws BlogRepositoryException{
		sqlSession.insert("blog.basicUpdate",blogVo);
	}

	public List<CategoryVo> findAllCategory(String id) throws BlogRepositoryException{
		return sqlSession.selectList("blog.selectCategory",id);
	}


	public void insertCategory(CategoryVo categoryVo) throws BlogRepositoryException{	
		sqlSession.insert("blog.insertCategory",categoryVo);

	}


	public void deleteCategory(String categoryNo) throws BlogRepositoryException{
		sqlSession.delete("blog.deleteCategory",categoryNo);
	}


	

}
