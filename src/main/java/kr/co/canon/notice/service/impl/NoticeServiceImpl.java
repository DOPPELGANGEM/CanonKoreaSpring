package kr.co.canon.notice.service.impl;

import java.util.List;

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
	
	/**
	 *  공지사항 등록 Serviceimpl
	 */
	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	/**
	 *  공지사항 전체 갯수 조회 Serviceimpl
	 */
	@Override
	public int getListCount() {
		int result = nStore.selectListCount(session);
		return result;
	}
	
	/**
	 *  공지사항 목록 조회 Serviceimpl
	 */
	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}

	
	

}
