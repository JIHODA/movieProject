package first_led_project.view.customComp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import first_led_project.controller.PanelController;
import first_led_project.dto.UserDTO;
import first_led_project.util.Util;



public class GoogleLoginButton extends JButton implements ActionListener{
	private static final String CLIENT_ID = "686246348461-smd69ifcrrhtcpjr2hevutrv75asqc32.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/oauth2callback";
    private static final String AUTH_URL_BASE = "https://accounts.google.com/o/oauth2/auth";
    private static final String TOKEN_URL_BASE = "https://oauth2.googleapis.com/token";
    private static final String SCOPE = "profile%20email";
	
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/firstprojectdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    Util util = new Util();
    
    private static String authCode;
    private static HttpServer server;
    private static ExecutorService executorService;
	
	public GoogleLoginButton() {
		setIcon(util.reSizeIcon(new ImageIcon("./img/icon/googleLoginIcon.png"),50, 50));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			startServer();
			openAuthUrl();
	        while (authCode == null) {
	            Thread.sleep(100);
	        }
	        stopServer();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (authCode == null) {
		    JOptionPane.showMessageDialog(this, "인가코드 에러");
		    return;
		}

        String accessToken = getAccessToken(authCode);
        if (accessToken == null) {
            JOptionPane.showMessageDialog(this, "엑세스토큰 에러");
            authCode = null;
            return;
        }

        JSONObject userInfo = getUserInfo(accessToken);
        if (userInfo == null) {
            JOptionPane.showMessageDialog(this, "유저 정보 에러");
            authCode = null;
            return;
        }

        if (selectUserInfo(userInfo)) {
        	JOptionPane.showMessageDialog(getComponentPopupMenu(), "로그인성공");
        	UserDTO user = new UserDTO();
        	user.setMember_id(userInfo.getString("email"));
        	user.setMember_name(userInfo.getString("name"));
        	PanelController.getInstane().setUser(user);
			PanelController.getInstane().updateCinemaMain(user);
			authCode = null;
        	return;
        }
        	
        UserDTO user = new UserDTO();
    	user.setMember_id(userInfo.getString("email"));
    	user.setMember_name(userInfo.getString("name"));
    	JOptionPane.showMessageDialog(getComponentPopupMenu(), "첫로그인입니다. 추가정보를 입력해주세요.");
    	user.setBirth_date(JOptionPane.showInputDialog(getComponentPopupMenu(), "생년월일을 입력해주세요 (ex. 900101)"));
    	user.setMember_phone(JOptionPane.showInputDialog(getComponentPopupMenu(), "휴대폰번호를 입력해주세요 (ex. 01012341234)"));
        
        // 사용자 정보 데이터베이스에 저장
        if (!saveUserInfo(user)) {
            JOptionPane.showMessageDialog(this, "DB insert에러");
            authCode = null;
            return;
        }

        // 로그인 성공
    	PanelController.getInstane().setUser(user);
		PanelController.getInstane().updateCinemaMain(user);
		JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "로그인성공");
		authCode = null;
    }
		
	private static void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        System.out.println("서버시작");
        server.createContext("/oauth2callback", new CallbackHandler());
        executorService = Executors.newSingleThreadExecutor();
        server.setExecutor(executorService);
        server.start();
    }

    private static void stopServer() {
        server.stop(0);
        System.out.println("서버종료");
        executorService.shutdown();
    }

    private static void openAuthUrl() throws IOException {
        String authUrl = AUTH_URL_BASE + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&scope=" + SCOPE;
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + authUrl);
        System.out.println("구글 로그인 진행중...");
    }

    static class CallbackHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("GET")) {
                String query = exchange.getRequestURI().getQuery();
                authCode = extractAuthCode(query);
                String response = "Login Sucess, Return to LED CINEMA";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
    
    private static String extractAuthCode(String query) {
        String[] queryParams = query.split("&");
        for (String param : queryParams) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("code")) {
                return keyValue[1];
            }
        }
        return null;
    }


    private String getAccessToken(String authCode) {
        try {
            URL url = new URL(TOKEN_URL_BASE);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "code=" + authCode + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri=" + REDIRECT_URI + "&grant_type=authorization_code";
            byte[] postData = params.getBytes("UTF-8");
            int postDataLength = postData.length;
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            JSONObject response = readJsonFromResponse(conn);
            String accessToken = response.getString("access_token");
            return accessToken;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private JSONObject getUserInfo(String accessToken) {
        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JSONObject response = readJsonFromResponse(conn);
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    

    private boolean saveUserInfo(UserDTO user) {
        try {

            Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "INSERT INTO member_tb (member_name, member_id, birth_date , member_profile, member_phone, login_type) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getMember_name());
            stmt.setString(2, user.getMember_id());
            stmt.setString(3, user.getBirth_date());
            stmt.setString(4, user.getMember_profile());
            stmt.setString(5, user.getMember_phone());
            stmt.setString(6, "google");
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            conn.close();

            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    //사용자정보 조회
    private boolean selectUserInfo(JSONObject userInfo) {
    	try {

    		Class.forName("org.mariadb.jdbc.Driver");
    		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    		int result = 0;

    		String email = userInfo.getString("email");
//            String encryptedEmail = email;
//            String encryptedName = name;
    		
    		String query = "SELECT COUNT(*) FROM member_tb WHERE login_type = 'google' and member_id = ?";
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, email);
    		ResultSet rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			result = rs.getInt(1);
    		}
    		rs.close();
    		pstmt.close();
    		conn.close();
    		
    		return result > 0;
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		return false;
    	}
    }
    
    private JSONObject readJsonFromResponse(HttpsURLConnection conn) throws IOException, JSONException {
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        return new JSONObject(response.toString());
    }

    //암호화
//    private String encrypt(String str) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        byte[] hash = md.digest(str.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(hash);
//    }

}
