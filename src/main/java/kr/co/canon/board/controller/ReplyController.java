package kr.co.canon.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.canon.board.domain.Reply;
import kr.co.canon.board.service.ReplyService;

@Controller
@RequestMapping("/reply") // 공용주소
public class ReplyController {
	
	@Autowired
	private ReplyService rService;
	
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public ModelAndView insertReply(ModelAndView mv
	, @ModelAttribute Reply reply
	, HttpSession session) {
		
		String url = ""; // url 변수화
		try {
			// 세션에서 아이디가져오게되서 로그인하고 확인!
			String replyWriter = (String)session.getAttribute("memberId");
			System.out.println("replyWriter세션값:"+replyWriter);
			if(replyWriter != null && !replyWriter.equals("")) { // null 체크 앞에서 else로 가게하기방지
				reply.setReplyWriter(replyWriter);
				int result = rService.insertReply(reply);
				url = "/board/detail.do?boardNo="+reply.getRefBoardNo();
				System.out.println("result값" + result);
				System.out.println("url값" + url);
				if(result > 0) {
					mv.setViewName("redirect:"+url); //detail.jsp쪽
				} else {
					mv.addObject("msg", "댓글 등록이 완료되지 않았습니다.");
					mv.addObject("error", "댓글 등록 실패");
					mv.addObject("url", "/board/detail.do?boardNo="+reply.getRefBoardNo());
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("msg", "로그인이 되지 않았습니다.");
				mv.addObject("error", "로그인 정보확인 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public ModelAndView updateReply(ModelAndView mv
		, @ModelAttribute Reply reply	
		, HttpSession session	) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			System.out.println("수정된 replyWriter값 ="+ replyWriter);
			if(replyWriter != null && !replyWriter.equals("")) {
				url = "/board/detail.do?boardNo="+reply.getRefBoardNo();
				int result = rService.updateReply(reply);
				mv.setViewName("redirect:"+url);
			} else {
				mv.addObject("msg", "로그인이 되지 않았습니다.");
				mv.addObject("error", "로그인 정보확인 실패");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/serviceFailed");
			}
		} catch(Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFailed");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteReply(
		ModelAndView mv
	, @ModelAttribute Reply reply
	, HttpSession session ) {
		String url = ""; // 전역변수 catch 절때문
		try {
			String memberId = (String)session.getAttribute("memberId");
			String replyWriter = reply.getReplyWriter();
			System.out.println("삭제할 replyWriter값=" + replyWriter);
			url = "/board/detail.do?boardNo="+reply.getRefBoardNo();
			if(replyWriter != null && replyWriter.equals(memberId)) {
				// 삭제라는 것을 session과 똑같이 만약 그 아이디가 지우면 그 댓글이 지워지게
				int result = rService.deleteReply(reply);
				if(result > 0) {
					// 성공
					mv.setViewName("redirect:"+url);
				} else {
					// 실패
					mv.addObject("msg", "댓글 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "댓글 삭제 실패");
					mv.addObject("url", url);
					mv.setViewName("common/serviceFailed");
				}
			} else {
				mv.addObject("msg", "자신의 댓글만 삭제할 수 있습니다.");
				mv.addObject("error", "댓글 삭제 불가");
				mv.addObject("url", url);
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", url);
			mv.setViewName("common/serviceFailed");
		}
		return mv;
	}
	
	
	
	
}
