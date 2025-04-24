<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	int number;
	if (request.getParameter("id") == null) {
		number = 0;
	} else {

		number = Integer.parseInt(request.getParameter("id"));
	}
	%>

	<ul>
		<li>번호 : <%=articleRow.get("id")%>번
		</li>
		<li>등록 날짜 : <%=articleRow.get("regDate")%>번
		</li>
		<li>제목 : <%=articleRow.get("title")%>번
		</li>
		<li>내용 : <%=articleRow.get("body")%>번
		</li>
	</ul>
	
	<a href="list">리스트로 돌아가기</a>


</body>
</html>