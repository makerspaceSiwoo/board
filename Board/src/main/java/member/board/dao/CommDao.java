package member.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import member.board.dto.CommDto;

@Mapper
public interface CommDao {
	
	@Select("select * from comm where no = #{no}")
	List<CommDto> selectComm(int no);
	
	@Insert("insert into comm (id,content,regdate,no) values(#{id},#{content},now(),#{no})")
	int insertComm(CommDto dto);
	
	@Delete("delete from comm where cno = #{cno}")
	int deleteComm(int cno);

}
