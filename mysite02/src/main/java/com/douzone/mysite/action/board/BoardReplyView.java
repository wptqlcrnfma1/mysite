package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardReplyView implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		if (session.getAttribute("authUser") == null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}
		
		//UserVo v2 = (UserVo) session.getAttribute("authUser");
		//Long userNo = v2.getNo();
		
		String no = request.getParameter("no");
		Long no1 = Long.parseLong(no);
		
		
		BoardVo vo = new BoardRepository().findReplyNumbers(no1);
	
		
		System.out.println("vo[BoardReplyView.java] : " + vo);
		
		request.setAttribute("vo", vo); // ??
		
		WebUtil.forward("/WEB-INF/views/board/reply.jsp", request, response);
	}
}
