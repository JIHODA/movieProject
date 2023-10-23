package first_led_project.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.PaymentDAO;
import first_led_project.dto.SucessDTO;
import first_led_project.dto.UserDTO;
import first_led_project.util.Util;


public class Complete extends JPanel {

	JLabel complete_Name;
	JLabel complete_Movie;
	JLabel complete_Cinema;
	JLabel complete_Screen_Seat;
	JLabel complete_Date;
	JLabel complete_Person;
	JLabel complete_Price;
	JLabel tiketPoster;
	JLabel qrCord;
	JButton tiketcancel;
	SucessDTO sdto;
	UserDTO user = new UserDTO();
	Util util = new Util();
	PanelController pc;
	private int pointBack;


	public Complete() {
		setBackground(new Color(0, 0, 0));
		setSize(1384, 861);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC608\uB9E4\uAC00 \uC644\uB8CC \uB418\uC5C8\uC2B5\uB2C8\uB2E4!");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(12, 10, 1360, 139);
		add(lblNewLabel);
		
		tiketPoster = new JLabel("");
		tiketPoster.setHorizontalAlignment(SwingConstants.CENTER);
		tiketPoster.setOpaque(true);
		tiketPoster.setBackground(new Color(240, 240, 240));
		tiketPoster.setBounds(62, 159, 448, 668);
		add(tiketPoster);
		
		JLabel complete_NameLabel = new JLabel("\uC608\uB9E4\uC790");
		complete_NameLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_NameLabel.setOpaque(true);
		complete_NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_NameLabel.setForeground(Color.WHITE);
		complete_NameLabel.setBackground(Color.BLACK);
		complete_NameLabel.setBounds(682, 160, 131, 36);
		add(complete_NameLabel);
		
		JLabel complete_MovieLabel = new JLabel("\uC601\uD654");
		complete_MovieLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_MovieLabel.setOpaque(true);
		complete_MovieLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_MovieLabel.setForeground(Color.WHITE);
		complete_MovieLabel.setBackground(Color.BLACK);
		complete_MovieLabel.setBounds(682, 220, 131, 36);
		add(complete_MovieLabel);
		
		JLabel complete_CinemaLabel = new JLabel("\uADF9\uC7A5");
		complete_CinemaLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_CinemaLabel.setOpaque(true);
		complete_CinemaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_CinemaLabel.setForeground(Color.WHITE);
		complete_CinemaLabel.setBackground(Color.BLACK);
		complete_CinemaLabel.setBounds(682, 280, 131, 36);
		add(complete_CinemaLabel);
		
		JLabel complete_Screen_SeatLabel = new JLabel("\uC0C1\uC601\uAD00 / \uC88C\uC11D");
		complete_Screen_SeatLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_Screen_SeatLabel.setOpaque(true);
		complete_Screen_SeatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Screen_SeatLabel.setForeground(Color.WHITE);
		complete_Screen_SeatLabel.setBackground(Color.BLACK);
		complete_Screen_SeatLabel.setBounds(682, 340, 131, 36);
		add(complete_Screen_SeatLabel);
		
		JLabel complete_DateLabel = new JLabel("\uC0C1\uC601 \uB0A0\uC9DC");
		complete_DateLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_DateLabel.setOpaque(true);
		complete_DateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_DateLabel.setForeground(Color.WHITE);
		complete_DateLabel.setBackground(Color.BLACK);
		complete_DateLabel.setBounds(682, 400, 131, 36);
		add(complete_DateLabel);
		
		JLabel complete_PersonLabel = new JLabel("\uC778\uC6D0");
		complete_PersonLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_PersonLabel.setOpaque(true);
		complete_PersonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_PersonLabel.setForeground(Color.WHITE);
		complete_PersonLabel.setBackground(Color.BLACK);
		complete_PersonLabel.setBounds(682, 460, 131, 36);
		add(complete_PersonLabel);
		
		JLabel complete_PriceLabel = new JLabel("\uAE08\uC561");
		complete_PriceLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		complete_PriceLabel.setOpaque(true);
		complete_PriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		complete_PriceLabel.setForeground(Color.WHITE);
		complete_PriceLabel.setBackground(Color.BLACK);
		complete_PriceLabel.setBounds(682, 520, 131, 36);
		add(complete_PriceLabel);
		
		complete_Name = new JLabel("");
		complete_Name.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Name.setOpaque(true);
		complete_Name.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Name.setForeground(Color.WHITE);
		complete_Name.setBackground(Color.BLACK);
		complete_Name.setBounds(913, 160, 325, 36);
		add(complete_Name);
		
		complete_Movie = new JLabel("");
		complete_Movie.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Movie.setOpaque(true);
		complete_Movie.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Movie.setForeground(Color.WHITE);
		complete_Movie.setBackground(Color.BLACK);
		complete_Movie.setBounds(913, 220, 325, 36);
		add(complete_Movie);
		
		complete_Cinema = new JLabel("");
		complete_Cinema.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Cinema.setOpaque(true);
		complete_Cinema.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Cinema.setForeground(Color.WHITE);
		complete_Cinema.setBackground(Color.BLACK);
		complete_Cinema.setBounds(913, 280, 325, 36);
		add(complete_Cinema);
		
		complete_Screen_Seat = new JLabel("");
		complete_Screen_Seat.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Screen_Seat.setOpaque(true);
		complete_Screen_Seat.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Screen_Seat.setForeground(Color.WHITE);
		complete_Screen_Seat.setBackground(Color.BLACK);
		complete_Screen_Seat.setBounds(913, 340, 325, 36);
		add(complete_Screen_Seat);
		
		complete_Date = new JLabel("");
		complete_Date.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Date.setOpaque(true);
		complete_Date.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Date.setForeground(Color.WHITE);
		complete_Date.setBackground(Color.BLACK);
		complete_Date.setBounds(913, 400, 325, 36);
		add(complete_Date);
		
		complete_Person = new JLabel("");
		complete_Person.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Person.setOpaque(true);
		complete_Person.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Person.setForeground(Color.WHITE);
		complete_Person.setBackground(Color.BLACK);
		complete_Person.setBounds(913, 460, 325, 36);
		add(complete_Person);
		
		complete_Price = new JLabel("");
		complete_Price.setFont(new Font("Gulim", Font.PLAIN, 12));
		complete_Price.setOpaque(true);
		complete_Price.setHorizontalAlignment(SwingConstants.CENTER);
		complete_Price.setForeground(Color.WHITE);
		complete_Price.setBackground(Color.BLACK);
		complete_Price.setBounds(913, 520, 325, 36);
		add(complete_Price);
		
		tiketcancel = new JButton("\uC608\uB9E4 \uCDE8\uC18C");
		tiketcancel.setOpaque(false);
		tiketcancel.setHorizontalAlignment(SwingConstants.CENTER);
		tiketcancel.setBackground(new Color(0, 0, 0));
		tiketcancel.setBounds(993, 775, 160, 44);
		tiketcancel.setBorder(null);
		tiketcancel.putClientProperty("id", "cancelTiket");
		tiketcancel.setIcon(new ImageIcon("./img/icon/movieX.png"));
		add(tiketcancel);
		tiketcancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(sdto.getPayingWhat() == "payPoint" &&
					PaymentDAO.getInstance().cancelTiket(sdto) == 1) {
					pc = PanelController.getInstane();
					 JOptionPane.showMessageDialog(getComponentPopupMenu(), "예매 취소가 완료 되었습니다.");
					 JOptionPane.showMessageDialog(getComponentPopupMenu(), "포인트를 반환합니다.");
					 user.setMember_id(pc.getUser().getMember_id());
					 user.setTotal_point(pc.getUser().getTotal_point() + pc.getUser().getPrice());
					 user.setUsing_point(pc.getUser().getPrice());
					 PaymentDAO.getInstance().updatePoint(user);
					 pc.getUser().setTotal_point(user.getTotal_point());
					 CinemaMainPanel cmpanel = pc.getCmPanel();
					 setVisible(false);
					 	pc.getMovieInfo().remove();
						pc.getPayment().reset();
						pc.getSeatPanel().seatRemoveAll();
						pc.getSeatPanel().seatArrRemove();
					 cmpanel.setVisible(true);
				}else if(sdto.getPayingWhat() == "payCard" &&
						PaymentDAO.getInstance().cancelTiket(sdto) == 1) {
					 JOptionPane.showMessageDialog(getComponentPopupMenu(), "예매 취소가 완료 되었습니다.");
					 pc = PanelController.getInstane();
					 user.setMember_id(pc.getUser().getMember_id());
					 user.setTotal_point((int)(pc.getUser().getTotal_point() - sdto.getMember_point()));
					 System.out.println(">><<" + sdto.getMember_point());
					 System.out.println("<<<<" + pc.getUser().getUsing_point());
					 user.setUsing_point(pc.getUser().getUsing_point());
					 System.out.println(">>>>" + user.getUsing_point());
					 System.out.println("<<>>" + user.getTotal_point());
					 pc.getUser().setTotal_point(user.getTotal_point() + user.getUsing_point());
					 user.setTotal_point(pc.getUser().getTotal_point());
					 System.out.println(">>->>-" + user.getTotal_point());
					 PaymentDAO.getInstance().updatePoint(user);
					 CinemaMainPanel cmpanel = pc.getCmPanel();
					 setVisible(false);
					 	pc.getMovieInfo().remove();
						pc.getPayment().reset();
						pc.getSeatPanel().seatRemoveAll();
						pc.getSeatPanel().seatArrRemove();
					 cmpanel.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "예매 취소 중 오류가 발생했습니다." );			
				}
				
				
			}
		});
		
		JButton check = new JButton("\uD655\uC778");
		check.setOpaque(false);
		check.setBackground(new Color(0, 0, 0));
		check.setBounds(663, 775, 160, 44);
		check.setBorder(null);
		check.setHorizontalAlignment(SwingConstants.CENTER);
		check.putClientProperty("id", "sucessCheck");
		check.addActionListener(new BtnController());
		check.setIcon(new ImageIcon("./img/icon/Ok.png"));
		add(check);
		
		qrCord = new JLabel("");
		qrCord.setBounds(1128, 600, 120, 120);
		qrCord.setIcon(util.reSizeIcon(new ImageIcon("./img/icon/qrIcon.png"), 120, 120));
		qrCord.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(qrCord);
		
		JLabel caption = new JLabel("<html>&nbsp;&nbsp;  <b>\uC608\uB9E4 \uC720\uC758\uC0AC\uD56D</b><br>\r  "
				+ "\r\n  <br>\r\uC0C1\uC601\uC2DC\uC791 \uC2DC\uAC04 30\uBD84\uC804\uC5D0\uB294 \uC608\uB9E4\uB97C \uCDE8\uC18C\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.<br>"
				+ "\r\r\n  <br>\uC0C1\uC601\uC774 \uC2DC\uC791\uB41C \uD6C4\uC5D0\uB294 \uC0C1\uC601\uAD00 \uC785\uC7A5\uC774 \uC5B4\uB824\uC6B8 \uC218 \uC788\uC2B5\uB2C8\uB2E4.<br>"
				+ "\r\r\n  <br>\uBC18\uB824\uB3D9\uBB3C\uC740 \uCD9C\uC785\uD560 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.<br>"
				+ "\r\r\n  <br>\uC0C1\uC601\uAD00 \uB0B4\uC5D0\uC11C \uC18C\uB780\uC744 \uC77C\uC73C\uD0AC \uACBD\uC6B0 \uD1F4\uAD00\uC870\uCE58 \uB420 \uC218 \uC788\uC74C\uC744 \uBBF8\uB9AC \uC548\uB0B4\uB4DC\uB9BD\uB2C8\uB2E4.\r\r\n</html>");
		caption.setFont(new Font("Gulim", Font.PLAIN, 12));
		caption.setBackground(new Color(0, 0, 0));
		caption.setForeground(new Color(255, 255, 255));
		caption.setOpaque(true);
		caption.setBounds(653, 566, 499, 199);
		add(caption);
	}
	public void updateComplet(SucessDTO dto) {
		
		this.sdto = dto;
		
		tiketPoster.setIcon(util.reSizeIcon(new ImageIcon("./img/poster/poster_" + dto.getMovie_rank() + ".jpg"), 448, 668));
		complete_Name.setText(dto.getUserName());
		complete_Movie.setText(dto.getMovieTitle());
		complete_Cinema.setText(dto.getCinema());
		complete_Screen_Seat.setText(dto.getScreen() + " / " + dto.getSeat_name());
		complete_Date.setText(dto.getScreen_date().toString() +" "+ dto.getScreen_time().toString());
		complete_Person.setText(dto.getPerson() + " 명");
		complete_Price.setText(dto.getPrice() + " 원");
	}
	
	public void screenshot() {
		try {
			Robot robot = new Robot();
		    String fileName = "./img/ticketing/"+sdto.getTiketnumber()+".png";
		    Rectangle screenRect = new Rectangle( PanelController.getInstane().getJFrame().getContentPane().getLocationOnScreen(), PanelController.getInstane().getComplete().getSize());
		    BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		    ImageIO.write(screenFullImage, "png", new File(fileName));
		    } catch (AWTException | IOException ex) {
		    System.err.println(ex);
		    }
	}
}
