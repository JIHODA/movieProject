package first_led_project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.MovieDAO;
import first_led_project.dao.PaymentDAO;
import first_led_project.dto.SeatDTO;
import first_led_project.dto.ShowTimeDTO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;
import first_led_project.view.customComp.KakaoPayButton;
import first_led_project.view.customComp.PrevBtnCustom;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Payment extends JPanel {
	private UserDTO user;
	PanelController pc = PanelController.getInstane();
	private Component contentPane;
	private int member_point;
	private int plusPoint = 0;
	private int deductionPoint; 
	private int earnPoints;
	private int deductionUsingPoint;
	private int deductionPrice;
	private String payCard;
	private String payPoint;
	private int totPoint;
	private JTextField cardN;
	private JTextField cardN2;
	private JPasswordField cardN3;
	private JPasswordField cardN4;
	private JPasswordField cardPW;
	private JComponent cardPanel;
	private JLabel userName;
	private JLabel date;
	private JLabel pointPayment;
	JButton usingPointCheck;
	JLabel usingPointCheckBox;
	JButton pointpayButton;
	JButton cardpayButton;
	String cardNum1;
	String cardNum2;
	String cardNum3;
	String cardNum4;
	String cardPass;
	String usingBox;
	JPanel pointPanel;
	JLabel person;
	JLabel screen_seat;
	JLabel movieTitle;
	JLabel cinema;
	JLabel price;
	JButton payButton;
	JLabel pointbox;
	JButton pointCheck;
	JButton usingPoint;
	JPanel usingPointPanel;
	JTextField usingPointBox;
	SucessDTO sdto = new SucessDTO();
	ArrayList<SeatDTO> arr;
	KakaoPayButton kakaoPayBtn;
	
	public Payment() {
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		setSize(1384, 861);
		
		JPanel moviePanel = new JPanel();
		moviePanel.setBackground(new Color(0, 0, 0));
		moviePanel.setBounds(115, 335, 438, 396);
		add(moviePanel);
		moviePanel.setLayout(null);
		
		JLabel userNameLabel = new JLabel("\uC131\uD568");
		userNameLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		userNameLabel.setBounds(0, 0, 131, 36);
		moviePanel.add(userNameLabel);
		userNameLabel.setForeground(new Color(255, 255, 255));
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setOpaque(true);
		userNameLabel.setBackground(new Color(0, 0, 0));
		
		JLabel movieTitleLabel = new JLabel("\uC601\uD654");
		movieTitleLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		movieTitleLabel.setBounds(0, 60, 131, 36);
		moviePanel.add(movieTitleLabel);
		movieTitleLabel.setForeground(new Color(255, 255, 255));
		movieTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movieTitleLabel.setOpaque(true);
		movieTitleLabel.setBackground(new Color(0, 0, 0));
		
		JLabel cinemaLabel = new JLabel("\uADF9\uC7A5");
		cinemaLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		cinemaLabel.setBounds(0, 120, 131, 36);
		moviePanel.add(cinemaLabel);
		cinemaLabel.setForeground(new Color(255, 255, 255));
		cinemaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cinemaLabel.setOpaque(true);
		cinemaLabel.setBackground(new Color(0, 0, 0));
		
		JLabel screen_seatLabel = new JLabel("\uC0C1\uC601\uAD00 \uBC0F \uC88C\uC11D");
		screen_seatLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		screen_seatLabel.setBounds(0, 180, 131, 36);
		moviePanel.add(screen_seatLabel);
		screen_seatLabel.setForeground(new Color(255, 255, 255));
		screen_seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		screen_seatLabel.setOpaque(true);
		screen_seatLabel.setBackground(new Color(0, 0, 0));
		
		JLabel dateLabel = new JLabel("\uB0A0\uC9DC");
		dateLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		dateLabel.setBounds(0, 240, 131, 36);
		moviePanel.add(dateLabel);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setOpaque(true);
		dateLabel.setBackground(new Color(0, 0, 0));
		
		JLabel personLabel = new JLabel("\uC778\uC6D0");
		personLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		personLabel.setBounds(0, 300, 131, 36);
		moviePanel.add(personLabel);
		personLabel.setForeground(new Color(255, 255, 255));
		personLabel.setHorizontalAlignment(SwingConstants.CENTER);
		personLabel.setOpaque(true);
		personLabel.setBackground(new Color(0, 0, 0));
		
		JLabel priceLabel = new JLabel("\uAE08\uC561");
		priceLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		priceLabel.setBounds(0, 360, 131, 36);
		moviePanel.add(priceLabel);
		priceLabel.setForeground(new Color(255, 255, 255));
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setOpaque(true);
		priceLabel.setBackground(new Color(0, 0, 0));
		
		userName = new JLabel("New label");
		userName.setFont(new Font("굴림", Font.PLAIN, 12));
		userName.setBounds(143, 0, 267, 36);
		moviePanel.add(userName);
		userName.setForeground(new Color(255, 255, 255));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setOpaque(true);
		userName.setBackground(new Color(0, 0, 0));
		
		movieTitle = new JLabel("New label");
		movieTitle.setFont(new Font("굴림", Font.PLAIN, 12));
		movieTitle.setBounds(143, 60, 267, 36);
		moviePanel.add(movieTitle);
		movieTitle.setForeground(new Color(255, 255, 255));
		movieTitle.setHorizontalAlignment(SwingConstants.CENTER);
		movieTitle.setOpaque(true);
		movieTitle.setBackground(new Color(0, 0, 0));
		
		cinema = new JLabel("LED CINEMA");
		cinema.setFont(new Font("굴림", Font.PLAIN, 12));
		cinema.setBounds(143, 120, 267, 36);
		moviePanel.add(cinema);
		cinema.setForeground(new Color(255, 255, 255));
		cinema.setHorizontalAlignment(SwingConstants.CENTER);
		cinema.setOpaque(true);
		cinema.setBackground(new Color(0, 0, 0));
		
		screen_seat = new JLabel("New label");
		screen_seat.setFont(new Font("굴림", Font.PLAIN, 12));
		screen_seat.setBounds(143, 180, 267, 36);
		moviePanel.add(screen_seat);
		screen_seat.setForeground(new Color(255, 255, 255));
		screen_seat.setHorizontalAlignment(SwingConstants.CENTER);
		screen_seat.setOpaque(true);
		screen_seat.setBackground(new Color(0, 0, 0));
		
		date = new JLabel("");
		date.setFont(new Font("굴림", Font.PLAIN, 12));
		date.setBounds(143, 240, 267, 36);
		moviePanel.add(date);
		date.setForeground(new Color(255, 255, 255));
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setOpaque(true);
		date.setBackground(new Color(0, 0, 0));
		
		person = new JLabel("New label");
		person.setFont(new Font("굴림", Font.PLAIN, 12));
		person.setBounds(143, 300, 267, 36);
		moviePanel.add(person);
		person.setForeground(new Color(255, 255, 255));
		person.setHorizontalAlignment(SwingConstants.CENTER);
		person.setOpaque(true);
		person.setBackground(new Color(0, 0, 0));
		
		price = new JLabel("New label");
		price.setFont(new Font("굴림", Font.PLAIN, 12));
		price.setBounds(143, 360, 267, 36);
		moviePanel.add(price);
		price.setForeground(new Color(255, 255, 255));
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setOpaque(true);
		price.setBackground(new Color(0, 0, 0));
		
		JLabel cardPay = new JLabel("카드 결제");
		cardPay.setFont(new Font("굴림", Font.PLAIN, 17));
		cardPay.setForeground(new Color(255, 255, 255));
		cardPay.setHorizontalAlignment(SwingConstants.CENTER);
		cardPay.setBounds(623, 275, 147, 50);
		add(cardPay);
		cardPay.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				
				cardPanel.setVisible(true);
				cardpayButton.setVisible(true);
				usingPointPanel.setVisible(true);
				pointPanel.setVisible(false);
				pointpayButton.setVisible(false);
				
				cardN.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						String text = cardN.getText();
						if(text.length() >= 3) {
							cardN2.requestFocus();
						}
					}
				});
				
				cardN2.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						String text = cardN2.getText();
						if(text.length() >= 3) {
							cardN3.requestFocus();
						}
					}
				});
				
				cardN3.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char[] text = cardN3.getPassword();
						if(text.length >= 3) {
							cardN4.requestFocus();
						}
					}
				});
				
				cardN4.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char[] text = cardN4.getPassword();
						if(text.length >= 3) {
							cardPW.requestFocus();
						}
					}
				});
				cardPW.addKeyListener(new KeyAdapter() {
					
					@Override
					public void keyTyped(KeyEvent k) {
						if(cardPW.getPassword().length >= 2)
							k.consume();;
					}
				});
			
			}

		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(565, 335, 550, 8);
		add(separator);
		
		cardPanel = new JPanel();
		cardPanel.setBackground(new Color(0, 0, 0));
		cardPanel.setBounds(610, 427, 474, 193);
		add(cardPanel);
		cardPanel.setLayout(null);
		cardPanel.setVisible(false);
		
		JComboBox card = new JComboBox();
		card.setBounds(0, 0, 87, 30);
		cardPanel.add(card);
		card.addItem("삼성");
		card.addItem("현대");
		card.addItem("농협");
		card.addItem("신한");
		card.addItem("하나");
		card.addItem("카카오뱅크");
		
		cardN = new JTextField();
		cardN.setBounds(0, 82, 116, 30);
		cardPanel.add(cardN);
		cardN.setColumns(10);
		
		cardN2 = new JTextField();
		cardN2.setBounds(124, 82, 116, 30);
		cardPanel.add(cardN2);
		cardN2.setColumns(10);
		
		cardN3 = new JPasswordField();
		cardN3.setBounds(252, 82, 105, 30);
		cardPanel.add(cardN3);
		
		cardN4 = new JPasswordField();
		cardN4.setBounds(369, 82, 105, 30);
		cardPanel.add(cardN4);
		
		JLabel cPW = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC55E \uB450\uC790\uB9AC");
		cPW.setBounds(232, 163, 125, 30);
		cardPanel.add(cPW);
		cPW.setFont(new Font("굴림", Font.PLAIN, 13));
		cPW.setForeground(new Color(255, 255, 255));
		cPW.setHorizontalAlignment(SwingConstants.CENTER);
		
		cardPW = new JPasswordField();
		cardPW.setBounds(369, 163, 105, 30);
		cardPanel.add(cardPW);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(565, 729, 550, 8);
		add(separator_1);
		
		cardpayButton = new JButton("\uACB0\uC81C \uD558\uAE30");
		cardpayButton.setBorder(null);
		cardpayButton.setHorizontalAlignment(SwingConstants.CENTER);
		cardpayButton.setBackground(new Color(0, 0, 0));
		cardpayButton.setOpaque(false);
		cardpayButton.setBounds(630, 785, 160, 44);
		add(cardpayButton);
		cardpayButton.setIcon(new ImageIcon("./img/icon/moneyOut.png"));
		
		pointpayButton = new JButton("\uACB0\uC81C \uD558\uAE30");
		pointpayButton.setHorizontalAlignment(SwingConstants.CENTER);
		pointpayButton.setBorder(null);
		pointpayButton.setBackground(new Color(0, 0, 0));
		pointpayButton.setOpaque(false);
		pointpayButton.setBounds(630, 785, 160, 44);
		add(pointpayButton);
		pointpayButton.setIcon(new ImageIcon("./img/icon/moneyOut.png"));
		
		JButton backBtn = new PrevBtnCustom();
		backBtn.setBounds(12, 10, 50, 50);
		backBtn.putClientProperty("id", "paymentBack");
		backBtn.addActionListener(new BtnController());
		add(backBtn);
		
		pointPayment = new JLabel("포인트 결제");
		pointPayment.setHorizontalAlignment(SwingConstants.CENTER);
		pointPayment.setForeground(Color.WHITE);
		pointPayment.setFont(new Font("굴림", Font.PLAIN, 17));
		pointPayment.setBounds(903, 275, 147, 50);
		pointPayment.putClientProperty("id", "payPoint");
		add(pointPayment);
		
		pointPayment.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cardPanel.setVisible(false);
				cardpayButton.setVisible(false);
				usingPointPanel.setVisible(false);
				pointPanel.setVisible(true);
				pointpayButton.setVisible(true);
			}
		});
			
		pointPanel = new JPanel();
		pointPanel.setBackground(new Color(0, 0, 0));
		pointPanel.setBounds(700, 520, 320, 30);
		add(pointPanel);
		pointPanel.setLayout(null);
		pointPanel.setVisible(false);
		
		pointbox = new JLabel("");
		pointbox.setBounds(0, 0, 165, 30);
		pointPanel.add(pointbox);
		pointbox.setOpaque(true);
		
		pointCheck = new JButton("포인트 조회");
		pointCheck.setBounds(200, 0, 120, 30);
		pointCheck.putClientProperty("id", "checkPoint");
		pointPanel.add(pointCheck);	
		
		usingPointPanel = new JPanel();
		usingPointPanel.setBackground(new Color(0, 0, 0));
		pointPanel.setBackground(new Color(0, 0, 0));
		usingPointPanel.setBounds(610, 644, 474, 30);
		add(usingPointPanel);
		usingPointPanel.setLayout(null);
		usingPointPanel.setVisible(false);
		
		usingPointCheckBox = new JLabel("");
		usingPointCheckBox.setBounds(0, 0, 100, 30);
		usingPointPanel.add(usingPointCheckBox);
		usingPointCheckBox.setOpaque(true);
		
		usingPointCheck = new JButton("\uD3EC\uC778\uD2B8 \uC870\uD68C");
		usingPointCheck.setBounds(112, 0, 100, 30);
		usingPointPanel.add(usingPointCheck);
		
		usingPoint = new JButton("\uD3EC\uC778\uD2B8 \uC0AC\uC6A9");
		usingPoint.setBounds(374, 0, 100, 30);
		usingPointPanel.add(usingPoint);
		
		usingPointBox = new JTextField();
		usingPointBox.setBounds(262, 0, 100, 30);
		usingPointPanel.add(usingPointBox);
		usingPointBox.setColumns(10);
		
		
		
		pointpayButton.addMouseListener(new MouseAdapter() {
			
			private Component contentPane;
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 cardNum1 = cardN.getText();
				 cardNum2 = cardN2.getText();
				 cardNum3 = cardN3.getText();
				 cardNum4 = cardN4.getText();
				 cardPass = cardPW.getText();
				
			if(user.getTotal_point() < 12000 || sdto.getPrice() > user.getTotal_point()) {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "포인트가 부족합니다.");
			}else if((cardNum1.isEmpty() ||
					cardNum2.isEmpty() ||
					cardNum3.isEmpty() ||
					 cardNum4.isEmpty()) && user.getTotal_point() >= 12000) {
					deductionPoint = user.getTotal_point() - sdto.getPrice();
					System.out.println(user.getTotal_point());
					user.setTotal_point(deductionPoint);
					user.setUsing_point(sdto.getPrice());
					System.out.println(user.getTotal_point());
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "포인트를 사용합니다.");
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "결제가 완료 되었습니다.");
					sdto.setMember_point(0);
					sdto.setPayingWhat("payPoint");
					try {
						PaymentDAO.getInstance().insertTiketing(sdto, user);
						PaymentDAO.getInstance().insertSeatInfo(arr);
						PaymentDAO.getInstance().updatePoint(user);
						PaymentDAO.getInstance().updatePay(sdto);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Complete complete = pc.getComplete();
					pc.getMainPane().add(complete);
					pc.updateComplete(sdto);
					complete.setVisible(true);
					setVisible(false);
					
				}	
			}
		});
		
		usingPoint.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			if(usingPointBox.getText().isEmpty()) {
				JOptionPane.showMessageDialog(getComponentPopupMenu(), "포인트를 입력해주세요.");
			}
				
			if(user.getTotal_point() < Integer.parseInt(usingPointBox.getText()) ||
					user.getTotal_point() == 0) {
				JOptionPane.showMessageDialog(getComponentPopupMenu(), "포인트가 부족합니다.");
			}else {
				JOptionPane.showMessageDialog(getComponentPopupMenu(), "포인트를 사용합니다.");
				deductionUsingPoint = user.getTotal_point() - Integer.parseInt(usingPointBox.getText());
				deductionPrice = sdto.getPrice() - Integer.parseInt(usingPointBox.getText());
				sdto.setPrice(deductionPrice);
				user.setTotal_point(deductionUsingPoint);
				pc.getUser().setUsing_point(Integer.parseInt(usingPointBox.getText()));
				try {
					PaymentDAO.getInstance().updatePoint(user);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			
			}
		});
		

		cardpayButton.addMouseListener(new MouseAdapter() {
			
			private Component contentPane;
			

			public void mouseClicked(MouseEvent e) {
				 cardNum1 = cardN.getText();
				 cardNum2 = cardN2.getText();
				 cardNum3 = cardN3.getText();
				 cardNum4 = cardN4.getText();
				 cardPass = cardPW.getText();
				 usingBox = usingPointBox.getText();
				 
				 	
				if(cardNum1.length() < 4 || cardNum1.isEmpty() || 
						cardNum2.length() < 4 || cardNum2.isEmpty() || 
						cardNum3.length() < 4 || cardNum3.isEmpty() || 
						cardNum4.length() < 4 || cardNum4.isEmpty()) {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "카드 번호를 정확히 입력해주세요.");
				}else if(cardPass.length() < 2 || cardPass.length() > 2 || cardPass.isEmpty()) {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "카드 비밀번호를 정확히 입력해주세요.");
				}else {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "결제가 완료 되었습니다.");
					sdto.setPayingWhat("payCard");
					sdto.setMember_point((int)(sdto.getPrice() * 0.05));
					earnPoints = plusPoint + (int)sdto.getMember_point();
					System.out.println(deductionPoint);
					user.setTotal_point((int)(user.getTotal_point() + earnPoints));
					user.setTotal_point(pc.getUser().getTotal_point());
					JOptionPane.showMessageDialog(getComponentPopupMenu(), sdto.getMember_point() + "포인트가 적립 되었습니다.");
					
				if(usingBox.isEmpty() || usingBox == "0") {
						user.setUsing_point(0);
				 }
					
					try {
						PaymentDAO.getInstance().insertTiketing(sdto, user);
						PaymentDAO.getInstance().insertSeatInfo(arr);
						PaymentDAO.getInstance().updatePoint(user);
						PaymentDAO.getInstance().updatePay(sdto);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Complete complete = pc.getComplete();
					pc.getMainPane().add(complete);
					pc.updateComplete(sdto);
					complete.setVisible(true);
					setVisible(false);
				}
			};
		});
		
			pointCheck.addMouseListener(new MouseAdapter() {
			
			Component contentPane;
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
				pointbox.setText("" + pc.getUser().getTotal_point());
				
				if(user.getTotal_point() >= sdto.getPrice()) {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "사용 가능한 포인트가 있습니다.");
				}else {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "사용 가능한 포인트가 부족합니다.");
				}
				
			}
		});
			
			usingPointCheck.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					usingPointCheckBox.setText("" + pc.getUser().getTotal_point());
				}
			});
			kakaoPayBtn = new KakaoPayButton(sdto);
			kakaoPayBtn.setBackground(new Color(0, 0, 0));
			kakaoPayBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			kakaoPayBtn.setBorder(null);
			kakaoPayBtn.setBounds(940, 782, 100, 50);
			add(kakaoPayBtn);
		
	}
	public void update(ArrayList<SeatDTO> arr, ShowTimeDTO stDTO) {
		
		this.arr = arr;
		String[] movieInfo = MovieDAO.getInstance().movieName(stDTO);
		this.user = PanelController.getInstane().getUser();
		
		String tkNum = tiketingNumber();
		
		sdto.setMember_id(user.getMember_id());//유저 아이디
		sdto.setUserName(user.getMember_name());//유저 이름
		sdto.setShowtime_id(stDTO.getShowtime_id());
		sdto.setMovie_id(movieInfo[0]);//영화코드
		sdto.setMovieTitle(movieInfo[1]);//영화제목
		sdto.setMovie_rank(movieInfo[2]);//영화랭크
		sdto.setCinema("LEDCINEMA");//극장
		sdto.setScreen(stDTO.getScreen_id());//상영관
		sdto.setScreen_date(stDTO.getScreen_date());//상영날짜
		sdto.setScreen_time(stDTO.getScreen_time());//상영시간
		sdto.setPerson(""+arr.size());//인원
		sdto.setShowtime_id(stDTO.getShowtime_id());//상영관 날짜 시간 합친거
		sdto.setPrice(user.getPrice());
		sdto.setTiketnumber(tkNum);
		String seatNames = stDTO.getScreen_id()+" / ";
		String ScreenNum = "";
		for(SeatDTO dto : arr) {
			ScreenNum += dto.getSeat_name()+", ";
			seatNames += dto.getSeat_name()+", ";
			dto.setTiketnumber(tkNum);
		}
		sdto.setSeat_name(ScreenNum.substring(0, ScreenNum.length()-2));
	
		
		seatNames = seatNames.substring(0, seatNames.length()-2);
	
		userName.setText(user.getMember_name());
		movieTitle.setText(sdto.getMovieTitle());
		cinema.setText("LEDCINEMA");
		screen_seat.setText(seatNames);
		date.setText(sdto.getScreen_date().toString());
		person.setText(sdto.getPerson()+" 명");
		price.setText(""+sdto.getPrice()+" 원");
		
	}
	
	public SucessDTO getSusDTO() {
		return sdto;
	}
	
	public void reset() {
		cardN.setText("");
		cardN2.setText("");
		cardN3.setText("");
		cardN4.setText("");
		cardPW.setText("");
		pointbox.setText("");
		usingPointBox.setText("");
		usingPointCheckBox.setText("");
		cardPanel.setVisible(false);
		pointPanel.setVisible(false);
		usingPointPanel.setVisible(false);
	}
	
	public String tiketingNumber() {		
		
	     Random random = new Random(System.currentTimeMillis());
	      
	     int tkNum = random.nextInt(10000000);
	     
	     return "" + tkNum;
	}

	
	public ArrayList<SeatDTO> getSeatArr(){
		return arr;
	}
}
