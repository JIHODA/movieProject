package first_led_project.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import first_led_project.dao.MovieDAO;
import lombok.Data;

public class StillcutCraw {

//	CGV 스틸컷 크롤링 1~19위
	public static void main(String[] args) throws IOException {
		String cgv_url = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
		int i = 1;
//		String url = cgv_url + "default.aspx";
		Document doc = Jsoup.connect(cgv_url).get();
		Elements movieList = doc.select("div.sect-movie-chart ol > li");
		cgv_url = cgv_url.substring(0,cgv_url.indexOf('?'));
		for (Element movie : movieList) {
			String movieLink = movie.select(".box-image a").attr("href");
			movieLink = movieLink.substring(8);
			
			String movieTitle = movie.select("strong.title").text();
			String movieUrl = cgv_url + movieLink;
			System.out.println(movieUrl);
			
			Document movieDoc = Jsoup.connect(movieUrl).get();
			Elements stillCuts = movieDoc.select("div.sect-stillcut img");
			
			for (Element stillCut : stillCuts) {
				StillcutDTO dto = new StillcutDTO();
				dto.setStillcut_url(stillCut.attr("data-src"));
				dto.setMovie_id("movie"+i);
				MovieDAO.getInstance().insertStillcut(dto);
			}
			i++;
		}
	}
	@Data
	public
	static class StillcutDTO {
		private String stillcut_url;
		private String movie_id;
	}
}
