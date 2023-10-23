package first_led_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import first_led_project.dto.CommentDTO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	private Connection conn;
	private PreparedStatement pstmt;
	
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/firstprojectdb";
	
	private UserDAO() {
		
	}
	
	public static UserDAO getInstance() {
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
	
	
		//회원을 추가 하는 메서드
		public int insertMember(UserDTO dto) {
			int result = 0;
			conn = getConnection();
			String sql = "Insert into member_tb Values (?,?,?,?,?,?,?,?,?,?,now())";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getMember_name());
				pstmt.setString(2, dto.getMember_id());
				pstmt.setString(3, dto.getMember_pwd());
				pstmt.setString(4, dto.getMember_phone());
				pstmt.setString(5, dto.getBirth_date());
				pstmt.setString(6, dto.getPwQs());
				pstmt.setString(7, dto.getMember_pwqs());
				pstmt.setString(8, dto.getMember_profile());
				pstmt.setInt(9, 12000);
				pstmt.setString(10, "norm");
				
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
				return 1;
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				return 0;
			}
		}
		
		
		//아이디 중복체크 메서드
		public int idDuplicationCheck(String id) {
			try {
				Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				String sql = "Select * from member_tb where member_id = '"+id+"' ";
				ResultSet rs = stmt.executeQuery(sql);
				int result = 0;
				
				if(rs.next()) {
					result = 1;
				}
				
				rs.close();
				stmt.close();
				conn.close();
				
				return result;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		
		//유저가 입력한 아이디의 비밀번호 가져와서 대조
		public int pwCheck(UserDTO user) {
			Connection conn = getConnection();
			Statement stmt;
			
			try {
				stmt = conn.createStatement();
				String sql = "Select member_pwd from member_tb where member_id = '"+user.getMember_id()+"' ";
				ResultSet rs = stmt.executeQuery(sql);
				int result = 0;
				
				while(rs.next()) {
					if(rs.getString("member_pwd").equals(user.getMember_pwd())) {
						
						result = 1;
					}
				}
				rs.close();
				stmt.close();
				conn.close();
				
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		
			
			return 0;
			
		}
		
		//유저 모든 정보 가져와서 이름 번호 생일 마이페이지 내정보에 보냄
		public void selectUser(UserDTO dto) {
			conn = getConnection();
			String sql = "select * from member_tb where member_id= ?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getMember_id());
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					dto.setMember_name(rs.getString("member_name"));
					dto.setMember_phone(rs.getString("member_phone"));
					dto.setBirth_date(rs.getDate("birth_date").toString());
					dto.setMember_profile(rs.getString("member_profile"));
					dto.setTotal_point(rs.getInt("total_point"));
					dto.setReg_date(rs.getDate("reg_date").toString());
				}
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		//로그인에서 비밀번호 찾기할때 유저 비번찾기 질문과 답 
		public int pwFind1(UserDTO dto) {
			Connection conn = getConnection();
			int result = 0;
			try {
				Statement stmt = conn.createStatement();
				String sql = "select pwQs, member_pwqs from member_tb where member_id= '"+dto.getMember_id()+"' ";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					dto.setPwQs(rs.getString("pwQs"));
					dto.setMember_pwqs(rs.getString("member_pwqs"));
					result = 1;
				}
				
				rs.close();
				stmt.close();
				conn.close();
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		//새로운 비밀번호로 변경시 업데이트
		public void pwUpdate(UserDTO dto) {
			Connection conn = getConnection();
			try {
				String sql = "UPDATE member_tb SET member_pwd = ? where member_id = ?  ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getMember_pwd());
				pstmt.setString(2, dto.getMember_id());
				
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		//회원삭제
		public int userX(UserDTO dto) {
			Connection conn = getConnection();
			try {
				String sql = "DELETE FROM member_tb where member_id = '" + dto.getMember_id() + "' ";
				Statement stmt = conn.createStatement();
				int rs = stmt.executeUpdate(sql);
				
				
				stmt.close();
				conn.close();
				
				return rs;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return 0;
		}
		
		
		
		public void pwFind2(UserDTO user) {
			Connection conn = getConnection();
			try {
				Statement stmt = conn.createStatement();
				String sql = "select pwQs, member_pwqs from member_tb where member_pwd= '"+user.getMember_pwd()+"' ";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					user.setPwQs(rs.getString("pwQs"));
					user.setMember_pwqs(rs.getString("member_pwqs"));
					
				}
				rs.close();
				stmt.close();
				conn.close();
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		public void pwUpdate1(UserDTO user) {
			Connection conn = getConnection();
			try {
				String sql = "UPDATE member_tb SET member_pwd = ? where member_pwqs = ?  ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getMember_pwd());
				pstmt.setString(2, user.getMember_pwqs());
				
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		
		//프로필 변경 저장
		public void profile(UserDTO user) {
			Connection conn = getConnection();
			try {
				String sql = "UPDATE member_tb SET member_profile = ? where member_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, user.getMember_profile());
				pstmt.setString(2, user.getMember_id());
				pstmt.executeUpdate();
				
			
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		public ArrayList<SucessDTO> movieAllReservation(UserDTO user) {
			Connection conn = getConnection();
			ArrayList<SucessDTO> movieAll = new ArrayList<>();
			try {
				String sql = "SELECT s.*, m.movie_rank, st.screen_date, st.screen_time, st.screen_id " +
						"FROM sucess_tb s " +
						"INNER JOIN movie_tb m ON s.movie_id = m.movie_id " +
						"INNER JOIN showtime_tb st ON s.showtime_id = st.showtime_id " +
						"WHERE member_id = '" + user.getMember_id() + "' order by now desc";
								
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					SucessDTO dto = new SucessDTO();
					dto.setTiketnumber(rs.getString("tiketnumber"));
					dto.setMovieTitle(rs.getString("movietitle"));
					dto.setScreen(rs.getString("screen_id"));
					dto.setSeat_name(rs.getString("seat_name"));
					dto.setScreen_date(rs.getDate("screen_date"));
					dto.setScreen_time(rs.getTime("screen_time"));
					dto.setTicketing_reg(rs.getTimestamp("now"));
					dto.setMovie_rank(rs.getString("movie_rank"));
					
					movieAll.add(dto);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return movieAll;
		}
		
		//내 한줄평 
		public ArrayList<CommentDTO> allMyReview(UserDTO user) {
			Connection conn = getConnection();
			ArrayList<CommentDTO> reviewAll = new ArrayList<>();
					
				String sql = "SELECT c.*, m.movie_rank, m.movie_title "+
						"FROM comment_tb c " +
						"INNER JOIN movie_tb m ON c.movie_id = m.movie_id " +
						"WHERE member_id = '"+user.getMember_id()+"' order by write_date desc ";
					
				Statement stmt;
				try {
					stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
						
					while(rs.next()) {
						CommentDTO dto = new CommentDTO();
						dto.setContents(rs.getString("contents"));
						dto.setMovie_id(rs.getString("movie_id"));
						dto.setMovie_rank(rs.getString("movie_rank"));
						dto.setMovie_title(rs.getString("movie_title"));
						dto.setWrite_date(rs.getDate("write_date"));
						
						reviewAll.add(dto);
					}		
						rs.close();
						stmt.close();
						conn.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return reviewAll;
				}
}