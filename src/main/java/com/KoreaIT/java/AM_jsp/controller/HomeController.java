package com.KoreaIT.java.AM_jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HomeController {
	private HttpServletRequest request;
	private HttpServletResponse response;

	public HomeController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void main() throws ServletException, IOException {

		HttpSession session = request.getSession();
		boolean isLogined = false;

		if (session.getAttribute("loginedMemer") != null) {
			isLogined = true;
		}

		session.setAttribute("isLogined", isLogined);

		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

}
