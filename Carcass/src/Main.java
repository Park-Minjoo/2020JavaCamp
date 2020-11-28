import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main extends JFrame {
	static JScrollPane scrollPane;
	static boolean refereeB = false;
	static boolean turnB = true;
	JPanel panelPan;
	BufferedImage panImg;
	BufferedImage background;
	BufferedImage timeOver;
	boolean buttonS = false;
	public static Can[][] win = new Can[20][20];
	static int count = 16;
	static Timer t;
	/**
	 * @wbp.nonvisual location=480,301
	 */
	private final JLabel timer = new JLabel(" ");
	private final JLabel timerName = new JLabel("TIMER");
	static JLabel whoseTurn = new JLabel(" ");
	
	public Main() {
		getContentPane().setBackground(new Color(119, 136, 153));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			background = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/cc.png"));
			timeOver = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/timeover.png"));
		} catch (IOException e1) {
//			 TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getContentPane().setLayout(null);
		
		timer.setBounds(850, 150, 130, 40);
		timerName.setBounds(845, 120, 130, 40);
		whoseTurn.setBounds(800, 600, 200, 40);
		
		whoseTurn.setFont(new Font("Bradley Hand", Font.BOLD, 20));
		whoseTurn.setForeground(new Color(250, 150, 122));
		
		getContentPane().add(timer);
		getContentPane().add(timerName);
		getContentPane().add(whoseTurn);
		
		t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count--;
				timer.setFont(new Font("Serif", Font.BOLD, 20));
				timer.setForeground(Color.BLACK);
				timer.setText(String.valueOf(count));
				if (count >= 1 && count <= 5)
					timer.setForeground(Color.RED);
					timer.setFont(new Font("Serif", Font.BOLD, 30));
				if (count == 1) {
//					count = 16;
				}
				if (count == 0) {
					JFrame lateFrame = new JFrame() {
						public void paint(Graphics g) {
							super.paint(g);
							Graphics2D g2 = (Graphics2D) g;
							g2.drawImage(timeOver, 0, 20, 300, 300, null);
						}
					};
					lateFrame.setSize(300, 300);
					lateFrame.setLocation(730, 380);
//					lateFrame.setBackground(new Color(119, 136, 153));
					lateFrame.setVisible(true);
					lateFrame.repaint();
					t.stop();
				}
			}
		});

		JButton redDol = new JButton("Referee");
		redDol.setBounds(800, 200, 130, 40);
		redDol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("referee"+refereeB);
				/*
				 * if(refereeB) refereeB = false; else
				 */ turnB = false;
				refereeB = true;
			}
		});
		getContentPane().add(redDol);
		JButton gStart = new JButton("Game Start");
		gStart.setBounds(800, 250, 130, 40);
		gStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.start();
				refereeB = false;
				turnB = true;
			}
		});
		getContentPane().add(gStart);

		JButton startB = new JButton("Can We Start?");
		startB.setBounds(550, 50, 130, 50);

		startB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonS = true;
				// 바둑판 생성
				panelPan.setVisible(true);

				badookPan();
			}
		});
		panelPan = new JPanel() {
			public void paintComponent(Graphics g) {

				if (buttonS) {
					g.drawImage(panImg, 10, -30, 480, 500, null);
					for (int i = 1; i <= 19; i++) {
						g.drawLine(25, 25 * i - 15, 500 - 25, 25 * i - 15);
						g.drawLine(25 * i, 25 - 15, 25 * i, 25 * 19 - 15);
					}
					for (int j = 0; j < 3; j++) {
						g.fillOval(25 * 4 - 4, 25 * (4 + j * 6) - 4 - 15, 8, 8);
						g.fillOval(25 * 10 - 4, 25 * (4 + j * 6) - 4 - 15, 8, 8);
						g.fillOval(25 * 16 - 4, 25 * (4 + j * 6) - 4 - 15, 8, 8);
					}
				}
			}
		};
		panelPan.setBounds(80, 130, 500, 500);
		panelPan.setLayout(null);
		panelPan.setVisible(false);
		// panelPan.addMouseListener(new Can(0, 0));
		getContentPane().add(startB);

	}

	public void badookPan() {
		Can[][] board = new Can[19][19];
		try {
			panImg = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/blue.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 이미지 경로
		getContentPane().add(panelPan);
		repaint();

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				board[i][j] = new Can(i, j);
				panelPan.add(board[i][j]);
			}
		}
		win = board;

	}

	public void gameWin() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
			}
		}
	}

	public void paint(Graphics g) {

//		try {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		super.paint(g);

	}

//	public void paintComponent(Graphics g) {
//		super.paintComponents(g);
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main test = new Main();
		// JFrame mainFrame = new JFrame("민주 프레임~");
		Dimension dim = new Dimension(1100, 700);
		test.setLocation(20, 10);
		test.setPreferredSize(dim);
		test.pack();
		test.setVisible(true);
	}

}
