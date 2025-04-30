<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.KoreaIT.java.AM_jsp.dto.Article"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
int totalPage = (int) request.getAttribute("totalPage");
int cPage = (int) request.getAttribute("page");
int totalCnt = (int) request.getAttribute("totalCnt");
int viewPageLimitFrom = (int) request.getAttribute("viewPageLimitFrom");
int viewPageLimitTo = (int) request.getAttribute("viewPageLimitTo");
int viewPage = (int) request.getAttribute("viewPage");
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
	<a href="writePage">글쓰기</a>
	<a href="myList">내 글 보기</a>



	<table style="border-collapse: collapse; border-color: green;"
		border="1px">
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>작성자</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Article article : articles) {
			%>
			<tr style="text-align: center;">
				<td><%=article.getId()%>번</td>
				<td><%=article.getRegDate()%></td>
				<td><%=article.getName()%></td>
				<td><a href="detail?id=<%=article.getId()%>"><%=article.getTitle()%></a></td>
				<td><%=article.getBody()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

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
		<a href="list?viewPage=<%=viewPage - 1%>&page=<%=cPage%>"> < </a>
		<%
		for (int i = viewPageLimitFrom; i <= viewPageLimitTo; i++) {
		%>
		<a class="<%=cPage == i ? "cPage" : ""%>" href="list?page=<%=i%>&viewPage=<%=viewPage%>">
			<%=i%>
		</a>
		<%
		}
		%>
		<a href="list?viewPage=<%=viewPage + 1%>&page=<%=cPage%>"> > </a>
	</div>

</body>
</html>