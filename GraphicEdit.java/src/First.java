import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import LineDrawEx.MyPanel;
import LineDrawEx.MyPanel.MyMouseListener;

import javax.swing.JButton;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;

/*
@SuppressWarnings("serial")
abstract class Shape implements Serializable{
    int startX;
    int startY;
    Color color;
    int lastX;
    int lastY;
    int Thickness;
    boolean fillFlag;
    abstract void draw(Graphics g);
    abstract void draw(Graphics g, int x, int y, int x2, int y2);

    public Shape() {
        // TODO Auto-generated constructor stub
        startX = startY = 0;
        lastX = lastY = 0;
        color = new Color(255,255,255);
        fillFlag = false;
    }

    void setCoordinate(int x, int y, int x2, int y2) {
        startX = x;
        startY = y;
        lastX = x2;
        lastY = y2;
    }

    void setColor(Color color) {
        this.color = color;
    }

    void setThickness(int Thickness) {
        this.Thickness = Thickness;
    }

    static Shape create(String selected)
    {
        if(selected.equals("Line"))
            return new Line();
        if(selected.equals("Oval"))
            return new Oval();
        if(selected.equals("Rect"))
            return new Rect();
        else
            return null;
    }
}

@SuppressWarnings("serial")
class Line extends Shape implements Serializable{
    void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(Thickness, BasicStroke.CAP_ROUND, 0));
        g2.setColor(color);
        g2.drawLine(startX, startY, lastX, lastY);
    }

    void draw(Graphics g, int x, int y, int x2, int y2)
    {
        g.setColor(color);
        g.drawLine(x, y, x2, y2);
    }
}


@SuppressWarnings("serial")
class Oval extends Shape implements Serializable{
    void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(Thickness, BasicStroke.CAP_ROUND, 0));
        g2.setColor(color);
        if(fillFlag == true)
        {
            g2.fillOval(Math.min(startX, lastX), Math.min(startY, lastY),
                    Math.abs(lastX - startX), Math.abs(lastY - startY));

        }
        else
        {

            g2.drawOval(Math.min(startX, lastX), Math.min(startY, lastY),
                    Math.abs(lastX - startX), Math.abs(lastY - startY));

        }
    }

    void draw(Graphics g, int x, int y, int x2, int y2)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(Thickness, BasicStroke.CAP_ROUND, 0));
        g2.setColor(color);
        if(fillFlag == true)
        {
            g2.fillOval(Math.min(x, x2), Math.min(y, y2),
                    Math.abs(x2 - x), Math.abs(y2 - y));

        }
        else
        {

            g2.drawOval(Math.min(x, x2), Math.min(y, y2),
                    Math.abs(x2 - x), Math.abs(y2 - y));
        }
    }
}

@SuppressWarnings("serial")
class Rect extends Shape implements Serializable{
    void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(Thickness, BasicStroke.CAP_ROUND, 0));
        g2.setColor(color);
        if(fillFlag == true)
        {
            g2.fillRect(Math.min(startX, lastX), Math.min(startY, lastY),
                    Math.abs(lastX - startX), Math.abs(lastY - startY));
        }
        else
        {
            g2.drawRect(Math.min(startX, lastX), Math.min(startY, lastY),
                    Math.abs(lastX - startX), Math.abs(lastY - startY));
        }
    }

    void draw(Graphics g, int x, int y, int x2, int y2)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(Thickness, BasicStroke.CAP_ROUND, 0));
        g2.setColor(color);
        if(fillFlag == true)
        {
            g2.fillRect(Math.min(x, x2), Math.min(y,y2),
                    Math.abs(x2 - x), Math.abs(y2 - y));
        }
        else
        {
            g2.drawRect(Math.min(x, x2), Math.min(y, y2),
                    Math.abs(x2 - x), Math.abs(y2 - y));
        }
    }
}
*/
public class First extends JPanel implements ActionListener, Runnable {
	newWindow_draw draw;
	private JFrame frame = new JFrame();
	Container con = frame.getContentPane();
    JMenuBar MenuBar = new JMenuBar();
    int option;
    public static final int LINE = 1;
    
	@Override
/*	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		if( ((JButton)e.getSource()).getText().equals("Drawings")) {
			draw = new newWindow_draw(this);
		}
		else if ( ((JButton)e.getSource()).getText().equals("Line")) {
			
//			System.out.println("aa");
//			con.add(new JPanel()); 
			graphicOne g = new graphicOne();
			frame.add(g);
		}
	}
	*/
	public First() {
//		public static JFrame frame;
//		frame = new JFrame();
////		graph\.add(g);
//		frame.getContentPane().setBackground(new Color(248, 248, 255));
//		frame.getContentPane().setForeground(new Color(230, 230, 250));
//		frame.setBounds(300, 100, 900, 600);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new MyPanel());
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton DrawB = new JButton("Drawings");
		DrawB.setForeground(new Color(240, 128, 128));
		DrawB.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		DrawB.setBackground(new Color(250, 235, 215));
		DrawB.addActionListener(this);
//		
//		JMenu mnEdit = new JMenu("편집");
//	    MenuBar.add(mnEdit);
//		JMenuItem mntmLine = new JMenuItem("선 그리기");
//        mntmLine.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                selected = "Line";
//            }
//        });
//        mnEdit.add(mntmLine);
//		DrawB.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new newWindow_draw();
////				line
//			}
//		});
		frame.getContentPane().add(DrawB, BorderLayout.NORTH);
		
		JButton SetB = new JButton("Settings");
		SetB.setForeground(new Color(189, 183, 107));
		SetB.setFont(new Font("Bradley Hand", Font.PLAIN, 16));
		SetB.setBackground(new Color(143, 188, 143));
		SetB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = LINE;
			}
		});
		frame.getContentPane().add(SetB, BorderLayout.WEST);
		frame.add(SetB);
		
		JButton DivB = new JButton("Diverse Functions");
		DivB.setForeground(new Color(112, 128, 144));
		DivB.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
		DivB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newWindow_divF();
			}
		});
		frame.getContentPane().add(DivB, BorderLayout.SOUTH);
		
	}
	
		/**
		 * Launch the application.
		 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					First window = new First();
					window.frame.setVisible(true);
					//window.paint(g);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	class MyPanel extends JPanel{
		Vector<Point> sv = new Vector<Point>(); // 시작
		Vector<Point> se = new Vector<Point>(); // 끝점
		
		public MyPanel(){
			this.addMouseListener(new MyMouseListener()); // 리스너
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); // 부모 페인트호출
			
			if(option == LINE) {
			if(sv.size() == 0) // 벡터에 암것도없으면 리턴
				return;
			
			for(int i=0;i<sv.size();i++){ //벡터크기만큼
				Point sp = sv.get(i); // 벡터값을꺼내다
				Point ep = se.get(i);	
				g.drawLine(sp.x, sp.y, ep.x, ep.y);//그리다
				}
			}
		}
		
		class MyMouseListener extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				sv.add(e.getPoint()); // 클릭한부분을 시작점으로
			}
			public void mouseReleased(MouseEvent e){
				se.add(e.getPoint()); // 드래그 한부분을 종료점으로
				repaint(); // 다시그려라
			}
		}
	}
	
	
}
	

	
//}

 
