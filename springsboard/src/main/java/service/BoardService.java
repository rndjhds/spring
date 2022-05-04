package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;

public interface BoardService {

	public void insert(BoardBean b) throws Exception;

	public Map<String, Object> board_list(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public void hit(int board_num) throws Exception;

	public BoardBean board_cont(int board_num) throws Exception;

	public int edit(HttpServletResponse response, BoardBean b) throws Exception;

	public int del_ok(HttpServletResponse response, int board_num,
			String board_pass) throws Exception;

	public void reply_ok(BoardBean b) throws Exception;

}
