package kr.co.canon.notice.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.canon.notice.domain.Notice;
import kr.co.canon.notice.domain.PageInfo;
import kr.co.canon.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;
	

	/**
	 * 공지사항 insert Controller
	 * return "notice/insert";
	 */
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.GET)
	public String showInsertForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.POST)
	public String insertNotice( 
		@ModelAttribute Notice notice
		, @RequestParam(value="noticeFileAdd", required=false) MultipartFile noticeFileAdd
		, HttpServletRequest request
		, Model model
			) {
		try {
			if(!noticeFileAdd.getOriginalFilename().equals("")) {
				// 파일이름
				String fileName = noticeFileAdd.getOriginalFilename();
				// 저장 후 그 경로를 DB에 저장하도록 준비한다.
				String root = request.getSession().getServletContext().getRealPath("resources");
				// 폴더가 없을 경우 자동 생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root + "\\noticeFileUpload"; //resources가 root가 됨
				File folder = new File(saveFolder); // 파일 인스턴스
				if(!folder.exists()) {
					folder.mkdir();
				}
				// 파일경로
				String savePath = saveFolder + "\\" + fileName;
				File file = new File(savePath);
				// 파일저장
				noticeFileAdd.transferTo(file);
				// 파일크기
				long fileLength = noticeFileAdd.getSize();
				// DB에 저장하기 위해 notice 데이터를 Set 하는 부분
				notice.setNoticeFilename(fileName);
				notice.setNoticeFilepath(savePath);
				notice.setNoticeFilelength(fileLength);
			}
			int result = service.insertNotice(notice);
			if(result > 0) {
				System.out.println("성공");
				return "redirect:/notice/list.do";
			} else {
				model.addAttribute("msg", "공지사항 등록이 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 등록 실패");
				model.addAttribute("url", "/notice/insert.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/notice/insert.do");
			return "common/serviceFailed";
		}
	}
	
	/**
	 * 공지사항 list Controller
	 * return "notice/list";
	 */
	@RequestMapping(value="/notice/list.do", method=RequestMethod.GET) 
	public String showNoticeList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			,Model model) {
		try {
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = service.selectNoticeList(pInfo);
			if(nList.size() > 0) {
				model.addAttribute("nList", nList);
				return "notice/list";
			} else {
				model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 목록 조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			} 
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	//페이징 처리를 위한 코드
	public PageInfo getPageInfo(int currentPage, int totalCount) {
		
		PageInfo pi = null;
		int recordCountPerPage = 10; // 한 페이지당 보여질 게시물의 갯수
		int naviCountPerPage = 10; // 한페이지 범위에 보여질 페이지의 갯수
		int naviTotalCount; // 범위의 총갯수
		int startNavi;
		int endNavi;
		
		naviTotalCount = (int)((double)totalCount/recordCountPerPage+0.9);
		// Math.ceil((double)totalCount/recordCountPerPage)
		// currentPage값이 1 ~ 5일때 startNavi가 1로 고정되도록 구해주는 식
		startNavi = (((int)((double)currentPage/naviCountPerPage+0.9))-1) * naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		// endNavi는 startNavi에 무조건 naviCountPerPage값을 더하므로
		// 실제 최대값보다 커질 수 있음.
		if(endNavi > naviTotalCount)  {
			endNavi = naviTotalCount;
		}
		pi = new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pi;
		
	}
	
	/**
	 * 공지사항 search Controller
	 * return "notice/search";
	 */
	
	
}
