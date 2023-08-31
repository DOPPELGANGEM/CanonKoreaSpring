package kr.co.canon.member.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		return "member/joinMemberShip";
	}
	
	//doPost - 데이터 저장용
	//회원가입 Controller
	@RequestMapping(value="/member/register.do", method=RequestMethod.POST)
		public String registerMember(
			@ModelAttribute Member member
		, Model model) {
		
		try {
			int result = service.registerMember(member);
			if(result > 0) {
				// 성공시 로그인페이지로 이동
				return "redirect:/index.jsp";
			} else {
				// 실패시 에러페이지이동
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
	public String showModifyView(
			@RequestParam("memberId") String memberId
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
			@ModelAttribute Member member
		// 모델에 키와 값으로 데이터를 넣어주면 jsp에서 꺼내서 사용가능
			, Model model) {
		try {
			Member confirmMember = service.selectCountCheck(member); // 회원정보 수정 전 체크 로직
//			System.out.println("memberId값 : "+member);
//			System.out.println("model값 : "+model); 
			if (confirmMember == null) {
				int result = service.updateMember(member);
				if(result > 0) {
					return "redirect:/index.jsp";
				} else {
					model.addAttribute("msg", "회원정보 수정 실패");
					return "common/serviceFailed";
				}
			}	else {
				model.addAttribute("msg", "회원정보가같습니다..다시수정해주세요");
				model.addAttribute("url", "/member/update.do?memberId="+member.getMemberId());
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
	public String memberLogin(@ModelAttribute Member member, HttpSession session, Model model) {
			//SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
			Member mOne = service.memberLoginCheck(member);
			System.out.println("mOne값:"+mOne);
			try {
				if(mOne != null) {
					session.setAttribute("memberId", mOne.getMemberId());
					session.setAttribute("memberName", mOne.getMemberName());
					session.setAttribute("memberName",mOne.getMemberName());
					return "member/myCanon";
				} else {
					model.addAttribute("msg", "로그인이 완료되지 않았습니다.");
					model.addAttribute("error", "로그인 실패");
					model.addAttribute("url", "/index.jsp");
					return "common/serviceFailed";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "관리자에게 문의해주세요.");
				model.addAttribute("error", e.getMessage());
				model.addAttribute("url", "/member/register.kh");
				return "common/serviceFailed";
			}

	}
	
	//로그아웃 Controller
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout(HttpSession session, Model model){
		if(session != null) {
			session.invalidate(); //세션무효화
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg", "로그아웃을 완료하지 못했습니다.");
			model.addAttribute("error", "로그아웃 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFailed";
		}
	}
	
	//마이페이지 Controller
	@RequestMapping(value="/member/mypage.do", method= {RequestMethod.GET, RequestMethod.POST})  //2개가능 (쿼리스트링이아닌 세션에서가져오므로)
	public String showDetailMember(
		// 쿼리스트링 받기 위해서 RequestParam 써줌
	//	@RequestParam("memberId") String memberId
		// 모델에 키와 값으로 데이터를 넣어주면 jsp에서 꺼내서 사용가능
		HttpSession session
		, Model model){
		try {
			String memberId = (String)session.getAttribute("memberId");
			Member member = null;
			System.out.println("memberId값" + memberId);
			if(memberId != "" && memberId != null) {
				member = service.showOneById(memberId);
			}
			if(member != null) {
				model.addAttribute("member", member);
				System.out.println("member값" + member);
				return "member/myPage";
			} else {
				model.addAttribute("msg", "회원정보조회를 완료하지 못했습니다.");
				model.addAttribute("error", "마이페이지 조회 실패");
				model.addAttribute("url","/index.jsp");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFailed";
		}
	}
	

}

























