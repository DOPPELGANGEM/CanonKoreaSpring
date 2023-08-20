package kr.co.canon.notice.store;

import java.util.List;
import java.util.Map;

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

	/**
	 * 공지사항 검색 게시물 전체 갯수 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	int selectListCount(SqlSession session, Map<String, String> paramMap);

	/**
	 * 공지사항 조건에 따라 키워드로 검색 Store
	 * @param session
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);

}
