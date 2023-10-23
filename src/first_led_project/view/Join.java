package first_led_project.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import first_led_project.view.customComp.PrevBtnCustom;
import java.awt.Cursor;

public class Join extends JPanel {
	
	UserDTO userDto = new UserDTO();
	UserDAO userDao = UserDAO.getInstance();
	
	private JTextField nameField;
	private JTextField idField;
	private JTextField phoneField;
	private JTextField birthField;
	private JPasswordField passwordField2;
	private JPasswordField passwordField;
	private JComboBox pwQs;
	private JTextField pwQsfil;
	private JLabel gapNo; 
	private JLabel lblimg;
	private JLabel lblCheck;
	
	public Join() {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setSize(1384,861);
		setLayout(null);
		
		//네임 라벨
		JLabel lblName = new JLabel("NAME    :");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblName.setBounds(538, 282, 97, 31);
		add(lblName);
		
		//아이디 라벨
		JLabel lblId = new JLabel("ID    :");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblId.setBounds(579, 336, 56, 31);
		add(lblId);
		
		//비밀번호 라벨
		JLabel lblPw = new JLabel("PW    :");
		lblPw.setHorizontalAlignment(SwingConstants.LEFT);
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblPw.setBounds(567, 386, 68, 31);
		add(lblPw);
		
		//비밀번호 라벨
		JLabel lblPw2 = new JLabel("PW    :");
		lblPw2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPw2.setForeground(Color.WHITE);
		lblPw2.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblPw2.setBounds(567, 441, 68, 31);
		add(lblPw2);
		
		//폰번호 라벨
		JLabel lblPhone = new JLabel("PHONE    :");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblPhone.setBounds(529, 498, 106, 31);
		add(lblPhone);
		
		//생년월일 라벨
		JLabel lblbirthDate = new JLabel("Birth date    :");
		lblbirthDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblbirthDate.setForeground(Color.WHITE);
		lblbirthDate.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblbirthDate.setBounds(501, 549, 134, 31);
		add(lblbirthDate);
		
		//비밀번호 찾기 라벨
		JLabel lblpwQs = new JLabel("Password Question    :");
		lblpwQs.setHorizontalAlignment(SwingConstants.LEFT);
		lblpwQs.setForeground(Color.WHITE);
		lblpwQs.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblpwQs.setBounds(417, 602, 218, 31);
		add(lblpwQs);
		
		
		
		
		//이름 창
		nameField = new JTextField();
		nameField.setBounds(665, 287, 240, 21);
		add(nameField);
		nameField.setColumns(10);

		//아이디 창
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(665, 341, 240, 21);
		add(idField);
		idField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length() >=12) ke.consume();
			}
		});
		
		//비밀번호 창
		passwordField = new JPasswordField();
		passwordField.setBounds(665, 391, 240, 21);
		add(passwordField);
		
		//비밀번호 입력 창 길이 제한
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=16) ke.consume();
			}
		});
		
		//비밀번호 창
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(665, 446, 240, 21);
		add(passwordField2);
		
		//비밀번호 입력 창 길이 제한
		passwordField2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=16) ke.consume();
			}
		});
		
		//폰번호 입력창
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(665, 503, 240, 21);
		add(phoneField);
		
		//폰번호 입력 창 길이 제한
		phoneField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=11) ke.consume();
			}
		});
		
		//생년월일 입력 창
		birthField = new JTextField();
		birthField.setColumns(10);
		birthField.setBounds(665, 554, 240, 21);
		add(birthField);
		birthField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if(src.getText().length()>=6) ke.consume();
			}
		});
		
		//비밀번호 찾기 필드
		pwQsfil = new JTextField();
		pwQsfil.setColumns(10);
		pwQsfil.setBounds(665, 637, 240, 21);
		add(pwQsfil);
		pwQsfil.setVisible(false);
		pwQsfil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				    	 pwVerification();
				   }
			}
		});
		
		
	
		
		
		//아이디 아랫글
		JLabel idleng = new JLabel("6 ~ 12\uC790 \uC0AC\uC774\uB85C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		idleng.setForeground(Color.GRAY);
		idleng.setBounds(665, 364, 173, 15);
		add(idleng);
		
		//비밀번호 아랫글
		JLabel pwleng = new JLabel("8 ~ 16\uC790 \uC0AC\uC774\uB85C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		pwleng.setForeground(Color.GRAY);
		pwleng.setBounds(665, 415, 173, 15);
		add(pwleng);
		
		//폰번호 아랫글
		JLabel hypen = new JLabel("' - ' \uC5C6\uC774 \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		hypen.setForeground(new Color(128, 128, 128));
		hypen.setBounds(665, 527, 137, 15);
		add(hypen);
		
		//생년월일 아랫글
		JLabel yymmdd = new JLabel("' - ' \uC5C6\uC774 \uC785\uB825\uD574\uC8FC\uC138\uC694. ex) YYMMDD");
		yymmdd.setForeground(Color.GRAY);
		yymmdd.setBounds(665, 579, 240, 15);
		add(yymmdd);
		
		//비밀번호 찾기 필드 아랫글
		gapNo = new JLabel("\uACF5\uBC31 \uC5C6\uC774 \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		gapNo.setForeground(Color.GRAY);
		gapNo.setBounds(665, 662, 240, 15);
		add(gapNo);
		gapNo.setVisible(false);
		
		//로고 들어갈 부분 라벨
		JLabel lbllogo = new JLabel("Logo");
		lbllogo.setIcon(new Util().reSizeIcon(new ImageIcon("./img/icon/logo2.png"), 409, 205));
		lbllogo.setForeground(new Color(255, 255, 255));
		lbllogo.setBounds(548, 55, 409, 205);
		add(lbllogo);
		
		//중복체크 라벨 (이미지 들어갈곳)
		JLabel lblCheck = new JLabel("\uC911\uBCF5\uCCB4\uD06C");
		lblCheck.setForeground(new Color(255, 255, 255));
		lblCheck.setBounds(917, 342, 68, 21);
		add(lblCheck);
		lblCheck.setIcon(reSizeIcon(new ImageIcon("./img/icon/DuplicateCheck.png"), 67, 21) );
		lblCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(idField.getText().length() < 6 ) {
					JOptionPane.showMessageDialog(lblCheck, "아이디 길이를 확인해주세요.");
					lblimg.setIcon(reSizeIcon(new ImageIcon("./img/icon/X.png"), 20, 20));
					return;
				}
				if(UserDAO.getInstance().idDuplicationCheck(idField.getText()) == 0) {
					JOptionPane.showMessageDialog(lblCheck, "사용 가능한 아이디 입니다.");
					lblimg.setIcon(reSizeIcon(new ImageIcon("./img/icon/Check.png"), 20, 20));
					
				}else {
					JOptionPane.showMessageDialog(lblCheck, "이미 사용중인 아이디 입니다.");
					lblimg.setIcon(reSizeIcon(new ImageIcon("./img/icon/X.png"), 20, 20));
					
				}
				
			}
		});
	
		
		//회원가입 라벨 (이미지로 버튼 들어갈곳)
		JLabel joinbutton = new JLabel("Join");
		joinbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		joinbutton.setBackground(new Color(0, 0, 0));
		joinbutton.setBounds(627, 721, 150, 50);
		add(joinbutton);
		joinbutton.setIcon(new ImageIcon("./img/icon/bigJoin.png"));
		joinbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				pwVerification();
			}
		});
		
		
		
		
		//비밀번호 찾기 선택박스
			JComboBox pwQs = new JComboBox();
			pwQs.setBounds(665, 604, 240, 23);
			add(pwQs);
			pwQs.addItem("첫 해외여행은 ?");
			pwQs.addItem("가장 기억에 남는 선생님은?");
			pwQs.addItem("감명 깊게 읽은 책은?");
			pwQs.addItem("가장 친한 친구는?");
			pwQs.addItem("자신의 인생 좌우명은?");
			pwQs.addItem("유년시절 별명은?");
			pwQs.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					userDto.setPwQs(pwQs.getSelectedItem().toString());
				}
			});
			pwQs.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					pwQsfil.setVisible(true);
					gapNo.setVisible(true);
				}
			});
			pwQs.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					     if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					    	 pwQsfil.setVisible(true);
								gapNo.setVisible(true);
					   }
				}
			});
		
		
		//돌아가기 버튼
		JButton backBtn = new PrevBtnCustom();
		backBtn.setLocation(12, 10);
		backBtn.putClientProperty("id", "registBack");
		backBtn.addActionListener(new BtnController());
		add(backBtn);		
		
		lblimg = new JLabel("");
		lblimg.setForeground(Color.WHITE);
		lblimg.setBounds(989, 336, 36, 30);
		add(lblimg);
		
	}
	
	public void pwVerification(){
		
		
		String pwf = new String(passwordField.getPassword());
		String pwf2 = new String(passwordField2.getPassword());
		
		
		
		if(nameField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요.");
			nameField.requestFocusInWindow();
			return;
		}
		if(idField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.");
			idField.requestFocusInWindow();
			return;
		}
		if(passwordField.getText().equals("") && passwordField2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요.");
			passwordField.requestFocusInWindow();
			return;
		}
		if(phoneField .getText().equals("")) {
			JOptionPane.showMessageDialog(this, "핸드폰 번호를 입력해주세요.");
			phoneField.requestFocusInWindow();
			return;
		}
		if(birthField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "생년월일을 입력해주세요.");
			birthField.requestFocusInWindow();
			return;
		}
		if(pwQsfil.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "비밀번호 찾기 답변을 입력해주세요.");
			nameField.requestFocus();
			return;
		}
		
		
		
		if(idField.getText().length() < 6 ) {
			JOptionPane.showMessageDialog(idField, "아이디 길이를 확인해주세요.");
			idField.requestFocus();
			return;
		}
		
		
		if( ! pwf.equals(pwf2)) {
			JOptionPane.showMessageDialog(this, "비밀번호를 정확히 입력해주세요.");
			passwordField.setText("");
			passwordField2.setText("");
			return;
		}
		
		if(pwf.length() < 8 && pwf2.length() < 8 ) {
			JOptionPane.showMessageDialog(passwordField, "비밀번호 길이를 확인해주세요.");
			passwordField.requestFocus();;
			return;
		}
		
		if(phoneField.getText().length() < 11 ) {
			JOptionPane.showMessageDialog(phoneField, "핸드폰 번호를 정확히 입력해주세요.");
			phoneField.setText("");
			return;
		}
		
		if(birthField.getText().length() < 6 ) {
			JOptionPane.showMessageDialog(phoneField, "생년월일을 정확히 입력해주세요.");
			birthField.setText("");
			return;
		}
		
		

		userDto.setMember_name(nameField.getText());
		userDto.setMember_id(idField.getText());
		userDto.setMember_pwd(passwordField.getText());
		userDto.setMember_phone(phoneField.getText());
		userDto.setBirth_date(birthField.getText());
		userDto.setMember_pwqs(pwQsfil.getText());
	
	
		if(1 == userDao.insertMember(userDto)) {
			
			JOptionPane.showMessageDialog(this, "가입을 축하합니다!");
			JOptionPane.showMessageDialog(this, "첫 가입! 12,000 포인트가 적립됩니다!");
			setVisible(false);
			PanelController.getInstane().getCmPanel().setVisible(true);
			clearFile();
		}else {
			JOptionPane.showMessageDialog(idField, "아이디 중복체크를 해주세요.");
		}
	}
	
	public void clearFile() {
		nameField.setText("");
		idField.setText("");
		passwordField.setText("");
		passwordField2.setText("");
		phoneField.setText("");
		birthField.setText("");
		pwQsfil.setText("");
		hidePwQsfil();
	}
	
	public void hidePwQsfil() {
		pwQsfil.setVisible(false);
		gapNo.setVisible(false);
	}
	
	public ImageIcon reSizeIcon(ImageIcon icon,int width, int height){
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		return changeIcon;
		}
}
