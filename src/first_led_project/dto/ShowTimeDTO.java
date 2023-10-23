package first_led_project.dto;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowTimeDTO {
	
	private String showtime_id;
	private String movie_id;
	private String screen_id;
	private Date screen_date;
	private Time screen_time;
	
	public void setScreen_date(String date) {
		try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
	        java.util.Date parsedDate = dateFormat.parse(date);
	        this.screen_date = new java.sql.Date(parsedDate.getTime());
	    } catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	public void setScreen_time(String time) {
		try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
	        java.util.Date parsedDate = dateFormat.parse(time);
	        this.screen_time = new java.sql.Time(parsedDate.getTime());
	    } catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    }
	}
	public void setScreen_date(Date date) {
		this.screen_date = date;
	}
	public void setScreen_time(Time time) {
		this.screen_time = time;
	}
	
	
	
}
