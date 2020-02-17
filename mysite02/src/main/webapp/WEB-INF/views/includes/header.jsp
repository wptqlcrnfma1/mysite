<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser"); //jsp는 세션을 다 갖고있음
%>

<div id="header">
	<h1>
		<a href="${pageContext.request.contextPath}#">MySite</a>
	</h1>
	<ul>
	<c:choose>
	<c:when test="${empty authUser}">
		<li><a href="${pageContext.request.contextPath}/user?a=loginform">로그인</a>
		<li>
		<li><a href="${pageContext.request.contextPath}/user?a=joinform">회원가입</a>
		<li>
		</c:when>
		<c:otherwise>
		<li><a href="${pageContext.request.contextPath}/user?a=updateform">회원정보수정</a>
		<li>
		<li><a href="${pageContext.request.contextPath}/user?a=logout">로그아웃</a>
		<li>
		<li>${authUser.name}님 안녕하세요 ^^;</li>
		</c:otherwise>
		</c:choose>
	</ul>
</div>