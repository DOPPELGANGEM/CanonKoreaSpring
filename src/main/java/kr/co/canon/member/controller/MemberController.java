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

//@Controller ������̼�
@Controller
public class MemberController {
	//����������
	@Autowired
	private MemberService service;
	
	//doGet - ������ �̵���
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		
		return "member/joinMemberShip"; //jsp�����̸�
	}
	
	// doPost - ������ �����
	
	/**
	 * ȸ������ Controller
	 * 
	 */
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
				// ����
				return "redirect:/index.jsp";
			} else {
				// ����
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
		
	}
	/**
	 * ȸ�����������ϱ� Controller
	 * 
	 */
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET) 
	public String showModifyView(@RequestParam("memberId") String memberId
			, Model model) {
		
		try {
			Member member = service.showOneById(memberId);
			if(member != null) {
				model.addAttribute("member", member);
				return "member/modify";
			} else {
				model.addAttribute("msg", "��������ȸ�� �����Ͽ����ϴ�.");
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
				redirect.addAttribute("memberId", memberId); //�����̷�Ʈ�� ������Ʈ�������ִ¹�
				return "redirect:/index.jsp";
			} else {
				// �����ϸ� ������������ �̵�
				model.addAttribute("msg", "���������� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			// ���ܹ߻��� ������������ �̵�
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/serviceFailed";
		}
	}
	
	/**
	 * ȸ������ Controller
	 * 
	 */
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET) //a�±� get���
	public String removeMember(
		@RequestParam("memberId") String memberId
		,Model model) {
		try {
			int result = service.deleteMember(memberId);
			if(result > 0) {
				// �����ϸ� �����������̵� �׸��� �����ı�
				return "redirect:/member/logout.do";
			} else {
				// ����
				model.addAttribute("msg", "ȸ��Ż�� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "ȸ��Ż�� �Ϸ���� �ʾҽ��ϴ�.");
			return "common/serviceFailed";
		}
	}

	/**
	 * �α��� Controller
	 * 
	 */
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
					model.addAttribute("msg", "�����Ͱ� ��ȸ���� �ʾҽ��ϴ�.");
					return "common/serviceFailed";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", e.getMessage());
				return "common/serviceFailed";
			}

	}
	
	/**
	 * �α׾ƿ� Controller
	 * 
	 */
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout( 
		// SessionStatus�� �������� ������̼�(SessionAttributes)�� �����Ǵ� ������ �����Ų��.
		// ���Ǵ� �޼ҵ�� setComplete();
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
			model.addAttribute("msg", "�α׾ƿ��� �Ϸ����� ���߽��ϴ�.");
			return "common/serviceFailed";
		}
	}

	/**
	 * ���������� Controller
	 * 
	 */
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
					model.addAttribute("msg","������ ��ȸ�� �����Ͽ����ϴ�.");
					return "common/serviceFailed";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", e.getMessage());
				return "common/serviceFailed";
			}
		}
	
}

























