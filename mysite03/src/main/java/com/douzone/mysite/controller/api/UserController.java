package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;



//@Controller 이렇게만 쓰면 id를 안주게된 경우이므로 bean이 2개라 에러가 뜬다. > 제일 앞 소문자 자동으로 만드는 것
@Controller("userApiController")
@RequestMapping("/api/user")
public class UserController {
		
	@Autowired
		private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="/checkemail", method = RequestMethod.GET) //응답만 보기위한 소스코드
	public JsonResult checkEmail(@RequestParam(value="email", required=true, defaultValue="")String email) {
		boolean exist = userService.existUser(email);
		
		return JsonResult.success(exist);
	}
	
}
