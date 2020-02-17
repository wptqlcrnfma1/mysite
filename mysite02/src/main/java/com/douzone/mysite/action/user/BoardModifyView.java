package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class BoardModifyView implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		if (session.getAttribute("authUser") == null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}

		String no = request.getParameter("no");
		Long pNo = Long.parseLong(no);

		BoardVo boardVo = new BoardRepository().findBoardTitleContent(pNo); // 제목 내용

		request.setAttribute("vo", boardVo); // jsp로 날리기
		

		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}
}
