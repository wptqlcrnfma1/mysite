package com.douzone.mysite.action.gusetbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.GuestRepository;
import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class GuestBookWrite implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("authUser")==null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String contents = request.getParameter("content");
		String date = mSimpleDateFormat.format(c1.getTime());

		GuestBookVo vo = new GuestBookVo();

		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(contents);
		vo.setReg_date(date);

		new GuestRepository().insert(vo);

		WebUtil.forward("/guestbook?a=list", request, response);
	}

}
