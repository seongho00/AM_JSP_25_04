<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<a href="main">메인으로 가기</a>
	<h1>회원가입 페이지</h1>

	<form action="doRegister" method="get">
		<div>
			<label>아이디 : <input type="text" name="regId" /></label>
		</div>

		<div>
			<label>비밀번호 : <input type="password" name="regPw" /></label>
		</div>
		<div>
			<label>비밀번호 확인 : <input type="password" name="checkPw" /></label>
		</div>
		<div>
			<label>이름 : <input type="text" name="name" /></label>
		</div>
		<input type="submit" value="로그인" />
	</form>

</body>
</html>