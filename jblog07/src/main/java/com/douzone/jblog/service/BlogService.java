package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	private static String SAVE_PATH = "/jblog-upload-images";
	private static String URL_BASE = "/images";

	public void createBlog(String id) {
		blogRepository.create(id);
	}

	public void createCategory(String id) {
		blogRepository.insertBasicCategory(id);

	}

	public BlogVo getContents(String id) {

		return blogRepository.findTitle(id);
	}

	public void modifyContents(BlogVo blogVo) {
		blogRepository.basic_update(blogVo);
	}

	

//	public Map<String, Object>  getCategories(String id) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		List<CategoryVo> list = blogRepository.findAllCategory(id);
//		
//		
//		
//		return map; 
//	}
	public List<CategoryVo> getCategories(String id) {

		return blogRepository.findAllCategory(id);
	}

	public void addCategory(CategoryVo categoryVo) {
			blogRepository.insertCategory(categoryVo);		
	}

	public void removeCategory(String categoryNo) {
		blogRepository.deleteCategory(categoryNo);
	}

	public void addPost(PostVo postVo) {
		blogRepository.insertPost(postVo);
	}

	public List<PostVo> getContents(Long categoryNo, String blogId) {
		return blogRepository.findAll(categoryNo, blogId);
	}

	public void removePost(String postNo) {
		blogRepository.deletePost(postNo);
	}

	

	public CategoryVo getCategory(String categoryName, String blogId) {
		return blogRepository.findCategoryByName(categoryName, blogId);
	}

	public Map<String, Object> getContents(Map<String, Object> inputMap) {
		// init
		Map<String, Object> outputMap = new HashMap<>();
		List<PostVo> postList = null;
		PostVo postVo = null;
		List<CategoryVo> categoryList = null;
		Long selectedCategoryNo=null;
		
		// 입력값
		String blogId = (String) inputMap.get("blogId");
		Optional<Long> categoryNo = (Optional) inputMap.get("categoryNo");
		Optional<Long> postNo = (Optional) inputMap.get("postNo");
		
		//카테고리 리스트
		categoryList = blogRepository.findAllCategory(blogId);
		
		//카테고리 번호에 해당하는 포스트 리스트
		if(categoryNo.isPresent()) {
			selectedCategoryNo = (Long) categoryNo.get();
			postList = blogRepository.findAll(selectedCategoryNo, blogId);
		}else {
			selectedCategoryNo = categoryList.get(0).getNo();
			postList = blogRepository.findAll(categoryList.get(0).getNo(), blogId);
		}
		
		//포스트 번호에 해당하는 포스트 내용
		if(postNo.isPresent()) {
			postVo = getPostFromList(postList,(Long)postNo.get());
		}else {
			if(!postList.isEmpty()) {
				postVo =  postList.get(0);
			}
		}
		
		outputMap.put("selectedCategoryNo",selectedCategoryNo);
		outputMap.put("categoryList", categoryList);
		outputMap.put("postList", postList);
		outputMap.put("post", postVo);
		
		return outputMap;
	}

	public PostVo getPostFromList(List<PostVo> postList, Long postNo) {
		PostVo post = null;
		int index = 0;
		
		for(PostVo postVo: postList) {
			if(postVo.getNo() == postNo) {
				post = postList.get(index);
			}
			index++;
		}
		return post;
	}


}
