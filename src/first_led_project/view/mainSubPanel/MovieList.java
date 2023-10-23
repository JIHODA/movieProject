package first_led_project.view.mainSubPanel;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class MovieList extends JPanel {
	
	public MovieList() {
		init();
		start();
	}
	
	public void init() {
		setBounds(0, 0, 1384, 861);
		setBackground(Color.BLACK);
		setLayout(null);
	}
	
	public void start(){
		JPanel panel = new JPanel();
		panel.setBounds(12, 162, 1360, 689);
		add(panel);
		panel.setLayout(null);
	}
}
