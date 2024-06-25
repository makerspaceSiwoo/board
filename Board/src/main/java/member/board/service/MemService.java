package member.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.board.dao.MemDao;
import member.board.dto.MemDto;

@Service
public class MemService {

	@Autowired
	MemDao dao;
	
	public String idCheck(String id) {
		return dao.idCheck(id);
	}
	public int insertMem(MemDto dto) {
		return dao.insertMem(dto);
	}
	public MemDto login(MemDto dto) {
		return dao.login(dto);
	}
	public int updateMem(MemDto dto) {
		return dao.updateMem(dto);
	}
	public int deleteMem(String formpw, MemDto dto ) {
		String pw = dto.getPassword();
		if(pw.equals(formpw)) {
			return dao.deleteMem(dto.getId());
		}else {
			return 0;
		}
	}
	public int id_name_ck(String id, String name) {
		int check = dao.id_name_ck(id, name);
		if(check == 1) {// 아이디와 이름 값이 맞는 member가 존재
			return 1;
		}else {
			return 0;
		}
	}
	
	public void updatePw(String pw, String id) {
		dao.updatePw(pw, id);
	}
}
