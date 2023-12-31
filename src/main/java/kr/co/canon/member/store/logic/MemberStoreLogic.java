package kr.co.canon.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.canon.member.domain.Member;
import kr.co.canon.member.store.MemberStore;

//StoreLogic 어노테이션
@Repository
public class MemberStoreLogic implements MemberStore {

	@Override
	public int insertMember(SqlSession sqlSession, Member member) {
		int result = sqlSession.insert("MemberMapper.insertMember", member); //"" 파란색부분 xml부분빼먹으면안됨
		return result;
	}

	@Override
	public int updateMember(SqlSession sqlSession, Member member) {
		int result = sqlSession.insert("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession sqlSession, String memberId) {
		int result = sqlSession.delete("MemberMapper.deleteMember",memberId);
		return result;
	}

	@Override
	public Member selectMemberLogin(SqlSession sqlSession, Member member) {
		Member mOne = sqlSession.selectOne("MemberMapper.selectMemberLogin",member);
		return mOne;
	}

	@Override
	public Member selectOneById(SqlSession sqlSession, String memberId) {
		Member member = sqlSession.selectOne("MemberMapper.selectOneById", memberId);
		return member;
	}

	@Override
	public Member selectCountCheck(SqlSession sqlSession, Member member) {
		Member mOne = sqlSession.selectOne("MemberMapper.selectCountCheck", member);
		return mOne;
	}

	
}
