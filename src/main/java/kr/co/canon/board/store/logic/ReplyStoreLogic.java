package kr.co.canon.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.canon.board.domain.Reply;
import kr.co.canon.board.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore {

	@Override
	public int insertReply(SqlSession session, Reply reply) {
		int result = session.insert("ReplyMapper.insertReply", reply);
		return result;
	}

	@Override
	public int updateReply(SqlSession session, Reply reply) {
		int result = session.update("ReplyMapper.updateReply", reply);
		return result;
	}

	@Override
	public int updateLike(SqlSession session, Reply reply) {
		int result = session.update("ReplyMapper.updateLike", reply);
		return result;
	}

	@Override
	public int updateUnLike(SqlSession session, Reply reply) {
		int result = session.update("ReplyMapper.updateUnLike", reply);
		return result;
	}

	@Override
	public int deleteReply(SqlSession session, Reply reply) {
		int result = session.delete("ReplyMapper.deleteReply", reply);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(SqlSession session, int refBoardNo) {
		List<Reply> rList = session.selectList("ReplyMapper.selectReplyList", refBoardNo);
		return rList;
	}

}
