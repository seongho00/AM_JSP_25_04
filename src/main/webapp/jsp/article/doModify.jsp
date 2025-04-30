<%@page import="java.util.Map"%>
<%@page import="com.KoreaIT.java.AM_jsp.dto.Article"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Article article = (Article) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<body>
	<a href="../list">리스트로 돌아가기</a>
	<h2><%=article.getId()%>번 글 수정
	</h2>
	<form action="doModify" method="POST">
		<input type="hidden" name="id" value="<%=article.getId()%>" />
		<div>제목</div>
		<input type="text" name="title" value="<%=article.getTitle()%>" />
		<div>내용</div>
		<input type="text" name="body" value="<%=article.getBody()%>" /> <input
			type="submit" onClick="alert('글이 수정되었습니다.')" value="수정" />
	</form>
</body>
</html>