package first_led_project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SeatDTO {
	private int seat_id;
	private String showtime_id;
	private String seat_name;
	private String tiketnumber;
}
