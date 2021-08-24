<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<style>
	div#message{
		color:red;
	}
</style>
<script>
function loginCheck() {
	let userid   = document.getElementById("userid");
	let userpwd  = document.getElementById("userpwd");

	if(userid.value.trim().length < 3 || userid.value.trim().length > 5) {
		alert("아이디는 3~5글자 크기로 입력해 주세요");
		userid.select();
		return false;
	}
	
	if(userpwd.value.trim().length < 3 || userpwd.value.trim().length > 5) {
		alert("비밀번호는 3~5글자 크기로 입력해 주세요");
		userpwd.select();
		return false;
	}
	
	return true;
}
</script>
</head>
<body>
<div class="wrapper">
	<h2>[ 로그인 ]</h2>
	<div id="message">${message }</div>
	<form action="login" method="POST">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" id="userid" name="userid" placeholder="3~5자리의 ID 입력">
				<input type="checkbox">아이디 저장
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" id="userpwd" name="userpwd">
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="로그인" onclick="return loginCheck();">
				<input type="button" value="취소">
			</th>
			
		</tr>
	</table>	
	</form>
</div>
</body>
</html>