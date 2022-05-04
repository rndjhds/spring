package dao;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import model.BoardBean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDAOImpl implements BoardDao{

	@Autowired
	private SqlSession sqlSession;

//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}//스프링 setter DI 의존관계를 설정
	
	
	/* 게시판 저장 */
	@Transactional // 2개 이상의 sql을 실행 할 경우  쓴다. sql실행에서 문제 발생시 자동으로 Rollback시켜준다. 
	public void insertBoard(BoardBean b) throws Exception {
//		getSession();		
		sqlSession.insert("Test.board_insert", b);
//		sqlsession.commit();		
	}

	
	/* 게시물 목록  */
	@Transactional
	public List<BoardBean> getBoardList(int page) throws Exception {
		List<BoardBean> list = sqlSession.selectList("Test.board_list", page);

		return list;
	}

	
	/* 게시판 총 갯수  */
	@Transactional
	public int getListCount() throws Exception {
		int count = 0;	
		count = ((Integer) sqlSession.selectOne("Test.board_count")).intValue();

		return count;
	}

	
	/* 게시판 글내용보기  */
	@Transactional
	public BoardBean getBoardCont(int board_num) throws Exception {
		return (BoardBean) sqlSession.selectOne("Test.board_cont",board_num);
	}

	
	/* 게시판 조회수 증가  */
	@Transactional
	public void boardHit(int board_num) throws Exception {
		sqlSession.update("Test.board_hit", board_num);		
	}

	
	/* 게시물 수정  */
	@Transactional
	public void boardEdit(BoardBean b) throws Exception {
		sqlSession.update("Test.board_edit", b);		
	}

	
	/* 게시물 삭제  */
	@Transactional
	public void boardDelete(int board_num) throws Exception {
		sqlSession.delete("Test.board_del", board_num);				
	}

	
	/* 답변글 레벨 증가  */
	@Transactional
	public void refEdit(BoardBean b) throws Exception {
		sqlSession.update("Test.board_Level", b);		
	}

	
	/* 답변글 저장  */
	@Transactional
	public void boardReplyOk(BoardBean b) throws Exception {
		sqlSession.insert("Test.board_reply", b);		
	}

}
