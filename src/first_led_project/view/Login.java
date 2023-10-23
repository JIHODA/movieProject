package first_led_project.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.UserDAO;
import first_led_project.dto.UserDTO;
import first_led_project.util.Util;
import first_led_project.view.customComp.GoogleLoginButton;
import first_led_project.view.customComp.KakaoLoginButton;
import first_led_project.view.customComp.NaverLoginButton;
import first_led_project.view.customComp.PrevBtnCustom;

public class Login extends JPanel {
	private JTextField idField;
	private JPasswordField passwordField;
	String pw_id;
	UserDTO userDto = new UserDTO();
	UserDAO userDao = UserDAO.getInstance();
	Util util = new Util();
	
	public Login() {
		
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setSize(1384,861);
		setLayout(null);
		
		//아이디 라벨
		JLabel lblId = new JLabel("ID    :");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblId.setBounds(530, 368, 56, 31);
		add(lblId);
		
		//비밀번호 라벨
		JLabel lblPw = new JLabel("PW    :");
		lblPw.setHorizontalAlignment(SwingConstants.LEFT);
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblPw.setBounds(518, 418, 68, 31);
		add(lblPw);
		
		//아이디 입력창
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(616, 373, 240, 21);
		add(idField);
		
		//비밀번호 입력창
		passwordField = new JPasswordField();
		passwordField.setBounds(616, 423, 240, 21);
		add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				    	loginbut();
				   }
			}
		});
		
		//비밀번호 입력창 길이 제한
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=16) ke.consume();
			}
		});
		
		//로고 라벨 (로고 이미지 들어갈 부분)
		JLabel logo = new JLabel("");
		logo.setIcon(util.reSizeIcon(new ImageIcon("./img/icon/logo2.png"), 394, 217) );
		logo.setForeground(Color.WHITE);
		logo.setBounds(521, 114, 394, 217);
		add(logo);
		

		//회원가입 라벨
		JLabel join = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		join.setForeground(Color.GRAY);
		join.putClientProperty("id", "loginJoin");
		join.addMouseListener(new BtnController());
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				join.setForeground(Color.GRAY);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				join.setForeground(Color.WHITE);
			}
		});
		join.setBounds(660, 464, 56, 15);
		add(join);
		
		//비밀번호 찾기 라벨
		JLabel pwFind = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		pwFind.setForeground(Color.GRAY);
		pwFind.setBounds(742, 464, 77, 15);
		add(pwFind);
		pwFind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pwFind.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pwFind.setForeground(Color.GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			String pw_id = JOptionPane.showInputDialog("ID를 입력하세요.");
			
			userDto.setMember_id(pw_id);
			if(pw_id.equals("")) {
				JOptionPane.showMessageDialog(pwFind, "ID를 입력해주세요." , pw_id, JOptionPane.ERROR_MESSAGE);
				return;
			}else if(UserDAO.getInstance().pwFind1(userDto) == 1) {
				if(pw_id.equals(userDto.getMember_id())) {
					String pwQs1 = JOptionPane.showInputDialog(userDto.getPwQs());
					
						if(pwQs1.equals(userDto.getMember_pwqs())) {
							String newPwd = JOptionPane.showInputDialog("변경할 비밀번호를 입력해주세요.");
							userDto.setMember_pwd(newPwd);
							UserDAO.getInstance().pwUpdate(userDto);
							JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "비밀번호 변경이 완료되었습니다!");
						}
				}
			}else{
				JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "ID를 정확히 입력해주세요." , "ERR", JOptionPane.ERROR_MESSAGE);
			}
			
			}
		});
		
		
		//자체회원 로그인라벨 (이미지 버튼으로 들어갈 곳)
		JLabel login = new JLabel("Login");
		login.setForeground(Color.WHITE);
		login.setBounds(646, 550, 150, 50);
		add(login);
		login.setIcon(new ImageIcon("./img/icon/bigLogin.png"));
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginbut();
			}
		});
		
		
		//구글 버튼 (이미지 버튼으로 들어갈 곳)
		JButton googleLogin = new GoogleLoginButton();
		googleLogin.setBackground(Color.WHITE);
		googleLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		googleLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		googleLogin.setBorderPainted(false);
		googleLogin.setBorder(null);
		googleLogin.setBounds(720, 638, 50, 50);
		add(googleLogin);
		
		JButton backBtn = new PrevBtnCustom();
		backBtn.setLocation(12, 10);
		backBtn.putClientProperty("id", "loginBack");
		backBtn.addActionListener(new BtnController());
		add(backBtn);
		
		JButton btnNewButton = new NaverLoginButton();
		btnNewButton.setBorder(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(795, 638, 50, 50);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new KakaoLoginButton();
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(616, 638, 80, 50);
		add(btnNewButton_1);
	}
	
	
	public void loginbut() {
		userDto.setMember_id(idField.getText());
		userDto.setMember_pwd(passwordField.getText());
		if(UserDAO.getInstance().pwCheck(userDto) == 1) {
			JOptionPane.showMessageDialog(this, "로그인 성공!");
			PanelController.getInstane().setUser(userDto);
			PanelController.getInstane().updateCinemaMain(userDto);
			clearField();
		}else {
			JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 확인해주세요.");
			idField.requestFocusInWindow();
		}
	}
	
	
	public void clearField() {
		idField.setText("");
		passwordField.setText("");
	}
}
