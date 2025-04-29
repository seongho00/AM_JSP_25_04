package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/article/myList")
public class ArticleMyListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/AM_JSP_25_04?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
		String user = "";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, "root", "");
			response.getWriter().append("연결 성공!");
			HttpSession session = request.getSession();

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

			Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");

			sql = new SecSql();
			sql.append("SELECT A.*, M.name AS `name`");
			sql.append("FROM article AS A");
			sql.append("INNER JOIN `member` AS M");
			sql.append("ON A.memberId = M.id");
			sql.append("WHERE A.memberId = ?", loginedMember.get("id"));
			sql.append("ORDER BY A.id desc");
			sql.append("LIMIT ?, ? ;", limitFrom, viewArticleCount);

			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);

			request.setAttribute("articleRows", articleRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("page", page);
			request.setAttribute("totalCnt", totalCnt);

			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);

		} catch (SQLException e) {
			System.out.println("에러 1 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
