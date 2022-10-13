package com.gbr.exam.sbam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.sbam.service.UserMemberService;
import com.gbr.exam.sbam.vo.Member;

@Controller
public class UserMemberController {

	@Autowired
	private UserMemberService userMemberService;

	@RequestMapping("user/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {

		if (loginId == null || loginId.trim().length() == 0) {
			return "!! 아이디를 입력 해 주세요 !!";
		}
		if (loginPw == null || loginPw.trim().length() == 0) {
			return "!! 비밀번호를 입력 해 주세요 !!";
		}
		if (name == null || name.trim().length() == 0) {
			return "!! 이름을 입력 해 주세요 !!";
		}
		if (nickname == null || nickname.trim().length() == 0) {
			return "!! 닉네임을 입력 해 주세요 !!";
		}
		if (cellphoneNum == null || cellphoneNum.trim().length() == 0) {
			return "!! 전화번호를 입력 해 주세요 !!";
		}
		if (email == null || email.trim().length() == 0) {
			return "!! 이메일을 입력 해 주세요 !!";
		}
		
		int id = userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (id == -1) {
			return "!! 이미 존재하는 아이디입니다 !!";
		}
	
		Member member = userMemberService.getMemberById(id);

		return member;
	}

}