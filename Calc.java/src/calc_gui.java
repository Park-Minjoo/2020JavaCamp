import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class calc_gui extends JFrame implements ActionListener{
	JLabel lblNewLabel = new JLabel("");
	 private String currentContent;
	 boolean eq = false;
	 private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calc_gui frame = new calc_gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 @Override
	public void actionPerformed(ActionEvent e) {
		String currentContent = lblNewLabel.getText();	//현재 입력한 방정식
		//label.setText("하고싶은말");
/* String button=e.getActionCommand();*/
        int tempChar = 0;
		String tempString = null;
        switch (e.getActionCommand())
        {
        
            case "AC":
            	lblNewLabel.setText("0");
                break;
            case "?":
            	new newWindow(btnNewButton,
           	 btnNewButton_0,	
        	 btnNewButton_plus,
        	 btnNewButton_1,
        	 btnNewButton_2,
        	 btnNewButton_3,
        	 btnNewButton_minus,
        	 btnNewButton_4,
        	 btnNewButton_5,
        	 btnNewButton_6,
        	 btnNewButton_7,
        	 btnNewButton_8,
        	 btnNewButton_9,
        	 btnNewButton_mul,
        	 btnNewButton_div,
        	 btnNewButton_eq,
        	 btnNewButton_AC,
        	 btnNewButton_qu ); 
            	
            	break;
            case "0":
                if (currentContent.equals("0"))
                {
                    return;
                }
                else
                {
                	lblNewLabel.setText(currentContent + e.getActionCommand());
                }
                eq = false;
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (eq || currentContent.equals("0") )
                {
                	lblNewLabel.setText(e.getActionCommand());
                }
                else
                {
                	lblNewLabel.setText(currentContent + e.getActionCommand());
                }
                eq = false;
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                tempChar = currentContent.codePointAt(currentContent.length() - 1);
                if (!Character.isDigit(tempChar))
                {
                    return;
                }
                else
                {
                	lblNewLabel.setText(currentContent + e.getActionCommand());
                }
                eq = false;
                break;
            case ".":
                tempChar = currentContent.codePointAt(currentContent.length() - 1);
                if (!Character.isDigit(tempChar))
                {
                    return;
                }
                else
                {
                    if (-1 != currentContent.lastIndexOf('.'))
                    {
                        tempString = currentContent.substring(currentContent.lastIndexOf('.') + 1, currentContent.length());
                    }
                    lblNewLabel.setText(currentContent + e.getActionCommand());
                }
                eq = false;
                break;
            case "=":
            	CalculatorParser calculatorParser = new CalculatorParser();
            	eq = true;
	            //currentContent = lblNewLabel.getText();
	            
//	            if (currentContent.equals("=")) 
	            /*
	            String result=calculatorParser.getRPN(currentContent);
	            String result2=calculatorParser.getResult(result);
	            lblNewLabel.setText(result2);
	            */
	            lblNewLabel.setText(calculatorParser.getResult(calculatorParser.getRPN(lblNewLabel.getText())));
//	           String ans = calculatorParser.getResult(calculatorParser.getRPN(e.getActionCommand()));
//	        

        }
		
	}
	 
	 public boolean isNumeric(String input) { 
		 try { 
			 Double.parseDouble(input); 
			 return true; 
			 } 
		 catch (NumberFormatException e) { 
			 return false; 
			 } 
		 }
	 

	 
//	 class EqualButtonAction implements ActionListener
//	    {
//
//	        CalculatorParser calculatorParser = new CalculatorParser();
//	        public String currentContent;
//
//	        @Override
//	        public void actionPerformed(ActionEvent e)
//	        {
//	            currentContent = lblNewLabel.getText();
//	            System.out.println(currentContent);
//	         
//	            if (e.getActionCommand().equals("=")) {
//
//	            lblNewLabel.setText(calculatorParser.getResult(calculatorParser.getRPN(lblNewLabel.getText())));
//	            }
//	        }
//
//	    }
		
	
	/**
	 * Create the frame.
	 */
	 JButton btnNewButton;
	 JButton btnNewButton_0;	
	 JButton btnNewButton_plus;
	 JButton btnNewButton_1;
	 JButton btnNewButton_2;
	 JButton btnNewButton_3;
	 JButton btnNewButton_minus;
	 JButton btnNewButton_4;
	 JButton btnNewButton_5;
	 JButton btnNewButton_6;
	 JButton btnNewButton_7;
	 JButton btnNewButton_8;
	 JButton btnNewButton_9;
	 JButton btnNewButton_mul;
	 JButton btnNewButton_div;
	 JButton btnNewButton_eq;
	 JButton btnNewButton_AC;
	 JButton btnNewButton_qu;
	
	public calc_gui() {
		setTitle("Welcome to Minjoo Calculator");
		getContentPane().setBackground(new Color(250, 235, 215));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		getContentPane().setLayout(null);
		
		btnNewButton = new JButton(".");
		btnNewButton.addActionListener(this);
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
		
		btnNewButton_0 = new JButton("0");
		btnNewButton_0.addActionListener(this);
//		System.out.println(btnNewButton_0.getText());
		btnNewButton_0.setForeground(new Color(85, 107, 47));
		btnNewButton_0.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_0.setBackground(Color.DARK_GRAY);
		btnNewButton_0.setBounds(105, 500, 95, 75);
		getContentPane().add(btnNewButton_0);
		
		btnNewButton_plus = new JButton("+");
		btnNewButton_plus.addActionListener(this);
		btnNewButton_plus.setToolTipText("더하기 ");
		btnNewButton_plus.setForeground(new Color(128, 0, 0));
		btnNewButton_plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_plus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_plus.setBackground(new Color(128, 128, 0));
		btnNewButton_plus.setBounds(305, 500, 90, 75);
		getContentPane().add(btnNewButton_plus);
		
		btnNewButton_1 = new JButton("1");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setForeground(new Color(85, 107, 47));
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(6, 420, 95, 75);
		getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("2");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setForeground(new Color(85, 107, 47));
		btnNewButton_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_2.setBackground(new Color(0, 0, 0));
		btnNewButton_2.setBounds(105, 420, 95, 75);
		getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("3");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setForeground(new Color(85, 107, 47));
		btnNewButton_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.setBounds(205, 420, 95, 75);
		getContentPane().add(btnNewButton_3);
		
		btnNewButton_minus = new JButton("-");
		btnNewButton_minus.addActionListener(this);
		btnNewButton_minus.setToolTipText("빼기 ");
		btnNewButton_minus.setForeground(new Color(128, 0, 0));
		btnNewButton_minus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_minus.setBackground(Color.DARK_GRAY);
		btnNewButton_minus.setBounds(305, 420, 90, 75);
		getContentPane().add(btnNewButton_minus);
		
		btnNewButton_4 = new JButton("4");
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setForeground(new Color(85, 107, 47));
		btnNewButton_4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_4.setBackground(Color.DARK_GRAY);
		btnNewButton_4.setBounds(6, 340, 95, 75);
		getContentPane().add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("5");
		btnNewButton_5.addActionListener(this);
		btnNewButton_5.setForeground(new Color(85, 107, 47));
		btnNewButton_5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_5.setBackground(Color.DARK_GRAY);
		btnNewButton_5.setBounds(105, 340, 95, 75);
		getContentPane().add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("6");
		btnNewButton_6.addActionListener(this);
		btnNewButton_6.setForeground(new Color(85, 107, 47));
		btnNewButton_6.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_6.setBackground(Color.DARK_GRAY);
		btnNewButton_6.setBounds(204, 340, 95, 75);
		getContentPane().add(btnNewButton_6);
		
		btnNewButton_mul = new JButton("*");
		btnNewButton_mul.setToolTipText("곱하기 ");
		
		btnNewButton_mul.addActionListener(this);
		btnNewButton_mul.setForeground(new Color(128, 0, 0));
		btnNewButton_mul.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_mul.setBackground(Color.DARK_GRAY);
		btnNewButton_mul.setBounds(304, 340, 90, 75);
		getContentPane().add(btnNewButton_mul);
		
		btnNewButton_7 = new JButton("7");
		btnNewButton_7.addActionListener(this);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_7.setForeground(new Color(85, 107, 47));
		btnNewButton_7.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_7.setBackground(Color.DARK_GRAY);
		btnNewButton_7.setBounds(6, 260, 95, 75);
		getContentPane().add(btnNewButton_7);
		
		btnNewButton_8 = new JButton("8");
		btnNewButton_8.addActionListener(this);
		btnNewButton_8.setForeground(new Color(85, 107, 47));
		btnNewButton_8.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_8.setBackground(Color.DARK_GRAY);
		btnNewButton_8.setBounds(105, 260, 95, 75);
		getContentPane().add(btnNewButton_8);
		
		btnNewButton_9 = new JButton("9");
		btnNewButton_9.addActionListener(this);
		btnNewButton_9.setForeground(new Color(85, 107, 47));
		btnNewButton_9.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_9.setBackground(Color.DARK_GRAY);
		btnNewButton_9.setBounds(205, 260, 95, 75);
		getContentPane().add(btnNewButton_9);
		
		btnNewButton_div = new JButton("/");
		btnNewButton_div.setToolTipText("나누기 ");
		btnNewButton_div.addActionListener(this);
		btnNewButton_div.setForeground(new Color(128, 0, 0));
		btnNewButton_div.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_div.setBackground(Color.DARK_GRAY);
		btnNewButton_div.setBounds(305, 260, 90, 75);
		getContentPane().add(btnNewButton_div);
		
		btnNewButton_eq = new JButton("=");
		btnNewButton_eq.setToolTipText("결과는? ");
		btnNewButton_eq.addActionListener(this);
		btnNewButton_eq.setForeground(new Color(128, 0, 0));
		btnNewButton_eq.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_eq.setBackground(Color.DARK_GRAY);
		btnNewButton_eq.setBounds(205, 500, 95, 75);
		getContentPane().add(btnNewButton_eq);
		
		btnNewButton_AC = new JButton("AC");
		btnNewButton_AC.addActionListener(this);
		btnNewButton_AC.setForeground(new Color(100, 149, 237));
		btnNewButton_AC.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnNewButton_AC.setBackground(Color.DARK_GRAY);
		btnNewButton_AC.setBounds(6, 6, 50, 50);
		getContentPane().add(btnNewButton_AC);
		
		btnNewButton_qu = new JButton("?");
		btnNewButton_qu.setToolTipText("눌러보시오 !");
		btnNewButton_qu.addActionListener(this);
		btnNewButton_qu.setForeground(new Color(100, 149, 237));
		btnNewButton_qu.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnNewButton_qu.setBackground(Color.DARK_GRAY);
		btnNewButton_qu.setBounds(345, 6, 50, 50);
		getContentPane().add(btnNewButton_qu);
		
		
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(16, 198, 370, 50);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyEventHandler());
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		textField.setBackground(new Color(250, 235, 215));
		textField.setForeground(new Color(100, 149, 237));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
//		String input = " ";
//		textField.setText(input);
		textField.setBounds(16, 81, 370, 50);
		getContentPane().add(textField);
		textField.setColumns(10);
//		System.out.println(input);
		
		
	}
	class KeyEventHandler extends KeyAdapter {
    	CalculatorParser calculatorParser = new CalculatorParser();
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {

				System.out.println(textField.getText());
	            textField.setText(calculatorParser.getResult(calculatorParser.getRPN(textField.getText())));
	            
			}
		}
	}
}