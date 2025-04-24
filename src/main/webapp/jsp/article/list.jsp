<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
String inputedPage = request.getParameter("page");

if (inputedPage == null) {
	inputedPage = "1";
}

int nowPage = Integer.parseInt(inputedPage);
int viewArticleCount = 10;
int totalPage = articleRows.size() / viewArticleCount == 0 ? articleRows.size() / viewArticleCount
		: articleRows.size() / viewArticleCount + 1;
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
	<ul>
		<%
		int lastPage;
		if (nowPage == totalPage) {
			lastPage = articleRows.size();
		} else {
			lastPage = nowPage * 10;
		}

		for (int i = (nowPage - 1) * 10; i < lastPage; i++) {
		%>
		<li><a href="detail?id=<%=articleRows.get(i).get("id")%>"> <%=articleRows.get(i).get("id")%>번,
				<%=articleRows.get(i).get("regDate")%>, <%=articleRows.get(i).get("title")%>,
				<%=articleRows.get(i).get("body")%>
		</a></li>

		<%
		}
		%>
	</ul>
	<%
	for (int i = 1; i <= totalPage; i++) {
	%>
	<a styel="display:block; padding:5px; margin:3px;" class="page"
		href="list?page=<%=i%>"><%=i%></a>
	<%
	}
	%>
</body>
</html>