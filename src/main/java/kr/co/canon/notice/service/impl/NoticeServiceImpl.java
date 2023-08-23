package kr.co.canon.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.canon.notice.domain.Notice;
import kr.co.canon.notice.domain.PageInfo;
import kr.co.canon.notice.service.NoticeService;

import kr.co.canon.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private SqlSession session;
	@Autowired
	private NoticeStore nStore;
	
	// 공지사항 등록 ServiceImpl
	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	//공지사항 수정 ServiceImpl
	@Override
	public int updateNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	// 공지사항 전체 갯수 조회 ServiceImpl
	@Override
	public int getListCount() {
		int result = nStore.selectListCount(session);
		return result;
	}
	
	// 공지사항 목록 조회 ServiceImpl
	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}
	
	// 공지사항 검색 게시물 전체 갯수 ServiceImpl
	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = nStore.selectListCount(session, paramMap);
		return result;
	}
	
	// 공지사항 조건에 따라 키워드로 검색 ServiceImpl
	@Override
	public List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Notice> searchList = nStore.searchNoticesByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	// 공지사항 번호로 조회 ServiceImpl
	@Override
	public Notice selectNoticeByNo(Integer noticeNo) {
		Notice notice = nStore.selectNoticeByNo(session, noticeNo);
		return notice;
	}

	
	

}
