package kr.co.canon.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.canon.board.domain.Board;
import kr.co.canon.board.domain.PageInfo;
import kr.co.canon.board.service.BoardService;
import kr.co.canon.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardStore bStore;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertBoard(Board board) {
		int result = bStore.insertBoard(sqlSession, board);
		return result;
	}

	@Override
	public int getListCount() {
		int result = bStore.selectListCount(sqlSession);
		return result;
	}

	@Override
	public List<Board> selectBoardList(PageInfo pInfo) {
		List<Board> bList = bStore.selectBoardList(sqlSession, pInfo);
		return bList;
	}
	
}
