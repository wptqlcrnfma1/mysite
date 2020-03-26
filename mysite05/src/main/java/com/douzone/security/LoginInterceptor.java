package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. 파라미터를 받아준다
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//2. 서비스 접근
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		//userService로 객체를 생성해야지 new로 생성해버리면 인터셉터에서 객체를 만들게되므로 안된다
		UserVo authUser = userService.getUser(vo);
		
		if(authUser == null) { //로그인 안된거면 
			request.setAttribute("userVo", vo);
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
		System.out.println("------>authUser:" + authUser);
		
		//비밀번호 맞으면 session 처리
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser",authUser);
		response.sendRedirect(request.getContextPath()); //spring이 아니므로 getcontextpath 써야한다.
		return false; //보낼 메소드가 없는거
	}
	
}
