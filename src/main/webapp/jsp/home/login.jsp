<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
session.setAttribute("isLogined", true);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<a href="main">메인으로 가기</a>
	<h1>로그인 페이지</h1>

	<form action="doLogin" method="get">
		<div>
			<label>아이디 : <input type="text" name="loginId" /></label>
		</div>

		<div>
			<label>비밀번호 : <input type="password" name="loginPw" /></label>
		</div>
		
		<input type="submit" value="회원가입" />
	</form>

</body>
</html>