package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	private static String SAVE_PATH = "/jblog03-upload-images";
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

	public void modifyContents(BlogVo blogVo, MultipartFile multipartFile) {
		String url = null;
		try {
			if (multipartFile.getOriginalFilename().isEmpty()) {
				blogVo.setLogo(null);
			} else {
				String originFilename = multipartFile.getOriginalFilename();
				String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
				String saveFilename = generateSaveFilename(extName);

				byte[] data = multipartFile.getBytes();
				OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
				os.write(data);
				os.close();

				url = URL_BASE + "/" + saveFilename;
				blogVo.setLogo(url);

			}
		} catch (IOException ex) {
			throw new RuntimeException("file upload error:" + ex);
		}
		System.out.println("2");
		blogRepository.basic_update(blogVo);
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		return filename;
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

}
