package member.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.board.dao.BoardDao;
import member.board.dto.BoardDto;

@Service
public class BoardService {

	@Autowired
	BoardDao dao;
	
	public int insert(BoardDto dto) {
		return dao.insert(dto);
	}
	
	public List<BoardDto> boardList(int start){
		Map<String, Object> m = new HashMap<>();
		m.put("start", start);
		m.put("count", 10); // 10개 글 불러오기
		return dao.boardList(m);
	}
	
	public int count() {
		return dao.count();
	}
	
	public BoardDto boardOne(int no) {
		dao.addReadcount(no);// 조회수 증가
		return dao.boardOne(no);
	}
	
	public int updateBoard(BoardDto dto) {
		return dao.updateBoard(dto);
	}
	
	public int deleteBoard(int no) {
		return dao.deleteBoard(no);
	}
	public List<BoardDto> search(int searchn, String search, int start){
		Map<String, Object> m = new HashMap<>();
		m.put("start", start);
		m.put("count", 10); // 10개 글 불러오기
		m.put("searchn", searchn);
		m.put("search", search);
		return dao.search(m);
	}
	public int countSearch(int searchn, String search) {
		Map<String,Object> m = new HashMap<>();
		m.put("searchn", searchn);
		m.put("search", search);
		return dao.countSearch(m);
	}
	
}
