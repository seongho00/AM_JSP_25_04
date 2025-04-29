<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h1>메인 페이지</h1>
	<ul>

		<li><a href="../article/list">리스트로 이동</a></li>
		<li><a href="../member/registerPage">회원가입</a></li>
		<%
		if (session.getAttribute("loginedMember") == null) {
		%>
		<li><a href="../member/loginPage">로그인</a></li>
		
		<%
		}
		%>
		<%
		if (session.getAttribute("loginedMember") != null) {
		%>
		<li><a href="../member/logout">로그아웃</a></li>
		<%
		}
		%>
		

	</ul>

</body>
</html>