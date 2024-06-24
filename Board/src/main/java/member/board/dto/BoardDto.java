package member.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDto {

	private int no;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private int readcount;
}
