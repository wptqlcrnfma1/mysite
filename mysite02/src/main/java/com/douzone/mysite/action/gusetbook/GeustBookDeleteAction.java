package com.douzone.mysite.action.gusetbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.GuestRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GeustBookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		if (session.getAttribute("authUser") == null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}
		
		String no = request.getParameter("no"); // 받아온 no값
		String password = request.getParameter("password"); // 입력한거

		GuestBookVo vo = new GuestBookVo();

		vo.setNo(Long.parseLong(no));
		vo.setPassword(password);

		new GuestRepository().delete(vo);
		WebUtil.forward("/guestbook?a=list", request, response);
	}
}
