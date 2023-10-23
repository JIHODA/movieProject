package first_led_project.view.customComp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class NextBtnCustom extends JButton{

	public NextBtnCustom() {
		setUI(new BasicButtonUI() {
	        @Override
	        protected void paintButtonPressed(Graphics g, AbstractButton b) {
	            // 버튼이 눌렸을 때의 배경색을 검은색으로 유지
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, b.getWidth(), b.getHeight());
	        }
	    });
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.BLACK);
		setSize(50, 50);
		setBorder(null);
		setIcon(new ImageIcon("./img/icon/nextBtn1.png"));
		addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Color.BLACK);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
						setIcon(new ImageIcon("./img/icon/nextBtn2.png"));
					
			}

			@Override
			public void mouseExited(MouseEvent e) {
					setIcon(new ImageIcon("./img/icon/nextBtn1.png"));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
