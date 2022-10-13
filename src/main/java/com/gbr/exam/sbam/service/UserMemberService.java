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
		
		Member existsMember = getMemberByLoginId(loginId);

		// id 중복체크
		if (existsMember != null) {
			return -1;
		}
		
		// name + email 중복체크
		existsMember = getMemberByNameAndEmail(name, email);

		if (existsMember != null) {
			return -2;
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		return memberRepository.getLastInsertId();
	}
	
	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);

	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
