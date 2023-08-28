package kr.co.canon.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.canon.board.domain.Board;
import kr.co.canon.board.domain.PageInfo;

public interface BoardStore {

	/**
	 * 게시글 등록
	 * @param sqlSession
	 * @param board
	 * @return
	 */
	int insertBoard(SqlSession sqlSession, Board board);

	/**
	 * 게시글 전체갯수
	 * @param sqlSession
	 * @return
	 */
	int selectListCount(SqlSession sqlSession);

	/**
	 * 게시글 전체 조회 Store
	 * @param sqlSession
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(SqlSession sqlSession, PageInfo pInfo);

	/**
	 * 게시글 상세조회 Store
	 * @param sqlSession
	 * @param boardNo
	 * @return
	 */
	Board selectBoardByNo(SqlSession sqlSession, Integer boardNo);

}
