
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class MJMJ extends JPanel implements MouseListener, MouseMotionListener {
	private JPanel contentPane;
	Color color = Color.BLACK;
	Color eraserC = new Color(128,128,128);
	int option;
	int Linesize;
	boolean fill = false;
	Point temp1 = new Point();
	Point temp2 = new Point();

	Vector<Point> tempP = new Vector<Point>();
	Vector<Point> eraP = new Vector<Point>();
	Vector<Integer> opt_vector = new Vector<Integer>();

	static ArrayList<Option> list = new ArrayList<Option>();
	Option newOption;

	public static final int DRAW = 1;
	public static final int LINE = 2;
	public static final int RECT = 3;
	public static final int CIRCLE = 4;
	public static final int PEN = 5;
	public static final int COLORS = 6;
	public static final int ERASER = 7;
	public static final int RESIZE = 8;
	private JButton btnRe;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Copyrightsⓒ.Minjoo.All Rights Reserved.");
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MJMJ ged = new MJMJ();
		Container contentPane = frame.getContentPane();
		contentPane.add(ged);
		contentPane.add(ged.contentPane);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Option l : list) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(l.stro));
			g.setColor(l.col_pen);
			
			if (/* option==LINE */l.shape.equals("LINE")) {
				g2.drawLine(l.ptr1.x, l.ptr1.y, l.ptr2.x, l.ptr2.y);
			} 
			
			else if (l.shape.equals("RECT")) {
				if (l.filling == true) {
					g2.fillRect(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				} else {
					g2.drawRect(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				}
			}
			else if (l.shape.equals("CIRCLE")) {

				if (l.filling == true) {
					g2.fillOval(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				} else {
					g2.drawOval(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				}
			}
			else if (l.shape.equals("PEN")) {
				for(int i = 0; i < l.ptrP.size()-1; i++) {
					g2.drawLine(l.ptrP.get(i).x, l.ptrP.get(i).y, l.ptrP.get(i+1).x, l.ptrP.get(i+1).y );
				}
				for(int i = 0; i < tempP.size()-1; i++) {
					g2.drawLine(tempP.get(i).x, tempP.get(i).y, tempP.get(i+1).x, tempP.get(i+1).y );
				}
				if(temp1 != null) {
					g2.drawLine(temp1.x, temp1.y, temp2.x, temp2.y);
				}
				
			}
			else if (l.shape.equals("ERASER")) {
				for(int i = 0; i < l.eraO.size()-1; i++) {
					g2.setColor(eraserC);
					g2.drawLine(l.eraO.get(i).x, l.eraO.get(i).y, l.eraO.get(i+1).x, l.eraO.get(i+1).y );
				}
				for(int i = 0; i < eraP.size()-1; i++) {
					g2.setColor(eraserC);
					g2.drawLine(eraP.get(i).x, eraP.get(i).y, eraP.get(i+1).x, eraP.get(i+1).y );
				}
				if(temp1 != null) {
					g2.setColor(eraserC);
					g2.drawLine(temp1.x, temp1.y, temp2.x, temp2.y);
				}
			}
			//eraser
			
			//g.setColor(WHITE);
			//drawline
		}
	}
 
	public MJMJ() {

		setBounds(0, 100, 2000, 1000);
		addMouseMotionListener(this);
		addMouseListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JButton undo = new JButton("←");
		undo.setForeground(new Color(255, 215, 0));
		undo.setBounds(6, 6, 36, 29);
		contentPane.add(undo);

		JButton redo = new JButton("→");
		redo.setForeground(new Color(255, 215, 0));
		redo.setBounds(54, 6, 36, 29);
		contentPane.add(redo);

		JPanel panel = new JPanel();
		panel.setBackground(color.WHITE);
		panel.setBounds(6, 0, 900, 1000);
		contentPane.add(panel);
		panel.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(Color.WHITE);
		toolBar.setBackground(Color.WHITE);
		toolBar.setBounds(0, 44, 463, 57);
		panel.add(toolBar);

		JButton line = new JButton("Line");
		line.setForeground(new Color(205, 92, 92));
		toolBar.add(line);
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = LINE;
			}
		});
		line.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton rect = new JButton("Rectangular");
		rect.setForeground(new Color(205, 92, 92));
		toolBar.add(rect);
		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = RECT;
			}
		});
		rect.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton circle = new JButton("Circle");
		circle.setForeground(new Color(205, 92, 92));
		toolBar.add(circle);
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = CIRCLE;
			}
		});
		circle.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		
		JButton pen = new JButton("PEN");
		pen.setForeground(new Color(205, 92, 92));
		pen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = PEN;
				System.out.println("PEN BUTTON");
			}
		});
		pen.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		toolBar.add(pen);

		// 선의 굵기 선택
		JLabel lblNewLabel = new JLabel("   굵기 ");
		lblNewLabel.setForeground(new Color(189, 183, 107));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toolBar.add(lblNewLabel);
		Integer[] LineSize = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		JComboBox comboBox = new JComboBox(LineSize);
		comboBox.setForeground(new Color(85, 107, 47));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();

				int index = combo.getSelectedIndex();
				Linesize = index + 1;
				System.out.println(Linesize);

			}
		});
		comboBox.setMaximumRowCount(10);
		toolBar.add(comboBox);

		JButton colors = new JButton("COLORS");
		colors.setForeground(new Color(95, 158, 160));
		colors.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		colors.setBounds(90, 6, 120, 40);
		panel.add(colors);

		btnRe = new JButton("Resize");
		btnRe.setBounds(6, 505, 80, 39);
		panel.add(btnRe);
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRe.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton fillB = new JButton("Fill");
		fillB.setForeground(new Color(95, 158, 160));
		fillB.setBackground(Color.PINK);
		fillB.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		fillB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fill == true) {
					fill = false;
					System.out.println("true->false");
				} else if (fill == false) {
					fill = true;
				}
			}
		});
		fillB.setBounds(210, 6, 70, 40);
		panel.add(fillB);
		
		JButton eraser = new JButton("ERASER");
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = ERASER; 
				System.out.println("ERASER");
			}
		});
		eraser.setForeground(new Color(95, 158, 160));
		eraser.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		eraser.setBackground(new Color(95, 158, 160));
		eraser.setBounds(280, 6, 100, 40);
		panel.add(eraser);

		colors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "COLORS", Color.BLUE);
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (newOption.shape.equals("LINE")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		} else if (newOption.shape.equals("RECT")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		} else if (newOption.shape.equals("CIRCLE")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		}else if (newOption.shape.equals("PEN")) {
			temp1 = e.getPoint();
			temp2 = e.getPoint();
			tempP.add(temp2);
			repaint();
		}else if (newOption.shape.equals("ERASER")) {
			temp1 = e.getPoint();
			temp2 = e.getPoint();
			eraP.add(temp2);
			repaint();
		}
			
		
		// else......
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		newOption = new Option();
		newOption.stro = Linesize;
		newOption.col_pen = color;
		newOption.filling = fill;
		
		// color, fill else.....
		if (option == LINE) {
			newOption.shape = "LINE";
		} else if (option == RECT) {
			newOption.shape = "RECT";
		} else if (option == CIRCLE) {
			newOption.shape = "CIRCLE";
		} else if (option == PEN) {
			newOption.shape = "PEN";
		} else if (option == ERASER) {
			newOption.shape = "ERASER";
		} 

		// else rect, oval, ....

		if (newOption.shape.equals("LINE")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		} 
		else if (newOption.shape.equals("RECT")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		}
		else if (newOption.shape.equals("CIRCLE")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		}
		else if (newOption.shape.equals("PEN")) {
			list.add(newOption);
		} 
		else if (newOption.shape.equals("ERASER")) {
			list.add(newOption);
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		temp1 = null;
		temp2 = null;
		if(newOption.shape.equals("PEN")) {
			for(Point p: tempP) {
				newOption.ptrP.add(p);
			}
			tempP.clear();
		}
		if(newOption.shape.equals("ERASER")) {
			for(Point p: tempP) {
				newOption.ptrP.add(p);
			}
			eraP.clear();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}