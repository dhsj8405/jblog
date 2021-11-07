package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.BlogRepositoryException;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

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
		return sqlSession.selectList("blog.selectCategories",id);
	}


	public void insertCategory(CategoryVo categoryVo) throws BlogRepositoryException{	
		sqlSession.insert("blog.insertCategory",categoryVo);

	}


	public void deleteCategory(String categoryNo) throws BlogRepositoryException{
		sqlSession.delete("blog.deleteCategory",categoryNo);
	}


	public void insertPost(PostVo postVo) {
		sqlSession.insert("blog.insertPost",postVo);
	}

	public List<PostVo> findAll(Long categoryNo) {
		return sqlSession.selectList("blog.selectPost",categoryNo);
	}

	public void deletePost(String postNo) {
		sqlSession.delete("blog.deletePost", postNo);
	}


	public CategoryVo findCategoryByName(String categoryName, String blogId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("blogId",blogId);
		map.put("categoryName", categoryName);

		return sqlSession.selectOne("blog.selectCategoryByName",map);
	}





}
