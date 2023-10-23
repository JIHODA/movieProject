package first_led_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import first_led_project.dto.CommentDTO;
import first_led_project.dto.MovieDTO;
import first_led_project.dto.UserDTO;

public class CommentDAO {
	
	private static CommentDAO instance = new CommentDAO();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/firstprojectdb";
	
	private CommentDAO() {
	}
	
	public static CommentDAO getInstance() {
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
	
	public int insertLike(MovieDTO movie, UserDTO user) {
		
			if(selectLike(movie, user) > 0) {
				return 0;
			}
			
			conn = getConnection();
		try {
			String sql = "insert into movielike_tb values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovie_id());
			pstmt.setString(2, user.getMember_id());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return 1;
	}
	
	public int selectLike(MovieDTO movie, UserDTO user) {
		conn = getConnection();
		String sql = "select count(member_id) from movielike_tb where movie_id = ? and member_id = ?";
		int resultCount = 0;
		try { 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovie_id());
			pstmt.setString(2, user.getMember_id());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				resultCount = rs.getInt(1);
			}
				rs.close();
				pstmt.close();
				conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return resultCount;
	}
	
	public int allLike(MovieDTO dto) {
		conn = getConnection();
		String sql = "select count(*) from movielike_tb where movie_id = ?";
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMovie_id());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void InsertComment(CommentDTO comment) {
		conn = getConnection();
		String sql = "insert into comment_tb (member_id, contents, movie_id) values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getMember_id());
			pstmt.setString(2, comment.getContents());
			pstmt.setString(3, comment.getMovie_id());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public ArrayList<CommentDTO> selectComment(MovieDTO movie) {
		conn = getConnection();
		String sql = "select * from comment_tb where movie_id=? order by write_date desc";
		ArrayList<CommentDTO> comments = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovie_id());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setMember_id(rs.getString("member_id"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getDate("write_date"));
				comments.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return comments;
	}
	public int commentCount(CommentDTO dto) {
		conn = getConnection();
		String sql = "select count(*) from comment_tb where member_id = ? and movie_id=?";
		int commentCount = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMember_id());
			pstmt.setString(2, dto.getMovie_id());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				commentCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentCount;
	}

	public ArrayList<CommentDTO> newSelectComment(MovieDTO movie) {
		conn = getConnection();
		String sql = "SELECT c.*, m.member_profile, TIMESTAMPDIFF(minute, write_date, NOW() ) AS minuteCount, "
				+ "DATE_FORMAT(write_date, '%Y-%m-%d %H:%i') AS formatDate  "
				+ "FROM comment_tb c "
				+ "INNER JOIN member_tb m ON c.member_id = m.member_id "
				+ "WHERE movie_id = ? ORDER BY c.write_date desc";
		ArrayList<CommentDTO> comments = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovie_id());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setMember_profile(rs.getString("member_profile"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setContents(rs.getString("contents"));
				dto.setMovie_id(rs.getString("movie_id"));
				dto.setWrite_date(rs.getDate("write_date"));
				dto.setMinuteCount(rs.getInt("minuteCount"));
				comments.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return comments;
	}
	
	public void updateComment(CommentDTO comment) {
		conn = getConnection();
		String sql = "update comment_tb set contents = ? where member_id = ? and movie_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getContents());
			pstmt.setString(2, comment.getMember_id());
			pstmt.setString(3, comment.getMovie_id());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteComment(CommentDTO comment) {
		conn = getConnection();
		String sql = "delete from comment_tb where member_id = ? and movie_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getMember_id());
			pstmt.setString(2, comment.getMovie_id());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
