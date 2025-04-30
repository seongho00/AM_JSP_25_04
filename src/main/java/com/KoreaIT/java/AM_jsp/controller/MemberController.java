package com.KoreaIT.java.AM_jsp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dto.Member;
import com.KoreaIT.java.AM_jsp.service.MemberService;
import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;
	private MemberService memberService;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
		this.memberService = new MemberService(conn);
	}

	public void showLoginPage() throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMember") != null) {
			response.getWriter().append(String.format("<script>alert('이미 로그인 되어있음');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}
		request.getRequestDispatcher("/jsp/member/login.jsp").forward(request, response);
	}

	public void login() throws IOException {

		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		if (loginId.isEmpty()) {
			response.getWriter().append(String.format("<script>alert('아이디 입력 필요.');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		Member member = memberService.getMemberById(loginId);

		if (member == null) {
			response.getWriter().append(String.format("<script>alert('회원가입된 아이디 없음.');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		if (!member.getLoginPw().equals(loginPw)) {
			response.getWriter().append(String.format("<script>alert('비밀번호 틀림');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		session.setAttribute("loginedMember", member);
		response.getWriter().append(String.format("<script>alert('로그인 성공!');</script>"));
		response.getWriter().append(String.format("<script>location.replace('../home/main');</script>"));
	}

	public void logout() throws IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		if (session.getAttribute("loginedMember") == null) {
			response.getWriter().append(String.format("<script>alert('이미 로그아웃 되어있음');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		session.setAttribute("loginedMember", null);
		response.getWriter().append(String.format("<script>alert('로그아웃 됨');</script>"));
		response.getWriter().append(String.format("<script>location.replace('../home/main');</script>"));

	}

	public void doRegister() throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String checkPw = request.getParameter("checkPw");
		String name = request.getParameter("name");

		if (loginId.isEmpty()) {
			response.getWriter().append(String.format("<script>alert('아이디 입력 필요');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		if (loginPw.isEmpty()) {
			response.getWriter().append(String.format("<script>alert('비번 입력 필요');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		if (loginPw.trim().contains(" ")) {
			response.getWriter().append(String.format("<script>alert('비번 형식 안 맞음');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		if (!loginPw.equals(checkPw)) {
			response.getWriter().append(String.format("<script>alert('비번 확인 안 맞음');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		Member member = memberService.getMemberById(loginId);

		if (member != null) {
			response.getWriter().append(String.format("<script>alert('중복 아이디');</script>"));
			response.getWriter().append(String.format("<script>history.back();</script>"));
			return;
		}

		memberService.insertMember(loginId, loginPw, name);

		response.getWriter().append(String.format("<script>alert('회원가입 성공!');</script>"));
		response.getWriter().append(String.format("<script>location.replace('../home/main');</script>"));
	}

	public void showRegisterPage() throws IOException, ServletException {
		request.getRequestDispatcher("/jsp/member/register.jsp").forward(request, response);

	}

}
