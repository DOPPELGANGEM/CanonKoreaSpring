package kr.co.canon.member.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.canon.member.domain.Member;

public interface MemberStore {
	
	/**
	 * 회원 등록 Store
	 * @param sqlSession
	 * @param member
	 * @return
	 */
	public int insertMember(SqlSession sqlSession, Member member);

	/**
	 * 회원 수정 Store
	 * @param sqlSession
	 * @param member
	 * @return
	 */
	public int updateMember(SqlSession sqlSession, Member member);

	/**
	 * 회원 삭제 Store
	 * @param sqlSession
	 * @param memberId
	 * @return
	 */
	public int deleteMember(SqlSession sqlSession, String memberId);

	/**
	 * 회원 로그인 Store
	 * @param sqlSession
	 * @param member
	 * @return
	 */
	public Member selectMemberLogin(SqlSession sqlSession, Member member);

	/**
	 * 회원 상세 조회 Service
	 * @param sqlSession
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(SqlSession sqlSession, String memberId);
	
}
