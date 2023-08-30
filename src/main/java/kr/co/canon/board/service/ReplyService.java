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
	 * 게시글 댓글 수정 Service
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);

	/**
	 * 게시글 좋아요 Service
	 * @param reply
	 * @return
	 */
	int updateLike(Reply reply);

	/**
	 * 게시판 댓글삭제 Service 
	 * @param reply
	 * @return
	 */
	int deleteReply(Reply reply);

	/**
	 * 댓글 리스트 조회 Service
	 * Board Controller에서 옴
	 * @param boardNo
	 * @return
	 */
	List<Reply> selectReplyList(int refBoardNo);
	
}
