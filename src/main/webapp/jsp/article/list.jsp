<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int totalPage = (int) request.getAttribute("totalPage");
int cPage = (int) request.getAttribute("page");
int totalCnt = (int) request.getAttribute("totalCnt");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>

	<h1>게시글 목록</h1>

	<a href="../home/main">메인으로 가기</a>
	<div>
		총 게시글 개수 :
		<%=totalCnt%>
	</div>
	<a href="writePage" >글쓰기</a>

	<ul>
		<%
		for (int i = 0; i < articleRows.size(); i++) {
		%>
		<li><a href="detail?id=<%=articleRows.get(i).get("id")%>">
				<%=articleRows.get(i).get("id")%>번,
				<%=articleRows.get(i).get("regDate")%>,
				<%=articleRows.get(i).get("title")%>,
				<%=articleRows.get(i).get("body")%>
			</a></li>

		<%
		}
		%>
	</ul>

	<style type="text/css">
.page>a {
	color: black;
	text-decoration: none;
	display: inline-block;
	padding: 5px;
	margin: 3px;
}

.page>a.cPage {
	color: red;
	text-decoration: underline;
}
</style>

	<div class="page">
		<%
		for (int i = 1; i <= totalPage; i++) {
		%>
		<a class="<%=cPage == i ? "cPage" : ""%>" href="list?page=<%=i%>">
			<%=i%>
		</a>
		<%
		}
		%>
	</div>
</body>
</html>