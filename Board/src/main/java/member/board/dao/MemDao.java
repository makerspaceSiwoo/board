package member.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import member.board.dto.MemDto;

@Mapper
public interface MemDao {
	String idCheck(String id);
	int insertMem(MemDto dto);
	MemDto login(MemDto dto);
	int updateMem(MemDto dto);
	int deleteMem(String id);
	
	@Select("select count(*) from mem where id=#{id} and name=#{name}")
	int id_name_ck(@Param("id") String id, @Param("name") String name);
	
	@Update("update mem set password=#{password} where id=#{id}")
	int updatePw(@Param("password")String password, @Param("id") String id);
	
	@Update("update mem set password=#{password} where id=#{id} and name=#{name}")
	int updatePassword(@Param("id")String id, @Param("name")String name,@Param("password") String password);
}
