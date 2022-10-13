package com.gbr.exam.sbam.service;

import org.springframework.stereotype.Service;

import com.gbr.exam.sbam.repository.MemberRepository;
import com.gbr.exam.sbam.vo.Member;

@Service
public class UserMemberService {

	private MemberRepository memberRepository;

	public UserMemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		return memberRepository.getLastInsertId();
	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
