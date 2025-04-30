package com.KoreaIT.java.AM_jsp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dto.Article;
import com.KoreaIT.java.AM_jsp.dto.Member;
import com.KoreaIT.java.AM_jsp.service.ArticleService;
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
	private ArticleService articleService;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
		this.articleService = new ArticleService(conn);
	}

	public void showList() throws ServletException, IOException {

		int page = 1;

		String inputedPage = request.getParameter("page");

		if (inputedPage != null) {
			page = Integer.parseInt(inputedPage);
		}
		int viewArticleCount = 10;

		int limitFrom = (page - 1) * viewArticleCount;

		int totalCnt = articleService.getTotalArticleCount();

		int totalPage = (int) Math.ceil(totalCnt / (double) viewArticleCount);

		List<Article> articles = articleService.getForPrintArticles(limitFrom, viewArticleCount);

		int viewPage = 1;
		int viewPageCount = 10;
		String inputedviewPage = request.getParameter("viewPage");
		int viewTotalPage = (int) Math.ceil(totalPage / (double) viewPageCount);

		if (inputedviewPage != null) {
			viewPage = Integer.parseInt(inputedviewPage);
		}
		if (viewPage <= 0) {
			viewPage = 1;
		}
		if (viewPage > viewTotalPage) {
			viewPage = viewTotalPage;
		}

		int viewPageLimitFrom = (viewPage - 1) * viewPageCount + 1;
		int viewPageLimitTo = viewPage * viewPageCount;
		if (viewPageLimitTo > totalPage) {
			viewPageLimitTo = totalPage;
		}

		request.setAttribute("articles", articles);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", page);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("viewPageLimitFrom", viewPageLimitFrom);
		request.setAttribute("viewPageLimitTo", viewPageLimitTo);
		request.setAttribute("viewPage", viewPage);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

	public void showWritePage() throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Member loginedMember = (Member) session.getAttribute("loginedMember");

		if (loginedMember == null) {
			response.getWriter().append(String.format("<script>alert('로그인 필요.');</script>"));
			response.getWriter().append(String.format("<script>location.replace('../member/loginPage');</script>"));
			return;
		}

		request.getRequestDispatcher("/jsp/article/doWrite.jsp").forward(request, response);
	}

	public void doWrite() throws IOException {

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		String memberId = request.getParameter("memberId");

		articleService.insertArticle(title, body, memberId);

		response.getWriter().append(String.format("<script>location.replace('list');</script>"));
	}

	public void doModify() throws IOException {

		String title = request.getParameter("title");
		int id = Integer.parseInt(request.getParameter("id"));
		String body = request.getParameter("body");

		articleService.updateArticle(title, body, id);

		response.getWriter().append(String.format("<script>location.replace('detail?id=%d');</script>", id));

	}

	public void doDelete() throws IOException {

		HttpSession session = request.getSession();

		Member loginedMember = (Member) session.getAttribute("loginedMember");

		if (loginedMember == null) {
			response.getWriter().append(String.format("<script>alert('로그인 필요.');</script>"));
			response.getWriter().append(String.format("<script>location.replace('../member/loginPage');</script>"));
			return;
		}

		Article article = articleService.getArticleByMemberId((int) loginedMember.getId());

		int id = Integer.parseInt(request.getParameter("id"));

		if ((int) article.getMemberId() != (int) loginedMember.getId()) {
			response.getWriter()
					.append(String.format("<script>alert('%d번 글에 대한 권한 x'); location.replace('list');</script>", id));
			return;
		}

		articleService.deleteArticle(id);

		response.getWriter().append(String.format("<script>location.replace('list');</script>"));

	}

	public void showDetail() throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getForPrintArticleById(id);

		request.setAttribute("article", article);

		if (article == null) {
			response.getWriter().append(String.format("<script>location.replace('list');</script>"));
			response.getWriter().append(String.format("<script>alert('Hello');</script>"));
		}

		request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);

	}

	public void showModifyPage() throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		Article article = articleService.getArticleById(id);

		request.setAttribute("article", article);
		request.setAttribute("id", id);

		request.getRequestDispatcher("/jsp/article/doModify.jsp").forward(request, response);

	}

	public void showMyList() throws ServletException, IOException {

		HttpSession session = request.getSession();

		Member loginedMember = (Member) session.getAttribute("loginedMember");
		if (loginedMember == null) {
			response.getWriter().append(String.format("<script>alert('로그인 필요.');</script>"));
			response.getWriter().append(String.format("<script>location.replace('../member/loginPage');</script>"));
			return;
		}
		int page = 1;
		String inputedPage = request.getParameter("page");

		if (inputedPage != null) {
			page = Integer.parseInt(inputedPage);
		}
		int viewArticleCount = 10;

		int limitFrom = (page - 1) * viewArticleCount;

		int totalCnt = articleService.getTotalArticleCount();

		int totalPage = (int) Math.ceil(totalCnt / (double) viewArticleCount);

		SecSql sql = new SecSql();
		sql.append("SELECT A.*, M.name AS `name`");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.memberId = ?", loginedMember.getId());
		sql.append("ORDER BY A.id desc");
		sql.append("LIMIT ?, ? ;", limitFrom, viewArticleCount);

		List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

		request.setAttribute("articleRows", articleRows);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", page);
		request.setAttribute("totalCnt", totalCnt);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

	}

}
