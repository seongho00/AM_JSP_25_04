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

@WebServlet("/article/delete")
public class ArticleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
		if (loginedMember == null) {
			response.getWriter().append(String.format("<script>alert('로그인 필요.');</script>"));
			response.getWriter().append(String.format("<script>location.replace('../member/loginPage');</script>"));
			return;
		}

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
			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE memberId = ?", loginedMember.get("id"));

			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

			if ((int) articleRow.get("memberId") == (int) loginedMember.get("id")) {
				response.getWriter().append(
						String.format("<script>alert('%d번 글에 대한 권한 x'); location.replace('list');</script>", id));
				return;
			}

			sql = new SecSql();

			sql.append("DELETE FROM article WHERE id =?;", id);

			DBUtil.delete(conn, sql);

			response.getWriter().append(String.format("<script>location.replace('list');</script>"));

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
