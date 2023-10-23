package first_led_project.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class CommentDTO {
	private String member_id;
	private String contents;
	private String movie_id;
	private String movie_rank;
	private String movie_title;
	private Date write_date;
	private int minuteCount;
	
	private String member_profile;
}
