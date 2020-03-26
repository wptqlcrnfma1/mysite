package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.controller.MainController;

@ControllerAdvice // Controller AOP Advice
public class GlobalExceptionHandler {

	private static final Log LOG = LogFactory.getLog( GlobalExceptionHandler.class );
	
	
	@ExceptionHandler(Exception.class) // 모든 excpetion 처리를 받겠다
	public void handlerException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {

		// 1. 로깅(logging) : catch에서 로깅
		// e.printStackTrace(); // 화면에 뿌리지만 서버는 콘솔이 없어서 파일로 저장해야한다
		StringWriter errors = new StringWriter(); // 버퍼
		e.printStackTrace(new PrintWriter(errors));
		LOG.error(errors.toString());

		// 2. 안내페이지 가기(정상종료)
		request.setAttribute("exception", errors.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);

	}
}
