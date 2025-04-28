<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> article = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%=article.get("id")%>번 글 수정
	</h2>
	<form action="http://localhost:8080/AM_JSP_25_04/article/doModify">
		<div>아이디</div>
		<input type="text" name="id" value="<%=article.get("id")%>" readonly/>
		<div>제목</div>
		<input type="text" name="title" value="<%=article.get("title")%>" />
		<div>내용</div>
		<input type="text" name="body" value="<%=article.get("body")%>" /> <input
			type="submit" onClick="alert('글이 수정되었습니다.')" value="수정" />
	</form>
</body>
</html>