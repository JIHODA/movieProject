package first_led_project.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import first_led_project.controller.PanelController;

public class MainFrame extends JFrame {
	private PanelController ec;
	private JPanel mainPane;
	
	public MainFrame() {
		super("LED CINEMA");
		setIconImage(new ImageIcon("./img/icon/frameIcon.png").getImage());
		ec = PanelController.getInstane();
		init();
		start();
		ec.setMainPane(mainPane);
	}
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 900);
		setLocationRelativeTo(null);
		setResizable(false);
		mainPane = new JPanel();
		setContentPane(mainPane);
		mainPane.setLayout(null);
	}
	
	public void start(){
		mainPane.add(ec.getCmPanel());
		mainPane.add(ec.getMovieInfo());
		ec.getCmPanel().setVisible(true);
	}
	
}
