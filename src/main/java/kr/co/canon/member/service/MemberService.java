package kr.co.canon.member.service;

import kr.co.canon.member.domain.Member;

public interface MemberService {

	/**
	 * 硫ㅻ쾭�벑濡� Service
	 * @param member
	 * @return
	 */
	public int registerMember(Member member);

	/**
	 * 회원정보수정
	 * @param member
	 * @return
	 */
	public int updateMember(Member member);

	/**
	 * �쉶�썝�궘�젣
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId);

	/**
	 * 硫ㅻ쾭 濡쒓렇�씤 Service
	 * @param member
	 * @return
	 */
	public Member memberLoginCheck(Member member);

	/**
	 * �쉶�썝�긽�꽭 議고쉶
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);
	
	
	
	
	
	
	
	
	
}
