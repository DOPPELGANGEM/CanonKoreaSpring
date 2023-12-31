package kr.co.canon.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.canon.board.domain.Reply;
import kr.co.canon.board.service.ReplyService;
import kr.co.canon.board.store.ReplyStore;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyStore rStore;
	
	@Autowired
	private SqlSession session;

	@Override
	public int insertReply(Reply reply) {
		int result = rStore.insertReply(session, reply);
		return result;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = rStore.updateReply(session, reply);
		return result;
	}

	@Override
	public int updateLike(Reply reply) {
		int result = rStore.updateLike(session, reply);
		return result;
	}

	@Override
	public int updateUnLike(Reply reply) {
		int result = rStore.updateUnLike(session, reply);
		return result;
	}

	@Override
	public int deleteReply(Reply reply) {
		int result = rStore.deleteReply(session, reply);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(int refBoardNo) {
		List<Reply> rList = rStore.selectReplyList(session, refBoardNo);
		return rList;
	}

	

	
	
	
}
