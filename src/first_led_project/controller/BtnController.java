package first_led_project.controller;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import first_led_project.view.Complete;
import first_led_project.view.Join;
import first_led_project.view.Login;
import first_led_project.view.MyPage;
import first_led_project.view.Payment;
import first_led_project.view.SeatPanel;
import first_led_project.view.Ticketing;
import first_led_project.view.mainSubPanel.MovieInfo;

public class BtnController implements ActionListener, MouseListener{
	PanelController pc =PanelController.getInstane();
	JPanel panel;
	
	public BtnController() {
	}
	public BtnController(JPanel panel) {
		this.panel = panel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JLabel ) {
			JLabel label = (JLabel)e.getSource();
			String labelId = (String) label.getClientProperty("id");
			
			if(labelId.equals("loginJoin")) {
				pc.getLogin().setVisible(false);
				pc.getMainPane().add(pc.getJoin());
				pc.getJoin().setVisible(true);
			}
			if(labelId.equals("login")) {
				Login login = pc.getLogin();
				pc.getMainPane().add(login);
				pc.getCmPanel().setVisible(false);
				login.setVisible(true);
			}
			if(labelId.equals("regist")) {
				Join join = pc.getJoin();
				pc.getMainPane().add(join);
				pc.getCmPanel().setVisible(false);
				join.setVisible(true);
			}
			if(labelId.equals("logout")) {
				JOptionPane.showMessageDialog(pc.getJFrame(), "로그아웃되었습니다.");
				pc.getCmPanel().LogoutUser();
				pc.setUser(null);
				pc.getLogin().clearField();
			}
			if(labelId.equals("myPage")) {
				MyPage myPage = pc.getMyPage();
				myPage.setUserInfo(pc.getUser()); 
				pc.getMainPane().add(myPage);
				pc.getCmPanel().setVisible(false);
				myPage.setVisible(true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() instanceof JLabel ) {
			JLabel label = (JLabel)e.getSource();
			String labelId = (String) label.getClientProperty("id");
			
			if(labelId.equals("login")) {
				label.setIcon(new ImageIcon("./img/icon/login2.png"));
			}
			if(labelId.equals("logout")) {
				label.setIcon(new ImageIcon("./img/icon/logOut2.png"));
			}
			if(labelId.equals("regist")) {
				label.setIcon(new ImageIcon("./img/icon/join2.png"));
			}
			if(labelId.equals("myPage")) {
				label.setIcon(new ImageIcon("./img/icon/myPage2.png"));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() instanceof JLabel ) {
			JLabel label = (JLabel)e.getSource();
			String labelId = (String) label.getClientProperty("id");
			
			if(labelId.equals("login")) {
				label.setIcon(new ImageIcon("./img/icon/login1.png"));
			}
			if(labelId.equals("logout")) {
				label.setIcon(new ImageIcon("./img/icon/logOut1.png"));
			}
			if(labelId.equals("regist")) {
				label.setIcon(new ImageIcon("./img/icon/join1.png"));
			}
			if(labelId.equals("myPage")) {
				label.setIcon(new ImageIcon("./img/icon/myPage1.png"));
			}
		}
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton ) {
			JButton button = (JButton)e.getSource();
			String btnId = (String) button.getClientProperty("id");
			
			
			if(btnId.equals("loginBack")) {
				Login login = pc.getLogin();
				login.setVisible(false);
				pc.getCmPanel().setVisible(true);
			}
			if(btnId.equals("registBack")) {
				Join join = pc.getJoin();
				join.clearFile();
				pc.getCmPanel().setVisible(true);
				join.setVisible(false);
			}
			if(btnId.equals("myPageBack")) {
				MyPage myPage = pc.getMyPage();
				pc.getCmPanel().setVisible(true);
				myPage.removeHistory();
				myPage.setVisible(false);
				myPage.profileChoiceBye();
			}
			if(btnId.equals("infoTicketing")) {
				if(pc.getUser() == null) {
					JOptionPane.showMessageDialog(pc.getJFrame(), "로그인 후 이용해주세요.");
					return;
				}
				Ticketing ticketing = pc.getTicketing();
				pc.getMainPane().add(ticketing);
				pc.getMovieInfo().setVisible(false);
				ticketing.setVisible(true);
			}
			if(btnId.equals("ticketingBack")) {
				Ticketing ticketing = pc.getTicketing();
				pc.getMovieInfo().setVisible(true);
				ticketing.removeTime();
				ticketing.setVisible(false);
			}
			if(btnId.equals("infoMainBack")) {
				MovieInfo movieInfo = pc.getMovieInfo();
				pc.getCmPanel().setVisible(true);
				movieInfo.remove();
				movieInfo.setVisible(false);
			}
			if(btnId.equals("ticketingSeatBtn")) {
				System.out.println(pc.getTicketing().getSelectCheck());
				if(!pc.getTicketing().getSelectCheck()) {
					JOptionPane.showMessageDialog(pc.getJFrame(), "인원을 선택해주 세요.");
					return;
				}
				pc.getTicketing().removeTime();
				SeatPanel seatPanel = pc.getSeatPanel();
				pc.updateSeat(pc.getTicketing().selected());
				pc.getMainPane().add(seatPanel);
				pc.getSeatPanel().setVisible(true);
				pc.getTicketing().setVisible(false);
			}
			if(btnId.equals("seatBack")) {
				SeatPanel seatPanel = pc.getSeatPanel();
				Ticketing ticketing = pc.getTicketing();
				seatPanel.setVisible(false);
				seatPanel.seatRemoveAll();
				seatPanel.seatArrRemove();
				ticketing.setVisible(true);
			}
			if(btnId.equals("seatNext")) {
				SeatPanel seatPanel = pc.getSeatPanel();
				Payment payment = pc.getPayment();
				payment.update(seatPanel.getSelectedSeat(),pc.getTicketing().selected());
				seatPanel.setVisible(false);
				pc.getMainPane().add(payment);
				payment.setVisible(true);
			}
			if(btnId.equals("sucessCheck")) {
				Complete complete = pc.getComplete();
				pc.getMovieInfo().remove();
				pc.getPayment().reset();
				pc.getSeatPanel().seatRemoveAll();
				pc.getSeatPanel().seatArrRemove();
				complete.screenshot();
				complete.setVisible(false);
				pc.getCmPanel().setVisible(true);
			}
			if(btnId.equals("paymentBack")) {
				SeatPanel seatpanel = pc.getSeatPanel();
				Payment payment = pc.getPayment();
				payment.setVisible(false);
				seatpanel.setVisible(true);
			}
		}
	}
}
