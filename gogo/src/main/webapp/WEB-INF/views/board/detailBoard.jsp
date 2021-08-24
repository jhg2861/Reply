<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 자세히 보기</title>
<link rel="stylesheet" href="resources/style/detail.css">
<script>
function boardList(){
	let targetPlace = "listboard";
	location.href = targetPlace;
}
function boardDelete(){
	let boardnum = '${board.boardnum}'
	let answer = confirm('게시글을 삭제하시겠습니까?');
	
	if(answer){
		location.href = "deleteboard?boardnum=" + boardnum;
	}
}
function boardUpdate(){
	let boardnum = '${board.boardnum}';
	
	location.href = "updateboard?boardnum="+boardnum;
}
</script>
</head>
<body>
<div class="wrapper">
	<h2>[ 게시판 글보기 ]</h2>
	
	<table border = "1">
		<tr>
			<th>작성자</th>
			<td>${board.userid}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.regdate}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${board.originalfile != null}">
					<a href="download?boardnum=${board.boardnum}">${board.originalfile}</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<pre>${board.userid}</pre>
			</td>
		</tr>
		<tr>
			<th class="btn" colspan="2">
				<input type="button" value="글목록" onclick="boardList();">
				
				<c:if test="${sessionScope.loginId == board.userid}">
					<input type="button" value="글삭제" onclick="boardDelete();">
					<input type="button" value="글수정" onclick="boardUpdate();">
				</c:if>
			</th>	
		</tr>
	</table>
	<br>
	
	<c:if test="${sessionScope.loginId != null}">
		<form action="" method="POST">
			<table id="replyInput" class="reply">
				<tr>
					<td>
						<input id="reply_txt" 	 type="text" name="replytext">
						<input id="reply_submit" type="text" name="댓글 저장">
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</div>
</body>
</html>




















