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

	<script type="text/javascript">
		function RegisterForm__submit(form) {
			console.log('test');
			if (form.regId.value.length == 0) {
				alert('아이디 필요');
				form.regId.focus();
				return;
			}
		
			form.submit();
		}
	</script>

	<form onsubmit="RegisterForm__submit(this); return false;"
		action="doRegister" method="POST">
		<div>
			<label>아이디 : <input autocomplete="off" type="text"
				name="regId" /></label>
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
		<input type="submit" value="회원가입" />
	</form>

</body>
</html>