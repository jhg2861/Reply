package scit.master.good.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import scit.master.good.vo.Member;

@Repository
public class MemberRepository {
	
	@Autowired
	SqlSession session;
	
	public int join(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = 0;
		
		
		try {
			result = mapper.insertMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public Member login(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		Member m = null;
		
		try {
			m = mapper.selectMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}

	public Member selectMember(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		Member m = null;
		
		try {
			m = mapper.selectMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return m;
	}

	public int updateMember(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result = 0;
		
		try {
			result = mapper.updateMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
