package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;
import com.douzone.jblog.dto.JsonResult;


@RestController("userApiController") //핸들러들 전부 responsebody 가진것처럼 메세지 컨버터 작용 : Auth와 비슷
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	//@ResponseBody
	//@RequestMapping("/checkemail")
	@GetMapping("/checkid")
	public JsonResult checkid(
			@RequestParam(value="id", required = true, defaultValue ="") String id) {
		
		UserVo userVo = userService.getUser(id);
		return JsonResult.success(userVo != null);

//		return map;
	}
}
