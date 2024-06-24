package member.board.dao;

import org.apache.ibatis.annotations.Mapper;

import member.board.dto.MemDto;

@Mapper
public interface MemDao {
	String idCheck(String id);
	int insertMem(MemDto dto);
	MemDto login(MemDto dto);
	int updateMem(MemDto dto);
	int deleteMem(String id);
}
