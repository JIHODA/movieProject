package first_led_project.controller;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import first_led_project.dao.MovieDAO;
import first_led_project.dao.UserDAO;
import first_led_project.dto.MovieDTO;
import first_led_project.dto.ShowTimeDTO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;
import first_led_project.view.CinemaMainPanel;
import first_led_project.view.Complete;
import first_led_project.view.Join;
import first_led_project.view.Login;
import first_led_project.view.MyPage;
import first_led_project.view.Payment;
import first_led_project.view.SeatPanel;
import first_led_project.view.Ticketing;
import first_led_project.view.mainSubPanel.MovieInfo;

public class PanelController {
	// 영화, 유저, 예매현황에 맞게 패널의 컨텐츠를 업데이트 해주는 클래스
	// DB에서 정보를 받아와서 해당패널을 업데이트 해준다
	private static final Map<String, MovieDTO> movies;
	private static final Login login;
	private static final Join join;
	private static final MyPage myPage;
	private static final CinemaMainPanel cinemaMainPanel;
	private static final MovieInfo movieInfo;
	private static final Ticketing ticketing;
	private static final SeatPanel seatPanel;
	private static final Payment payment;
	private static final Complete complete;
	private static PanelController instance = new PanelController();
	
	private UserDTO user = null;
	private JPanel mainPane;
	private JFrame jframe;
	
	static{
		movies = MovieDAO.getInstance().movieInfoAll();
		login = new Login();
		join  = new Join();
		cinemaMainPanel = new CinemaMainPanel();
		myPage = new MyPage();
		movieInfo = new MovieInfo();
		ticketing = new Ticketing();
		seatPanel = new SeatPanel();
		payment = new Payment();
		complete = new Complete();
	}
	
	private PanelController(){
	}
	public static PanelController getInstane() {
		return instance;
	}
	
	// DB의 모든 영화정보 리턴메서드
	public Map<String, MovieDTO> getMovies() {
		return movies;
	}
	
	// mainContentPane 가져오기
	public void setMainPane(JPanel mainPane) {
		this.mainPane =  mainPane;
	}
	public void setJFrame(JFrame jframe) {
		this.jframe = jframe;
	}
	
	// Panel Getter
	public Login getLogin() {
		return login;
	}
	public Join getJoin() {
		return join;
	}
	public JPanel getMainPane() {
		return mainPane;
	}
	public CinemaMainPanel getCmPanel() {
		return cinemaMainPanel;
	}
	public MovieInfo getMovieInfo() {
		return movieInfo;
	}
	public MyPage getMyPage() {
		return myPage;
	}
	public Ticketing getTicketing() {
		return ticketing;
	}
	public SeatPanel getSeatPanel() {
		return seatPanel;
	}
	public Payment getPayment() {
		return payment;
	}
	public Complete getComplete() {
		return complete;
	}
	//frame gettet
	public JFrame getJFrame() {
		return jframe;
	}
	
	//MovieInfo 해당영화로 업데이트 메서드 
	public void updateMovieInfo(MovieDTO dto) {
		movieInfo.update(dto);
		ticketing.ticketingUpdate(dto);
	}
	
	public void updateSeat(ShowTimeDTO dto) {
		int number = ticketing.selectNumber();
		seatPanel.updateSeat(dto, number);
	}
	
	public void updateCinemaMain(UserDTO user) {
		login.setVisible(false);
		cinemaMainPanel.LoginUser();
		cinemaMainPanel.setVisible(true);
	}
	
	public void setUser(UserDTO user) {
		this.user = user;
		if(user != null) {
		UserDAO.getInstance().selectUser(user);
		}
	}
	
	public UserDTO getUser() {
		return user;
	}
	
	public void updatePayment() {
		
	}
	
	public void updateComplete(SucessDTO sdto) {
		complete.updateComplet(sdto);
	}
}
