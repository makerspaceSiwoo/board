package member.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import member.board.dto.BoardDto;    

@Mapper
public interface BoardDao {
	
	@Insert("insert into board(id,title,content,regdate) values(#{id},#{title},#{content},now())")
	int insert(BoardDto dto);
	
	@Select("select * from board order by regdate desc limit #{srart}, #{count}")
	List<BoardDto> boardList(Map<String, Object> m);
	
	@Select("select count(*) from board")
	int count();//전체 글 갯수
	
	@Select("select * from board where no=#{no}")
	BoardDto boardOne(int no);
	
	@Update("update board set title=#{title}, content=#{content} where no=#{no}")
	int updateBoard(BoardDto dto);
	
	@Delete("delete from board where no=#{no}")
	int deleteBoard(int no);

}
