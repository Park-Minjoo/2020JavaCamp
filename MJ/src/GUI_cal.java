//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import javax.swing.JScrollPane;
//import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI_cal extends JFrame {

	/**
	 * Launch the application.
	 */
	 static JLabel label;
     static JLabel info;
     static int flag = 0;
     static int check = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_cal frame = new GUI_cal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_cal() {
		setTitle("Minjoossi Calculator");
		
		Container c = getContentPane();
//		c.setLayout(new BorderLayout(5,5));
//        c.setBackground(Color.BLACK);
//        
	
//        c.add(NP, BorderLayout.EAST);
//        
//        CenterPanel CP = new CenterPanel();
//        c.add(CP, BorderLayout.SOUTH);
//        setSize(500, 600);
//        setVisible(true);
//        CenterPanel CP = new CenterPanel();
		getContentPane().setBackground(new Color(250, 235, 215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		getContentPane().setLayout(null);
		
		NorthPanel NP = new NorthPanel();
		NP.setBackground(new Color(250, 235, 215));
		NP.setBounds(16, 57, 370, 201);
		getContentPane().add(NP);
	}	
	
	class NorthPanel extends JPanel {
        public NorthPanel() {
////    		JPanel panel = new JPanel();
//    		setBackground(new Color(250, 235, 215));
//    		setBounds(16, 57, 370, 201);
//    		getContentPane().add(NorthPanel);
    //	
//                setLayout(new GridLayout(3,1));
//                setBackground(Color.BLACK);
                info = new JLabel("안녕하세요 ");
                label = new JLabel("");

                info.setFont(new Font("맑은 고딕", 0, 20));
                info.setBackground(Color.BLACK);
                info.setForeground(Color.WHITE);
                info.setHorizontalAlignment(SwingConstants.RIGHT);

                label.setFont(new Font("맑은 고딕", 0, 40));
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.RIGHT);

                add(info); add(label);
                label.addMouseListener(new MyMouse());
        }
	}
	
	class MyMouse extends MouseAdapter {
          public void mousePressed(MouseEvent e) {
                  if (e.getClickCount() == 2) {
                          flag = 0;
                          label.setText("");
                          info.setText("수식을 입력하세요 ");
                  }
          }
	}
		
	class CenterPanel extends JPanel{
		public CenterPanel() {
		JButton btnNewButton = new JButton(".");
		btnNewButton.setToolTipText("소수점");
//		System.out.println(btnNewButton.getText());
		btnNewButton.setForeground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(240, 128, 128));
		btnNewButton.setBounds(6, 500, 95, 75);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_0 = new JButton("0");
//		System.out.println(btnNewButton_0.getText());
		btnNewButton_0.setForeground(new Color(85, 107, 47));
		btnNewButton_0.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_0.setBackground(Color.DARK_GRAY);
		btnNewButton_0.setBounds(105, 500, 95, 75);
		getContentPane().add(btnNewButton_0);
		
		JButton btnNewButton_plus = new JButton("+");
		btnNewButton_plus.setToolTipText("더하기");
		btnNewButton_plus.setForeground(new Color(128, 0, 0));
		btnNewButton_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_plus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_plus.setBackground(new Color(128, 128, 0));
		btnNewButton_plus.setBounds(305, 500, 90, 75);
		getContentPane().add(btnNewButton_plus);
		
		JButton btnNewButton_1 = new JButton("1");
		btnNewButton_1.setForeground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(6, 420, 95, 75);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("2");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_2.setForeground(new Color(85, 107, 47));
		btnNewButton_1_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_1_2.setBackground(new Color(0, 0, 0));
		btnNewButton_1_2.setBounds(105, 420, 95, 75);
		getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_3 = new JButton("3");
		btnNewButton_3.setForeground(new Color(85, 107, 47));
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.setBounds(205, 420, 95, 75);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_minus = new JButton("-");

		btnNewButton_minus.setToolTipText("빼기");
		btnNewButton_minus.setForeground(new Color(128, 0, 0));
		btnNewButton_minus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_minus.setBackground(Color.DARK_GRAY);
		btnNewButton_minus.setBounds(305, 420, 90, 75);
		getContentPane().add(btnNewButton_minus);
		
		JButton btnNewButton_4 = new JButton("4");
		btnNewButton_4.setForeground(new Color(85, 107, 47));
		btnNewButton_4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_4.setBackground(Color.DARK_GRAY);
		btnNewButton_4.setBounds(6, 340, 95, 75);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("5");
		btnNewButton_5.setForeground(new Color(85, 107, 47));
		btnNewButton_5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_5.setBackground(Color.DARK_GRAY);
		btnNewButton_5.setBounds(105, 340, 95, 75);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("6");
		btnNewButton_6.setForeground(new Color(85, 107, 47));
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_6.setBackground(Color.DARK_GRAY);
		btnNewButton_6.setBounds(204, 340, 95, 75);
		getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_mul = new JButton("*");
		btnNewButton_mul.setToolTipText("곱하기");
		btnNewButton_mul.setForeground(new Color(128, 0, 0));
		btnNewButton_mul.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_mul.setBackground(Color.DARK_GRAY);
		btnNewButton_mul.setBounds(304, 340, 90, 75);
		getContentPane().add(btnNewButton_mul);
		
		JButton btnNewButton_7 = new JButton("7");
		btnNewButton_7.setForeground(new Color(85, 107, 47));
		btnNewButton_7.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_7.setBackground(Color.DARK_GRAY);
		btnNewButton_7.setBounds(6, 260, 95, 75);
		getContentPane().add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("8");
		btnNewButton_8.setForeground(new Color(85, 107, 47));
		btnNewButton_8.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_8.setBackground(Color.DARK_GRAY);
		btnNewButton_8.setBounds(105, 260, 95, 75);
		getContentPane().add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("9");
		btnNewButton_9.setForeground(new Color(85, 107, 47));
		btnNewButton_9.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_9.setBackground(Color.DARK_GRAY);
		btnNewButton_9.setBounds(205, 260, 95, 75);
		getContentPane().add(btnNewButton_9);
		
		JButton btnNewButton_div = new JButton("/");
		btnNewButton_div.setToolTipText("나누기");
		btnNewButton_div.setForeground(new Color(128, 0, 0));
		btnNewButton_div.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_div.setBackground(Color.DARK_GRAY);
		btnNewButton_div.setBounds(305, 260, 90, 75);
		getContentPane().add(btnNewButton_div);
		
		JButton btnNewButton_eq = new JButton("=");
		btnNewButton_eq.setToolTipText("결과는?");
		btnNewButton_eq.setForeground(new Color(128, 0, 0));
		btnNewButton_eq.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_eq.setBackground(Color.DARK_GRAY);
		btnNewButton_eq.setBounds(205, 500, 95, 75);
		getContentPane().add(btnNewButton_eq);
		
		JButton btnNewButton_AC = new JButton("AC");
		btnNewButton_AC.setToolTipText("초기화");
		btnNewButton_AC.setForeground(new Color(100, 149, 237));
		btnNewButton_AC.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnNewButton_AC.setBackground(Color.DARK_GRAY);
		btnNewButton_AC.setBounds(6, 6, 50, 50);
		getContentPane().add(btnNewButton_AC);
		
		JButton btnNewButton_qu = new JButton("?");
		btnNewButton_qu.setToolTipText("눌러보세요 !");
		btnNewButton_qu.setForeground(new Color(100, 149, 237));
		btnNewButton_qu.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnNewButton_qu.setBackground(Color.DARK_GRAY);
		btnNewButton_qu.setBounds(345, 6, 50, 50);
		getContentPane().add(btnNewButton_qu);	
	/*	
		for (int i = 0; i <= 15; i++) {
            if (i%4 != 3 && i < 11) {
                    bt[i].setFont(new Font("맑은 고딕", 0, 30));
                    bt[i].setBackground(Color.GRAY);
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);

                    bt[i].addActionListener(new ActionListener () {
                            public void actionPerformed(ActionEvent e) {
                                    if (flag == 0) {
                                            JButton b = (JButton)e.getSource();
                                            String oldtext = label.getText();
                                            String text = b.getText();
                                            String newtext = oldtext + text;

                                            int n = newtext.length();
                                            if (n <= 10) label.setFont(new Font("맑은 고딕", 0, 40));
                                            else if (n > 10) label.setFont(new Font("맑은 고딕", 0, 30));

                                            if (n <= 25) {
                                                    label.setText(newtext);
                                                    info.setText("수식을 계산 중입니다 ");
                                            }
                                            else if (n > 25) info.setText("입력 가능한 범위를 초과하였습니다 ");
                                    }
                            }
                    });
            }
            else if (i == 12 || i == 14) {
                    bt[i].setFont(new Font("맑은 고딕", 0, 30));
                    bt[i].setBackground(Color.LIGHT_GRAY);
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);

                    bt[12].addActionListener(new ActionListener () {
                            public void actionPerformed(ActionEvent e) {
                                    int n = label.getText().length()-1;

                                    if (n == 0) {
                                            label.setText("");
                                            info.setText("수식을 입력하세요 ");
                                            flag = 0;
                                    }
                                    else if (n > 0 && n <= 10) {
                                            label.setFont(new Font("맑은 고딕", 0, 40));
                                            label.setText(label.getText().substring(0, n));
                                            info.setText("수식을 지우는 중입니다 ");
                                    }
                                    else {
                                            label.setFont(new Font("맑은 고딕", 0, 35));
                                            label.setText(label.getText().substring(0, n));
                                            info.setText("수식을 지우는 중입니다 ");
                                    }
                            }
                    });
                    bt[14].addActionListener(new CalcListener());
            }

            else if (i % 4 == 3){
                    bt[i].setFont(new Font("맑은 고딕", 0, 40));
                    bt[i].setBackground(new Color (234,150,72));
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);
                    bt[i].addActionListener(new MyListener());
            }

            else if (i == 13) {
                    bt[i].setFont(new Font("맑은 고딕", 0, 30));
                    bt[i].setBackground(Color.GRAY);
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);
                    bt[i].addActionListener(new MyListener());
            }
		}		
	}
}
	
	private class MyListener implements ActionListener{
             public void actionPerformed(ActionEvent e) {
                     JButton b = (JButton)e.getSource();
                     int n = label.getText().length();
                     Character c = label.getText().charAt(n-1);
                     if (flag == 0 && c != '+' && c != '-' && c != '×' && c != '÷') {
                             String oldtext = label.getText();
                             String text = b.getText();
                             String newtext = oldtext + text;
                             label.setText(newtext);
                             info.setText("수식을 계산 중입니다 ");
                     }
             }
     }
	public class CalcListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
                String s = label.getText();
                double result = Calculator(s);
                label.setFont(new Font("맑은 고딕", 0, 40));

                if (result < 0) {
                        info.setText("양수 범위의 계산만 할 수 있습니다 ");
                        label.setText(Double.toString(result));
                        flag = 1;
                }
                else if (result >= 10000000) {
                        info.setText("10,000,000 미만의 범위만 계산할 수 있습니다 ");
                        label.setText("0.0");
                }
                else label.setText(Double.toString(result));
        }*/
}
    public double Calculator(String ss) {
        int i; double ans;
        check = 0;
        ArrayList<Double> v = new ArrayList<Double>();
        ArrayList<String> op = new ArrayList<String>();

        op.add(null);
        String str = new String("");
        for (i = 0; i < ss.length(); i++) {
                Character c = ss.charAt(i);
                String s = Character.toString(c);

                if(Character.isDigit(c)) {
                        str += Character.toString(c);
                        if(i == ss.length()-1)
                                v.add(Double.parseDouble(str));
                }
                else if (s.equals(".")) str += s;
                else {
                        v.add(Double.parseDouble(str));
                        op.add(Character.toString(c));
                        str = "";
                }
        }
        for(i = 0; i < v.size(); i++) {
                if (v.get(i) >= 10000000) {
                        check = 1;
                        info.setText("10,000,000 미만의 수끼리만 계산할 수 있습니다 ");
                        break;
                }
        }
        if (check == 0) {
                for(i = 1; i < v.size(); i++) {
                        String s = op.get(i);
                        double tmp;

                        if (s.equals("×")) {
                                tmp = v.get(i-1) * v.get(i);
                                op.remove(i);
                                v.remove(i);
                                v.remove(i-1);
                                v.add(i-1, tmp);
                                i--;
                        }
                        else if (s.equals("÷")) {
                            tmp = v.get(i-1) / v.get(i);
                            op.remove(i);
                            v.remove(i);
                            v.remove(i-1);
                            v.add(i-1, tmp);
                            i--;
                    }
            }
            ans = v.get(0);
            for(i = 1; i < v.size(); i++) {
                    String s = op.get(i);
                    double n = v.get(i);

                    if(s.compareTo("+")==0) ans = ans + n;
                    else if(s.compareTo("-")==0) ans = ans - n;
            }

            return ans;
    }

        return 0;
    }
    

	
	}	
}
	
