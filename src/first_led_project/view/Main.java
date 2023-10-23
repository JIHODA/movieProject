package first_led_project.view;

import first_led_project.controller.PanelController;

public class Main {
	private static MainFrame mainFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
		PanelController.getInstane().setJFrame(mainFrame);
		
		Intro intro = new Intro();
		
		try {
			Thread.sleep(4000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		intro.dispose();
		
		mainFrame.setVisible(true);
	}
}