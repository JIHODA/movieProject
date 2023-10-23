package first_led_project.dto;

import java.net.URL;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class MovieDTO {
	private String movie_id;
	private int rank;
	private double rate;
	private String title;
	private String director;
	private String actor;
	private String otherInfo;
	private String poster;
	private String genre;
	private String openingDate;
	private String description;
}
