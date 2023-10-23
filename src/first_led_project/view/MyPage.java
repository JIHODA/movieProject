package first_led_project.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.PaymentDAO;
import first_led_project.dao.UserDAO;
import first_led_project.dto.CommentDTO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;
import first_led_project.view.customComp.PrevBtnCustom;
import java.awt.Cursor;

public class MyPage extends JPanel implements ActionListener{

	private UserDTO user;
	private JLabel total_s;
	private JPanel userInformation;
	private JPanel all;
	private String pw1;
	private JLabel name;
	private JLabel id;
	private JLabel phone;
	private JLabel date;
	private JLabel PencilIcon;
	private JLabel Storage;
	private JLabel point;
	private JLabel subscription;
	private JPanel profileChoice;
	
	private JLabel profileIcon;
	private JLabel basics;
	private JLabel red;
	private JLabel pink;
	private JLabel blue;
	private JLabel green;
	private JLabel p_1;
	private JLabel p_2;
	private JLabel p_3;
	private JLabel p_4;
	private JLabel p_5;
	private JLabel m_1;
	private JLabel m_2;
	private JLabel m_3;
	private JLabel m_4;
	private JLabel m_5;
	
	
	private JPanel historyScrollPanel;
	private JPanel ReviewHistoryScrollPanel;
	private ArrayList<SucessDTO> history;
	private ArrayList<CommentDTO> reviewHistory;
	private JPanel review;
	
	
	//창에서 왼쪽 라인 부분은 로직 마지막 쪽으로 가있어요.
	//각 패널 로직 끝나면 나옵니당.
	
