package com.KoreaIT.java.AM_jsp.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dto.Article;
import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

public class ArticleDao {

	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int getTotalArticleCount() {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*)");
		sql.append("FROM article;");
		return DBUtil.selectRowIntValue(conn, sql);
	}

	public List<Article> getForPrintArticles(int limitFrom, int viewArticleCount) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*, M.name");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id desc");
		sql.append("LIMIT ?, ? ;", limitFrom, viewArticleCount);

		List<Map<String, Object>> articleMaps = DBUtil.selectRows(conn, sql);
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : articleMaps) {
			articles.add(new Article(articleMap));
		}
		

		return articles;
	}

	public void insertArticle(String title, String body, String memberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body` = ?,", body);
		sql.append("memberId = ?;", memberId);

		DBUtil.insert(conn, sql);
	}

	public void updateArticle(String title, String body, int id) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article ");
		sql.append("SET regDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body` = ?", body);
		sql.append("WHERE id = ?;", id);

		DBUtil.update(conn, sql);

	}

	public Article getArticleByMemberId(int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE memberId = ?", memberId);

		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

		Article article = new Article(articleMap);

		return article;
	}

	public void deleteArticle(int id) {

		SecSql sql = new SecSql();

		sql.append("DELETE FROM article WHERE id =?;", id);

		DBUtil.delete(conn, sql);
	}

	public Article getForPrintArticleById(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT A.*, M.name");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("WHERE A.id = ?;", id);

		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

		Article article = new Article(articleMap);

		return article;
	}

	public Article getArticleById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT * FROM article WHERE id =?;", id);

		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

		Article article = new Article(articleMap);

		return article;
	}

}
