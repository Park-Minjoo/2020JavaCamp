import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
@SuppressWarnings("serial")

public class LineDrawEx extends JFrame implements MouseListener, MouseMotionListener {
	private JPanel contentPane;
	int cur1, cur2;
	int prev1, prev2;
	Color color = Color.BLACK;
	int option;
	
	Vector<Point> ptr1=new Vector<Point>();
	Vector<Point> ptr2=new Vector<Point>();
	Vector<Integer> opt_vector=new Vector<Integer>();
	Vector<Color> col_pen=new Vector<Color>();
	
//	int option = LINE;
//	Color selectedColor=Color.BLACK;
	public static final int LINE = 1;
	public static final int RECT = 2;
	public static final int OVAL = 3;
	public static final int CIRCLE = 4;

	public void ExPanel(Graphics g) {
		for(int i=0; i<ptr1.size(); i++) {
			Point p = ptr1.get(i);
			Point p2 = ptr2.get(i);
			int opt=opt_vector.get(i);
			Color tempcolor=col_pen.get(i);
			
			g.setColor(tempcolor);
			
			if(opt==LINE) {
				g.drawLine(p.x, p.y, p2.x, p2.y);
			}
			else if(opt == RECT) {
				g.drawRect(Math.min(p.x, p2.x), Math.min(p.y, p2.y), Math.abs(p.x - p2.x), Math.abs(p2.y - p.y));
			}
			
			else if(opt == OVAL) {
				g.drawOval(Math.min(p.x, p2.x), Math.min(p.y, p2.y), Math.abs(p.x - p2.x), Math.abs(p2.y - p.y));;
			}
			else if(opt == CIRCLE) {
				g.drawOval(Math.min(p.x, p2.x), Math.min(p.y, p2.y), Math.abs(p.x - p2.x), Math.abs(p.x - p2.x));;
			}
		}
	}
	
	public void MyPanel(Graphics g) {
		super.MyPanel(g);
		ExPanel(g);
		g.setColor(color);
		
		if(option==LINE) {
			g.drawLine(prev1, prev2, cur1, cur2);
		}
		
		else if(option==RECT) {
			g.drawRect(Math.min(prev1, cur1), Math.min(prev2, cur2), Math.abs(prev1 - cur1), Math.abs(cur2 - prev2));
		}
		
		else if(option==OVAL) {
			g.drawOval(Math.min(prev1, cur1), Math.min(prev2, cur2), Math.abs(prev1-cur1), Math.abs(cur2-prev2));
		}
		
		else if(option==CIRCLE) {
			g.drawOval(Math.min(prev1, cur1), Math.min(prev2, cur2), Math.abs(prev1-cur1), Math.abs(prev1-cur1));
		}
	}
	
	public LineDrawEx() {
		getContentPane().setForeground(new Color(250, 240, 230));
		getContentPane().setBackground(new Color(250, 240, 230));
		setContentPane(new MyPanel());
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(255, 222, 173));
		toolBar.setBounds(0, 0, 900, 60);
		getContentPane().add(toolBar);

