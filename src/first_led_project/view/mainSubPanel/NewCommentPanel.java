package first_led_project.view.mainSubPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import first_led_project.controller.PanelController;
import first_led_project.dao.CommentDAO;
import first_led_project.dto.CommentDTO;
import first_led_project.util.Util;

public class NewCommentPanel extends JPanel implements ActionListener{
	JButton reWriteBtn;
	JButton deleteBtn;
	JTextField editorPane;
	JButton btnNewButton;
	CommentDAO commentDAO = CommentDAO.getInstance();
	CommentDTO dto;
	MovieInfo movieInfo = PanelController.getInstane().getMovieInfo();
	
	public NewCommentPanel(CommentDTO dto) {
		this.dto = dto;
		setPreferredSize(new Dimension(400, 210));
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLACK,2));
		setLayout(null);
		
		JLabel label = new JLabel();
		label.setBounds(20, 30, 100, 100);
		label.setIcon(new Util().reSizeIcon(new ImageIcon(dto.getMember_profile()), 100, 100));
		add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBorder(null);
		lblNewLabel.setBounds(144, 30, 170, 21);
		lblNewLabel.setText(dto.getMember_id());
		add(lblNewLabel);
		
		editorPane = new JTextField();
		editorPane.setFont(new Font("굴림", Font.PLAIN, 14));
		editorPane.setHorizontalAlignment(SwingConstants.LEFT);
		editorPane.setDisabledTextColor(Color.BLACK);
		editorPane.setBackground(Color.WHITE);
		editorPane.setEnabled(false);
		editorPane.setEditable(false);
		editorPane.setBorder(null);
		editorPane.setBounds(144, 55, 252, 29);
		editorPane.setText(dto.getContents());
		add(editorPane);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setBounds(144, 183, 124, 21);
		lblNewLabel_1.setText(dto.getWrite_date().toString());
		add(lblNewLabel_1);
		
		JLabel minuteCount = new JLabel("");
		minuteCount.setBounds(339, 10, 57, 21);
		minuteCount.setFont(new Font("굴림", Font.PLAIN, 12));
		int count = dto.getMinuteCount();
		if(count < 3) {
			minuteCount.setText("방금 전");
		}else if(count < 60){
			minuteCount.setText(count+"분 전");
		}else if(count < 1440){
			minuteCount.setText((count/60)+"시간 전");
		}else {
			minuteCount.setText((count/1440)+"일 전");
		}
		add(minuteCount);
		
		reWriteBtn = new JButton("\uC218\uC815");
		reWriteBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		reWriteBtn.setBounds(270, 171, 57, 35);
		reWriteBtn.setVisible(false);
		reWriteBtn.addActionListener(this);
		add(reWriteBtn);
		
		deleteBtn = new JButton("\uC0AD\uC81C");
		deleteBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		deleteBtn.setBounds(331, 171, 57, 35);
		deleteBtn.setVisible(false);
		deleteBtn.addActionListener(this);
		add(deleteBtn);
		
		btnNewButton = new JButton("\uC644\uB8CC");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 11));
		btnNewButton.setBounds(270, 171, 57, 35);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(this);
		add(btnNewButton);
		
		if(PanelController.getInstane().getUser() != null) {
			if(dto.getMember_id().equals(PanelController.getInstane().getUser().getMember_id())) {
				reWriteBtn.setVisible(true);
				deleteBtn.setVisible(true);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn =(JButton) e.getSource();
		if(btn.equals(reWriteBtn)) {
			editorPane.setEnabled(true);
			editorPane.setEditable(true);
			editorPane.setBackground(Color.GRAY);
			btnNewButton.setVisible(true);
			reWriteBtn.setVisible(false);
		}
		if(btn.equals(deleteBtn)) {
			commentDAO.deleteComment(dto);
			JOptionPane.showMessageDialog(getComponentPopupMenu(), "삭제되었습니다.");
			movieInfo.commentInit();
		}
		if(btn.equals(btnNewButton)) {
			CommentDTO updateDTO = new CommentDTO();
			updateDTO.setContents(editorPane.getText());
			updateDTO.setMember_id(dto.getMember_id());
			updateDTO.setMovie_id(dto.getMovie_id());
			commentDAO.updateComment(updateDTO);
			JOptionPane.showMessageDialog(getComponentPopupMenu(), "수정되었습니다.");
			editorPane.setEnabled(false);
			editorPane.setEditable(false);
			editorPane.setBackground(Color.WHITE);
			btnNewButton.setVisible(false);
			reWriteBtn.setVisible(true);
		}
	}
}
