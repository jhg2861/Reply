<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원게시판</title>
</head>
<body>
<h2>[ 회원 게시판 ]</h2>
<ul>
	<c:if test="${sessionScope.loginId == null}">
		<li><a href="join">회원가입</a></li>
		<li><a href="login">로그인</a></li>
	</c:if>	
	
	<c:if test="${sessionScope.loginId != null}">
		<li>
			${sessionScope.loginName}님, 
			<a href="logout">로그아웃</a>
		</li>
		<li><a href="modify?userid=${sessionScope.loginId}">개인정보 수정</a></li>
	</c:if>
	
	<li><a href="listboard">게시판</a></li>
</ul>
</body>
</html>