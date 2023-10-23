package first_led_project.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import first_led_project.dao.MovieDAO;
import first_led_project.dto.MovieDTO;

public class CrawCGV {

    public static void main(String[] args) throws Exception {
    	int i = 1;
    	MovieDAO dao = MovieDAO.getInstance();
        String url = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
        Document doc = Jsoup.connect(url).get();
        Elements movieList = doc.select(".sect-movie-chart ol li");
        
        for (Element movie : movieList) {
            String title = movie.select(".title").text();
            String reservationRate = movie.select(".percent span").text();
            String detailLink = movie.select(".box-image a").attr("href");
            
            // 절대 URL로 변환
            
            detailLink = url.substring(0,url.indexOf('m')-1)+detailLink;
            System.out.println(detailLink);
            MovieDTO dto = new MovieDTO();
            // 상세 페이지에서 정보 가져오기
            Document detailDoc = Jsoup.connect(detailLink).get();
            dto.setMovie_id("movie"+i);
            dto.setTitle(title);
            dto.setRate(Double.parseDouble(reservationRate.substring(0,reservationRate.indexOf('%'))));
            dto.setDirector(detailDoc.select(".spec dd").get(0).text());
            dto.setOtherInfo(detailDoc.select(".spec dd").get(4).text());
            dto.setActor(detailDoc.select(".spec dd").get(2).text());
            dto.setGenre(detailDoc.select(".spec dt").get(2).text().substring(4));
            dto.setOpeningDate(detailDoc.select(".spec dd").get(5).text());
            dto.setDescription(detailDoc.select(".sect-story-movie").text());
            dto.setPoster(detailDoc.select(".thumb-image img").attr("src"));
            String rank = movie.select(".rank").text();
            rank = rank.substring(rank.indexOf('.')+1);
            dto.setRank(Integer.parseInt(rank));
            
            dao.movieInsert(dto);
            i++;
        }
    }
}
