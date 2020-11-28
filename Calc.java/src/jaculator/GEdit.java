package jaculator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class GEdit extends JPanel implements MouseListener, MouseMotionListener {
	private JPanel contentPane;
//	Shape shape = new ;

	Color color = Color.BLACK;
	Color eraserC = new Color(128, 128, 128);
	int option = SELECTOR;
	int Linesize;
	boolean fill = false;
	boolean sel = false;
	boolean moveMode = false;
	Point temp1 = new Point(); // pen, eraser
	Point temp2 = new Point(); // pen, eraser

	Point temp3 = new Point(); // sel.start
	Point temp4 = new Point(); // sel.end

	Vector<Point> tempP = new Vector<Point>();
	Vector<Point> eraP = new Vector<Point>();
	Vector<Integer> opt_vector = new Vector<Integer>();

	static ArrayList<Option> copy = new ArrayList<Option>();
	static ArrayList<Option> list = new ArrayList<Option>();
	static ArrayList<Option> list2 = new ArrayList<Option>();
	Option newOption = new Option();
	Option copyOption = new Option();

	public static final int DRAW = 1;
	public static final int LINE = 2;
	public static final int RECT = 3;
	public static final int CIRCLE = 4;
	public static final int PEN = 5;
	public static final int COLORS = 6;
	public static final int ERASER = 7;
	public static final int CLEAR = 8;
	public static final int COPY = 9;
	public static final int SELECTOR = 10;
	public static final int CANCEL = 11;
	public static final int TEXT = 12;
	
	private JButton coB;
	private JButton paB;
	private JButton front;
	private JButton back;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Copyrightsⓒ.Minjoo.All Rights Reserved.");
		frame.setBounds(100, 100, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GEdit ged = new GEdit();
		ged.setBackground(Color.WHITE);
		Container contentPane = frame.getContentPane();
		contentPane.add(ged);
		contentPane.add(ged.contentPane);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (sel) {
//			System.out.println(temp3.toString()+ temp4.toString());
			g2.setColor(Color.GREEN);
			g2.setStroke(
					new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1f, new float[] { 10 }, 2f));
			g2.drawRect(Math.min(temp3.x, temp4.x), Math.min(temp3.y, temp4.y), Math.abs(temp3.x - temp4.x),
					Math.abs(temp3.y - temp4.y));

		}
		if (option != SELECTOR) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).selected = false;
			}
		}

		for (Option l : list) {
//			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(l.stro));
			g2.setColor(l.col_pen);

			if (/* option==LINE */l.shape.equals("LINE")) {
				g2.drawLine(l.ptr1.x, l.ptr1.y, l.ptr2.x, l.ptr2.y);
				if (l.selected == true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1f,
							new float[] { 10 }, 2f));
					g2.setColor(Color.RED);
					g2.drawLine(l.ptr1.x, l.ptr1.y, l.ptr2.x, l.ptr2.y);
				}
			}

			else if (l.shape.equals("RECT")) {
//				System.out.println("l.selected"+ l.selected);

				if (l.filling == true) {
					g2.fillRect(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				} else {
					g2.drawRect(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				}
				if (l.selected == true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1f,
							new float[] { 10 }, 2f));
					g2.setColor(Color.RED);
					g2.drawRect(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				}
			} else if (l.shape.equals("CIRCLE")) {

				if (l.filling == true) {
					g2.fillOval(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				} else {
					g2.drawOval(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				}
				if (l.selected == true) {
					g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1f,
							new float[] { 10 }, 2f));
					g2.setColor(Color.RED);
					g2.drawOval(Math.min(l.ptr1.x, l.ptr2.x), Math.min(l.ptr1.y, l.ptr2.y),
							Math.abs(l.ptr1.x - l.ptr2.x), Math.abs(l.ptr1.y - l.ptr2.y));
				}
			} else if (l.shape.equals("PEN")) {
				System.out.println(l.selected);
				for (int i = 0; i < l.ptrP.size() - 1; i++) {
					g2.drawLine(l.ptrP.get(i).x, l.ptrP.get(i).y, l.ptrP.get(i + 1).x, l.ptrP.get(i + 1).y);
					if (l.selected == true) {
						g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1f,
								new float[] { 10 }, 2f));
						g2.setColor(Color.RED);
						g2.drawLine(l.ptrP.get(i).x, l.ptrP.get(i).y, l.ptrP.get(i + 1).x, l.ptrP.get(i + 1).y);
					}
				}
				for (int i = 0; i < tempP.size() - 1; i++) {
					g2.drawLine(tempP.get(i).x, tempP.get(i).y, tempP.get(i + 1).x, tempP.get(i + 1).y);
					if (l.selected == true) {
						g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1f,
								new float[] { 10 }, 2f));
						g2.setColor(Color.RED);
						g2.drawLine(tempP.get(i).x, tempP.get(i).y, tempP.get(i + 1).x, tempP.get(i + 1).y);
					}
				}
