package first_led_project.view.customComp;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import first_led_project.controller.PanelController;
import first_led_project.dao.PaymentDAO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;
import first_led_project.util.Util;
import first_led_project.view.Complete;

public class KakaoPayButton extends JButton implements ActionListener{
	
	private static final String ADMIN_KEY = "dfa0c0498dbe4c956fc466054f66448f";
	private static final String KAKAOPAY_URL = "https://kapi.kakao.com/v1/payment/ready";

    private static HttpServer server;
    private static ExecutorService executorService;
	private static boolean result = false;
    private static SucessDTO dto;
	
    private Thread th;
    
	public KakaoPayButton(SucessDTO dto){
		KakaoPayButton.dto = dto;
		setIcon(new Util().reSizeIcon(new ImageIcon("./img/icon/kaconL.png"), 100, 50) );
		setBackground(Color.BLACK);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					startServer();
					getAccessToken();
					while (!result) {
			            Thread.sleep(100);
			        }
					stopServer();
					
					UserDTO user = PanelController.getInstane().getUser();
					PanelController pc = PanelController.getInstane();
					PaymentDAO dao = PaymentDAO.getInstance();
					
					dao.insertTiketing(dto, user);
					dao.insertSeatInfo(pc.getPayment().getSeatArr());
					dao.updatePoint(user);
					dto.setPayingWhat("kakao");
					dao.updatePay(dto);
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "결제가 완료되었습니다.");
					result = false;
					Complete complete = pc.getComplete();
					pc.getMainPane().add(complete);
					pc.updateComplete(dto);
					complete.setVisible(true);
					pc.getPayment().setVisible(false);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		th.start();
	}
	
	
	private static void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        System.out.println("서버시작");
        server.createContext("/paycallback", new PaymentCallbackHandler());
        executorService = Executors.newSingleThreadExecutor();
        server.setExecutor(executorService);
        server.start();
    }

    private static void stopServer() {
        server.stop(0);
        System.out.println("서버종료");
        executorService.shutdown();
    }
    
    static class PaymentCallbackHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
        	String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("GET")) {
                String query = exchange.getRequestURI().getQuery();
                result = true;
                String response = "Sucess, Return to LED CINEMA";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
	

	private void getAccessToken() {
        try {
        	URL url = new URL(KAKAOPAY_URL);
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        	conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setDoOutput(true);
            
            String postData = "cid=TC0ONETIME";
            postData += "&partner_order_id="+dto.getTiketnumber(); // 예매번호
            postData += "&partner_user_id="+dto.getMember_id();	// 유저아이디
            postData += "&item_name="+dto.getMovieTitle();	// 영화제목
            postData += "&quantity="+dto.getPerson();	// 인원
            postData += "&total_amount="+dto.getPrice();	//금액
            postData += "&tax_free_amount=0";	//세금
            postData += "&approval_url=http://localhost:8080/paycallback";
            postData += "&cancel_url=http://localhost:8080/oauth2callback";
            postData += "&fail_url=http://localhost:8080/oauth2callback";
        	
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes());
            outputStream.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            String approvalUrl = parseApprovalUrl(response.toString());
            if (approvalUrl != null) {
                openBrowser(approvalUrl);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    private static String parseApprovalUrl(String response) {
    	JSONObject response2 = new JSONObject(response);
    	String redirect_url = response2.getString("next_redirect_pc_url");
        return redirect_url;
    }
    
    private static void openBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
