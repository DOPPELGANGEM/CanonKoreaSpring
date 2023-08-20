package kr.co.canon.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.canon.notice.domain.Notice;
import kr.co.canon.notice.domain.PageInfo;
import kr.co.canon.notice.store.NoticeStore;

@Repository
public class NoticeServiceLogic implements NoticeStore{

	/**
	 * 공지사항 등록 StoreLogic
	 */
	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	/**
	 * 공지사항 갯수 조회 StoreLogic
	 */
	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.selectListCount");
		return result;
	}
	
	/**
	 * 공지사항 목록 조회 StoreLogic
	 */
	@Override
	public List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage(); // 가져오는 행의 갯수
		int offset = (pInfo.getCurrentPage() - 1)*limit; // 변하는 default 값
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}
	
}
