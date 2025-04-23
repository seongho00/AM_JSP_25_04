package com.KoreaIT.java.AM_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/printDan")
public class PirntDanServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

//		response.getWriter().append("===8단===<br>");
//		response.getWriter().append("8 * 1 = 8<br>");
//		response.getWriter().append(String.format("%d * %d = %d<br>", 8, 1, 8));
//		response.getWriter().append("===8단===");

//		자바, HTML 을 섞어서 쓸 수 있다.
//		int dan = 8;
//
//		for (int i = 1; i <= 9; i++) {
//			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
//		}
		// parameter 보내기 : 주소 뒤에 ?변수이름=값(?dan=8)
		// 두 개 이상의 parameter 보내기 : 첫번째만 ?, 두번째부터 & (?dan=8&limit=10)
		//null 값 처리
		
		String inputedDan = request.getParameter("dan");
		
		if (inputedDan == null) {
			inputedDan = "1";
		}
		
		int dan = Integer.parseInt(inputedDan);
		
		for (int i = 1; i <= 9; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
		}
		
	}

}
