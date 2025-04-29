<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>

	<form action="doWrite" method="get">
		<div>제목</div>
		<input type="text" name="title" />
		<div>내용</div>
		<input type="text" name="body" />
		<input type="hidden" name="memberId" value="<%=loginedMember.get("id")%>"/>
		<input type="submit"
			onClick="alert('글이 생성되었습니다.')" value="글쓰기" />

	</form>
</body>
</html>