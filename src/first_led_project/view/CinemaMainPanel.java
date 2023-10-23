package first_led_project.view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicButtonUI;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.MovieDAO;
import first_led_project.dto.MovieDTO;
import first_led_project.util.Util;
import first_led_project.view.mainSubPanel.MovieInfo;

public class CinemaMainPanel extends JPanel implements ActionListener, MouseListener{
	MovieDAO movieDAO;
	Map<String, MovieDTO> movies = new HashMap();
	Util util = new Util();
	ImageIcon[] banners = new ImageIcon[5];
	JPanel[] posterPanels;
	PanelController ec;
	
	JLabel loginBtn;
	JLabel registBtn;
	
	JPanel bannerPanel;
	JPanel posterPanel;
	JPanel posterNamePanel;
	private JLabel myInfoBtn;
	
	private int currentIndex = 0;
    private int posterCount = 19;
    private int postersPerPage = 5;
    private JButton prev;
    private JButton next;
    private JLabel logoutBtn;
	
	public CinemaMainPanel() {
		ec = PanelController.getInstane();
		movies = ec.getMovies();
		init();
		start();
	}
	
	public void init() {
		setBounds(0, 0, 1384, 861);
		setBackground(Color.BLACK);
		setLayout(null);
		movieDAO = MovieDAO.getInstance();
		banneInit();
	}
	private void start() {
		
		registBtn = new JLabel(util.reSizeIcon(new ImageIcon("./img/icon/join1.png"), 110, 40));
		registBtn.setBounds(1254, 10, 110, 40);
		registBtn.putClientProperty("id", "regist");
		registBtn.addMouseListener(new BtnController());
		
		logoutBtn = new JLabel(util.reSizeIcon(new ImageIcon("./img/icon/logOut1.png"), 110, 40));
		logoutBtn.setVisible(false);
		logoutBtn.setBounds(1135, 10, 110, 40);
		logoutBtn.putClientProperty("id", "logout");
		logoutBtn.addMouseListener(new BtnController());
		add(logoutBtn);
		add(registBtn);
		
		myInfoBtn = new JLabel(util.reSizeIcon(new ImageIcon("./img/icon/myPage1.png"), 110, 40));
		myInfoBtn.setVisible(false);
		myInfoBtn.setBounds(1254, 10, 110, 40);
		myInfoBtn.putClientProperty("id", "myPage");
		myInfoBtn.addMouseListener(new BtnController());
		add(myInfoBtn);
		
		loginBtn = new JLabel(util.reSizeIcon(new ImageIcon("./img/icon/login1.png"), 110, 40) );
		loginBtn.setBounds(1135, 10, 110, 40);
		loginBtn.putClientProperty("id", "login");
		loginBtn.addMouseListener(new BtnController());
		add(loginBtn);
		
		bannerPanel = new BannerPanel(banners);
		bannerPanel.setBounds(0, 0, 1384, 530);
		bannerPanel.setBorder(null);
		bannerPanel.setLayout(null);
		add(bannerPanel);
		
		posterPanel = new JPanel();
		posterPanel.setBounds(218, 558, 912, 293);
		posterPanel.setBackground(Color.BLACK);
		add(posterPanel);
		
		prev = new JButton("");
		prev.setUI(new BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        });
		prev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		prev.setBackground(Color.BLACK);
		prev.setBorder(null);
		prev.setBounds(149, 678, 50, 50);
		prev.addActionListener(this);
		prev.setIcon(new ImageIcon("./img/icon/prevBtn1.png"));
		prev.addMouseListener(this);
		add(prev);
		
