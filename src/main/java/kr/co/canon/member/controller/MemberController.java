package kr.co.canon.member.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.canon.member.domain.Member;
import kr.co.canon.member.service.MemberService;

//@Controller 어노테이션
@Controller
public class MemberController {
	//의존성주입
	@Autowired
	private MemberService service;
	
	//doGet - 페이지 이동용
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		
		return "member/joinMemberShip"; //jsp 파일
	}
	
	//doPost - 데이터 저장용
	
	//회원가입 Controller
	@RequestMapping(value="/member/register.do", method=RequestMethod.POST)
		public String registerMember(
			HttpServletRequest request
		, HttpServletResponse response
		, @RequestParam("memberId") String memberId
		, @RequestParam("memberPw") String memberPw
		, @RequestParam("memberName") String memberName
		, @RequestParam("memberAge") int memberAge
		, @RequestParam("memberGender") String memberGender
		, @RequestParam("memberEmail") String memberEmail
		, @RequestParam("memberPhone") String memberPhone
		, @RequestParam("memberAddress") String memberAddress
		, @RequestParam("memberHobby") String memberHobby
		, Model model) {
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
		
		try {
			int result = service.registerMember(member);
			if(result > 0) {
				// 성공
				return "redirect:/index.jsp";
			} else {
				// 실패
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
		
	}
	
	//회원수정 Controller
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET) 
	public String showModifyView(@RequestParam("memberId") String memberId
			, Model model) {
		
		try {
			Member member = service.showOneById(memberId);
			if(member != null) {
				model.addAttribute("member", member);
				return "member/modify";
			} else {
				model.addAttribute("msg", "데이터 조회에 실패하였습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String modifyMember( 
			@RequestParam("memberId") String memberId
		,	@RequestParam("memberPw") String memberPw
		, @RequestParam("memberEmail") String memberEmail
		, @RequestParam("memberPhone") String memberPhone
		, @RequestParam("memberAddress") String memberAddress
		, @RequestParam("memberHobby") String memberHobby
		, RedirectAttributes redirect
		,	Model model) {
		try {
			Member member = new Member(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
			int result = service.updateMember(member);
			if(result > 0) {
				redirect.addAttribute("memberId", memberId);
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "데이터 조회에 실패하였습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	//회원삭제 Controller
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET) //a�±� get���
	public String removeMember(
		@RequestParam("memberId") String memberId
		,Model model) {
		try {
			int result = service.deleteMember(memberId);
			if(result > 0) {
				// 성공하면 메인페이지로 가고, 세션 파괴 되어야 함.
				return "redirect:/member/logout.do";
			} else {
				// 실패
				model.addAttribute("msg", "회원탈퇴가 완료되지 않았습니다.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}

	//로그인 Controller
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String showLoginForm(Model model) {
		
		return "member/myCanon";
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String memberLogin(
		HttpServletRequest request
		,@RequestParam("memberId") String memberId
		,@RequestParam("memberPw") String memberPw
		, Model model) {
			try {
				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				Member mOne = service.memberLoginCheck(member);
				if(mOne != null) {
					model.addAttribute("memberId", mOne.getMemberId());
					model.addAttribute("memberPw", mOne.getMemberPw());
					model.addAttribute("memberName",mOne.getMemberName());
					return "member/myCanon";
				} else {
					model.addAttribute("msg", "데이터가 조회되지 않았습니다.");
					return "common/serviceFailed";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", e.getMessage());
				return "common/serviceFailed";
			}

	}
	
	//로그아웃 Controller
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout( 
		// SessionStatus는 스프링의 어노테이션(@SessionAttributes)로 지원되는 세션을 만료시킨다.
		// 사용된 메소드는 setComplete();
		HttpSession sessionPrev
	,	SessionStatus session
	, Model model){
		if(session != null) {
			session.setComplete();
			if(session.isComplete()) {
				//���Ǹ��� ��ȿ��üũ
			}
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg", "로그아웃을 완료하지 못했습니다.");
			return "common/serviceFailed";
		}
	}

	//마이페이지 Controller
	@RequestMapping(value="/member/mypage.do", method=RequestMethod.GET) 
		public String showDetailMember(
			@RequestParam("memberId") String memberId
			,Model model){
			try {
				Member member = service.showOneById(memberId);
				if(member != null) {
					model.addAttribute("member",member);
					return "member/myPage";
				} else {
					model.addAttribute("msg", "데이터 조회에 실패하였습니다.");
					return "common/serviceFailed";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", e.getMessage());
				return "common/serviceFailed";
			}
		}
	
}

























