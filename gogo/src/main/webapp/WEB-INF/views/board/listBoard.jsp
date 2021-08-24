<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글목록</title>
<script>
	function detailBoard(num){
		let targetPlace = 'detailboard?boardnum=' + num;
		location.href = targetPlace;
	}
</script>
<style>
	body {
		font-family : sans-serif;
		font-size : 0.9em;
	}
	input[type=submit] {
		border : none;
	}
	div.wrapper{
		width : 900px;
		margin : 0 auto;
		text-align : center;
	}
	table{
		width : 900px;
	}
	.title {
		width : 450px;
	}
	th {
		background : #efefef;
	}
	.write {
		text-align : right;
	}
	.home {
		text-align : left;
	}
	a {
		display : inline-block;
	}
	a, a:link, a:visited, a:active, a:hover {
		text-decoration:none;
		color : black;
	}
	a:hover {
		font-weight : bolder;
		color : #f00;
	}
	#search {
		text-align : right;
	}
	div.home p {
		width : 500px;
		float : left;
		margin : 0px;
	}
	form { 
		float : right:
	}
	table {
		clear : both;
	}
	img {
		width : 25px;
	}
</style>
</head>
<body>
<div class="wrapper">
	<h2>[ 게시판 ]</h2>
	<div class="home">
		<p><a href="${pageContext.request.contextPath}"><img src="resources/images/home.png"></a></p>
		<form id="search" action="listboard" method="GET">
			<select name="searchItem">
				<option value="title" 	${searchItem=='title' ? 'selected':''}>제목</option>
				<option value="userid" 	${searchItem=='userid'?	'selected':''}>작성자</option>
				<option value="text" 	${searchItem=='text'  ?	'selected':''}>글내용</option>
			</select>
			<input type="text" name="searchWord" value="${searchWord}">
			<input class="btn" type="submit" value="검색">
		</form>
	</div>
	
	<!-- 게시글 목록 시작 -->
	<table border="1">
		<tr>
			<th>번호</th>
			<th class="title">글제목</th>
			<th>글쓴날</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		
		<!-- 게시글 출력 -->
		<c:forEach var="board" items="${list}" varStatus="stat" >		
			<tr>
				<td>${stat.index + navi.srow}</td>
				<td class="title">
					<c:if test="${board.originalfile != null}">
						<img src="resources/images/attach.png" style="width:13px;">
					</c:if>
					<a href="javascript:detailBoard(${board.boardnum});">${board.title}</a>
				</td>
				<td>${board.regdate}</td>
				<td>${board.userid}</td>
				<td>${board.hitcount }</td>
			</tr>
		</c:forEach>
	</table>
	
	<!--  글쓰기 버튼 -->
	<c:if test="${sessionScope.loginId != null}">
		<div class="write"><a href="writeboard">글쓰기</a></div>
	</c:if>
	
	<!-- 페이징 출력되는 부분 -->
	요청페이지 ${navi.currentPage}
	<div class="navigator">
		<a href="listboard?currentPage=${navi.currentPage-navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">
		◁◁
		</a>
	
		<a href="listboard?currentPage=${navi.currentPage-1}&searchItem=${searchItem}&searchWord=${searchWord}">◀</a>&nbsp;
		<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
			<c:if test="${navi.currentPage == page}">
				<span style="color:blue;font-weight:bolder;font-size:1.3em">${page}</span> &nbsp;
			</c:if>
			
			<c:if test="${navi.currentPage != page}">
				<a href="listboard?currentPage=${page}&searchItem=${searchItem}&searchWord=${searchWord}">${page}</a> &nbsp;
			</c:if>	
		</c:forEach>
		<a href="listboard?currentPage=${navi.currentPage+1}&searchItem=${searchItem}&searchWord=${searchWord}">
		▶
		</a>
		&nbsp;
		
		<a href="listboard?currentPage=${navi.currentPage+navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">
		▷▷
		</a>
		
	</div>
	
</div>
</body>
</html>














