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

@WebServlet("/home/logout")
public class HomeLogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		if ((boolean) session.getAttribute("isLogined") == false || session.getAttribute("isLogined") == null) {
			response.getWriter().append(String.format("<script>alert('이미 로그아웃 되어있음');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}
		
		
		session.setAttribute("isLogined", false);
		response.getWriter().append(String.format("<script>alert('로그아웃 됨');</script>"));
		response.getWriter().append(String.format("<script>location.replace('main');</script>"));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}