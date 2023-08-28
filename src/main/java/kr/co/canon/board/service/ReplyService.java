package kr.co.canon.board.service;

import java.util.List;

import kr.co.canon.board.domain.Reply;

public interface ReplyService {

	/**
	 * 게시글 등록 Service
	 * @param reply
	 * @return
	 */
	int insertReply(Reply reply);

	/**
	 * Board Controller에서 옴
	 * 댓글 리스트 조회 Service
	 * @param boardNo
	 * @return
	 */
	List<Reply> selectReplyList(int refBoardNo);
	
}
