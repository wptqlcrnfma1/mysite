package com.douzone.mysite.action.gusetbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.GuestRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookListView implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("authUser")==null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}
		
		List<GuestBookVo> list = new GuestRepository().findAll();
		
		request.setAttribute("list", list);
		
		WebUtil.forward("/WEB-INF/views/guestbook/list.jsp", request, response);
	}
}
