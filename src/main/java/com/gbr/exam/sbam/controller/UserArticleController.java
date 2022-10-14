package com.gbr.exam.sbam.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		ResultData<Integer> writeArticleRd = userArticleService.writeArticle(loginedMemberId, title, body);

		int id = (int) writeArticleRd.getData1();
		
		Article article = userArticleService.getArticle(id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	@RequestMapping("/user/article/getArticles")
	public String showList(Model model) {
		List<Article> articles = userArticleService.getArticles();

		model.addAttribute("articles", articles);

		return "user/article/list";
	}
	

	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "!! 로그인 후 이용 가능합니다 !!");
		}
		
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다 :(", id), "id", id);
		} 
		// 삭제 권한 체크
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", Ut.f("%d번 게시물에 대한 삭제 권한이 없습니다.", id));
		}

		userArticleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다. :)", id), "id", id);
	}
	
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession httpSession, int id, String title, String body) {

		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다 :(", id), "id", id);
		}

		ResultData actorCanModifyRd = userArticleService.actorCanModify(loginedMemberId, article);

		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}

		return userArticleService.modifyArticle(id, title, body);

	}

	@RequestMapping("/user/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다. :(", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), "article", article);
	}

}