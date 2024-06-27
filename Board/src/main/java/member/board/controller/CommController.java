package member.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import member.board.dao.CommDao;
import member.board.dto.CommDto;
import member.board.service.CommService;

@Controller
public class CommController {
	
	@Autowired
	CommService service;
	
	@GetMapping("/comm/insert")
	@ResponseBody
	public String insertComm(CommDto dto) {
		int i = service.insertComm(dto);
		return i+"";
	}
	
	@GetMapping("/comm/delete")
	@ResponseBody
	public String deleteComm(int cno) {
		int i = service.deleteComm(cno);
		return i+"";
	}
	
	

}
