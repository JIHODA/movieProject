package first_led_project.view.customComp;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class MovieInfoScroll extends JScrollPane {
    private int startY;

    public MovieInfoScroll(Component view) {
        super(view);

        MouseAdapter adapter = new MyMouseAdapter();

        getViewport().getView().addMouseListener(adapter);
        getViewport().getView().addMouseMotionListener(adapter);
    }

    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            startY = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Rectangle viewRect = getViewport().getViewRect();

            int minY = 0;
            int maxY = getViewport().getViewSize().height - viewRect.height;

            int dy = startY - e.getY();

            JViewport viewport = getViewport();
            Point viewPosition = viewport.getViewPosition();
            viewPosition.translate(0, dy);

            if (viewPosition.y < minY) {
                viewPosition.y = minY;
            }
            if (viewPosition.y > maxY) {
                viewPosition.y = maxY;
            }

            viewport.setViewPosition(viewPosition);

            startY = e.getY();
        }
    }
}
