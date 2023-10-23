package first_led_project.util;

import first_led_project.dao.ScreenDAO;
import first_led_project.dto.ShowTimeDTO;

public class ScreenTest {


	public static void main(String[] args) {
		String[] sId = {"0930","1210","1450","1700","1930"};
		String[] movie = {"movie16","movie17","movie18","movie19","movie1"};
		String[] time = {"09:30","12:10","14:50","17:00","19:30"};
		
		ScreenDAO dao = ScreenDAO.getInstance();
		
		for(int i = 0; i<5; i++) {
			ShowTimeDTO dto = new ShowTimeDTO();
			dto.setShowtime_id("s40807"+sId[i]);
			dto.setMovie_id(movie[i]);
			dto.setScreen_id("4관");
			dto.setScreen_date( "23-08-07");
			dto.setScreen_time(time[i]);
			dao.insertSreen(dto);
		}
	}
	// 19개 영화 하루에 한 상영관에 4편영화  
}
