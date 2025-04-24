<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%

	int dan = Integer.parseInt(request.getParameter("dan"));
	String color = request.getParameter("color");
	int limit = Integer.parseInt(request.getParameter("limit"));
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.color {
	color: <%=color%>;
}
</style>
</head>
<body>
	<h1>구구단 출력</h1>
	<h2>
		==<%=dan%>단==
	</h2>
	<div class="color">
	<%
	for (int i = 0; i <= limit; i++) {
	%>
		<div></div>
	<%
	}
	%>
	</div>
</body>
</html>