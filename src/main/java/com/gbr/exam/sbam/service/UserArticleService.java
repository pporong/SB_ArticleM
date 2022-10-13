package com.gbr.exam.sbam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbr.exam.sbam.repository.ArticleRepository;
import com.gbr.exam.sbam.vo.Article;

@Service
public class UserArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public UserArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		articleRepository.makeTestData();
	}
	
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}
	
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}
	
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}


}
