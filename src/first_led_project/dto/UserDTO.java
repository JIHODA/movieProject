package first_led_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
	private String member_name;
	private String member_id;
	private String member_pwd;
	private String member_phone;
	private String birth_date;
	private String member_pwqs;
	private String pwQs;
	private String member_profile = "./img/profileicon/profileIcon.png";
	private String reg_date;
	
	private int price;
	private int user_point = 12000;
	private int total_point;
	private int using_point;
}
