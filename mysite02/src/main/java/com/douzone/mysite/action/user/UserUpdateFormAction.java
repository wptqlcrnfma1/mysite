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

public class UserUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 접근제어(access control)

		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		UserVo userVo = new UserRepository().findByNo(authUser.getNo()); //select 한 줄
		request.setAttribute("userVo", userVo); //jsp로 날리기
		
		WebUtil.forward("/WEB-INF/views/user/updateform.jsp", request, response);
		
	}

}
