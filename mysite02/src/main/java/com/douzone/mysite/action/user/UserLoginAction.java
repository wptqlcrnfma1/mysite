package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class UserLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);

		UserVo authUser = new UserRepository().findByEmailAndPassword(vo);
		if (authUser == null) {
			/* 로그인실패 */
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}

		/* 로그인 처리 */
		//로그인 되면 authUser란 세션이 생긴다.
		//no랑 name만 올린 이유는 no로 회원정보수정할때 관리에 메모리 사용에 용이하도록
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser); //authUser 세션으로 관리하겠다.
		
		WebUtil.redirect(request.getContextPath(), request, response);
	}

}