		next = new JButton("");
		next.setUI(new BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
        });
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		next.setBackground(Color.BLACK);
		next.setBounds(1145, 678, 50, 50);
		next.addActionListener(this);
		next.setIcon(new ImageIcon("./img/icon/nextBtn1.png"));
		next.addMouseListener(this);
		next.setBorder(null);
		add(next);
		
		
		posterPanels = new JPanel[posterCount];
		for(int i = 0; i < 19; i++) {
			JPanel poster = new PosterPanel(i+1);
			poster.setPreferredSize(new Dimension(180, 284));
			posterPanels[i] = poster;
		}
		
		for (int i = 0;  i < posterCount; i++) {
            posterPanels[i].setBounds(i * 180, 0, 180, 284);
            posterPanel.add(posterPanels[i]);
        }
		prev.setVisible(false);
	}
	
	class PosterPanel extends JPanel{
		public PosterPanel(int rank) {
			setSize(180, 284);
			setLayout(null);
			setBackground(Color.BLACK);
			JLabel poster = new JLabel("");
			poster.setBounds(0, 0, 180, 250);
			poster.setIcon(util.reSizeIcon(new ImageIcon("./img/poster/poster_"+rank+".jpg"), 180, 250) );
			poster.setCursor(new Cursor(Cursor.HAND_CURSOR));
			poster.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MovieInfo movieInfo = ec.getMovieInfo();
					movieInfo.start();
					ec.updateMovieInfo(movies.get("movie"+rank));
					ec.getCmPanel().setVisible(false);
					movieInfo.setVisible(true);
				}
			});
			JLabel title = new JLabel("");
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(0, 250, 180, 20);
			title.setText(movies.get("movie"+rank).getTitle());
			title.setForeground(Color.WHITE);
			title.setFont(new Font("굴림", Font.BOLD, 13));
			JLabel rate = new JLabel("");
			rate.setHorizontalAlignment(SwingConstants.CENTER);
			rate.setBounds(0, 270, 180, 14);
			rate.setText("예매율 : "+movies.get("movie"+rank).getRate()+"%");
			rate.setFont(new Font("굴림", Font.PLAIN, 12));
			rate.setForeground(Color.WHITE);
			add(poster);
			add(title);
			add(rate);
		}
	}
	
	private void banneInit() {
		for(int i = 1; i<=5; i++) {
			banners[i-1] = util.reSizeIcon(new ImageIcon("./img/banner/banner"+i+".jpg"), 1384, 530);
		}
	}
	
	class BannerPanel extends JPanel implements ActionListener{
		private static final int TIMER_DELAY = 3500;
		private Image[] images;
		private int currentImageIndex;
		private Timer timer;
		
	    public BannerPanel(ImageIcon[] banners) {
	    	this.setOpaque(false);
	    	banneInit();
	        images = new Image[banners.length];
	        for (int i = 0; i < banners.length; i++) {
	            images[i] =  banners[i].getImage();
	        }

	        currentImageIndex = 0;

	        timer = new Timer(TIMER_DELAY, this);
	        timer.start();

	        setPreferredSize(new Dimension(images[0].getWidth(this), images[0].getHeight(this)));
	        setBorder(BorderFactory.createEtchedBorder());
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        Image image = images[currentImageIndex];
	        int x = (getWidth() - image.getWidth(this)) / 2;
	        int y = (getHeight() - image.getHeight(this)) / 2;
	        g.drawImage(image, x, y, this);
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        currentImageIndex = (currentImageIndex + 1) % images.length;
	        repaint();
	    }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == prev) {
	            currentIndex -= 1;
	            if (currentIndex < 0) {
	                currentIndex = 0;
	            }
	        } else if (e.getSource() == next) {
	            currentIndex += 1;
	            if (currentIndex >= posterCount - postersPerPage + 1) {
	                currentIndex = posterCount - postersPerPage;
	            }
	        }

	        updatePosterPanel();

	        prev.setVisible(currentIndex > 0);
	        next.setVisible(currentIndex < posterCount - postersPerPage);
 }
	
	private void updatePosterPanel() {
        for (int i = 0; i < posterCount; i++) {
            int index = i - currentIndex;
            if (index >= 0 && index < postersPerPage) {
                posterPanels[i].setVisible(true);
            } else {
                posterPanels[i].setVisible(false);
            }
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		next.setBackground(Color.BLACK);
		prev.setBackground(Color.BLACK);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
			if(e.getSource().equals(next)) {
				next.setIcon(new ImageIcon("./img/icon/nextBtn2.png"));
			}
			if(e.getSource().equals(prev)) {
				prev.setIcon(new ImageIcon("./img/icon/prevBtn2.png"));
			}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(next)) {
			next.setIcon(new ImageIcon("./img/icon/nextBtn1.png"));
		}
		if(e.getSource().equals(prev)) {
			prev.setIcon(new ImageIcon("./img/icon/prevBtn1.png"));
		}
	}
	public void LoginUser() {
		loginBtn.setVisible(false);
		registBtn.setVisible(false);
		logoutBtn.setVisible(true);
		myInfoBtn.setVisible(true);
	}
	public void LogoutUser() {
		loginBtn.setVisible(true);
		registBtn.setVisible(true);
		logoutBtn.setVisible(false);
		myInfoBtn.setVisible(false);
	}
}
