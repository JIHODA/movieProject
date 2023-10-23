package first_led_project.view.mainSubPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import first_led_project.controller.BtnController;
import first_led_project.controller.PanelController;
import first_led_project.dao.CommentDAO;
import first_led_project.dao.MovieDAO;
import first_led_project.dao.PaymentDAO;
import first_led_project.dto.CommentDTO;
import first_led_project.dto.MovieDTO;
import first_led_project.dto.SucessDTO;
import first_led_project.util.Util;
import first_led_project.view.customComp.ButtonCustom;
import first_led_project.view.customComp.MovieInfoScroll;
import first_led_project.view.customComp.PrevBtnCustom;

public class MovieInfo extends JPanel {
	private MovieDTO movie;
	private Util util = new Util();
	
	private JLabel poster;
	private JLabel title;
	private JLabel opening;
	private JLabel otherInfo;
	private JLabel director;
	private JLabel actor;
	private JLabel genre;
	private JTextPane story;
	private ArrayList<ImageIcon> stillcuts = new ArrayList<>();
	private ArrayList<CommentDTO> comments;
	private JPanel stillcutPanel;
	private JScrollPane scrollPane;
	private JPanel defaultPanel;
	private JPanel commentPanel;
	private JLabel label;
	private CommentDTO commentDTO;
	private JScrollPane commentScroll;
	private JPanel commentScrollPanel;
	private JLabel like;
	private JLabel likeCountLabel;
	JLabel commentLabel;
	JLabel defaultLabel;
	JLabel stillcutLabel;
	
	public MovieInfo() {
		init();
		start();
	}
	
	private void init() {
		setBounds(0, 0, 1384, 861);
		setBackground(Color.BLACK);
		setLayout(null);
		setVisible(false);
		
		JButton ticketingBtn = new JButton();
		ticketingBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ticketingBtn.setBorder(null);
		ticketingBtn.setIcon(util.reSizeIcon(new ImageIcon("./img/icon/movieOn.png"), 150, 50));
		ticketingBtn.setBackground(Color.BLACK);
		ticketingBtn.setBounds(1222, 316, 150, 50);
		ticketingBtn.putClientProperty("id", "infoTicketing");
		ticketingBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ticketingBtn.setIcon(util.reSizeIcon(new ImageIcon("./img/icon/movieOn2.png"), 150, 50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ticketingBtn.setIcon(util.reSizeIcon(new ImageIcon("./img/icon/movieOn.png"), 150, 50));
			}
		});
		ticketingBtn.addActionListener(new BtnController());
		
		commentPanel = new JPanel();
		commentPanel.setBackground(Color.BLACK);
		commentPanel.setBorder(new EmptyBorder(0, 100, 0, 100));
		commentPanel.setBounds(0, 478, 1384, 383);
		commentPanel.setLayout(null);
		commentPanel.setVisible(false);
		
		commentScroll = new JScrollPane();
		commentScroll.setBackground(Color.BLACK);
		commentScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		commentScroll.setBorder(new LineBorder(Color.WHITE));
		commentScroll.setBounds(263, 149, 857, 212);
		commentScroll.getVerticalScrollBar().setUnitIncrement(15);
		commentPanel.add(commentScroll);
		
		commentScrollPanel = new JPanel();
		commentScrollPanel.setBackground(Color.BLACK);
		commentScroll.setViewportView(commentScrollPanel);
		commentScrollPanel.setLayout(new GridLayout(0, 2, 0, 0));
		add(commentPanel);
		
		likeCountLabel = new JLabel("");
		likeCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		likeCountLabel.setForeground(Color.WHITE);
		likeCountLabel.setBounds(490, 434, 57, 15);
		add(likeCountLabel);
		
		defaultPanel = new JPanel();
		defaultPanel.setBackground(Color.BLACK);
		defaultPanel.setBounds(0, 478, 1384, 383);
		add(defaultPanel);
		defaultPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(ticketingBtn);
		
		stillcutPanel = new JPanel();
		stillcutPanel.setBackground(Color.BLACK);
		stillcutPanel.setLayout(new GridLayout(0, 4, 10, 30));
		
		scrollPane = new MovieInfoScroll(stillcutPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 478, 1384, 383);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(scrollPane);
		
