package myspring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import myspring.model.Board;
import myspring.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	// 글작성 폼
	@RequestMapping("boardform.do")
	public String boardform() {
		return "board/boardform";
	}
	
	// 글작성
	@RequestMapping("boardwrite.do")
	public String boardwrite(Board board, Model model) {
		
		int result = service.insert(board);
		System.out.println("result:"+result);
		
		model.addAttribute("result", result);
		
		return "board/insertresult";
	}
	
	// 글 목록
	@RequestMapping("boardlist.do")
	public String boardlist(HttpServletRequest request, Model model) {
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page -1) * limit + 1;
		int endRow = page * limit;
		
		int listcount = service.getCount();
		System.out.println("listcount:"+listcount);
		
		List<Board> boardlist = service.getBoardList(page);
		System.out.println("boardlist:"+boardlist);
		
		// 총 페이지 수
		int pageCount = listcount/limit+((listcount%limit==0)?0:1);
		
		int startPage = ((page - 1) / 10) * limit + 1;	// 1, 11, 21...
		int endPage = startPage + 10 - 1;	// 10, 20, 30...
		
		if(endPage > pageCount)
			endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/boardlist";
		
	}
	
	// 상세 페이지 : 조회수 1증가 + 상세 정보 구하기
	@RequestMapping("boardcontent.do")
	public String boardcontent(int no, int page, Model model) {
		
		service.updatecount(no);	// 조회수 1증가
		Board board = service.getBoard(no);	// 상세 정보 구하기
		String content = board.getContent().replace("\n", "<br>");
		
		model.addAttribute("board", board);
		model.addAttribute("content", content);
		model.addAttribute("page", page);
		
		return "board/boardcontent";
	}
	
	
	
}
