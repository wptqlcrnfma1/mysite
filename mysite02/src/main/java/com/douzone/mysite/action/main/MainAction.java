package com.douzone.mysite.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int visitCount = 0;
		//처음 접근하면 1이 셋팅되고 들어왔을 때, 읽혀서 visitCount는 1에서 2로 되고 ... 2에서 ... 3 .. 계속 증가한다. 하루동안 몇번오는지 알수있다.
		// 쿠키읽기
		Cookie[] cookies = request.getCookies(); // 배열
		
		if (cookies != null && cookies.length > 0) { // string으로 보내니까 파싱해야한다
			for (Cookie cookie : cookies) {
				if ("visitCount".equals(cookie.getName())) { // 얘랑 같으면 value를 뽑아야한다
					visitCount = Integer.parseInt(cookie.getValue()); // string이니까 integer로 바꾼다.
				}
			}

		}

		// 쿠키쓰기(굽기)
		visitCount++;
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount)); // (이름,값)visitCount 이름으로 쿠키를 구워라
		cookie.setMaxAge(24 * 60 * 60); // 하루동안 쿠키를 갖고 있어라/ -1로 되있으면 디스크에 저장안하고 메모리에 저장
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
		WebUtil.forward("/WEB-INF/views/main/index.jsp", request, response);
	}

}