package com.gbr.exam.sbam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.sbam.service.UserArticleService;
import com.gbr.exam.sbam.service.UserMemberService;
import com.gbr.exam.sbam.vo.Article;
import com.gbr.exam.sbam.vo.Member;

@Controller
public class UserMemberController {

	@Autowired
	private UserMemberService userMemberService;

	@RequestMapping("user/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		int id = userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		Member member = userMemberService.getMemberById(id);

		return member;
	}

}