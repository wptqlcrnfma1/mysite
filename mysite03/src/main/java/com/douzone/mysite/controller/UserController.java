package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, @ModelAttribute UserVo vo) {
		// login은 service에 요청하는게 아니다. 기술이 들어가야한다. 나중을 위해
		UserVo authUser = userService.getUser(vo);
		if (authUser == null) {
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		return "redirect:/"; // main
	}

	@RequestMapping(value = "/logout")
	public String login(HttpSession session) {

		///////////////////////////// 접근제어//////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////////////

		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/"; // main
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {

		///////////////////////////// 접근제어//////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////////////

		Long no = authUser.getNo();
		UserVo vo = userService.getUser(no);

		model.addAttribute("userVo", vo);
		return "user/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo, @RequestParam("no") Long no,@RequestParam("password") String password, Model model) {

		///////////////////////////// 접근제어//////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////////////
		
		UserVo voPass = userService.getPassword(no);
		
		if(voPass.getPassword().equals(password)) {
			
			System.out.println("기존의 비밀번호와 동일합니다.");
			return "redirect:/user/update";
		}
		
		if(password.equals("")) {
			System.out.println("비밀번호를 입력하세요");
			return "redirect:/user/update";
		}
		userService.update(userVo);
		return "user/updatesuccess";
	}
	
	@ExceptionHandler(Exception.class) //모든 exception은 이쪽으로 받겠다.
	public String handleException() {
		return "error/exception";
	}
}
