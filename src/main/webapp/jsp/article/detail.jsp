<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.KoreaIT.java.AM_jsp.dto.Article"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Article article = (Article) request.getAttribute("article");
Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>
	<a href="../home/main">메인으로 가기</a>
	<h2>게시글 상세보기</h2>
	<%
	int number;
	if (request.getParameter("id") == null) {
		number = 0;
	} else {

		number = Integer.parseInt(request.getParameter("id"));
	}
	%>

	<ul>
		<li>번호 : <%=article.getId()%>번
		</li>
		<li>등록 날짜 : <%=article.getRegDate()%>
		</li>
		<li>제목 : <%=article.getTitle()%>
		</li>
		<li>내용 : <%=article.getBody()%>
		</li>
		<li>작성자 : <%=article.getName()%>
		</li>
	</ul>


	<style type="text/css">
.modify, .delete {
	visibility: hidden;
}
</style>


	<a
		class="<%=loginedMember != null ? ((int) article.getMemberId() == (int) loginedMember.get("id") ? "" : "modify") : "modify"%>"
		href="modifyPage?id=<%=article.getId()%>">수정하기</a>



	<a
		class="<%=loginedMember != null ? ((int) article.getMemberId() == (int) loginedMember.get("id") ? "" : "delete") : "delete"%>"
		onClick="if(confirm('정말로 삭제하시겠습니까?') == false){return false;}"
		href="delete?id=<%=article.getId()%>">삭제하기</a>
	<div>
		<a href="list">리스트로 돌아가기</a>
	</div>




</body>
</html>