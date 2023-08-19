package kr.co.canon.notice.service;

import java.util.List;

import kr.co.canon.notice.domain.Notice;
import kr.co.canon.notice.domain.PageInfo;

public interface NoticeService {

	/**
	 * 공지사항 등록 Service
	 * @param notice
	 * @return
	 */
	int insertNotice(Notice notice);

	/**
	 * 공지사항 전체 갯수 조회 Service
	 * @return
	 */
	int getListCount();

	/**
	 * 공지사항 목록 조회 Service
	 * @return
	 */
	List<Notice> selectNoticeList(PageInfo pInfo);

}
