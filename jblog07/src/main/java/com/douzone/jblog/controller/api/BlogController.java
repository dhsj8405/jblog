package com.douzone.jblog.controller.api;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;


@RestController("blogApiController") //핸들러들 전부 responsebody 가진것처럼 메세지 컨버터 작용 : Auth와 비슷
@RequestMapping("category/api")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	ServletContext servletContext;
	
	@GetMapping("/checkCategory")
	public JsonResult checkCategory(
			@RequestParam(value="categoryName", required = true, defaultValue ="") String categoryName) {
		
			BlogVo blogVo = (BlogVo)servletContext.getAttribute("blogVo");
			CategoryVo categoryVo = blogService.getCategory(categoryName,blogVo.getId());
			
			return JsonResult.success(categoryVo != null);

	}
}
