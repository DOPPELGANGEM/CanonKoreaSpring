package kr.co.canon.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
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
			if(noticeFileAdd != null && !noticeFileAdd.getOriginalFilename().equals("")) { // NullPointException 방지
				Map<String, Object> nMap = this.saveFile(noticeFileAdd, request); //saveFile 먼저
				System.out.println("nMap값 :" + nMap); //console창 확인 fileName,fileRename,filepath,fileLength 다넘어오고있음
				String fileName = (String)nMap.get("fileName");
				String fileRename = (String)nMap.get("fileRename");
				String filepath = (String)nMap.get("filePath");
				long fileLength = (long)nMap.get("fileLength");
				
				// DB에 저장하기 위해 notice 데이터를 Set 하는 부분
				notice.setNoticeFilename(fileName);
				notice.setNoticeFileRename(fileRename);
				notice.setNoticeFilepath(filepath);
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
	 * 공지사항 modify 수정 컨트롤러
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notice/modify.do", method=RequestMethod.GET)
	public String showModifyForm(
		@RequestParam("noticeNo") Integer noticeNo
		, Model model	) {
		Notice notice = service.selectNoticeByNo(noticeNo);
		model.addAttribute("notice", notice);
		return "notice/modify";
	}
	
	@RequestMapping(value="/notice/modify.do", method=RequestMethod.POST)
	public String updateNotice(
		//@ModelAttribute 는 jsp쪽 name값 그리고 Notice필드명이랑 같아야한다 그래야쓸 수 있음
		@ModelAttribute Notice notice
	,	@RequestParam (value="noticeFileAdd", required=false) MultipartFile noticeFileAdd
	, Model model
	, HttpServletRequest request ) {
		try {
			if(noticeFileAdd != null && !noticeFileAdd.isEmpty()) {
				// 수정
				// 1.대체 , 2.삭제 후 등록
				// 기존 업로드 된 파일 존재 여부 체크 후
				String fileName = notice.getNoticeFileRename(); //vo쪽 FileRename()
				if(fileName != null && !fileName.equals("")) {
					// 파일 있으면 기존파일 삭제
					this.deleteFile(request, fileName);
				}
				// 없으면 새로 업로드 하려는 파일 저장
				Map<String, Object> infoMap = this.saveFile(noticeFileAdd, request);
				String noticeFileName = (String)infoMap.get("fileName");
				String noticeFileReName = (String)infoMap.get("fileReName");
				String noticeFilePath = (String)infoMap.get("filePath");
				long noticeFilelength = (long)infoMap.get("fileLength");
				
				// notice 인스턴스
				notice.setNoticeFilename(noticeFileName);
				notice.setNoticeFileRename(noticeFileReName);
				notice.setNoticeFilepath(noticeFilePath);
				notice.setNoticeFilelength(noticeFilelength);
			}
			// 이 다음 비즈니스 로직수정
			int result = service.updateNotice(notice);
			if(result > 0) {
				return "redirect:/notice/detail.do?noticeNo="+notice.getNoticeNo();
			} else {
				model.addAttribute("msg", "정보 수정이 완료되지 않았습니다.");
				model.addAttribute("error", "공지사항 수정 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
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
			System.out.println("pInfo값:" + pInfo);
			System.out.println("nList값:" + nList);
			if(nList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
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
			return "common/serviceFailed";
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
	@RequestMapping(value="/notice/search.do", method=RequestMethod.GET)
	public String searchNoticeList(
		@RequestParam("searchCondition") String searchCondition //noticeMapper if test="" 이부분
	, @RequestParam("searchKeyword") String searchKeyword //noticeMapper if test="" 아래 쿼리문부분
	,	@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
	, Model model	) {
		// 2개의 값을 하나의 변수로 다루는 방법
		// 1.VO클래스 만드는 방법
		// 2.HashMap 사용하는 방법
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition); // paramMap.put(key,value)
		paramMap.put("searchKeyword", searchKeyword);
		int totalCount = service.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		// put() 메소드를 사용해서 key-value 설정을 하는데
		// key값(파란색)이 mapper.xml에서 사용된다!!
		List<Notice> searchList = service.searchNoticesByKeyword(pInfo, paramMap);
		
		if(!searchList.isEmpty()) {
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("sList", searchList);
			return "notice/search";
		} else {
			model.addAttribute("msg", "데이터 조회가 완료되지 않았습니다.");
			model.addAttribute("error", "공지사항 제목으로 조회 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/serviceFailed";
		}
	}
	
	/**
	 * 공지사항 상세 detail Controller
	 * return "notice/detail";
	 */
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
	public String showNoticeDetail(
		@RequestParam("noticeNo") Integer noticeNo //Integer NULL 체크 가능
		, Model model) {
			Notice notice = service.selectNoticeByNo(noticeNo);
			model.addAttribute("notice", notice);
			return "notice/detail";
	}
	
	/**
	 * Util 모음 File(saveFile 메서드)
	 * @param noticeFileAdd
	 * @param request
	 * @return
	 * 
	 */
	private Map<String, Object> saveFile(MultipartFile noticeFileAdd, HttpServletRequest request) throws Exception { //위에서 try - catch 할것이니 여기 오류던저줌
		// 넘겨야 하는 값이 여러개일때사용한다.
		// 1.VO클래스를 만드는 방법
		// 2.HashMap을 사용하는 방법 (보통 이방법을 사용함)
		
		// String도 있고 long도 있으니 object
		Map<String, Object> infoMap = new HashMap<String, Object>();
		System.out.println("infoMap값 :" + infoMap);
		// =================== 파일 이름 ===================
		String fileName = noticeFileAdd.getOriginalFilename();
		// 내가 저장한 후 그 경로를 DB에 저장하도록 준비하는 것
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 폴더가 없을 경우 자동 생성(내가 업로드한 파일을 저장할 폴더)
		String saveFolder = root + "\\noticeFileUpload";
		File folder = new File(saveFolder); //파일 인스턴스
		if(!folder.exists()) {
			folder.mkdir();
		}
		// =================== 파일 경로 ===================
		//	Random rand = new Random();
		//	String strResult = "N";
		//	for(int i =0; i < 7; i++) {
		//		int result = rand.nextInt(20)+1;
		//		strResult += result+"";
		//	}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //나중에 SS랑비교 중요 꼭기억하기!
		String strResult = sdf.format(new Date(System.currentTimeMillis())); // 이걸로 파일이름
		// https://change-words.tistory.com/entry/%EC%9E%90%EB%B0%94JAVA-substring-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A1%9C-%ED%8C%8C%EC%9D%BC%EA%B3%BC-%ED%99%95%EC%9E%A5%EC%9E%90%EB%AA%85-%EA%B5%AC%EB%B6%84-%EC%A7%93%EA%B8%B0
		String ext = fileName.substring(fileName.lastIndexOf(".")+1); // 파일이름에서 확장자명 자르는방법 .을 포함하지않고 자름 그래서 +1
		String fileRename = "notice_"+strResult+"."+ext; //png값을 잘랏다 .은 내가 만듬 Notice_ 파일이름
		String filePath = saveFolder + "\\" + fileRename;
		File file = new File(filePath);
		// ******************* 파일 저장 *******************
		noticeFileAdd.transferTo(file);
		// =================== 파일 크기 ===================
		long fileLength = noticeFileAdd.getSize();
		// 파일이름, 경로, 크기를 넘겨주기 위해 Map에 정보를 저장 한 후 return 함
		// 왜 return하는가? DB에 저장하기 위해서 필요한 정보라서!!
		infoMap.put("fileName", fileName);
		infoMap.put("fileReName", fileRename);
		infoMap.put("filePath", filePath);  
		infoMap.put("fileLength", fileLength);
		return infoMap;
	}
	
	private void deleteFile(HttpServletRequest request, String fileName) {
		//예시 //D:\\SpringWorkSpace\\KHChangHeeSpringMVC\\src\\main\\webapp\\resources
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delFilepath = root + "\\noticeFileUpload\\" + fileName;
		File file = new File(delFilepath);
		if(file.exists()) {
			file.delete();
		}
	}
	
	

}



























