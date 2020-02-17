package com.douzone.mysite.action.user;

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

public class BoardReplyAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		if (session.getAttribute("authUser") == null) {
			request.setAttribute("result", "fail"); // result 값에 fail을 보내준다.
			WebUtil.forward("/WEB-INF/views/user/loginform.jsp", request, response);
			return;
		}

		UserVo v2 = (UserVo) session.getAttribute("authUser");

		String title = request.getParameter("title");
		String contents = request.getParameter("content");

		String gNo = request.getParameter("groupNo");
		int groupNo = Integer.parseInt(gNo);

		String oNo = request.getParameter("orderNo");
		int orderNo = Integer.parseInt(oNo);
			
		String dNo = request.getParameter("depth");
		int depthNo = Integer.parseInt(dNo);

		Long userNo = v2.getNo();

		BoardVo vo = new BoardVo();

		vo.setTitle(title);
		vo.setContents(contents);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDepthNo(depthNo);
		vo.setUserNo(userNo);
		
		request.setAttribute("vo", vo); // ??
		
		
		new BoardRepository().updateReplySort(vo);
		new BoardRepository().insertReply(vo);

		WebUtil.redirect(request.getContextPath() + "/board?a=list", request, response);

	}
}
