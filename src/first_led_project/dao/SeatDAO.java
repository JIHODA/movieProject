package first_led_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import first_led_project.dto.SeatDTO;
import first_led_project.dto.ShowTimeDTO;

public class SeatDAO {
	private static SeatDAO instance = new SeatDAO();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/firstprojectdb";
	
	private SeatDAO() {
	}
	
	public static SeatDAO getInstance() {
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
	
	public void insertSeat(SeatDTO dto) {
		conn = getConnection();
		String sql = "insert into seats_tb (showtime_id, seat_name) values(?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getShowtime_id());
			pstmt.setString(2, dto.getSeat_name());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public ArrayList<String> selectSeat(ShowTimeDTO dto) {
		conn = getConnection();
		ArrayList<String> arr = new ArrayList();
		String sql = "select seat_name from seats_tb where showtime_id = ?";
		System.out.println(dto.getShowtime_id());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getShowtime_id());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arr.add(rs.getString("seat_name"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			return arr;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arr;
		
	}
}
