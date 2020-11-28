import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Ai extends JLabel implements MouseListener, MouseMotionListener {

	Point point;
	int color = -1; // 색깔을 바꿔주기 위한 변수 
	static int num = 0; // 턴을 넘겨주기 위한 변수 
	static BufferedImage blackDol ; // 지구 
	static BufferedImage whiteDol ; // 나트륨 
	static BufferedImage victory ; // 지구 승리 이미지 
	static BufferedImage lose ; // 지구 패배 이미지 
	static Robot mibot;
	int win = -1; // 돌이 6개인지를 확인하기 위한 변수 
	Timer execute ;
	boolean turn = false;
	static ArrayList<Point> redDol = new ArrayList<Point>();
	static ArrayList<Point>  Na = new ArrayList<Point>();
	static ArrayList<Point>  earth = new ArrayList<Point>();

	public Ai(int x, int y) { // 배열 전체 값을 받아옴. 
//		Color backColor = new Color(94, 129, 151); // 라벨 하나의 배경 
//		setBackground(backColor);
		
		
		
		point = new Point(x, y); //point 주소 
		setLocation(25 * x + 13, 25 * y - 2); // 라벨의 위치 를 지정 
		setSize(23, 23); // 라벨의 사이즈를 지정 
		addMouseListener(this); // 마우스 리스너 
//		setOpaque(true); // 
		try {
			mibot = new Robot();
		} catch (AWTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// 색깔 지정
		try {
			whiteDol = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/YELLOW.png"));
			blackDol = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/BLUE.png"));
			victory = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/oneTong.png"));
			lose = ImageIO.read(new File("/Users/minjoo/Desktop/eclipse-workspace/Carcass/55.png"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.setEnabled(false);
		Ai[][] tempWin = Main.win;
		System.out.println(point.x+" "+ point.y);
		for(int i = 0; i < Main.weightGraph.length; i++) {
			for(int j = 0; j < Main.weightGraph[i].length; j++) {
				System.out.print(Main.weightGraph[i][j]+ "   ");
			}
			System.out.println(" ");
		}
		//순서 체크 
		if (Main.refereeB) { //referee 버튼을 눌렀을때 
			color = 2;
			Main.weightGraph[point.y][point.x] = 9999;
			redDol.add(e.getPoint()); // 적돌 리스트에 추가. 
		} else if (Main.turnB == true && Main.refereeB == false) { //턴 버튼을 눌렀을 때 
			color = 0; // 검은색으로 그린다 
			num++; 
			Main.whoseTurn.setText("EARTH's TURN"); //지구의 차례 
			if (num % 4 == 1) { //나머지가 1인 경우 
				Main.turnB = false; 
				Main.count = 16; //카운트 다시 올림 
				Main.whoseTurn.setText("Na+'s TURN"); // 나트륨의 차례 
			}
			Main.weightGraph[point.y][point.x] = 1111;
			Na.add(e.getPoint()); // 나트륨 리스트에 추가 
		} else if (Main.turnB == false && Main.refereeB == false) { //턴 버튼을 다시 누른 경우 
			//하얀돌 
			color = 1;
			num++;
			Main.whoseTurn.setText("Na+'s TURN"); //나트륨의 차례라고 알린다 
			if (num % 4 == 3) {
				Main.turnB = true;
				Main.count = 16;
				Main.whoseTurn.setText("EARTH's TURN");// 지구의 차례라고 알린다 
			}
			Main.weightGraph[point.y][point.x] = 2222;
			earth.add(e.getPoint()); //지구 리스트에 추가 
		}
		
//		System.out.println(point.x+" "+ point.y);

		
		// 수비 
		// 4개 측정해서 양쪽 막기 
		
		// 5개 나오면 양 쪽 막기
		
		// 공격
		// 4개있을 때 끝내기 
		 
		
		
		// 승패 판단 
		// 마지막 놓은 돌의 가로, 세로, 대각선체크
		
		
		// 가로
		for (int i = 0; i < 19; i++) {
			//처음부터 끝까지 체크 
			if (tempWin[point.x][point.y].color != 2 && point.x + i < 19
					&& tempWin[point.x][point.y].color == tempWin[point.x + i][point.y].color) {
				//심판돌이 아니고
				//인덱스설정하고 
				// 오른쪽과 색깔을 비교 
				win++; // 같으면 증가 
			} else
				break; // 아니면 if문 빠져나감. 
		}
		for (int i = 0; i < 19; i++) {
			//마찬가지로 가로 왼쪽 
			if (tempWin[point.x][point.y].color != 2 && point.x - i >= 0
					&& tempWin[point.x][point.y].color == tempWin[point.x - i][point.y].color) {
				win++;
			} else
				break;
		}
		
		if (win == 6) { // 같은 색의 돌이 6개 일때 
			Main.t.stop(); // 카운터 멈춤 
			@SuppressWarnings("serial")
			JFrame winFrame = new JFrame() {
				public void paint(Graphics g) {
					super.paintComponents(g);
					if (tempWin[point.x][point.y].color == 0) { // 그 색이 지일 때 
						System.out.println("Earth Wins!");
						g.drawImage(lose, 0, 20, 300, 300, rootPane);
						
					} else if (tempWin[point.x][point.y].color == 1) { // 그 색이 나트륨일 때 
						System.out.println("Na+ Wins!");
						g.drawImage(victory, 0, 20, 300, 300, rootPane);
					}
					
				}
			};
			//이겼을 때 나오는 프레임 조정 
			winFrame.setSize(300, 300);
			winFrame.setLocation(730, 380);
			winFrame.setBackground(new Color(119, 136, 153));
			winFrame.setTitle("You Win!");
			winFrame.setVisible(true);
			
		}
		
		win = -1; // 변수 초기화 
		// 세로
		for (int i = 0; i < 19; i++) {
			//세로 
			if (tempWin[point.x][point.y].color != 2 && point.y + i < 19
					&& tempWin[point.x][point.y].color == tempWin[point.x][point.y + i].color) {
				win++;
//				System.out.println("right"+win);
			} else
				break;
		}
		for (int i = 0; i < 19; i++) {
			//세로 아래 
			if (tempWin[point.x][point.y].color != 2 && point.y - i >= 0
					&& tempWin[point.x][point.y].color == tempWin[point.x][point.y - i].color) {
				win++;
			} else
				break;
		}
		
		if (win == 6) { 
			Main.t.stop();
			@SuppressWarnings("serial")
			JFrame winFrame = new JFrame() {
				public void paint(Graphics g) {
					if (tempWin[point.x][point.y].color == 0) {
						System.out.println("Earth Wins!");
						g.drawImage(lose, 0, 20, 300, 300, null);
					} else if (tempWin[point.x][point.y].color == 1) {
						System.out.println("Na+ Wins!");
						g.drawImage(victory, 0, 20, 300, 300, null);
					}
				}
			};
			winFrame.setSize(300, 300);
			winFrame.setLocation(730, 380);
			winFrame.setBackground(new Color(119, 136, 153));
			winFrame.setVisible(true);
		}
		win = -1;
		
		// 정비례 대각선
		for (int i = 0; i < 19; i++) {
			if (tempWin[point.x][point.y].color != 2 && point.x + i < 19 && point.y - i >= 0
					&& tempWin[point.x][point.y].color == tempWin[point.x + i][point.y - i].color) {
				win++;
			} else
				break;
		}
		for (int i = 0; i < 19; i++) {	
			if (tempWin[point.x][point.y].color != 2 && point.x - i >= 0 && point.y + i < 19
					&& tempWin[point.x][point.y].color == tempWin[point.x - i][point.y + i].color) {
				win++;
			} else
				break;
		}
		if (win == 6) {
			Main.t.stop();
			@SuppressWarnings("serial")
			JFrame winFrame = new JFrame() {
				public void paint(Graphics g) {
					if (tempWin[point.x][point.y].color == 0) {
						System.out.println("Earth Wins!");
						g.drawImage(lose, 0, 20, 300, 300, null);
					} else if (tempWin[point.x][point.y].color == 1) {
						System.out.println("Na+ Wins!");
						g.drawImage(victory, 0, 20, 300, 300, null);
					}
				}
			};
			winFrame.setSize(300, 300);
			winFrame.setLocation(730, 380);
			winFrame.setBackground(new Color(119, 136, 153));
			winFrame.setVisible(true);
		}
		win = -1;
		
		// 반비례 대각선
		for (int i = 0; i < 19; i++) {
			if (tempWin[point.x][point.y].color != 2 && point.x + i < 19 && point.y + i < 19
					&& tempWin[point.x][point.y].color == tempWin[point.x + i][point.y + i].color) {
				win++;
			} else
				break;
		}
		for (int i = 0; i < 19; i++) {
			if (tempWin[point.x][point.y].color != 2 && point.x - i >= 0 && point.y - i >= 0
					&& tempWin[point.x][point.y].color == tempWin[point.x - i][point.y - i].color) {
				win++;
			} else
				break;
		}
		if (win == 6) {
			Main.t.stop();
			@SuppressWarnings("serial")
			JFrame winFrame = new JFrame() {
				public void paint(Graphics g) {
					if (tempWin[point.x][point.y].color == 0) {
						System.out.println("Earth Wins!");
						g.drawImage(lose, 0, 20, 300, 300, null);
					} else if (tempWin[point.x][point.y].color == 1) {
						System.out.println("Na+ Wins!");
						g.drawImage(victory, 0, 20, 300, 300, null);
					}
				}
			};
			winFrame.setSize(300, 300);
			winFrame.setLocation(730, 380);
			winFrame.setBackground(new Color(119, 136, 153));
			winFrame.setVisible(true);
		}
		
		repaint(); // 이미지를 다시 불러옴 
		this.removeMouseListener(this); // 마우 리스너 없애서 클릭한 버튼 다시 못누르도록 
		this.removeMouseMotionListener(this);

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 라벨 안에 들어오면 핸드 모양 
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 라벨을 나가면 디폴트 
	}
	
//	public void blackDol() {
//		color = 0;
//		num++;
//		Main.whoseTurn.setText("EARTH's TURN");
//		if (num % 4 == 1) {
//			Main.turnB = false;
//			Main.count = 16;
//			Main.whoseTurn.setText("Na+'s TURN");
//		}
//	}
	

	public void paint(Graphics g) { // 이미지를 그리자. 
		if (color == 0) {
			
			g.drawImage(blackDol, 0, 0, 23, 23, null);
		} else if (color == 1) {
			g.drawImage(whiteDol, 0, 0, 23, 23, null);
		} else if (color == 2) {
			g.setColor(Color.BLACK);
			g.fillOval(0, 0, 18, 18);
		}
	}

	public static void robotClass(int x, int y) {
		mibot.mouseMove(x, y);
		mibot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		mibot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		
	}

}
