package kr.co.canon.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.canon.board.domain.Board;
import kr.co.canon.board.domain.PageInfo;
import kr.co.canon.board.service.BoardService;

@Controller
//@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	// *** 게시글등록 Controller ***
	//Model과 차이점은 Model은 데이터만 저장하는데,
	//ModelAndView는 데이터와 이동하고자 하는 View Page를 같이 저장한다
	@RequestMapping(value="/board/insert.do", method=RequestMethod.GET)
	public ModelAndView showInsertForm(ModelAndView mv) {
		mv.setViewName("board/insert");
		return mv;
	}
	
	@RequestMapping(value="/board/insert.do", method=RequestMethod.POST)
	public ModelAndView boardRegister(
		ModelAndView mv
	, @ModelAttribute Board board
	, @RequestParam(value="boardFileAdd", required=false) MultipartFile boardFileAdd
	,	HttpServletRequest request) {
		try {
			if(boardFileAdd != null && !boardFileAdd.getOriginalFilename().equals("")) {
				// 파일정보(이름,리네임,경로,크기) 및 파일저장
				Map<String, Object> bMap = this.saveFile(request, boardFileAdd);
				System.out.println("bMap값:" + bMap);
				board.setBoardFileName((String)bMap.get("fileName"));
				board.setBoardFileRename((String)bMap.get("fileRename"));
				board.setBoardFilePath((String)bMap.get("filePath"));
				board.setBoardFileLength((long)bMap.get("fileLength"));
			}
			// 비즈니스 로직
			int result = bService.insertBoard(board);
			System.out.println("result 값:"+result);
			mv.setViewName("redirect:/board/list.do");
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url","/board/insert.do");
			mv.setViewName("common/serviceFailed");
		}
		return mv;
		
	}
	
	@RequestMapping(value="/board/list.do", method=RequestMethod.GET) 
	public ModelAndView showBoardList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			,ModelAndView mv) {	
		try {
			Integer totalCount = bService.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			System.out.println("pInfo값:" + pInfo);
			List<Board> bList = bService.selectBoardList(pInfo);
			if(!bList.isEmpty()) {
				//jsp에 값들 넘겨줌
//				mv.addObject("bList", bList);
//				mv.addObject("pInfo", pInfo);
//				mv.setViewName("board/list");
				mv.addObject("bList",bList).addObject("pInfo", pInfo).setViewName("board/list");
			} else {
				mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
				mv.addObject("error", "게시글 상세 조회 실패");
				mv.addObject("url", "/board/list.do");
				mv.setViewName("common/serviceFailed");
			}
		} catch (Exception e) {
			mv.addObject("msg", "게시글 조회가 완료되지 않았습니다. 관리자에게문의하세요!");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/insert.do");
			mv.setViewName("common/serviceFailed");
		} 
		return mv;
	}
	
	@RequestMapping(value="/board/detail.do", method=RequestMethod.GET)
	public ModelAndView showBoardDetail(
		ModelAndView mv
	, @RequestParam("boardNo") Integer boardNo
			) {
		
		return mv;
	}


	private PageInfo getPageInfo(Integer currentPage, int totalCount) {
		
		int recordCountPerPage = 10; // 한페이징보여질 갯수
		int naviCountPerPage = 10; // 몇개씩할건지
		int naviTotalCount;
		
		naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage); //내장객에 올림 Math.ceil ex) 102 /10 = 10.2 => 11.0 앞에잇는 int로 0을잘라서 넣게함!
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage -1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		return pInfo;
	}


	private Map<String, Object> saveFile(HttpServletRequest request, MultipartFile boardFileAdd) throws Exception {
		
		Map<String, Object> fileMap = new HashMap<String, Object>();
		// resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 파일 저장 경로 구하기
		String savePath = root + "\\buploadFiles";
		// 파일 이름 구하기
		String fileName = boardFileAdd.getOriginalFilename();
		// 파일 확장자 구하기
		String extension = boardFileAdd.getOriginalFilename().substring(boardFileAdd.getOriginalFilename().lastIndexOf(".")+1); //.을 포함하지 않으면서 +1
		System.out.println("extension 값:"+ extension);
		// 시간으로 파일리네임
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHss");
		String fileRename = "board_" + sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
		
		// 파일 저장 전 폴더 만들기
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		// 파일저장
		File saveFile = new File(savePath + "\\" + fileRename);
		boardFileAdd.transferTo(saveFile);
		long fileLength = boardFileAdd.getSize();
		// 파일정보리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/buploadFiles"+fileRename);
		fileMap.put("fileLength", fileLength);
		
		return fileMap;
	}
	
	
	
	
	
	
}
