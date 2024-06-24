package member.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import member.board.dto.BoardDto;
import member.board.dto.MemDto;
import member.board.service.BoardService;

@Controller
@SessionAttributes("user")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@ModelAttribute("user")
	public MemDto getDto() {
		return new MemDto();
	}
	
	//요청 page 번호를 받아서 페이지에 맞는 글을 갯수에 맞게 꺼내옴
	//전체 글 갯수에 맞춰 페이징 처리
	@RequestMapping("/board/list")
	public String list(@RequestParam(name="p", defaultValue = "1") int page, Model m) {
		// 글이 있는 지 체크
		int count = service.count();
		if(count > 0) {
			int perPage = 10; // 한 페이지에 보일 글의 갯수
			int startRow = (page -1) * perPage;
			
			List<BoardDto> boardList = service.boardList(startRow);
			m.addAttribute("bList",boardList);
			int pageNum = 5;
			int totalPages = count / perPage + (count % perPage > 0 ? 1 : 0); //전체 페이지 수
			
			int begin = (page - 1) / pageNum * pageNum + 1;
			int end = begin + pageNum -1;
			if(end > totalPages) {
				end = totalPages;
			}
			 m.addAttribute("begin", begin);
			 m.addAttribute("end", end);
			 m.addAttribute("pageNum", pageNum);
			 m.addAttribute("totalPages", totalPages);
			
			}
		m.addAttribute("count", count);
		return "board/list";
	}   
		
	@GetMapping("/board/write")
	public String writeForm(@ModelAttribute("user") MemDto dto) {
		return "board/write";
	}
	
	@PostMapping("/board/write")
	public String write(BoardDto dto) {
		service.insert(dto);
		return "redirect:/board/list";
	}
	
	@GetMapping("board/content/{no}")
	public String content(@ModelAttribute("user")MemDto user, @PathVariable("no") int no, Model m) {
		BoardDto dto = service.boardOne(no);
		m.addAttribute("dto", dto);
		return "board/content";
	}

	@GetMapping("board/update/{no}")
	public String updateForm(@PathVariable("no") int no, Model m) {
		BoardDto dto = service.boardOne(no);
		m.addAttribute("dto", dto);
		return "board/updateForm";
	}
	
	@PutMapping("/board/update")
	public String update(BoardDto dto) {
		service.updateBoard(dto);
		return "redirect:list";
	}
	
	@DeleteMapping("/board/delete")
	@ResponseBody
	public String delete(@RequestParam("no") int no) {
		int i = service.deleteBoard(no); 
		return ""+i;
	}
	

}
