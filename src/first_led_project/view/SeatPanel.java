package first_led_project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.SeatDAO;
import first_led_project.dto.SeatDTO;
import first_led_project.dto.ShowTimeDTO;
import first_led_project.view.customComp.PrevBtnCustom;
import first_led_project.view.customComp.NextBtnCustom;

public class SeatPanel extends JPanel {

	JPanel SelectSeatPanel;
	int selectedSeatCount;
	int maxCount = 0;
	JButton seatNextBtn;
	ShowTimeDTO showtimeDTO;
	ArrayList<SeatDTO> seatArr = new ArrayList();

	public SeatPanel() {
		init();
		start();
	}

	private void init() {
		setBounds(0, 0, 1384, 861);
		setBackground(Color.BLACK);
		setLayout(null);
	}

	public void start() {
		JLabel screenLabel = new JLabel("S C R E E N");
		screenLabel.setOpaque(true);
		screenLabel.setBorder(new LineBorder(Color.WHITE, 3));
		screenLabel.setForeground(Color.WHITE);
		screenLabel.setBackground(Color.GRAY);
		screenLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 30));
		screenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		screenLabel.setSize(500,60);
		screenLabel.setLocation((getWidth() - screenLabel.getWidth()) / 2, 85); // 가로 가운데 맞춤
		add(screenLabel);

		SelectSeatPanel = new JPanel();
		SelectSeatPanel.setBackground(new Color(0, 0, 0));
		SelectSeatPanel.setBounds(243, 220, 900, 517);
		add(SelectSeatPanel);
		SelectSeatPanel.setLayout(new GridLayout(4, 10, 10, 10));

		JButton backBtn = new PrevBtnCustom();
		backBtn.setLocation(12,10);
		backBtn.putClientProperty("id", "seatBack");
		backBtn.addActionListener(new BtnController());
		add(backBtn);

		seatNextBtn = new NextBtnCustom();
		seatNextBtn.setEnabled(false);
		seatNextBtn.setLocation(1235, 459);
		seatNextBtn.putClientProperty("id", "seatNext");
		seatNextBtn.addActionListener(new BtnController());
		add(seatNextBtn);
		
		JLabel door = new JLabel("New label");
		door.setIcon(new ImageIcon("./img/icon/door.png"));
		door.setBounds(1170, 690, 50, 40);
		add(door);
		
		JLabel door_1 = new JLabel("New label");
		door_1.setIcon(new ImageIcon("./img/icon/door.png"));
		door_1.setBounds(163, 220, 50, 40);
		add(door_1);
	}

	public void updateSeat(ShowTimeDTO dto, int num) {
		this.showtimeDTO = dto;
		ArrayList<String> arr = SeatDAO.getInstance().selectSeat(dto);
		maxCount = num;
		for (char c = 'A'; c < 'E'; c++) {
			for (int i = 1; i <= 10; i++) {
				JToggleButton button = new JToggleButton(("" + c) + i);
				button.setForeground(Color.white);
				button.setBackground(Color.black);
				button.setBorder(new LineBorder(Color.white, 1));
				//
				maxCount = num;
				if (!arr.isEmpty()) {
					if (arr.contains(button.getText())) {
						button.setEnabled(false);
						button.setBackground(Color.gray);
						button.setIcon(new ImageIcon("./img/icon/lock1.png"));
						button.setHorizontalTextPosition(SwingConstants.CENTER);
//						button.setText(" ");
					}
				}
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (button.isSelected()) {
							selectedSeatCount++;
							// button.setBackground(Color.LIGHT_GRAY);
							SeatDTO seatDTO = new SeatDTO();
	                        seatDTO.setShowtime_id(showtimeDTO.getShowtime_id());
	                        seatDTO.setSeat_name(button.getText());
	                        seatArr.add(seatDTO);
							if (selectedSeatCount == maxCount) {
								JOptionPane.showMessageDialog(SelectSeatPanel, "모든 좌석이 선택되었습니다.");
								seatNextBtn.setEnabled(true);

								// 클릭된 버튼이 아닌 버튼들 비활성화
								Component[] components = SelectSeatPanel.getComponents();
								for (Component c : components) {
									if (c instanceof JToggleButton) {
										JToggleButton toggleButton = (JToggleButton) c;
										if (!toggleButton.isSelected()) {
											toggleButton.setEnabled(false);
											toggleButton.setBackground(Color.gray);
										}
									}
								}
							}
						} else {
							selectedSeatCount--;
							if(selectedSeatCount < maxCount) {
								seatNextBtn.setEnabled(false);
							}
							
							for(SeatDTO sDTO : seatArr) {
	                    		if(sDTO.getSeat_name().equals(button.getText())) {
	                    			seatArr.remove(sDTO);
	                    			break;
	                    		}
	                    	}

							// 활성화 되어있는 버튼을 다시 클릭하면 비활성화된 버튼을 활성화 시켜줌
							Component[] components = SelectSeatPanel.getComponents();
							for (Component c : components) {
								if (c instanceof JToggleButton) {
									JToggleButton toggleButton = (JToggleButton) c;
									if (!toggleButton.isSelected()&&!arr.contains(toggleButton.getText())) {
										toggleButton.setEnabled(true);
										toggleButton.setBackground(Color.black);
									}
								}
							}
						}
					}
				});
				SelectSeatPanel.add(button);
			}
		}
	}
	
	public ArrayList<SeatDTO> getSelectedSeat(){
		return seatArr;
	}
	
	public void seatArrRemove() {
		seatArr.clear();
	}
	
	public void seatRemoveAll() {
		for(Component comp : SelectSeatPanel.getComponents()) {
			SelectSeatPanel.remove(comp);
		}
		seatNextBtn.setEnabled(false);
		selectedSeatCount = 0;
	}
}
