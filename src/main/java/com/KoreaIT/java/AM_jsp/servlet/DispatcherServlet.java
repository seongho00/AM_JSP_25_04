package com.KoreaIT.java.AM_jsp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.controller.ArticleController;
import com.KoreaIT.java.AM_jsp.controller.HomeController;
import com.KoreaIT.java.AM_jsp.controller.MemberController;
import com.KoreaIT.java.AM_jsp.dto.Member;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		// DB 연결
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 x");
			e.printStackTrace();

		}

		String url = "jdbc:mysql://127.0.0.1:3306/AM_JSP_25_04?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);

			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Member loginedMember = null;

			if (session.getAttribute("loginedMemberId") != null) {
				isLogined = true;
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				loginedMember = (Member) session.getAttribute("loginedMember");
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMember", loginedMember);

			String requestUri = request.getRequestURI();

			String[] reqUriBits = requestUri.split("/");
			// /~~~/s/article/list

			if (reqUriBits.length < 5) {
				response.getWriter()
						.append(String.format("<script>alert('올바른 요청 x'); location.replace('../home/main');</script>"));
				return;
			}

			String controllerName = reqUriBits[3];
			String actionMethodName = reqUriBits[4];

			if (controllerName.equals("article")) {
				ArticleController articleController = new ArticleController(request, response, conn);

				switch (actionMethodName) {
				case "list":
					articleController.showList();
					break;
				case "writePage":
					articleController.showWritePage();
					break;
				case "doWrite":
					articleController.doWrite();
					break;
				case "modifyPage":
					articleController.showModifyPage();
					break;
				case "doModify":
					articleController.doModify();
					break;
				case "delete":
					articleController.doDelete();
					break;
				case "detail":
					articleController.showDetail();
					break;
				case "myList":
					articleController.showMyList();
				default:
					break;

				}
			} else if (controllerName.equals("member")) {
				MemberController memberController = new MemberController(request, response, conn);
				switch (actionMethodName) {
				case "loginPage":
					memberController.showLoginPage();
					break;
				case "doLogin":
					memberController.login();
					break;
				case "logout":
					memberController.logout();
					break;
				case "registerPage":
					memberController.showRegisterPage();
					break;
				case "doRegister":
					memberController.doRegister();
					break;
				default:
					break;
				}

			} else if (controllerName.equals("home")) {
				HomeController homeController = new HomeController(request, response);
				switch (actionMethodName) {
				case "main":
					homeController.main();
					break;
				default:
					break;
				}

			}

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
