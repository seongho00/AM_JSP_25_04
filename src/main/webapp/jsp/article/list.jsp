<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 목록</h1>
	<ul>
		<li><%=articleRows.get(0).get("id") %> 번</li>
		<li><%=articleRows.get(1).get("id") %> 번</li>
		<li><%=articleRows.get(2).get("id") %> 번</li>
	</ul>
</body>
</html>