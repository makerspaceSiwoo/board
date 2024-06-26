package member.board.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoardDto {

	private int no;
	@NotNull(message="id is null.")
	@NotEmpty(message="id is empty.")
	private String id;
	@NotNull(message="title is null.")
	@NotEmpty(message="title is empty.")
	private String title;
	private String content;
	private Date regdate; // 작성일
	private int readcount;
}
