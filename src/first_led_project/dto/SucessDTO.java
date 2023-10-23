package first_led_project.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class SucessDTO {

	private String tiketnumber;//예매번호o
	private String showtime_id;//상영관 날짜 시간o 
	private String movie_id;//영화코드o
	private String movie_rank;//영화랭크o
	private Date screen_date;//상영날짜o
	private Time screen_time;//상영시간o
	private String seat_name;//좌석
	private String member_id;//유저아이디o
	private String userName;//유저이름o
	private String movieTitle;//영화제목o
	private String cinema;//극장o
	private String person;//인원o
	private String screen;//상영관o
	private int price;//금액o
	private double member_point = 0;//포인트o
	private String payingWhat;
	private Timestamp ticketing_reg;
}
