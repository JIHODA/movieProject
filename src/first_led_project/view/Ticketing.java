package first_led_project.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.ScreenDAO;
import first_led_project.dto.MovieDTO;
import first_led_project.dto.ShowTimeDTO;
import first_led_project.util.Util;
import first_led_project.view.customComp.PrevBtnCustom;

public class Ticketing extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel layout = new JPanel();
	private JLabel poster = new JLabel("포스터");
	private JPanel personPanel;
	private JButton seatSelectBtn;
	private ButtonGroup butGroup1 = new ButtonGroup();
	private ButtonGroup butGroup2 = new ButtonGroup();
	private ButtonGroup butGroup3 = new ButtonGroup();
	private JPanel datePanel;
	private Util util;
	private ButtonGroup dateBtnG;
	private ButtonGroup timeBtnG;
	int timeY = 0;
	JPanel timePanel;
	ScreenDAO screenDAO;
	MovieDTO movie;
	ArrayList<ShowTimeDTO> showTimes;
	private ButtonGroup[] btnGroups = { butGroup1, butGroup2, butGroup3 };

	int adul_count = 0;
	int teen_count = 0;
	int trea_count = 0;
	int total_count = 0;

	int adul_total = 0;
	int teen_total = 0;
	int trea_total = 0;

	int adul_cash = 12000;
	int teen_cash = 10000;
	int trea_cash = 8000;
	int total_cash = 0;

	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;

	public Ticketing() {
		util = new Util();
		screenDAO = ScreenDAO.getInstance();
		setSize(new Dimension(1384, 861));
		setBackground(Color.BLACK);
		setLayout(null);

		JButton backBtn = new PrevBtnCustom();
		backBtn.setLocation(12, 10);
		add(backBtn);
		backBtn.putClientProperty("id", "ticketingBack");
		backBtn.addActionListener(new BtnController());

		layout = new JPanel();
		layout.setBackground(Color.BLACK);
		layout.setBounds(25, 21, 1332, 810);
		add(layout);
		layout.setLayout(null);

		timePanel = new JPanel();
		timePanel.setBackground(Color.BLACK);
		timePanel.setBounds(702, 78, 150, 731);
		layout.add(timePanel);
		timePanel.setLayout(null);

		poster = new JLabel("포스터");
		poster.setOpaque(true);
		poster.setBounds(0, 43, 523, 757);
		poster.setBackground(Color.CYAN);
		layout.add(poster);

		datePanel = new JPanel();
		datePanel.setBackground(Color.BLACK);
		datePanel.setBounds(540, 78, 150, 731);
		layout.add(datePanel);
		datePanel.setLayout(null);

		personPanel = new JPanel();
		personPanel.setOpaque(true);
		personPanel.setBounds(862, 43, 444, 266);
		personPanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 255, 255)));
		personPanel.setBackground(new Color(0, 0, 0));
		layout.add(personPanel);
		personPanel.setLayout(null);
		// 인원 선택 창 숨김
		personPanel.setVisible(false);

		JLabel adult = new JLabel("성  인");
		adult.setForeground(new Color(255, 255, 255));
		adult.setBounds(12, 72, 57, 15);
		adult.setHorizontalAlignment(SwingConstants.CENTER);
		adult.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		personPanel.add(adult);

		JLabel teen = new JLabel("청소년");
		teen.setForeground(new Color(255, 255, 255));
		teen.setBounds(12, 132, 57, 15);
		teen.setHorizontalAlignment(SwingConstants.CENTER);
		teen.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		personPanel.add(teen);

		JLabel treatment = new JLabel("우  대");
		treatment.setForeground(new Color(255, 255, 255));
		treatment.setBounds(12, 192, 57, 15);
		treatment.setHorizontalAlignment(SwingConstants.CENTER);
		treatment.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		personPanel.add(treatment);

		JPanel adul_select = new JPanel();
		adul_select.setBounds(86, 62, 346, 35);
		personPanel.add(adul_select);
		adul_select.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel teen_select = new JPanel();
		teen_select.setBounds(86, 122, 346, 35);
		personPanel.add(teen_select);
		teen_select.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel trea_select = new JPanel();
		trea_select.setBounds(86, 182, 346, 35);
		personPanel.add(trea_select);
		trea_select.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel cou_Sel = new JLabel("인원선택");
		cou_Sel.setOpaque(true);
		cou_Sel.setHorizontalAlignment(SwingConstants.CENTER);
		cou_Sel.setForeground(new Color(238, 238, 238));
		cou_Sel.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		cou_Sel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		cou_Sel.setBackground(new Color(100, 100, 100));
		cou_Sel.setBounds(0, 0, 444, 35);
		personPanel.add(cou_Sel);

		for (int i = 0; i < 9; i++) {
			JToggleButton adul_but = new JToggleButton();

			adul_but.setText("" + i);
			adul_but.setBackground(Color.WHITE);
			adul_but.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
			adul_but.setHorizontalAlignment(SwingConstants.CENTER);
			adul_but.setForeground(Color.black);
			adul_but.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					adul_count = Integer.parseInt(adul_but.getText());
					adul_total = adul_count * adul_cash;
					lblNewLabel.setText(adul_cash + " * " + adul_count + " = " + adul_total);
					total_cash = adul_total + teen_total + trea_total;
					lblNewLabel_3.setText(total_cash + " 원");
				}
			});
			adul_select.add(adul_but);
			butGroup1.add(adul_but);
		}

		for (int i = 0; i < 9; i++) {
			JToggleButton teen_but = new JToggleButton();

			teen_but.setText("" + i);
			teen_but.setBackground(Color.WHITE);
			teen_but.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
			teen_but.setHorizontalAlignment(SwingConstants.CENTER);
			teen_but.setForeground(Color.black);
			teen_but.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					teen_count = Integer.parseInt(teen_but.getText());
					teen_total = teen_count * teen_cash;
					lblNewLabel_1.setText(teen_cash + " * " + teen_count + " = " + teen_total);
					total_cash = adul_total + teen_total + trea_total;
					lblNewLabel_3.setText(total_cash + " 원");
				}
			});
			teen_select.add(teen_but);
			butGroup2.add(teen_but);
		}

		for (int i = 0; i < 9; i++) {
			JToggleButton trea_but = new JToggleButton();

			trea_but.setText("" + i);
			trea_but.setBackground(Color.WHITE);
			trea_but.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
			trea_but.setHorizontalAlignment(SwingConstants.CENTER);
			trea_but.setForeground(Color.black);
			trea_but.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					trea_count = Integer.parseInt(trea_but.getText());
					trea_total = trea_count * trea_cash;
					lblNewLabel_2.setText(trea_cash + " * " + trea_count + " = " + trea_total);
					total_cash = adul_total + teen_total + trea_total;
					lblNewLabel_3.setText(total_cash + " 원");
				}
			});
			trea_select.add(trea_but);
			butGroup3.add(trea_but);
		}

		JPanel cashPenel = new JPanel();
		cashPenel.setBackground(new Color(0, 0, 0));
		cashPenel.setBounds(862, 341, 444, 315);
		cashPenel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 255, 255)));
		layout.add(cashPenel);
		cashPenel.setLayout(null);

		JLabel adult_str = new JLabel("\uC131  \uC778");
		adult_str.setForeground(new Color(255, 255, 255));
		adult_str.setBounds(24, 75, 38, 17);
		adult_str.setHorizontalAlignment(SwingConstants.CENTER);
		adult_str.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		cashPenel.add(adult_str);

		JLabel teen_str = new JLabel("\uCCAD\uC18C\uB144");
		teen_str.setForeground(new Color(255, 255, 255));
		teen_str.setBounds(22, 135, 42, 17);
		teen_str.setHorizontalAlignment(SwingConstants.CENTER);
		teen_str.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		cashPenel.add(teen_str);

		JLabel trea_str = new JLabel("\uC6B0  \uB300");
		trea_str.setForeground(new Color(255, 255, 255));
		trea_str.setBounds(24, 195, 38, 17);
		trea_str.setHorizontalAlignment(SwingConstants.CENTER);
		trea_str.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		cashPenel.add(trea_str);

		JPanel adul_box = new JPanel();
		adul_box.setBounds(82, 66, 350, 35);
		cashPenel.add(adul_box);
		adul_box.setLayout(new GridLayout(1, 0, 0, 0));

		lblNewLabel = new JLabel("  12000 * 0 = 0");
		lblNewLabel.setBounds(new Rectangle(10, 0, 0, 0));
		lblNewLabel.setLocation(84, 0);
		adul_box.add(lblNewLabel);

		JPanel teen_box = new JPanel();
		teen_box.setBounds(82, 125, 350, 35);
		cashPenel.add(teen_box);
		teen_box.setLayout(new GridLayout(1, 0, 0, 0));

		lblNewLabel_1 = new JLabel("  10000 * 0 = 0");
		lblNewLabel_1.setBounds(new Rectangle(10, 0, 0, 0));
		lblNewLabel_1.setLocation(84, 0);
		teen_box.add(lblNewLabel_1);

		JPanel trea_box = new JPanel();
		trea_box.setBounds(82, 185, 350, 35);
		cashPenel.add(trea_box);
		trea_box.setLayout(new GridLayout(1, 0, 0, 0));

		lblNewLabel_2 = new JLabel("  8000 * 0 = 0");
		lblNewLabel_2.setBounds(new Rectangle(10, 0, 0, 0));
		lblNewLabel_2.setLocation(84, 0);
		trea_box.add(lblNewLabel_2);

		JLabel total_str = new JLabel("\uCD1D  \uC561");
		total_str.setForeground(new Color(255, 255, 255));
		total_str.setHorizontalAlignment(SwingConstants.CENTER);
		total_str.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		total_str.setBounds(24, 255, 38, 17);
		cashPenel.add(total_str);

		JPanel total_box = new JPanel();
		total_box.setBounds(82, 245, 350, 35);
		cashPenel.add(total_box);
		total_box.setLayout(null);

		lblNewLabel_3 = new JLabel("  0 \uC6D0");
		lblNewLabel_3.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_3.setBounds(0, 0, 350, 35);
		total_box.add(lblNewLabel_3);

		
		JLabel cashStr = new JLabel("금   액");
		cashStr.setOpaque(true);
		cashStr.setHorizontalAlignment(SwingConstants.CENTER);
		cashStr.setBackground(new Color(100, 100, 100));
		cashStr.setForeground(new Color(238, 238, 238));
		cashStr.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		cashStr.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		cashStr.setBounds(0, 0, 444, 35);
		cashPenel.add(cashStr);

		JLabel dateStr = new JLabel("일 자");
		dateStr.setBounds(540, 43, 150, 35);
		layout.add(dateStr);
		dateStr.setOpaque(true);
		dateStr.setBackground(new Color(100, 100, 100));
		dateStr.setHorizontalAlignment(SwingConstants.CENTER);
		dateStr.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		dateStr.setForeground(new Color(238, 238, 238));
		dateStr.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		dateStr.setBackground(new Color(100, 100, 100));
		dateStr.setForeground(new Color(238, 238, 238));

		JLabel timeStr = new JLabel("시 간");
		timeStr.setBounds(702, 43, 150, 35);
		layout.add(timeStr);
		timeStr.setBackground(new Color(100, 100, 100));
		timeStr.setForeground(new Color(238, 238, 238));
		timeStr.setOpaque(true);
		timeStr.setHorizontalAlignment(SwingConstants.CENTER);
		timeStr.setFont(new Font("HY견고딕", Font.PLAIN, 14));
		timeStr.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		seatSelectBtn = new JButton();
		seatSelectBtn.setBorder(null);
		seatSelectBtn.setBounds(1012, 706, 150, 50);
		seatSelectBtn.setIcon(new ImageIcon("./img/icon/seatChoice.png"));
		seatSelectBtn.setHorizontalAlignment(SwingConstants.CENTER);
		layout.add(seatSelectBtn);
		seatSelectBtn.setFont(new Font("굴림", Font.BOLD, 14));
		seatSelectBtn.putClientProperty("id", "ticketingSeatBtn");
		seatSelectBtn.setBackground(Color.black);
		seatSelectBtn.setEnabled(false);
		seatSelectBtn.addActionListener(new BtnController());
	}

	public void setDate() {
		Set<String> dateSet = new HashSet<String>();
		for (ShowTimeDTO stDTO : showTimes) {
			dateSet.add(stDTO.getScreen_date().toString());
		}
		ArrayList<String> arr = new ArrayList<String>(dateSet);
		Collections.sort(arr);

		for (int i = 0; i < arr.size(); i++) {
			JToggleButton button = new JToggleButton(arr.get(i));
			button.setBounds(0, i * 50, 150, 50);
			button.setBackground(Color.white);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeTime();
					JToggleButton button = (JToggleButton) e.getSource();
					String date = button.getText();

					for (ShowTimeDTO stDTO : showTimes) {
						if (stDTO.getScreen_date().toString().equals(date)) {
							JToggleButton timeBtn = new JToggleButton();
							timeBtn = new JToggleButton(
									stDTO.getScreen_id() + " / " + stDTO.getScreen_time().toString());
							timeBtn.setBounds(0, timeY * 50, 150, 50);
							timeBtn.setBackground(Color.white);
							timeBtn.setHorizontalAlignment(JToggleButton.CENTER);
							timeBtnG.add(timeBtn);
							timePanel.add(timeBtn);
							timePanel.repaint();
							timeY++;

							// ActionListener for each time button
							timeBtn.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									seatSelectBtn.setEnabled(true);
									personPanel.setVisible(true);
								}
							});
						}
					}
					timeY = 0;
				}
			});
			dateBtnG.add(button);
			datePanel.add(button);
		}
	}

	public void ticketingUpdate(MovieDTO dto) {
		this.movie = dto;
		this.showTimes = screenDAO.getShowTime(dto);
		dateBtnG = new ButtonGroup();
		timeBtnG = new ButtonGroup();

		poster.setIcon(util.reSizeIcon(new ImageIcon("./img/poster/poster_" + dto.getRank() + ".jpg"), 523, 757));
		removeDate();
		setDate();
	}

	public void removeDate() {
		datePanel.removeAll();
		timePanel.removeAll();
	}

	public void removeTime() {
		timePanel.removeAll();
		personPanel.setVisible(false);
	}

	public ShowTimeDTO selected() {
		String date = selectedBtnText(dateBtnG);
		String time = selectedBtnText(timeBtnG);
		ShowTimeDTO showTimeDTO = null;
		time = time.substring(time.indexOf('/') + 2);

		for (ShowTimeDTO stDTO : showTimes) {
			if (stDTO.getScreen_date().toString().equals(date)) {
				if (stDTO.getScreen_time().toString().equals(time)) {
					showTimeDTO = stDTO;
				}
			}
		}
		return showTimeDTO;
	}

	public int selectNumber() {
		total_count = adul_count + teen_count + trea_count;
		PanelController.getInstane().getUser().setPrice(total_cash);
		selectedBtn(btnGroups);
		seatSelectBtn.setEnabled(false);
		personPanel.setVisible(false);
		return total_count;
	}

	public void selectedBtn(ButtonGroup[] btnGroup) {
		for (int i = 0; i < btnGroup.length; i++) {
			Enumeration<AbstractButton> buttonGroup = btnGroup[i].getElements();
			while (buttonGroup.hasMoreElements()) {
				AbstractButton button = buttonGroup.nextElement();
				if (button.getText().equals("0"))
					button.doClick();
			}
		}
	}

	public boolean selectCheck(ButtonGroup[] btnGroup) {
		Boolean[] bool = new Boolean[3];
		for (int i = 0; i < btnGroup.length; i++) {
			Enumeration<AbstractButton> buttonGroup = btnGroup[i].getElements();
			while (buttonGroup.hasMoreElements()) {
				AbstractButton button = buttonGroup.nextElement();
				if (button.isSelected()) {
					if (button.getText().equals("0")) {
						bool[i] = false;
						break;
					} else if (!button.getText().equals("0")) {
						bool[i] = true;
						break;
					}
				}
				bool[i] = false;
			}
		}

		if ((bool[0] || bool[1] || bool[2])) {
			return true;
		} else {
			return false;
		}
	}

	// 버튼그룹을 넘겨주면 그 그룹내에서 선택된 버튼의 텍스트를 리턴
	private String selectedBtnText(ButtonGroup btnGroup) {
		Enumeration<AbstractButton> buttonGroup = btnGroup.getElements();
		String text = null;
		while (buttonGroup.hasMoreElements()) {
			AbstractButton button = buttonGroup.nextElement();
			if (button.isSelected()) {
				text = button.getText();
			}
		}
		return text;
	}

	public boolean getSelectCheck() {
		return selectCheck(btnGroups);
	}
}