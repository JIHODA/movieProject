package first_led_project.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Intro extends JFrame{
	public Intro() {
		setBounds(0, 0, 1400, 900);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("./img/intro.gif"));
		lblNewLabel.setBounds(0, 0, 1384, 861);
		getContentPane().add(lblNewLabel);
		
		setVisible(true);
	}
}
