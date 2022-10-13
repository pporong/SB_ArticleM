package com.gbr.exam.sbam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.sbam.service.UserArticleService;
import com.gbr.exam.sbam.util.Ut;
import com.gbr.exam.sbam.vo.Article;
import com.gbr.exam.sbam.vo.ResultData;

@Controller
public class UserArticleController {
	
	// 인스턴스 변수
	@Autowired
	private UserArticleService userArticleService;

	// 액션메서드 
	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = userArticleService.writeArticle(title, body);

		Article article = userArticleService.getArticle(id);

		return article;
	}

	@RequestMapping("/user/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return userArticleService.getArticles();
	}
	

	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :(";
		}

		userArticleService.deleteArticle(id);

		return id + " 번 게시물이 삭제되었습니다. :)";
	}
	
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :(";
		}

		userArticleService.modifyArticle(id, title, body);

		return article;
	}

	@RequestMapping("/user/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다. :(", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), article);
	}

}