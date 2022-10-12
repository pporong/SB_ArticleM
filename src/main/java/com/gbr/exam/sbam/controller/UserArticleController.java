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
	}

	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);

		articles.add(article);
		lastArticleId = id;

		return article;
	}
}