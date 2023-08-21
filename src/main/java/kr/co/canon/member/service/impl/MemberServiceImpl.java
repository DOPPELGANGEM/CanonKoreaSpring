package kr.co.canon.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.canon.member.domain.Member;
import kr.co.canon.member.service.MemberService;
import kr.co.canon.member.store.MemberStore;

//@ServiceImpl 어노테이션
@Service
public class MemberServiceImpl implements MemberService { 

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private MemberStore mStore;
	
	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(sqlSession, member); 
		return result;
	}

	@Override
	public int updateMember(Member member) {
		int result = mStore.updateMember(sqlSession, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(sqlSession, memberId);
		return result;
	}

	@Override
	public Member memberLoginCheck(Member member) {
		Member mOne = mStore.selectMemberLogin(sqlSession, member);
		return mOne;
	}

	@Override
	public Member showOneById(String memberId) {
		Member member = mStore.selectOneById(sqlSession, memberId);
		return member;
	}

	@Override
	public Member selectCountCheck(Member member) {
		Member mOne = mStore.selectCountCheck(sqlSession, member);
		return mOne;
	} 

}
