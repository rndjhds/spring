package myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myspring.dao.BoardDao;
import myspring.model.Board;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;
	
	public int insert(Board board) {
		return dao.insert(board);
	}
}
