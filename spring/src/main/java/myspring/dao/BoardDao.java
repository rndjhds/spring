package myspring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import myspring.model.Board;

@Repository
public class BoardDao {	

	@Autowired
	private SqlSession session;	// root-context.xml이 설정되어야 사용할 수 있다.
	
	public int insert(Board board) {
		return session.insert("insert",board);
	}
	
	public int getCount() {
		return session.selectOne("count");
	}
	
	public List<Board> getBoardList(int page){
		return session.selectList("list", page);
	}
	
	public void updatecount(int no) {
		session.update("hit", no);
	}
	
	public Board getBoard(int no) {
		return session.selectOne("content", no);
	}
}