		JButton Line = new JButton("Line");
		Line.setForeground(new Color(95, 158, 160));
		Line.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		Line.setBackground(new Color(250, 235, 215));
		toolBar.add(Line);
		Line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = LINE;
			}
		});

		JButton Rect = new JButton("Rectangular");
		Rect.setForeground(new Color(95, 158, 160));
		Rect.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		toolBar.add(Rect);
		Rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = RECT;
			}
		});

		JButton oval = new JButton("Oval");
		oval.setForeground(new Color(95, 158, 160));
		oval.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		toolBar.add(oval);
		oval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = OVAL;
				System.out.println("Circle");
			}
		});
		
		JButton circle = new JButton("Circle");
		circle.setForeground(new Color(95, 158, 160));
		circle.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		toolBar.add(circle);
		
		JButton color = new JButton("Color");
		color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color selectedColor = JColorChooser.showDialog(null,"Color", Color.YELLOW);
			}
		});
		
		color.setBounds(6, 543, 117, 29);
		getContentPane().add(color);
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = CIRCLE;
			}
		});

		setVisible(true);
	}

	class MyPanel extends JPanel {
		Point startP = null;
		Point endP = null;
		int width, height;

		Vector<Point> sv = new Vector<Point>(); // line시작
		Vector<Point> se = new Vector<Point>(); // line끝점
		Vector<Point> sv1 = new Vector<Point>(); // Rect 시작
		Vector<Point> se1 = new Vector<Point>(); // Rect 끝점
		Vector<Point> sv2 = new Vector<Point>(); // oval 시작
		Vector<Point> se2 = new Vector<Point>(); // oval 끝점
		Vector<Point> sv3 = new Vector<Point>(); // one 시작
		Vector<Point> se3 = new Vector<Point>(); // one 끝점
		Vector<Color> co = new Vector<Color>(); //color set
			
		public MyPanel() {
			MyMouseListener ml = new MyMouseListener();

			this.addMouseListener(new MyMouseListener()); // 리스너
			this.addMouseMotionListener(ml);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 부모 페인트호출
//			1)for문을만든다 
//			2) 안에 vector값 3개를가쟈온다.
//			3) if문을써서 모드값에 따라 drawRect와 line을따로 부른다.

//line
			if (option == LINE) {
				if (sv.size() != 0) {
					for (int i = 0; i < se.size(); i++) { // 벡터크기만큼
						Point sp = sv.get(i); // 벡터값을꺼내다
						Point ep = se.get(i);
						g.setColor(selectedColor);
						Color tempColor = co.get(i);
//						Color l = co.get(i);
						g.drawLine(sp.x, sp.y, ep.x, ep.y);// 그리다
					}
				}
				if (sv1.size() != 0) {
					for (int i = 0; i < se1.size(); i++) { // 벡터크기만큼
						Point sp = sv1.get(i); // 벡터값을꺼내다
						Point ep = se1.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						sp.x = Math.min(ep.x, sp.x);
						sp.y = Math.min(ep.y, sp.y);
						g.drawRect(sp.x, sp.y, width, height);// 그리다
					}
				}
				if (sv2.size() != 0) {
					for (int i = 0; i < se2.size(); i++) { // 벡터크기만큼
						Point sp = sv2.get(i); // 벡터값을꺼내다
						Point ep = se2.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
					}
				}
				if (sv3.size() != 0) {
					for (int i = 0; i < se3.size(); i++) { // 벡터크기만큼
						Point sp = sv3.get(i); // 벡터값을꺼내다
						Point ep = se3.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, width);// 그리다
					}
				}
				if (startP != null)
					g.drawLine(startP.x, startP.y, endP.x, endP.y);
			}

//rect
			if (option == RECT) {
				if (sv.size() != 0) {
					for (int i = 0; i < se.size(); i++) { // 벡터크기만큼
						Point sp = sv.get(i); // 벡터값을꺼내다
						Point ep = se.get(i);
						g.drawLine(sp.x, sp.y, ep.x, ep.y);// 그리다
					}
				}

				if (sv1.size() != 0) {
					for (int i = 0; i < se1.size(); i++) { // 벡터크기만큼
						Point sp = sv1.get(i); // 벡터값을꺼내다
						Point ep = se1.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawRect(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
					}
				}

				if (sv2.size() != 0) {
					for (int i = 0; i < se2.size(); i++) { // 벡터크기만큼
						Point sp = sv2.get(i); // 벡터값을꺼내다
						Point ep = se2.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
//						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);
						System.out.println("oval");
					}
				}
				if (sv3.size() != 0) {
					for (int i = 0; i < se3.size(); i++) { // 벡터크기만큼
						Point sp = sv3.get(i); // 벡터값을꺼내다
						Point ep = se3.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, width);// 그리다
					}
				}
				if (startP != null)
					g.drawRect(Math.min(startP.x, endP.x), Math.min(startP.y, endP.y), width, height); // 현재값
			}

//oval
			if (option == OVAL) {
				if (sv.size() != 0) {
					for (int i = 0; i < se.size(); i++) { // 벡터크기만큼
						Point sp = sv.get(i); // 벡터값을꺼내다
						Point ep = se.get(i);
						g.drawLine(sp.x, sp.y, ep.x, ep.y);// 그리다
					}
				}

				if (sv1.size() != 0) {
					for (int i = 0; i < se1.size(); i++) { // 벡터크기만큼
						Point sp = sv1.get(i); // 벡터값을꺼내다
						Point ep = se1.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawRect(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
					}
				}
				if (sv2.size() != 0) {
					for (int i = 0; i < se2.size(); i++) { // 벡터크기만큼
						Point sp = sv2.get(i); // 벡터값을꺼내다
						Point ep = se2.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
					}
				}
				if (sv3.size() != 0) {
					for (int i = 0; i < se3.size(); i++) { // 벡터크기만큼
						Point sp = sv3.get(i); // 벡터값을꺼내다
						Point ep = se3.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, width);// 그리다
					}
				}
				if (startP != null)
					g.drawOval(Math.min(startP.x, endP.x), Math.min(startP.y, endP.y), width, height);
			}
			
//pen
			if (option == CIRCLE) {
				if (sv.size() != 0) {
					for (int i = 0; i < se.size(); i++) { // 벡터크기만큼
						Point sp = sv.get(i); // 벡터값을꺼내다
						Point ep = se.get(i);
						g.drawLine(sp.x, sp.y, ep.x, ep.y);// 그리다
					}
				}

				if (sv1.size() != 0) {
					for (int i = 0; i < se1.size(); i++) { // 벡터크기만큼
						Point sp = sv1.get(i); // 벡터값을꺼내다
						Point ep = se1.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawRect(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
					}
				}
				if (sv2.size() != 0) {
					for (int i = 0; i < se2.size(); i++) { // 벡터크기만큼
						Point sp = sv2.get(i); // 벡터값을꺼내다
						Point ep = se2.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, height);// 그리다
					}
				}
				if (sv3.size() != 0) {
					for (int i = 0; i < se3.size(); i++) { // 벡터크기만큼
						Point sp = sv3.get(i); // 벡터값을꺼내다
						Point ep = se3.get(i);
						int width = Math.abs(ep.x - sp.x);
						int height = Math.abs(ep.y - sp.y);
						g.drawOval(Math.min(sp.x, ep.x), Math.min(sp.y, ep.y), width, width);// 그리다
					}
				}
				if (startP != null)
					g.drawOval(Math.min(startP.x, endP.x), Math.min(startP.y, endP.y), width, width);
			}

		}

		class MyMouseListener extends MouseAdapter implements MouseMotionListener {
			
			public void mousePressed(MouseEvent e) {
				startP = e.getPoint();
				if (option == LINE) {
					sv.add(e.getPoint()); // 클릭한부분을 시작점으로
					
				}
				if (option == RECT) {
					sv1.add(e.getPoint()); // 클릭한부분을 시작점으로
				}
				if (option == OVAL) {
					sv2.add(e.getPoint()); // 클릭한부분을 시작점으로
				}
				if (option == CIRCLE) {
					sv3.add(e.getPoint()); // 클릭한부분을 시작점으로
				}
//				co.add(e.getColor());
			}

			public void mouseReleased(MouseEvent e) {
				Color temp_color = new Color(selectedColor.getRGB());
				if (option == LINE) {
					se.add(e.getPoint()); // 드래그 한부분을 종료점으로
					co.add(temp_color);
				}
				if (option == RECT) {
					se1.add(e.getPoint()); // 드래그 한부분을 종료점으로
					co.add(temp_color);
				}
				if (option == OVAL) {
					se2.add(e.getPoint()); // 드래그 한부분을 종료점으로
					co.add(temp_color);
				}
				if (option == CIRCLE) {
					se3.add(e.getPoint()); // 드래그 한부분을 종료점으로
					co.add(temp_color);
				}
//				mode.add(option);
				endP = e.getPoint();
				repaint(); // 다시그려라
			}

			public void mouseDragged(MouseEvent e) {
				endP = e.getPoint();
				width = Math.abs(endP.x - startP.x);
				height = Math.abs(endP.y - startP.y);

				repaint();
			}

			public void mouseMoved(MouseEvent e) {

			}
			
		}

	}

	public static void main(String[] args) {
		new LineDrawEx();
	}
}