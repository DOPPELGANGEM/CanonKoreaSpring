package kr.co.canon.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.canon.board.domain.Reply;

public interface ReplyStore {

	/**
	 * 게시글 댓글 등록 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int insertReply(SqlSession session, Reply reply);

	/**
	 * 댓글 전체조회
	 * @param session
	 * @param refBoardNo
	 * @return
	 */
	List<Reply> selectReplyList(SqlSession session, int refBoardNo);
	
	
	
	

}
