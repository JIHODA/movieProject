package first_led_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import first_led_project.dto.SeatDTO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;

public class PaymentDAO {

	Statement stmt;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	int omg;

	private static PaymentDAO instance = new PaymentDAO();

	private String url = "jdbc:mariadb://localhost:3306/firstprojectdb";
	private String user = "root";
	private String pass = "root";

	private PaymentDAO() {
	}

	public static PaymentDAO getInstance() {
		return instance;
	}

	private Connection getConnection() {

		conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("db연결");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	private void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public int insertTiketing(SucessDTO sdto, UserDTO udto) throws Exception {
		int result = 0;
		conn = getConnection();

		String sql = "INSERT INTO sucess_tb "
				+ "(tiketnumber, member_id, movie_id, showtime_id, username, movietitle, cinema, seat_name, person, "
				+ "paywhat, member_point, using_point, price) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, sdto.getTiketnumber());
		pstmt.setString(2, sdto.getMember_id());
		pstmt.setString(3, sdto.getMovie_id());
		pstmt.setString(4, sdto.getShowtime_id());
		pstmt.setString(5, sdto.getUserName());
		pstmt.setString(6, sdto.getMovieTitle());
		pstmt.setString(7, sdto.getCinema());
		pstmt.setString(8, sdto.getSeat_name());
		pstmt.setString(9, sdto.getPerson());
		pstmt.setString(10, sdto.getPayingWhat());
		pstmt.setInt(11, (int)sdto.getMember_point());
		pstmt.setInt(12, udto.getUsing_point());
		pstmt.setInt(13, sdto.getPrice());

		result = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return result;

	}

	public void insertSeatInfo(ArrayList<SeatDTO> dtoList) throws SQLException {

		conn = getConnection();

		String sql = "INSERT INTO seats_tb (showtime_id, seat_name, tiketnumber) VALUES (?, ?, ?)";

		pstmt = conn.prepareStatement(sql);

		for (SeatDTO dto : dtoList) {
			pstmt.setString(1, dto.getShowtime_id());
			pstmt.setString(2, dto.getSeat_name());
			pstmt.setString(3, dto.getTiketnumber());
			pstmt.executeUpdate();
		}

		pstmt.close();
		conn.close();
	}

	public int cancelTiket(SucessDTO dto) {
		conn = getConnection();

		try {
			String sql = "DELETE FROM sucess_tb " + "WHERE tiketnumber = '" + dto.getTiketnumber() + "'";
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();

			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}

	public void updatePoint(UserDTO udto) {
		
		conn = getConnection();
		
		String sql = "UPDATE member_tb SET total_point = ? WHERE member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, udto.getTotal_point());
			pstmt.setString(2, udto.getMember_id());
			pstmt.executeUpdate();
			omg = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePay(SucessDTO sdto) {
		
		conn = getConnection();
		
		String sql = "UPDATE sucess_tb SET paywhat = ? WHERE tiketnumber = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sdto.getPayingWhat());
			pstmt.setString(2, sdto.getTiketnumber());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void selectPoint(UserDTO udto) {

		conn = getConnection();

		String sql = "SELECT p.mamber_point, p.using_point "
				+ "FROM point_tb p, sucess_tb s "
				+ "WHERE (Select MAX(NOW) last FROM sucess_tb) = p.now AND p.member_id = ' ? '";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, udto.getMember_id());
			rs = pstmt.executeQuery();
			System.out.println("---->" + udto.getTotal_point());
			System.out.println("---->>");

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int selectSucess(SucessDTO sucessDTO) {
		conn = getConnection();
		String sql = "select count(*) from sucess_tb where member_id=? and movie_id = ?";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sucessDTO.getMember_id());
			pstmt.setString(2, sucessDTO.getMovie_id());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void pointReturn(SucessDTO sucess, UserDTO user) {
		
		conn = getConnection();
		try {
			String sql = "select * from sucess_tb where tiketnumber = '"+sucess.getTiketnumber()+"' ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				sucess.setPayingWhat(rs.getString("paywhat"));
				sucess.setMember_point(rs.getDouble("member_point"));
				user.setUsing_point(rs.getInt("using_point"));
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
