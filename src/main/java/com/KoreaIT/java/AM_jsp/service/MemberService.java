package com.KoreaIT.java.AM_jsp.service;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dao.MemberDao;
import com.KoreaIT.java.AM_jsp.dto.Member;

public class MemberService {
	private Connection conn;
	private MemberDao memberDao;

	public MemberService(Connection conn) {
		this.conn = conn;
		this.memberDao = new MemberDao(conn);
	}

	public Member getMemberById(String loginId) {

		return memberDao.getMemberById(loginId);
	}

	public void insertMember(String loginId, String loginPw, String name) {
		memberDao.insertMember(loginId, loginPw, name);
	}

}
