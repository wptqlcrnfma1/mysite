package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		if (session.getAttribute("authUser") == null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}
		UserVo v2 = (UserVo) session.getAttribute("authUser");
		
		//String no = request.getParameter("no");
		//Long no1 = Long.parseLong(no);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Long userNo = v2.getNo(); //authUser의 no 즉 로그인 한 아이디의 no, name밖에 없음
		
		BoardVo vo = new BoardVo();
		
	//	vo.setNo(no1);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(userNo);

		new BoardRepository().insert(vo);
		
		request.setAttribute("vo", vo);
		
		WebUtil.redirect(request.getContextPath() + "/board?a=list", request, response);

	}

}
