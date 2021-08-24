<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script>
function formCheck() {
	let title = document.getElementById("title");
	let text  = document.getElementById("text");
	
	if(title.value.trim().length == 0 || text.value.trim().length == 0) {
		alert('제목과 글 내용을 입력해 주세요');
		return false;
	}
	return true;
}

function boardList() {
	let target = '${pageContext.request.contextPath}';
	location.href = target + '/listboard';
}
</script>
</head>
<body>
<div class="wrapper">
	<h2>[ 게시판 글쓰기 ]</h2>
	<form action="writeboard" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="userid" value="${sessionScope.loginId}">
		
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>${sessionScope.loginName}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="upload" size="30"></td>
			</tr>
		
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="50" name="text" id="text"></textarea>
				</td>
			</tr>
			<tr>
				<th class="btn" colspan="2">
					<input type="button" value="글목록" onclick="boardList();">
					<input type="submit" value="글저장" onclick="return formCheck();">
				</th>
			</tr>
			
		</table>
	</form>
</div>
</body>
</html>