	public MyPage() {
		
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setSize(1384,861);
		setLayout(null);
		
		
		
		
		////////////////회원정보 패널/////////////////////
		
		userInformation = new JPanel();
		userInformation.setBounds(507, 164, 823, 639);
		add(userInformation);
		userInformation.setLayout(null);
		
		JLabel userinfo = new JLabel("\uD68C\uC6D0\uC815\uBCF4");
		userinfo.setHorizontalAlignment(SwingConstants.LEFT);
		userinfo.setForeground(new Color(73, 73, 73));
		userinfo.setFont(new Font("경기천년제목 Bold", Font.BOLD, 26));
		userinfo.setBounds(12, 10, 124, 31);
		userInformation.add(userinfo);
		
		JLabel lblId = new JLabel("ID    :");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		lblId.setForeground(new Color(0, 0, 0));
		lblId.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblId.setBounds(159, 157, 56, 31);
		userInformation.add(lblId);
		
		JLabel lblPhone = new JLabel("PHONE    :");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setForeground(new Color(0, 0, 0));
		lblPhone.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblPhone.setBounds(109, 208, 106, 31);
		userInformation.add(lblPhone);
		
		JLabel lblbirthDate = new JLabel("Birth date    :");
		lblbirthDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblbirthDate.setForeground(new Color(0, 0, 0));
		lblbirthDate.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblbirthDate.setBounds(81, 259, 134, 31);
		userInformation.add(lblbirthDate);
		
		JLabel lblName = new JLabel("NAME    :");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 22));
		lblName.setBounds(118, 103, 97, 31);
		userInformation.add(lblName);
		
		name = new JLabel("\uC774\uB984");
		name.setFont(new Font("굴림", Font.BOLD, 14));
		name.setBounds(241, 110, 86, 17);
		userInformation.add(name);
		
		id = new JLabel("\uC544\uC774\uB514");
		id.setFont(new Font("굴림", Font.BOLD, 14));
		id.setBounds(241, 164, 116, 17);
		userInformation.add(id);
		
		phone = new JLabel("\uBC88\uD638");
		phone.setFont(new Font("굴림", Font.BOLD, 14));
		phone.setBounds(241, 215, 116, 17);
		userInformation.add(phone);
		
		date = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		date.setFont(new Font("굴림", Font.BOLD, 14));
		date.setBounds(241, 266, 116, 17);
		userInformation.add(date);
		
		//비밀번호 찾기 라벨
		JLabel pwModify = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC218\uC815");
		pwModify.setForeground(new Color(128, 128, 128));
		pwModify.setBounds(81, 542, 86, 15);
		userInformation.add(pwModify);
		pwModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pw1 = JOptionPane.showInputDialog("기존 비밀번호를 입력하세요.");
				if(pw1.equals("")) {
					JOptionPane.showMessageDialog(pwModify, "비밀번호를 입력해주세요.", pw1, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				UserDAO.getInstance().pwFind2(user);
				if(pw1.equals(user.getMember_pwd())) {
					String pwQs1 = JOptionPane.showInputDialog(user.getPwQs());
					if(pwQs1.equals(user.getMember_pwqs())) {
						String newpw = JOptionPane.showInputDialog("변경할 비밀번호를 입력해주세요.");
						user.setMember_pwd(newpw);
						UserDAO.getInstance().pwUpdate1(user);
						JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "비밀번호가 변경되었습니다.");
					}
					
				}
				
			}
		});
		
		//회원 탈퇴라벨
		JLabel userX = new JLabel("\uD68C\uC6D0 \uD0C8\uD1F4");
		userX.setForeground(new Color(128, 128, 128));
		userX.setBounds(179, 542, 56, 15);
		userInformation.add(userX);
		
		JLabel lblMypoint = new JLabel("\uB0B4 \uD3EC\uC778\uD2B8    :");
		lblMypoint.setHorizontalAlignment(SwingConstants.LEFT);
		lblMypoint.setForeground(Color.BLACK);
		lblMypoint.setFont(new Font("굴림", Font.BOLD, 18));
		lblMypoint.setBounds(476, 103, 116, 31);
		userInformation.add(lblMypoint);
		
		point = new JLabel("\uD3EC\uC778\uD2B8");
		point.setFont(new Font("굴림", Font.BOLD, 14));
		point.setBounds(616, 110, 116, 17);
		userInformation.add(point);
		
		JLabel lblsubscriptionDay = new JLabel("\uAC00\uC785\uC77C    :");
		lblsubscriptionDay.setHorizontalAlignment(SwingConstants.LEFT);
		lblsubscriptionDay.setForeground(Color.BLACK);
		lblsubscriptionDay.setFont(new Font("굴림", Font.BOLD, 18));
		lblsubscriptionDay.setBounds(500, 157, 92, 31);
		userInformation.add(lblsubscriptionDay);
		
		subscription = new JLabel("\uAC00\uC785\uC77C\uC790");
		subscription.setFont(new Font("굴림", Font.BOLD, 14));
		subscription.setBounds(616, 164, 116, 17);
		userInformation.add(subscription);
		
		
		
		
		
		userX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pw1 = JOptionPane.showInputDialog("비밀번호를 입력하세요.");
				if(user.getMember_pwd().equals(pw1)) {
					
					int userXshow = JOptionPane.showConfirmDialog(null, "정말 탈퇴 하시겠습니까?","Comfirm",JOptionPane.YES_NO_OPTION);
					
					if(userXshow == JOptionPane.YES_OPTION) {
						UserDAO.getInstance().userX(user);
						JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "회원 탈퇴 되었습니다. \n 이용해주셔서 감사합니다.");
						PanelController.getInstane().getCmPanel().LogoutUser();
						setVisible(false);
						PanelController.getInstane().getCmPanel().setVisible(true);
						
					}
					
				}
				
			}
		});

		
		/////////////전체 예매 이력 패널///////////////////
		
		all = new JPanel();
		all.setVisible(false);
		all.setBounds(507, 164, 823, 639);
		add(all);
		all.setLayout(null);
		
		////메서드 따로있음
		JLabel lblAll = new JLabel("\uC804\uCCB4 \uC608\uB9E4 \uC774\uB825");
		lblAll.setBounds(12, 10, 175, 27);
		all.add(lblAll);
		lblAll.setHorizontalAlignment(SwingConstants.LEFT);
		lblAll.setForeground(new Color(73, 73, 73));
		lblAll.setFont(new Font("경기천년제목 Bold", Font.BOLD, 26));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 100, 660, 500);
		all.add(scrollPane);
		
		historyScrollPanel = new JPanel();
		scrollPane.setViewportView(historyScrollPanel);
		historyScrollPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		////////////////내 한줄평 패널/////////////////////
		review = new JPanel();
		review.setVisible(false);
		review.setBounds(507, 164, 823, 639);
		add(review);
		review.setLayout(null);		
		
		JLabel lblreview = new JLabel("\uB0B4 \uD55C\uC904\uD3C9");
		lblreview.setHorizontalAlignment(SwingConstants.LEFT);
		lblreview.setForeground(new Color(73, 73, 73));
		lblreview.setFont(new Font("경기천년제목 Bold", Font.BOLD, 26));
		lblreview.setBounds(12, 10, 175, 27);
		review.add(lblreview);
		review.setForeground(Color.WHITE);
		review.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 26));
		
		JScrollPane scrollPaneR = new JScrollPane();
		scrollPaneR.setBounds(100, 100, 660, 500);
		review.add(scrollPaneR);
		
		ReviewHistoryScrollPanel = new JPanel();
		scrollPaneR.setViewportView(ReviewHistoryScrollPanel);
		ReviewHistoryScrollPanel.setLayout(new GridLayout(0,1,0,0));
		
		
		
		
			//여기부터 왼쪽라인
			
			//프로필 아이콘
			profileIcon = new JLabel("");
			profileIcon.setBounds(122, 164, 195, 211);
			profileIcon.setFont(new Font("굴림", Font.PLAIN, 64));
			profileIcon.setHorizontalAlignment(SwingConstants.CENTER);
			profileIcon.setForeground(new Color(255, 255, 255));
			add(profileIcon);
		
				
				//프로필선택 패널
				profileChoice = new JPanel();
				profileChoice.setBackground(new Color(0, 0, 0));
				profileChoice.setBounds(24, 385, 424, 179);
				add(profileChoice);
				profileChoice.setLayout(null);
				profileChoice.setVisible(false);
				
				basics = new JLabel("");
				basics.setBounds(12, 10, 72, 38);
				basics.putClientProperty("path", "./img/profileicon/profileIcon.png");
				profileChoice.add(basics);
				basics.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/profileIcon.png"), 60, 38));
				basics.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) { 
						profileIcon.putClientProperty("path", basics.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)basics.getClientProperty("path")),190,190));
					}
				});
				
				red = new JLabel("");
				red.setBounds(96, 10, 72, 38);
				red.putClientProperty("path", "./img/profileicon/profile2.png");
				profileChoice.add(red);
				red.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/profile2.png"),60,38));
				red.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", red.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)red.getClientProperty("path")),190,190));
					}
				});
				
				blue = new JLabel("");
				blue.setBounds(180, 10, 72, 38);
				blue.putClientProperty("path", "./img/profileicon/profile4.png");
				profileChoice.add(blue);
				blue.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/profile4.png"),60,38));
				blue.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", blue.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)blue.getClientProperty("path")),190,190));
					}
				});
				
				pink = new JLabel("");
				pink.setBounds(264, 10, 72, 38);
				pink.putClientProperty("path", "./img/profileicon/profile1.png");
				profileChoice.add(pink);
				pink.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/profile1.png"),60,38));
				pink.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", pink.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)pink.getClientProperty("path")),190,190));
					}
				});
				
				green = new JLabel("");
				green.setBounds(348, 10, 72, 38);
				green.putClientProperty("path", "./img/profileicon/profile3.png");
				profileChoice.add(green);
				green.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/profile3.png"),60,38));
				green.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", green.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)green.getClientProperty("path")),190,190));
					}
				});
				
				
				p_1 = new JLabel("");
				p_1.setBounds(12, 72, 72, 38);
				p_1.putClientProperty("path", "./img/profileicon/1-1.png");
				profileChoice.add(p_1);
				p_1.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/1-1.png"), 60, 38));
				p_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", p_1.getClientProperty("path"));
						profileIcon.setIcon(new ImageIcon((String)p_1.getClientProperty("path")));
					}
				});
				
				p_2 = new JLabel("");
				p_2.setBounds(96, 72, 72, 38);
				p_2.putClientProperty("path", "./img/profileicon/1-2.png");
				profileChoice.add(p_2);
				p_2.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/1-2.png"), 60, 38));
				p_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", p_2.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)p_2.getClientProperty("path")),190,190));
					}
				});
				
				p_3 = new JLabel("");
				p_3.setBounds(180, 72, 72, 38);
				p_3.putClientProperty("path", "./img/profileicon/1-3.png");
				profileChoice.add(p_3);
				p_3.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/1-3.png"), 60, 38));
				p_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", p_3.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)p_3.getClientProperty("path")),190,190));
					}
				});
				
				p_4 = new JLabel("");
				p_4.setBounds(264, 72, 72, 38);
				p_4.putClientProperty("path", "./img/profileicon/1-4.png");
				profileChoice.add(p_4);
				p_4.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/1-4.png"), 60, 38));
				p_4.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", p_4.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)p_4.getClientProperty("path")),190,190));
					}
				});
				
				p_5 = new JLabel("");
				p_5.setBounds(348, 72, 72, 38);
				p_5.putClientProperty("path", "./img/profileicon/1-5.png");
				profileChoice.add(p_5);
				p_5.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/1-5.png"), 60, 38));
				p_5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", p_5.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)p_5.getClientProperty("path")),190,190));
					}
				});
				
				m_1 = new JLabel("");
				m_1.setBounds(12, 132, 72, 38);
				m_1.putClientProperty("path", "./img/profileicon/2-1.png");
				profileChoice.add(m_1);
				m_1.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/2-1.png"), 60, 38));
				m_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", m_1.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)m_1.getClientProperty("path")),190,190));
					}
				});
				
				m_2 = new JLabel("");
				m_2.setBounds(96, 132, 72, 38);
				m_2.putClientProperty("path", "./img/profileicon/2-2.png");
				profileChoice.add(m_2);
				m_2.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/2-2.png"), 60, 38));
				m_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", m_2.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)m_2.getClientProperty("path")),190,190));
					}
				});
				
				m_3 = new JLabel("");
				m_3.setBounds(180, 132, 72, 38);
				m_3.putClientProperty("path", "./img/profileicon/2-3.png");
				profileChoice.add(m_3);
				m_3.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/2-3.png"), 60, 38));
				m_3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", m_3.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)m_3.getClientProperty("path")),190,190));
					}
				});
				
				m_4 = new JLabel("");
				m_4.setBounds(264, 132, 72, 38);
				m_4.putClientProperty("path", "./img/profileicon/2-4.png");
				profileChoice.add(m_4);
				m_4.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/2-4.png"), 60, 38));
				m_4.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", m_4.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)m_4.getClientProperty("path")),190,190));
					}
				});
				
				m_5 = new JLabel("");
				m_5.setBounds(348, 132, 72, 38);
				m_5.putClientProperty("path", "./img/profileicon/2-5.png");
				profileChoice.add(m_5);
				m_5.setIcon(reSizeIcon(new ImageIcon("./img/profileicon/2-5.png"), 60, 38));
				m_5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileIcon.putClientProperty("path", m_5.getClientProperty("path"));
						profileIcon.setIcon(reSizeIcon(new ImageIcon((String)m_5.getClientProperty("path")),190,190));
					}
				});
				
				
				
				
				
				//프로필 변경 저장버튼
				Storage = new JLabel("\uC800\uC7A5");
				Storage.setHorizontalAlignment(SwingConstants.CENTER);
				Storage.setForeground(new Color(255, 255, 255));
				Storage.setBounds(394, 568, 78, 29);
				add(Storage);
				Storage.setVisible(false);
					
				Storage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						profileChoice.setVisible(false);
						Storage.setVisible(false);
						
						user.setMember_profile((String)profileIcon.getClientProperty("path"));
						UserDAO.getInstance().profile(user);
					}
				});
				
		
				//프로필 수정 아이콘 (연필)
				PencilIcon = new JLabel("");
				PencilIcon.setHorizontalAlignment(SwingConstants.CENTER);
				PencilIcon.setBounds(284, 314, 101, 72);
				PencilIcon.setForeground(new Color(255, 255, 255));
				add(PencilIcon);
				PencilIcon.setIcon(reSizeIcon(new ImageIcon("./img/icon/PencilIcon.png"), 30, 30));
				PencilIcon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(profileChoice.isVisible()){
							profileChoiceBye();
							profileIcon.setIcon(reSizeIcon(new ImageIcon(user.getMember_profile()),190, 190)); 
							
						}else {
							profileChoice.setVisible(true);
							Storage.setVisible(true);
							
						}
						
						
					}
				});
				
		
				//회원 정보 선택 (왼쪽부분)
				JLabel userInfo_s = new JLabel("\uD68C\uC6D0 \uC815\uBCF4");
				userInfo_s.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				userInfo_s.setBounds(122, 505, 195, 37);
				userInfo_s.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 26));
				userInfo_s.setForeground(new Color(255, 255, 255));
				add(userInfo_s);
				userInfo_s.setIcon(new ImageIcon("./img/icon/userInfor.png"));
				userInfo_s.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						userInformation.setVisible(true);
						all.setVisible(false);
						review.setVisible(false);
						point.setText("" + user.getTotal_point());
						
					}
				});
						
						
				//내 한줄평 선택 (왼쪽부분)
				JLabel myReview = new JLabel("내 한줄평");
				myReview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				myReview.setForeground(Color.WHITE);
				myReview.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 26));
				myReview.setBounds(122, 568, 195, 37);
				add(myReview);
				myReview.setIcon(new ImageIcon("./img/icon/myReview.png"));
				myReview.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						all.setVisible(false);
						userInformation.setVisible(false);
						review.setVisible(true);
								
					}
				});
						
						
				//전체 예매이력 선택 (왼쪽부분)
				total_s = new JLabel("\uC804\uCCB4 \uC608\uB9E4 \uC774\uB825");
				total_s.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				total_s.setBounds(122, 630, 195, 37);
				total_s.setForeground(Color.WHITE);
				total_s.setFont(new Font("경기천년제목 Bold", Font.PLAIN, 26));
				add(total_s);
				total_s.setIcon(new ImageIcon("./img/icon/myPageAllmovie.png"));
				total_s.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						all.setVisible(true);
						userInformation.setVisible(false);
						review.setVisible(false);
					}
				});
				
				
				JButton backBtn = new PrevBtnCustom();
				backBtn.setLocation(12, 10);
				backBtn.putClientProperty("id", "myPageBack");
				backBtn.addActionListener(new BtnController());
				add(backBtn);
				
		
				
	}
	
	public void profileChoiceBye() {
		profileChoice.setVisible(false);
		Storage.setVisible(false);
	}
	
	
	
	public void setUserInfo(UserDTO user) {
		
		this.user = user;
		reviewHistory();
		allHistory();
		name.setText(user.getMember_name());
		id.setText(user.getMember_id());
		phone.setText(user.getMember_phone());
		date.setText(user.getBirth_date());
		subscription.setText(user.getReg_date());
		point.setText("" + user.getTotal_point());
		
		profileIcon.setIcon(reSizeIcon(new ImageIcon(user.getMember_profile()), 190, 190));
	}
	
	
	public void allHistory() {
		this.history = UserDAO.getInstance().movieAllReservation(user);
		if(history.size() == 0) {
			return;
		}
		for(int i = 0; i < history.size(); i++) {
			JPanel panel = new MovieHistoryPanel(history.get(i));
			historyScrollPanel.add(panel);
		}
	}
	public void removeHistory() {
		historyScrollPanel.removeAll();
		ReviewHistoryScrollPanel.removeAll();
		all.setVisible(false);
		userInformation.setVisible(true);
		review.setVisible(false);
	}
	
	
	public void reviewHistory() {
		this.reviewHistory = UserDAO.getInstance().allMyReview(user);
		if(reviewHistory.size() == 0) {
			return;
		}
		
		
		for(int i = 0; i < reviewHistory.size(); i++) {
			JPanel panel = new reviewHistoryPanel(reviewHistory.get(i));
			ReviewHistoryScrollPanel.add(panel);
		}
		
	}
	
	
	public ImageIcon reSizeIcon(ImageIcon icon,int width, int height){
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		return changeIcon;
		
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class MovieHistoryPanel extends JPanel{
		
		public MovieHistoryPanel(SucessDTO dto) {
			setBorder(new LineBorder(new Color(128, 128, 192)));
			setPreferredSize(new Dimension(633, 173));
//			setBounds(97, 71, 633, 173);
			setLayout(null);
			
			JLabel ticketCode = new JLabel();
			ticketCode.setBounds(504, 10, 117, 15);
			ticketCode.setFont(new Font("굴림", Font.PLAIN, 11));
			ticketCode.setText(dto.getTiketnumber());
			add(ticketCode);
			
			JLabel MovieTitle = new JLabel("영화제목");
			MovieTitle.setFont(new Font("굴림", Font.BOLD, 16));
			MovieTitle.setBounds(173, 20, 279, 27);
			MovieTitle.setText(dto.getMovieTitle());
			add(MovieTitle);
			
			JLabel ScreenDateLabel = new JLabel("상영날짜 :");
	         ScreenDateLabel.setBounds(173, 95, 70, 27);
	         add(ScreenDateLabel);
			
			JLabel showDay = new JLabel("상영날짜");
			showDay.setFont(new Font("굴림", Font.BOLD, 12));
			showDay.setBounds(238, 95, 190, 27);
			showDay.setText(dto.getScreen_date().toString());
			add(showDay);
			
			JLabel theater = new JLabel("상영관");
			theater.setFont(new Font("굴림", Font.BOLD, 12));
			theater.setBounds(173, 47, 36, 27);
			theater.setText(dto.getScreen());
			add(theater);
			
			JLabel seat = new JLabel("좌석정보");
			seat.setFont(new Font("굴림", Font.BOLD, 12));
			seat.setBounds(221, 47, 231, 27);
			seat.setText("좌석 : "+dto.getSeat_name());
			add(seat);
			
			JLabel ticketingDateLabel = new JLabel("예매날짜 :");
	         ticketingDateLabel.setBounds(173, 120, 72, 27);
	         add(ticketingDateLabel);
			
			JLabel moneyDay = new JLabel("\uC608\uB9E4 \uB0A0\uC9DC");
			moneyDay.setBounds(238, 120, 233, 27);
			add(moneyDay);
			moneyDay.setFont(new Font("굴림", Font.BOLD, 12));
			moneyDay.setText(dto.getTicketing_reg().toString());
			
			JLabel poster = new JLabel("");
			poster.setBounds(12, 10, 130, 150);
			add(poster);
			poster.setIcon(reSizeIcon(new ImageIcon("./img/poster/poster_"+dto.getMovie_rank()+".jpg"), 130, 150));
			
			JButton movieXbutton = new JButton("예매취소");
			movieXbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SucessDTO sucessDTO = new SucessDTO();
					sucessDTO.setTiketnumber(ticketCode.getText());
					pointReturn(user, sucessDTO);
					PaymentDAO.getInstance().updatePoint(user);
					PaymentDAO.getInstance().cancelTiket(sucessDTO);
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "예매가 취소되었습니다.");
					
					historyScrollPanel.removeAll();
					historyScrollPanel.revalidate();
					historyScrollPanel.repaint();
					allHistory();
				}
			});
			movieXbutton.setBackground(new Color(255, 255, 255));
			movieXbutton.setBounds(524, 140, 97, 23);
			add(movieXbutton);
			
			JButton ticketInfo = new JButton("상세");
			ticketInfo.setBackground(new Color(255, 255, 255));
			ticketInfo.setBounds(454,140,60,23);
			ticketInfo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame jf =new JFrame();
					jf.setSize(1300,900);
					Container jfPanel = jf.getContentPane();
					jf.setContentPane(jfPanel);
					JLabel screenShot = new JLabel(reSizeIcon(new ImageIcon("./img/ticketing/"+dto.getTiketnumber()+".png"), 1360, 860) );
					screenShot.setSize(jfPanel.getSize());
					screenShot.setLocation(0, 0);
					jfPanel.add(screenShot);
					jf.setVisible(true);
				}
			});
			add(ticketInfo);
		}
	}
	
	class reviewHistoryPanel extends JPanel{
		public reviewHistoryPanel(CommentDTO dto) {
			
			setBorder(new LineBorder(new Color(128, 128, 192)));
			setPreferredSize(new Dimension(633, 173));
			setLayout(null);
			
			JLabel movieTitle = new JLabel("영화제목");
			movieTitle.setFont(new Font("굴림", Font.BOLD, 16));
			movieTitle.setBounds(156, 10, 220, 30);
			movieTitle.setText(dto.getMovie_title());
			add(movieTitle);
			
			JLabel poster = new JLabel("");
			poster.setBounds(12, 10, 118, 144);
			add(poster);
			poster.setIcon(reSizeIcon(new ImageIcon("./img/poster/poster_"+ dto.getMovie_rank()+".jpg"), 130, 150));
			
			JLabel  writing = new JLabel("작성일자  : ");
			writing.setFont(new Font("굴림", Font.BOLD, 12));
			writing.setBounds(377, 10, 70, 30);
			add(writing);
			
			
			JLabel  writeDate = new JLabel();
			writeDate.setFont(new Font("굴림", Font.BOLD, 12));
			writeDate.setBounds(447, 10, 150, 30);
			add(writeDate);
			writeDate.setText(dto.getWrite_date().toString());
			
			JTextArea myreview = new JTextArea();
			myreview.setBounds(156, 50, 460, 104);
			add(myreview);
			myreview.setText(dto.getContents());
			myreview.setEditable(false);
			
			
		}
		
	}
	
	public void pointReturn(UserDTO user, SucessDTO sucess) {
		
		
		PaymentDAO.getInstance().pointReturn(sucess, user);
		
		if(sucess.getPayingWhat().equals("payCard")) {
			int re1 = user.getUsing_point() + user.getTotal_point() - (int)sucess.getMember_point();
			user.setTotal_point(re1);
			
			return;
		}else if(sucess.getPayingWhat().equals("payPoint")){
			int re2 = user.getUsing_point() + user.getTotal_point();
			user.setTotal_point(re2);
			return;
		}
		
		
	}
}
