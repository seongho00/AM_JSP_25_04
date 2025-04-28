<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="http://localhost:8080/AM_JSP_25_04/article/doWrite">
		<div>제목</div>
		<input type="text" name="title" />
		<div>내용</div>
		<input type="text" name="body" /> 
		<input type="submit"
			onClick="alert('글이 생성되었습니다.')" value="글쓰기"/>

	</form>
</body>
</html>