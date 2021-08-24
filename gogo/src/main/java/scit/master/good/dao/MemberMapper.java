package scit.master.good.dao;

import scit.master.good.vo.Member;

public interface MemberMapper {
	//회원가입
	public int insertMember(Member member) throws Exception;
	//로그인 / 아이디 중복확인
	public Member selectMember(Member member) throws Exception;
	
	public int updateMember(Member member) throws Exception;
	
	
}
