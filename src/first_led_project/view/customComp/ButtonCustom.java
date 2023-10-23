package first_led_project.view.customComp;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class ButtonCustom extends JButton{
	private static final long serialVersionUID = 1L;

	public ButtonCustom(String text) {
		super(text);
		setUI(new BasicButtonUI() {
	        @Override
	        protected void paintButtonPressed(Graphics g, AbstractButton b) {
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, b.getWidth(), b.getHeight());
	        }
	    });
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBackground(Color.BLACK);
		setBorder(null);
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
				setBackground(Color.WHITE);
				setForeground(Color.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.BLACK);
				setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
