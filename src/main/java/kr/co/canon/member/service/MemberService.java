package kr.co.canon.member.service;

import kr.co.canon.member.domain.Member;

public interface MemberService {

	/**
	 * 멤버 등록 Service
	 * @param member
	 * @return
	 */
	public int registerMember(Member member);

	/**
	 * 회원 정보 수정
	 * @param member
	 * @return
	 */
	public int updateMember(Member member);

	/**
	 * 회원 삭제(탈퇴)
	 * @param memberId
	 * @return
	 */
	public int deleteMember(String memberId);

	/**
	 * 멤버 로그인 Service
	 * @param member
	 * @return
	 */
	public Member memberLoginCheck(Member member);

	/**
	 * 회원 상세 조회
	 * @param memberId
	 * @return
	 */
	public Member showOneById(String memberId);
	
	
	
	
	
	
	
	
	
}
