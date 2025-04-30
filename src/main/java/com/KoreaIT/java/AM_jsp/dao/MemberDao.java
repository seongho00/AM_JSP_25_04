package com.KoreaIT.java.AM_jsp.dao;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dto.Member;
import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

public class MemberDao {
	private Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public Member getMemberById(String loginId) {

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM member");
		sql.append("WHERE loginId = ?;", loginId);

		Map<String, Object> MemberMap = DBUtil.selectRow(conn, sql);
		Member member;

		member = new Member(MemberMap);

		return member;
	}

	public void insertMember(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW(),");
		sql.append("loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("`name` = ?;", name);

		DBUtil.insert(conn, sql);

	}

}
