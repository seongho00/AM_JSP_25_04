package com.KoreaIT.java.AM_jsp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ArticleController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
	}

	public void showList() throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		int page = 1;
		String inputedPage = request.getParameter("page");

		if (inputedPage != null) {
			page = Integer.parseInt(inputedPage);
		}
		int viewArticleCount = 10;

		int limitFrom = (page - 1) * viewArticleCount;

		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*)");
		sql.append("FROM article;");

		int totalCnt = DBUtil.selectRowIntValue(conn, sql);

		int totalPage = (int) Math.ceil(totalCnt / (double) viewArticleCount);

		sql = new SecSql();

		sql.append("SELECT A.*, M.name");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id desc");
		sql.append("LIMIT ?, ? ;", limitFrom, viewArticleCount);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

		request.setAttribute("articleRows", articleRows);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", page);
		request.setAttribute("totalCnt", totalCnt);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void showWritePage() throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
		if (loginedMember == null) {
			response.getWriter().append(String.format("<script>alert('로그인 필요.');</script>"));
			response.getWriter().append(String.format("<script>location.replace('../member/loginPage');</script>"));
			return;
		}

		request.getRequestDispatcher("/jsp/article/doWrite.jsp").forward(request, response);
	}
	
	public void doWrite() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		SecSql sql = new SecSql();

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String memberId = request.getParameter("memberId");

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body` = ?,", body);
		sql.append("memberId = ?;", memberId);
		

		DBUtil.insert(conn, sql);

		response.getWriter().append(String.format("<script>location.replace('list');</script>"));
	}

	public void doModify() throws IOException {
		SecSql sql = new SecSql();

		String title = request.getParameter("title");
		int id = Integer.parseInt(request.getParameter("id"));
		String body = request.getParameter("body");

		sql.append("UPDATE article ");
		sql.append("SET regDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body` = ?", body);
		sql.append("WHERE id = ?;", id);

		DBUtil.update(conn, sql);

		response.getWriter().append(String.format("<script>location.replace('detail?id=%d');</script>", id));

	}

	public void doDelete() throws IOException {
		HttpSession session = request.getSession();
		Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
		if (loginedMember == null) {
			response.getWriter().append(String.format("<script>alert('로그인 필요.');</script>"));
			response.getWriter().append(String.format("<script>location.replace('../member/loginPage');</script>"));
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE memberId = ?", loginedMember.get("id"));

		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

		if ((int) articleRow.get("memberId") == (int) loginedMember.get("id")) {
			response.getWriter()
					.append(String.format("<script>alert('%d번 글에 대한 권한 x'); location.replace('list');</script>", id));
			return;
		}

		sql = new SecSql();

		sql.append("DELETE FROM article WHERE id =?;", id);

		DBUtil.delete(conn, sql);

		response.getWriter().append(String.format("<script>location.replace('list');</script>"));

	}

	public void showDetail() throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));

		SecSql sql = new SecSql();

		sql.append("SELECT A.*, M.name");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?;", id);

		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

		request.setAttribute("articleRow", articleRow);

		if (articleRow == null) {
			response.getWriter().append(String.format("<script>location.replace('list');</script>"));
			response.getWriter().append(String.format("<script>alert('Hello');</script>"));
		}

		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);

	}

	public void showModifyPage() throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		SecSql sql = new SecSql();

		int id = Integer.parseInt(request.getParameter("id"));

		request.setAttribute("id", id);

		sql.append("SELECT * FROM article WHERE id =?;", id);

		Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

		request.setAttribute("articleRow", articleRow);

		request.getRequestDispatcher("/jsp/article/doModify.jsp").forward(request, response);
		
	}

}
