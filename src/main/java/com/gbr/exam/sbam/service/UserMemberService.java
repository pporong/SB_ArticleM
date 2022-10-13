package com.gbr.exam.sbam.service;

import org.springframework.stereotype.Service;

import com.gbr.exam.sbam.repository.MemberRepository;

@Service
public class UserMemberService {

	private MemberRepository memberRepository;

	public UserMemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);

	}
}
