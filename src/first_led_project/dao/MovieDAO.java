package first_led_project.dao;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import first_led_project.dto.MovieDTO;
import first_led_project.dto.ShowTimeDTO;
import first_led_project.util.StillcutCraw.StillcutDTO;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driver = "org.mariadb.jdbc.Driver";
	private String url = "jdbc:mariadb://localhost:3306/firstprojectdb";
	
	private MovieDAO() {
	}
	
	public static MovieDAO getInstance() {
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
	
	public void movieInsert(MovieDTO dto) {
		conn = getConnection();
		String sql = "insert into movie_tb values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMovie_id());
			pstmt.setInt(2, dto.getRank());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getDirector());
			pstmt.setString(5, dto.getActor() );
			pstmt.setString(6, dto.getOtherInfo() );
			pstmt.setString(7, dto.getPoster() );
			pstmt.setString(8, dto.getGenre() );
			pstmt.setString(9, dto.getOpeningDate() );
			pstmt.setString(10, dto.getDescription() );
			pstmt.setDouble(11, dto.getRate() );
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
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getData(String data) {
		conn = getConnection();
		ArrayList<String> arrL = new ArrayList<>();
		String sql = "select "+data+" from movie_tb order by movie_rank asc";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				arrL.add(rs.getString(data));
			}
			rs.close();
			stmt.close();
			conn.close();
		return arrL;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return arrL;
	}
	
	public void movieInfo(MovieDTO dto) {
		conn = getConnection();
		String sql = "select title, director, actor, runningTime, genre, openingDate, description from movie_tb where movie_id='"+dto.getMovie_id()+"' order by movie_rank asc";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dto.setTitle(rs.getString("title")); 
				dto.setDirector((rs.getString("director")));  
				dto.setActor(rs.getString("actor")); 
				dto.setOtherInfo(rs.getString("runningTime")); 
				dto.setGenre(rs.getString("genre")); 
				dto.setOpeningDate(rs.getString("openingDate")); 
				dto.setDescription(rs.getString("description"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public Map<String, MovieDTO> movieInfoAll() {
		conn = getConnection();
		Map<String, MovieDTO> movieMap= new HashMap<>();
		String sql = "select * from movie_tb order by movie_rank asc";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MovieDTO dto = new MovieDTO();
				dto.setMovie_id(rs.getString("movie_id"));
				dto.setRank(rs.getInt("movie_rank"));
				dto.setTitle(rs.getString("movie_title")); 
				dto.setDirector((rs.getString("movie_director")));  
				dto.setActor(rs.getString("movie_actor")); 
				dto.setPoster(URLEncoder.encode(rs.getString("movie_poster"), "UTF-8"));
				dto.setOtherInfo(rs.getString("other_data")); 
				dto.setGenre(rs.getString("movie_genre")); 
				dto.setOpeningDate(rs.getString("opening_date")); 
				dto.setDescription(rs.getString("description"));
				dto.setRate(rs.getDouble("ticket_rate"));
				movieMap.put(dto.getMovie_id(), dto);
			}
			rs.close();
			stmt.close();
			conn.close();
			return movieMap;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return movieMap;
	}
	
	public String[] movieName(ShowTimeDTO stDTO) {
		conn = getConnection();
		String[] title = new String[3];
		try {
			stmt = conn.createStatement();
			String sql = "select movie_rank,movie_id,movie_title from movie_tb where movie_id = '"+stDTO.getMovie_id() + "' ";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				title[0] = rs.getString("movie_id");
				title[1] =  rs.getString("movie_title");
				title[2] = rs.getString("movie_rank");
			}
			rs.close();
			stmt.close();
			conn.close();
			return title;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return title;
	}
	
	public void insertStillcut(StillcutDTO dto) {
		conn = getConnection();
		String sql = "insert into stillcut_tb values(?, ?)";
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getStillcut_url());
		pstmt.setString(2, dto.getMovie_id());
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		}catch (Exception e) {
		}
	}
	public ArrayList<StillcutDTO> selectStillcut(MovieDTO dto){
		conn = getConnection();
		String sql = "select stillcut_url from stillcut_tb where movie_id = ?";
		ArrayList<StillcutDTO> arr = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMovie_id());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StillcutDTO stillcut = new StillcutDTO();
				stillcut.setStillcut_url(rs.getString("Stillcut_url"));
				arr.add(stillcut);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	public ArrayList<String> getStillcut(String movieId){
		conn = getConnection();
		String sql = "select stillcut_url from stillcut_tb where movie_id = ?";
		ArrayList<String> arr = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arr.add(rs.getString("Stillcut_url"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	public int stillcutCount(MovieDTO dto) {
		conn = getConnection();
		String sql = "select count(*) from stillcut_tb where movie_id=?";
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMovie_id());
			rs = pstmt.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