		JButton backBtn = new PrevBtnCustom();
		backBtn.setLocation(12, 10);
		backBtn.putClientProperty("id", "infoMainBack");
		backBtn.addActionListener(new BtnController());
		add(backBtn);
	}
	
	public void start() {
		label = new JLabel();
		
		poster = new JLabel("");
		poster.setBorder(null);
		poster.setBounds(198, 49, 300, 400);
		add(poster);
		
		title = new JLabel("");
		title.setBorder(null);
		title.setFont(new Font("굴림", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		title.setBounds(562, 85, 594, 57);
		add(title);
		
		opening = new JLabel("");
		opening.setFont(new Font("굴림", Font.PLAIN, 12));
		opening.setBorder(null);
		opening.setForeground(Color.WHITE);
		opening.setBounds(562, 164, 161, 31);
		add(opening);
		
		otherInfo = new JLabel("");
		otherInfo.setFont(new Font("굴림", Font.PLAIN, 12));
		otherInfo.setBorder(null);
		otherInfo.setForeground(Color.WHITE);
		otherInfo.setBounds(721, 164, 203, 31);
		add(otherInfo);
		
		story = new JTextPane();
		story.setFont(new Font("굴림", Font.PLAIN, 12));
		story.setBorder(null);
		story.setForeground(Color.WHITE);
		story.setOpaque(false);
		story.setBounds(562, 266, 497, 171);
		add(story);
		
		director = new JLabel("");
		director.setFont(new Font("굴림", Font.PLAIN, 12));
		director.setForeground(Color.WHITE);
		director.setBorder(null);
		director.setBounds(562, 204, 268, 31);
		add(director);
		
		actor = new JLabel("");
		actor.setFont(new Font("굴림", Font.PLAIN, 12));
		actor.setForeground(Color.WHITE);
		actor.setBorder(null);
		actor.setBounds(562, 230, 689, 42);
		add(actor);
		
		genre = new JLabel("");
		genre.setFont(new Font("굴림", Font.PLAIN, 12));
		genre.setForeground(Color.WHITE);
		genre.setBorder(null);
		genre.setBounds(936, 165, 220, 30);
		add(genre);
		
		stillcutLabel = new JLabel("\uC2A4\uD2F8\uCEF7");
		stillcutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stillcutLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		stillcutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stillcutLabel.setForeground(Color.WHITE);
		stillcutLabel.setBorder(new LineBorder(Color.WHITE));
		stillcutLabel.setBounds(1039, 438, 129, 42);
		stillcutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!scrollPane.isVisible()) {
					scrollPane.setVisible(true);
				}
				if(defaultPanel.isVisible() || commentPanel.isVisible()) {
					defaultPanel.setVisible(false);
					commentPanel.setVisible(false);
				}
			}
		});
		add(stillcutLabel);
		
		commentLabel = new JLabel("\uD55C\uC904\uD3C9");
		commentLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		commentLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		commentLabel.setForeground(Color.WHITE);
		commentLabel.setBorder(new LineBorder(Color.WHITE));
		commentLabel.setBounds(1168, 438, 129, 42);
		commentLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(scrollPane.isVisible())
					scrollPane.setVisible(false);
				if(defaultPanel.isVisible()) {
					defaultPanel.setVisible(false);
				}
				commentScrollPanel.removeAll();
				commentScrollPanel.revalidate();
				commentScrollPanel.repaint();
				addComment();
				commentPanel.setVisible(true);
			}
		});
		
		add(commentLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBounds(0, 466, 910, 2);
		add(separator);
		
		defaultLabel = new JLabel("\uAE30\uBCF8\uC815\uBCF4");
		defaultLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		defaultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		defaultLabel.setForeground(Color.WHITE);
		defaultLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		defaultLabel.setBorder(new LineBorder(Color.WHITE));
		defaultLabel.setBounds(910, 438, 129, 42);
		defaultLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(scrollPane.isVisible()||commentPanel.isVisible()) {
					scrollPane.setVisible(false);
					commentPanel.setVisible(false);
					defaultPanel.setVisible(true);
				}
			}
		});
		add(defaultLabel);
		
		like = new JLabel("");
		like.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		like.setHorizontalAlignment(SwingConstants.CENTER);
		like.setForeground(Color.WHITE);
		like.setBounds(506, 400, 25, 25);
		like.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(PanelController.getInstane().getUser() == null) {
					JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "로그인 후 사용 가능합니다.");
					return;
				}
				if(CommentDAO.getInstance().insertLike(movie, PanelController.getInstane().getUser()) == 0) {
					JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "영화당 1회만 가능합니다.");
					return;
				}
				 like.setIcon(new ImageIcon("./img/icon/heart.png"));
				JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), movie.getTitle()+" 영화를 좋아합니다");
				likeCountLabel.setText(""+CommentDAO.getInstance().allLike(movie));
			}
		});
		add(like);
		scrollPane.setVisible(false);
	}
	
	public void update(MovieDTO dto) {
		this.movie = dto;
		setStillcut();
		setDefault();
		likeCountLabel.setText(""+CommentDAO.getInstance().allLike(dto));
		like.setIcon(new ImageIcon("./img/icon/emptyHeart.png"));
		if(PanelController.getInstane().getUser() != null) {
			if(CommentDAO.getInstance().selectLike(movie, PanelController.getInstane().getUser()) > 0) {
				like.setIcon(new ImageIcon("./img/icon/heart.png"));
			}
		}
		poster.setIcon(util.reSizeIcon(new ImageIcon("./img/poster/poster_"+movie.getRank()+".jpg"), 300, 400));
		title.setText(movie.getTitle());
		otherInfo.setText(movie.getOtherInfo());
		genre.setText("장르 : "+movie.getGenre());
		opening.setText("개봉일 : "+movie.getOpeningDate());
		director.setText("감독 : "+ movie.getDirector());
		actor.setText("배우 : "+movie.getActor());
		story.setText(movie.getDescription());
	}
	
	private void setStillcut() {
		int stillcutCount = MovieDAO.getInstance().stillcutCount(movie);
		for(int i = 1; i<=stillcutCount; i++) {
			String extension = ".jpg";
			if(movie.getRank() == 8)
				extension = ".png";
			stillcuts.add(new ImageIcon("./img/stillcut/stillcut_"+movie.getRank()+"_"+i+extension));
		}
		
		for (int i = 0; i < stillcuts.size(); i++) {
			try {
				JLabel stillcut = new JLabel("");
				stillcut.setIcon(util.reSizeIcon(stillcuts.get(i), 300, 280));
				stillcut.setBackground(Color.WHITE);
				stillcut.setSize(300, 280);
	            stillcutPanel.add(stillcut);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	private void setDefault() {
		label.setIcon(new ImageIcon("./img/canvas/canvas_"+ movie.getRank() +".png"));
		defaultPanel.add(label);
	}
		
	public void remove() {
		stillcutPanel.removeAll();
		stillcutPanel.revalidate();
		stillcutPanel.repaint();
		commentScrollPanel.removeAll();
		commentScrollPanel.revalidate();
		commentScrollPanel.repaint();
		stillcuts.clear();
		likeCountLabel.setText("");
		like.setIcon(new ImageIcon("./img/icon/emptyHeart.png"));
		defaultPanel.setVisible(true);
		commentPanel.setVisible(false);
		label.setIcon(null);
		poster.setIcon(null);
		Component[] comps = this.getComponents();
		for(Component comp : comps) {
			if(comp instanceof JLabel) {
				JLabel label = (JLabel)comp;
				label.setText("");
			}else if(comp instanceof JTextPane) {
				JTextPane jtp = (JTextPane)comp;
				jtp.setText("");
			}
		}
		poster.setIcon(null);
	}
	class CommentFrame extends JPanel{//댓글작성 칸
		public CommentFrame(){
			setBounds(263, 20, 857, 110);
			setBackground(Color.BLACK);
			setLayout(null);
			setBorder(new LineBorder(Color.WHITE));
			JLabel label = new JLabel(PanelController.getInstane().getUser().getMember_id()+"님");
			label.setBounds(0,5,190,100);
			label.setForeground(Color.WHITE);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			add(label);
			JTextPane area = new JTextPane();
			area.setBounds(190, 5, 547, 100);
			add(area);
			JButton button = new ButtonCustom("확인");
//			button.setBackground(Color.WHITE);
			button.setBorder(new LineBorder(Color.WHITE));
			button.setForeground(Color.WHITE);
			button.setBounds(747, 25, 100, 50);
//			button.putClientProperty("id", "commentWrite");
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(area.getText().equals("")) {
						JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "내용을 입력해주세요.");
						return;
					}
					SucessDTO dto = new SucessDTO();
					dto.setMovie_id(movie.getMovie_id());
					dto.setMember_id(PanelController.getInstane().getUser().getMember_id());
					if(PaymentDAO.getInstance().selectSucess(dto) == 0) {
						JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "영화 예매 후 작성가능합니다.");
						area.setText("");
						return;
					}
					if(CommentDAO.getInstance().commentCount(commentDTO) > 0) {
						JOptionPane.showMessageDialog(PanelController.getInstane().getJFrame(), "한줄평은 1회만 작성가능합니다.");
						area.setText("");
						return;
					}
					
					commentDTO.setContents(area.getText());
					CommentDAO.getInstance().InsertComment(commentDTO);
					area.setText("");
					commentInit();
					JOptionPane.showMessageDialog(getComponentPopupMenu(), "한줄평이 등록되었습니다.");
					area.requestFocusInWindow();
				}
			});
			add(button);
		}
	}
	public void commentInit() {
		commentScrollPanel.removeAll();
		commentScrollPanel.revalidate();
		commentScrollPanel.repaint();
		addComment();
	}
//	274, 161, 850, 50
	public void addComment() {
		if(PanelController.getInstane().getUser() != null) {
			commentPanel.add(new CommentFrame());
			this.commentDTO = new CommentDTO();
			commentDTO.setMember_id(PanelController.getInstane().getUser().getMember_id());
			commentDTO.setMovie_id(movie.getMovie_id());
		}
		
		this.comments = CommentDAO.getInstance().newSelectComment(movie);
		if(comments.size() == 0) {
			return;
		}
		
//		int locationY = 0;
		for(CommentDTO commDTO : comments) {
			JPanel panel = new NewCommentPanel(commDTO);
//			panel.setLocation(0, 50*locationY);
			commentScrollPanel.add(panel);
//			locationY++;
		}
		commentScroll.scrollRectToVisible(new Rectangle(0, 0, 1, 1));
	}
	

}