//				if (temp1 != null) {
//					g2.drawLine(temp1.x, temp1.y, temp2.x, temp2.y);
//				}

			} else if (l.shape.equals("ERASER")) {
				for (int i = 0; i < l.eraO.size() - 1; i++) {
					g2.setColor(color.WHITE);
					g2.drawLine(l.eraO.get(i).x, l.eraO.get(i).y, l.eraO.get(i + 1).x, l.eraO.get(i + 1).y);
				}
				for (int i = 0; i < eraP.size() - 1; i++) {
					g2.setColor(color.WHITE);
					g2.drawLine(eraP.get(i).x, eraP.get(i).y, eraP.get(i + 1).x, eraP.get(i + 1).y);
				}
//				if (temp1 != null) {
//					g2.setColor(color.WHITE);
//					g2.drawLine(temp1.x, temp1.y, temp2.x, temp2.y);
//				}
			} else if(l.shape.equals("TEXT")) {
//				byte[] bArray = {65,66,67};   // ASCII code A.B.C
//				  char[] cArray = {'a','b','c'};
				  String str = "( ღ'ᴗ'ღ )";

//				  g2.drawBytes(bArray, 0, bArray.length,10,30);
//				  g2.drawChars(cArray, 0, cArray.length,10,60);
				  g2.setFont(new Font("맑은 고딕",Font.BOLD,30));
				  g2.drawString(str,l.ptr1.x, l.ptr1.y);
			}
		}
	}

	public GEdit() {

		setBounds(0, 100, 2000, 1000);
		addMouseMotionListener(this);
		addMouseListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JButton undo = new JButton("←");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					list2.add(list.get(list.size() - 1));
					list.remove(list.size() - 1);
					repaint();
				} catch (Exception error) {
				}
			}
		});
		undo.setForeground(Color.PINK);
		undo.setBounds(6, 6, 36, 29);
		contentPane.add(undo);

		JButton redo = new JButton("→");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					list.add(list2.get(list2.size() - 1));
					list2.remove(list2.get(list2.size() - 1));
					repaint();
				} catch (Exception error) {
				}

			}
		});
		redo.setForeground(Color.PINK);
		redo.setBounds(54, 6, 36, 29);
		contentPane.add(redo);

		JPanel panel = new JPanel();
		panel.setBackground(color.WHITE);
		panel.setBounds(6, 0, 2000, 1000);
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
				repaint();
			}
		});
		line.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton rect = new JButton("Rectangular");
		rect.setForeground(new Color(205, 92, 92));
		toolBar.add(rect);
		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = RECT;
				repaint();
			}
		});
		rect.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton circle = new JButton("Circle");
		circle.setForeground(new Color(205, 92, 92));
		toolBar.add(circle);
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = CIRCLE;
				repaint();
			}
		});
		circle.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton pen = new JButton("Pen");
		pen.setForeground(new Color(205, 92, 92));
		pen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = PEN;
				System.out.println("PEN BUTTON");
				repaint();
			}
		});
		pen.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		toolBar.add(pen);
		
		JButton text = new JButton("♡");
		text.setForeground(new Color(205, 92, 92));
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = TEXT;
				System.out.println("TEXT B");
				repaint();
			}
		});
		text.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		toolBar.add(text);

		// 선의 굵기 선택
		JLabel lblNewLabel = new JLabel("   굵기 ");
		lblNewLabel.setForeground(new Color(189, 183, 107));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		toolBar.add(lblNewLabel);
		Integer[] LineSize = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30 };

		JComboBox comboBox = new JComboBox(LineSize);
		comboBox.setForeground(new Color(85, 107, 47));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				int index = combo.getSelectedIndex();
				Linesize = index + 5;
				System.out.println(Linesize);
			}
		});
		comboBox.setMaximumRowCount(11);
		toolBar.add(comboBox);

		JButton colors = new JButton("COLORS");
		colors.setForeground(new Color(95, 158, 160));
		colors.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		colors.setBounds(90, 6, 120, 40);
		panel.add(colors);

		coB = new JButton("COPY");
		coB.setBounds(715, 6, 90, 40);
		panel.add(coB);
		coB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i <= list.size() - 1; i++) {
					if (list.get(i).selected == true) {
						copyOption = new Option();
						copyOption.shape = list.get(i).shape;
						copyOption.col_pen = list.get(i).col_pen;
						copyOption.stro = list.get(i).stro;
						copyOption.filling = list.get(i).filling;
						if (list.get(i).shape.equals("PEN")) {
							for (Point p : list.get(i).ptrP) {
								copyOption.ptrP.add(new Point(p.x, p.y));
							}
						} else if (list.get(i).shape.equals("ERASER")) {
							for (Point p : list.get(i).eraO) {
								copyOption.eraO.add(new Point(p.x, p.y));
							}
						} else {
							copyOption.ptr1 = new Point(list.get(i).ptr1.x, list.get(i).ptr1.y);
							copyOption.ptr2 = new Point(list.get(i).ptr2.x, list.get(i).ptr2.y);
						}

						copy.add(copyOption);
					}
				}
			}
		});
		coB.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		paB = new JButton("PASTE");
		paB.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		paB.setBounds(805, 6, 90, 40);
		panel.add(paB);
		paB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < copy.size(); i++) {
					if (copy.get(i).shape.equals("PEN")) {
						for (int j = 0; j < copy.get(i).ptrP.size(); j++) {
							copy.get(i).ptrP.get(j).x += 30;
							copy.get(i).ptrP.get(j).y += 30;
						}
					} else {
						copy.get(i).ptr1.x += 30;
						copy.get(i).ptr1.y += 30;
						copy.get(i).ptr2.x += 30;
						copy.get(i).ptr2.y += 30;
					}
					list.add(copy.get(i));
					repaint();
				}
				copy.clear();
			}
		});
		paB.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		front = new JButton("FRONT");
		front.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		front.setBounds(895, 6, 90, 40);
		panel.add(front);
		front.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i = 0; i < list.size(); i++) {
						if(list.get(i).selected == true) {
							list.add(list.get(i));
							list.remove(i);
						}
						repaint();
					}
					
				} catch (Exception error) {
				}
				
			}
		});
		front.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		

		back = new JButton("BACK");
		back.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		back.setBounds(895+90, 6, 90, 40);
		panel.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i = 0; i < list.size(); i++) {
						if(list.get(i).selected == true) {
							list.add(0,list.get(i));
							list.remove(i+1);
						}
						repaint();
					}
					
				} catch (Exception error) {
				}
				
			}
		});
		back.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

		JButton fillB = new JButton("FILL");
		fillB.setForeground(new Color(95, 158, 160));
		fillB.setBackground(Color.PINK);
		fillB.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		fillB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fill == true) {
					fill = false;
//					System.out.println("true->false");
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

		JButton clear = new JButton("CLEAR");
		clear.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.clear();
				repaint();
			}
		});
		clear.setBounds(380, 6, 105, 40);
		panel.add(clear);

		JButton sel_mod = new JButton("SelectMode");
		sel_mod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = SELECTOR;
			}
		});
		sel_mod.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		sel_mod.setBounds(485, 6, 120, 40);
		panel.add(sel_mod);

		JButton cancel = new JButton("CANCEL");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i <= list.size() - 1; i++) {
					if (list.get(i).selected == true) {
						list2.add(list.get(i));
						list.remove(list.get(i));
					}
				}
				repaint();
			}
		});
		cancel.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
		cancel.setBounds(605, 6, 110, 40);
		panel.add(cancel);

		colors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(null, "COLORS", Color.BLUE);
			}
		});

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Shape selector_box = new Rectangle2D.Double(temp1.x,temp1.y,temp2.x,temp2.y);

		if (newOption.shape.equals("LINE")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		} else if (newOption.shape.equals("RECT")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		} else if (newOption.shape.equals("CIRCLE")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		} else if (newOption.shape.equals("PEN")) {
			temp1 = e.getPoint();
			temp2 = e.getPoint();
			tempP.add(temp2);
			repaint();
		} else if (newOption.shape.equals("ERASER")) {
			temp1 = e.getPoint();
			temp2 = e.getPoint();
			eraP.add(temp2);
			repaint();
		} else if (newOption.shape.equals("SELECTOR")) {
			temp4 = e.getPoint();
			if (moveMode) {
				double dx = temp4.x - temp3.x;
				double dy = temp4.y - temp3.y;
				moveShapes(dx, dy);
				temp3 = temp4;
			}

			else {
				Shape selector_box = new Rectangle2D.Double(Math.min(temp3.x, temp4.x), Math.min(temp3.y, temp4.y),
						Math.abs(temp4.x - temp3.x), Math.abs(temp4.y - temp3.y));
				for (int i = 0; i < list.size(); i++) {
					if (select(list.get(i), selector_box)) {
						list.get(i).selected = true;
						// System.out.println(list.get(i).selected);
					} else
						list.get(i).selected = false;
				}
			}
			repaint();
		} else if (newOption.shape.equals("TEXT")) {
			list.get(list.size() - 1).ptr2 = e.getPoint();
			repaint();
		}
	}

	public void moveShapes(double dx, double dy) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).selected == true) {
				if (list.get(i).shape.equals("PEN")) {
					for (int j = 0; j < list.get(i).ptrP.size(); j++) {
						list.get(i).ptrP.get(j).x += dx;
						list.get(i).ptrP.get(j).y += dy;
					}
				} else {
					list.get(i).ptr1.x += dx;
					list.get(i).ptr1.y += dy;
					list.get(i).ptr2.x += dx;
					list.get(i).ptr2.y += dy;
				}
			}
		}
	}

	public boolean select(Option tempoption, Shape selector_box) {
		if (tempoption.shape.equals("RECT") || tempoption.shape.equals("CIRCLE")) {
			tempoption.shapeobj = new Rectangle2D.Double(Math.min(tempoption.ptr1.getX(), tempoption.ptr2.getX()),
					Math.min(tempoption.ptr1.getY(), tempoption.ptr2.getY()),
					Math.abs(tempoption.ptr1.x - tempoption.ptr2.x), Math.abs(tempoption.ptr1.y - tempoption.ptr2.y));
		} else if (tempoption.shape.equals("LINE")) {
			tempoption.shapeobj = new Line2D.Double(tempoption.ptr1.x, tempoption.ptr1.y, tempoption.ptr2.x,
					tempoption.ptr2.y);
		} else if (tempoption.shape.equals("PEN")) {
			double maxX = 0;
			double minX = 1200;
			double maxY = 0;
			double minY = 1200;
			for (int i = 0; i < tempoption.ptrP.size(); i++) {
				maxX = Math.max(tempoption.ptrP.get(i).x, maxX);
				minX = Math.min(tempoption.ptrP.get(i).x, minX);
				maxY = Math.max(tempoption.ptrP.get(i).y, maxY);
				minY = Math.min(tempoption.ptrP.get(i).y, minY);
//				System.out.println("minX: " + minX + "minY: " + minY + "maxX: " + maxX + "maxY: " + maxY);
				tempoption.shapeobj = new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
			}
		} else
			return false;

		if (selector_box.contains(tempoption.shapeobj.getBounds2D())) {
			return true;
		} else
			return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		temp4 = e.getPoint();
		Shape selector_box = new Rectangle2D.Double(Math.min(temp3.x, temp4.x), Math.min(temp3.y, temp4.y),
				Math.abs(temp4.x - temp3.x), Math.abs(temp4.y - temp3.y));

		for (int i = 0; i < list.size(); i++) {
			if (select(list.get(i), selector_box)) {
				list.get(i).selected = true;
			} else
				list.get(i).selected = false;
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		newOption = new Option();
		newOption.stro = Linesize;
		newOption.col_pen = color;
		newOption.filling = fill;
		newOption.selected = sel;

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
		} else if (option == SELECTOR) {
			sel = true;
			System.out.println(sel);
			temp3 = e.getPoint();
			if (mouseContained(e)) {
				moveMode = true;
			} else
				moveMode = false;
//		} else if(option == CANCEL) {
//			if(sel == true) {
//				
//			}
		} else if (option == TEXT) {
			newOption.shape = "TEXT";
		} 

		// else rect, oval, ....

		if (newOption.shape.equals("LINE")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		} else if (newOption.shape.equals("RECT")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		} else if (newOption.shape.equals("CIRCLE")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		} else if (newOption.shape.equals("PEN")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		} else if (newOption.shape.equals("ERASER")) {
			list.add(newOption);
		} else if (newOption.shape.equals("TEXT")) {
			newOption.ptr1 = e.getPoint();
			list.add(newOption);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		temp1 = null;
//		temp2 = null;
		if (newOption.shape.equals("LINE")) {
			newOption.ptr2 = e.getPoint();
		}
		if (newOption.shape.equals("PEN")) {
			for (Point p : tempP) {
				newOption.ptrP.add(p);
			}
			tempP.clear();
		}
		if (newOption.shape.equals("ERASER")) {
			for (Point p : eraP) {
				newOption.eraO.add(p);
			}
			eraP.clear();
		}
		if (newOption.shape.equals("SELECTOR")) {
			sel = false;
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public boolean mouseContained(MouseEvent e) {
		double maxX = 0;
		double minX = 1200;
		double maxY = 0;
		double minY = 1200;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).selected == true) {
				Shape rectShape = new Rectangle2D.Double(0, 0, 0, 0);
				if (list.get(i).shape.equals("PEN")) {
					for (int j = 0; j < list.get(i).ptrP.size(); j++) {
						maxX = Math.max(list.get(i).ptrP.get(j).x, maxX);
						minX = Math.min(list.get(i).ptrP.get(j).x, minX);
						maxY = Math.max(list.get(i).ptrP.get(j).y, maxY);
						minY = Math.min(list.get(i).ptrP.get(j).y, minY);

						rectShape = new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);

					}
				} else {
					double[] pointData = convertCoo(list.get(i).ptr1, list.get(i).ptr2);
					rectShape = new Rectangle2D.Double(pointData[0], pointData[1], pointData[2], pointData[3]);
				}

				if (rectShape.contains(e.getPoint())) {
					return true;
				}
			}
		}
		return false;
	}

	public double[] convertCoo(Point p1, Point p2) {
		Math.min(p1.x, p2.x);
		Math.min(p1.y, p2.y);
		Math.abs(p1.x - p2.x);
		Math.abs(p1.y - p2.y);

		return new double[] { Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p1.x - p2.x),
				Math.abs(p1.y - p2.y) };
	}

}