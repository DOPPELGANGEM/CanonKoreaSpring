package kr.co.canon.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.canon.board.domain.Board;
import kr.co.canon.board.domain.PageInfo;
import kr.co.canon.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore {

	@Override
	public int insertBoard(SqlSession sqlSession, Board board) {
		int result = sqlSession.insert("BoardMapper.insertBoard", board);
		return result;
	}

	@Override
	public int deleteBoard(SqlSession sqlSession, Board board) {
		int result = sqlSession.update("BoardMapper.deleteBoard", board);
		return result;
	}

	@Override
	public int selectListCount(SqlSession sqlSession) {
		int result = sqlSession.selectOne("BoardMapper.selectListCount");
		return result;
	}

	@Override
	public List<Board> selectBoardList(SqlSession sqlSession, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<Board> bList = sqlSession.selectList("BoardMapper.selectBoardList", null, rowBounds);
		return bList;
	}

	@Override
	public Board selectBoardByNo(SqlSession sqlSession, Integer boardNo) {
		Board board = sqlSession.selectOne("BoardMapper.selectBoardByNo",boardNo);
		return board;
	}

}
