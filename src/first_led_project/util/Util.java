package first_led_project.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Util {
	// ImageIcon과 너비, 높이를 주면 그 사이즈로 변경 후 리턴
	public ImageIcon reSizeIcon(ImageIcon icon,int width, int height) {
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		return changeIcon;
	}
}
