<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
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
		<li>번호 : <%=articleRow.get("id")%>번
		</li>
		<li>등록 날짜 : <%=articleRow.get("regDate")%>
		</li>
		<li>제목 : <%=articleRow.get("title")%>
		</li>
		<li>내용 : <%=articleRow.get("body")%>
		</li>
		<li>작성자 : <%=articleRow.get("name")%>
		</li>
	</ul>


	<style type="text/css">
.modify, .delete {
	visibility: hidden;
}
</style>


	<a class="<%=loginedMember != null ? 
	(articleRow.get("memberId") == loginedMember.get("id") ? "" : "modify") : "modify"
	%>"
		href="modify/page?id=<%=articleRow.get("id")%>">수정하기</a>



	<a class="<%=loginedMember != null ? 
	(articleRow.get("memberId") == loginedMember.get("id") ? "" : "delete") : "delete"
	%>"
		onClick="if(confirm('정말로 삭제하시겠습니까?') == false){return false;}"
		href="delete?id=<%=articleRow.get("id")%>">삭제하기</a>
	<div>
		<a href="list">리스트로 돌아가기</a>
	</div>




</body>
</html>