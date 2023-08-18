package kr.co.canon.member.service;

import kr.co.canon.member.domain.Member;

public interface MemberService {

	/**
	 * 회원 등록 Service
	 * @param member
	 * @return
	 */
	public int registerMember(Member member);

	/**
	 * 회원 정보 수정 Service
	 * @param member
	 * @return
	 */
	public int updateMember(Member member);

	/**
	 * 회원 삭제(탈퇴) Service
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId);

	/**
	 * 회원 로그인 Service
	 * @param member
	 * @return
	 */
	public Member memberLoginCheck(Member member);

	/**
	 * 회원 상세 조회 Service
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);
	
	
	
	
	
	
	
	
	
}
