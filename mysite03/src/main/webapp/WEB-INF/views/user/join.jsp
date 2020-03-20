<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form
				modelAttribute="userVo" 
				id="join-form"
				 name="joinForm"
				  method="post"
				   action="${pageContext.request.contextPath}/user/join">
					 <label	class="block-label" for="name">이름</label>
					  <form:input path="name"/>
					  <p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
					  <spring:hasBindErrors name="userVo">
						<c:if test='${errors.hasFieldErrors("name") }'>
							<spring:message code='${errors.getFieldError("name").codes[0] }'/>
						</c:if>
					</spring:hasBindErrors>
					  </p>
					  
					  <!-- spring form tag 사용  -->
					   <label class="block-label" for="email">이메일</label>
					    <form:input path="email"/> <!-- 알아서 input tag를 만든다 ex)text .. -->
					     <input type="button" value="id 중복체크">
					     <p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
					     <form:errors path="email"/><!-- 이 한줄에서 에러처리 / resource파일의 messages 참고 -->
					   	</p>
					   
					   
					   
					     <label class="block-label">패스워드</label>
					      <form:password path="password"/>
						<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
					     <form:errors path="password"/>
					   	</p>
						
						
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>