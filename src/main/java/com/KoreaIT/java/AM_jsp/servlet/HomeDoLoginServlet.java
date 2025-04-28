package com.KoreaIT.java.AM_jsp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;
import com.mysql.cj.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home/doLogin")
public class HomeDoLoginServlet extends HttpServlet {

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

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			

			
			if (loginId.isEmpty()) {
				response.getWriter().append(String.format("<script>alert('아이디 입력 필요.');</script>"));
				response.getWriter().append(String.format("<script>history.back();</script>"));
				return;
			}
			
			
			

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM member");
			sql.append("WHERE regId = ?;", loginId);

			Map<String, Object> member = DBUtil.selectRow(conn, sql);

			if (member.isEmpty()) {
				response.getWriter().append(String.format("<script>alert('회원가입된 아이디 없음.');</script>"));
				response.getWriter().append(String.format("<script>history.back();</script>"));
				return;
			}

			if (!member.get("regPw").equals(loginPw)) {
				response.getWriter().append(String.format("<script>alert('비밀번호 틀림');</script>"));
				response.getWriter().append(String.format("<script>history.back();</script>"));
				return;
			}
			
	

			session.setAttribute("isLogined", true);
			response.getWriter().append(String.format("<script>alert('로그인 성공!');</script>"));
			response.getWriter().append(String.format("<script>location.replace('main');</script>"));

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}