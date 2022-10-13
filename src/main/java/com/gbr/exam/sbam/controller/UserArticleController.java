package com.gbr.exam.sbam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.sbam.vo.Article;

@Controller
public class UserArticleController {
	private int lastArticleId;
	private List<Article> articles;
	
	public UserArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			int id = lastArticleId + 1;
			String title = "제목 " + i;
			String body = "내용 " + i;

			Article article = new Article(id, title, body);

			articles.add(article);
			lastArticleId = id;
		}
	}
	
	@RequestMapping("/user/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}
	
}