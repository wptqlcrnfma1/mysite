package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//Object handler에는 뒤로(controller) 보내지는 객체의 정보
		
		//이미지 url로 들어오게되면 default handelr
		
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) { //login, out 제외 다들어오니까
			System.out.println("if(handler instanceof HandlerMethod == false) 구문 check");
			//(DefaultServletHandler가 처리하는 경우(보통, assets의 정적 자원 접근))
			return true; //이미지 들어오면 니 일을 계속해라
		}
		System.out.println("start");
		//2. casting (annotation의 정보를 빼오는거) 이게 목적
		HandlerMethod handlerMethod = (HandlerMethod)handler; //@method가 붙어있느냐
		
		//3.Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		System.out.println("handlerMethod.getMethodAnnotation(Auth.class) check" );
		
		//4. Method에 @Auth가 없으면 Type에 붙어 있는지 확인한다(과제)
		if(auth == null) {
			auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
			System.out.println("4번조건 auth == null check");
		}
		
		//5. type 이나 method 둘 다 @Auth가 적용이 안되어 있는 경우
		if(auth == null) { 
			System.out.println("5번조건 type 이나 method 둘 다 @Auth가 적용이 안되어 있는 경우 check");
			return true; 
		}
		
		
		//6. 인증 여부 확인(@Auth가 붙어 있기 때문)(Authentification) 이까지오면 @붙어있는거
		HttpSession session = request.getSession();
		if(session == null) {
			System.out.println("이까지오면 @붙어있는거 인증 여부 확인 check");
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//인증 확인해보는거
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//인증 안된거
		if(authUser == null) { //로그인안하고 @Auth붙은거 건드는거
			System.out.println("if(authUser == null) 인증안된거 check");
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//admin 추가했으니 이제 무조건 통과시켜주면 안된다. 비교작업 필요
		
		
		//6. 권한(Authorization) 체크를 위해서 @Auth의 role 가져오기("USER", "ADMIN")
		String role = auth.value();
		System.out.println("role : " + role);
		
		//7. 만약 @Auth의 role이 "USER"인 경우는 authUser의 role이 "USER" 이든 "ADMIN" 이든 상관없음. 무조건 true
		if("USER".equals(role)) {
			System.out.println("관리자이지만 USER.euqal에 올까?");
			return true;
		}
		
		//8. 만약 @Auth의 role이 "ADMIN"인 경우 반드시 authUser의 role이 "ADMIN" 여야 한다.
		if("ADMIN".equals(authUser.getRole()) == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//@Auth의 role = "ADMIN"
		//authUser의 role = "ADMIN"
		
		
		//인증확인 되었으므로 핸들러 메소드 실행 핸들러메소드. 3개불러야한다
		return true;
	}

}
