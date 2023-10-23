package first_led_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import first_led_project.dto.MovieDTO;
import first_led_project.dto.ShowTimeDTO;

public class ScreenDAO {
	private static ScreenDAO instance = new ScreenDAO();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/firstprojectdb";
	
	private ScreenDAO() {
	}
	
	public static ScreenDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection(){
			try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"root","root");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return conn;
	}
	
	public void insertSreen(ShowTimeDTO dto) {
		conn = getConnection();
		String sql = "insert into showtime_tb values(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getShowtime_id());
			pstmt.setString(2, dto.getMovie_id());
			pstmt.setString(3, dto.getScreen_id());
			pstmt.setDate(4, dto.getScreen_date());
			pstmt.setTime(5, dto.getScreen_time());
			pstmt.executeQuery();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(pstmt != null)pstmt.close();
					if(conn != null) conn.close();		
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public ArrayList<ShowTimeDTO> getShowTime(MovieDTO dto) {
		conn = getConnection();
		ArrayList<ShowTimeDTO> showTimeArr = new ArrayList<ShowTimeDTO>();
		String sql = "select * from showtime_tb where movie_id='"+dto.getMovie_id()+"' ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ShowTimeDTO sDTO = new ShowTimeDTO();
				sDTO.setShowtime_id(rs.getString("showTime_id"));
				sDTO.setMovie_id(rs.getString("movie_id"));
				sDTO.setScreen_id(rs.getString("screen_id"));
				sDTO.setScreen_date(rs.getDate("screen_date"));
				sDTO.setScreen_time(rs.getTime("screen_time"));
				showTimeArr.add(sDTO);
			}
			rs.close();
			stmt.close();
			conn.close();
			return showTimeArr;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return showTimeArr;
	}
}
