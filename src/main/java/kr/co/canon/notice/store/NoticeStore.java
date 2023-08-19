package kr.co.canon.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.canon.notice.domain.Notice;
import kr.co.canon.notice.domain.PageInfo;

public interface NoticeStore {

	/**
	 * 공지사항 등록 Store
	 * @param session
	 * @param notice
	 * @return
	 */
	int insertNotice(SqlSession session, Notice notice);

	/**
	 * 공지사항 갯수 조회 Store
	 * @param session
	 * @return
	 */
	int selectListCount(SqlSession session);

	/**
	 * 공지사항 목록 조회 Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo);

}
