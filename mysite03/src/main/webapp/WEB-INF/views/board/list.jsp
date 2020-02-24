<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.douzone.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath}/board?a=search" method="post">
				
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
								
				<table class="tbl-ex">
				
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='listCount' value='${fn:length(list)}' />
					<c:forEach items="${list}"  var="vo" varStatus='status'>
					<tr>
						<td>${listCount-status.index}</td>
	
	
						<c:choose>
							<c:when test="${vo.orderNo>1}">
								<td style = "text-align:left; padding-left:${vo.depthNo*15}px">
								<img src='/mysite02-bak/assets/images/reply.png'>
								<a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no}">${vo.title }</a></td>
							</c:when>
						
						<c:otherwise>
							<td style = "text-align:left; padding-left:${0*0}px">
							<a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no}">${vo.title }</a></td>
						</c:otherwise>
						</c:choose>
						
										
						<td>${vo.userName}</td>
						<td>${vo.hit}</td>
						<td>${vo.regDate}</td>
					
						<c:set var = "authName" value = "${authUser.name}" scope="session"/>
						<c:set var = "userName" value = "${vo.userName}" scope="session"/>
						<c:choose>
						<c:when test="${authName eq userName}">
						<td><a href="${pageContext.request.contextPath}/board?a=deleteform&no=${vo.no}" class="del"><img src='/mysite02-bak/assets/images/recycle.png'></a></td>
						</c:when>
						<c:otherwise>
						<td></td>
						</c:otherwise>
						</c:choose>
					</tr>
					</c:forEach>
				</table>
				
			
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?a=write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>