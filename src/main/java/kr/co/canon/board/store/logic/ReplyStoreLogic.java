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
	public List<Reply> selectReplyList(SqlSession session, int refBoardNo) {
		List<Reply> rList = session.selectList("ReplyMapper.selectReplyList", refBoardNo);
		return rList;
	}

}
