package com.gbr.exam.sbam.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.gbr.exam.sbam.vo.Member;

@Mapper
public interface MemberRepository {
	@Insert("""
				INSERT INTO `member`
				SET regDate = NOW(),
				updateDate = NOW(),
				loginId = #{loginId},
				loginPw = #{loginPw},
				`name` = #{name},
				nickname = #{nickname},
				cellphoneNum = #{cellphoneNum},
				email = #{email}
			""")
	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.id = #{id}
				""")
	Member getMemberById(int id);
	
	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.loginId = #{loginId}
				""")
	Member getMemberByLoginId(String loginId);
	
	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.name = #{name}
			AND M.email = #{email}
				""")
	Member getMemberByNameAndEmail(String name, String email);
}
