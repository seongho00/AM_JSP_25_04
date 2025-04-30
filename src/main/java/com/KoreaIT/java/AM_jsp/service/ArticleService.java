package com.KoreaIT.java.AM_jsp.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dao.ArticleDao;
import com.KoreaIT.java.AM_jsp.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	private Connection conn;

	public ArticleService(Connection conn) {
		this.conn = conn;
		this.articleDao = new ArticleDao(conn);
	}

	public int getTotalArticleCount() {
		return articleDao.getTotalArticleCount();
	}

	public List<Article> getForPrintArticles(int limitFrom, int viewArticleCount) {
		return articleDao.getForPrintArticles(limitFrom, viewArticleCount);
	}

	public void insertArticle(String title, String body, String memberId) {
		articleDao.insertArticle(title, body, memberId);
	}

	public void updateArticle(String title, String body, int id) {
		articleDao.updateArticle(title, body, id);
	}

	public Article getArticleByMemberId(int memberId) {
		return articleDao.getArticleByMemberId(memberId);
	}

	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);

	}

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
		
	}

	public void getForPrintArticlesById(int limitFrom, int viewArticleCount) {
		
		
	}

}
