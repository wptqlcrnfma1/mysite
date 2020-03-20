package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) { //modelAttribute로 받지는 않지만 보내줘야한다. 자동으로 JSP에 UserVo를 보내준다.
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		//@ModelAttribute @Valid UserVo vo는 회원가입 실패해도 text유지하기 위해
		//소문자 uservo 객체가 jsp로 넘어간다.
		
		
		//에러의 경우 jsp에서 처리 콘솔을 파싱해서 내용을 jsp로 돌려주는건 힘들다.
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error:list) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel()); //map으로 return된다 jsp로 간다 > 스프링 태그를 사용해서 jsp에서 처리하도록
			return "user/join";
		}
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

	/*
	 * 인터셉터를 만들어서 이제 안써도 되고 login.jsp에 auth로 보내게끔했다.
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
	*/
	
	/*
	 * login과 동일 com.douzone.security와 servlet-spring 참고
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
	
	 */
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		/*
		 * Auth를 만들어 interceptor 시켰으므로 필요없어지는 접근제어 구문
		///////////////////////////// 접근제어//////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////////////
		*/
		//UserVo authUser = (UserVo) session.getAttribute("authUser");
		Long no = authUser.getNo();
		UserVo vo = userService.getUser(no);
		model.addAttribute("userVo", vo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser,UserVo userVo, @RequestParam("no") Long no,@RequestParam("password") String password, Model model) {

		/*
		///////////////////////////// 접근제어//////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/";
		}
		////////////////////////////////////////////////////////////////////
		*/
		
		
		UserVo voPass = userService.getPassword(no);
		if(voPass.getPassword().equals(password)) {
			return "redirect:/user/update";
		}
		
		if(password.equals("")) {
			return "redirect:/user/update";
		}
		
		userVo.setNo(authUser.getNo());
		userService.update(userVo);
		
		return "user/updatesuccess";
	}
	
//	@ExceptionHandler(Exception.class) //모든 exception은 이쪽으로 받겠다.
//	public String handleException() {
//		return "error/exception";
//	}
}
