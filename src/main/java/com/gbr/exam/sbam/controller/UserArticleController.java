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
	public ResultData<Article> doAdd(String title, String body) {
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		ResultData<Integer> writeArticleRd = userArticleService.writeArticle(title, body);

		int id = (int) writeArticleRd.getData1();
		
		Article article = userArticleService.getArticle(id);

		return ResultData.newData(writeArticleRd, article);
	}

	@RequestMapping("/user/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = userArticleService.getArticles();

		return ResultData.from("S-1", "Article List", articles);
	}
	

	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(int id) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다 :(", id), id);
		}

		userArticleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다. :)", id), id);
	}
	
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(int id, String title, String body) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다 :(", id), id);
		}

		userArticleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다. :)", id), id);
	}

	@RequestMapping("/user/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다. :(", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), article);
	}

}