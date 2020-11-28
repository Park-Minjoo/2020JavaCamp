import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class newWindow extends JFrame {
	private JTextField txtCopyright;
    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
    newWindow( JButton btnNewButton,
	 JButton btnNewButton_0,
	 JButton btnNewButton_plus,
	 JButton btnNewButton_1,
	 JButton btnNewButton_2,
	 JButton btnNewButton_3,
	 JButton btnNewButton_minus,
	 JButton btnNewButton_4,
	 JButton btnNewButton_5,
	 JButton btnNewButton_6,
	 JButton btnNewButton_7,
	 JButton btnNewButton_8,
	 JButton btnNewButton_9,
	 JButton btnNewButton_mul,
	 JButton btnNewButton_div,
	 JButton btnNewButton_eq,
	 JButton btnNewButton_AC,
	 JButton btnNewButton_qu) {
        setTitle("Diverse Func");
        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
        JPanel NewWindowContainer = new JPanel();
        NewWindowContainer.setBackground(new Color(216, 191, 216));
        setContentPane(NewWindowContainer);
        
        JButton c[] = new JButton[5];
        
        c[0] = new JButton("Back");
        c[1] = new JButton("Word");
        c[2] = new JButton("ESC");
        
        c[0].setToolTipText("");
        c[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == c[0]) {
        			if(c[0].getText().equals("Back")) {
        				btnNewButton.setBackground(new Color(255, 192, 53));
        				btnNewButton_plus.setBackground(new Color(255, 192, 53));
        				btnNewButton_minus.setBackground(new Color(255, 192, 53));
        				btnNewButton_mul.setBackground(new Color(255, 192, 53));
        				btnNewButton_div.setBackground(new Color(255, 192, 53));
        				btnNewButton_eq.setBackground(new Color(255, 192, 53));
//        				btnNewButton_AC.setBackground(new Color(128, 128, 0));
//        				btnNewButton_qu.setBackground(new Color(128, 128, 0));
        				btnNewButton.setOpaque(true);
        				btnNewButton_plus.setOpaque(true);
        				btnNewButton_minus.setOpaque(true);
        				btnNewButton_mul.setOpaque(true);
        				btnNewButton_div.setOpaque(true);
        				btnNewButton_eq.setOpaque(true);
//        				btnNewButton_AC.setOpaque(true);
//        				btnNewButton_qu.setOpaque(true);
        			}
        			
        		}
        	}
        });
        
        txtCopyright = new JTextField();
        txtCopyright.setEditable(false);
        txtCopyright.setHorizontalAlignment(SwingConstants.CENTER);
        txtCopyright.setBackground(new Color(216, 191, 216));
        txtCopyright.setText("Copyright © 2020.Minjoo.ALL Rig");
        txtCopyright.setFont(new Font("KufiStandardGK", Font.ITALIC, 13));
        txtCopyright.setForeground(Color.DARK_GRAY);
        NewWindowContainer.add(txtCopyright);
        txtCopyright.setColumns(30);
        c[0].setForeground(new Color(128, 128, 0));
        c[0].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        c[0].setBackground(Color.DARK_GRAY);
        NewWindowContainer.add(c[0]);
        
        c[1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(c[1].getText().equals("Word")) {
        				btnNewButton_0.setForeground(Color.DARK_GRAY);
    				    btnNewButton_0.setOpaque(true);
    				    btnNewButton_1.setForeground(Color.DARK_GRAY);
    				    btnNewButton_1.setOpaque(true);
    				    btnNewButton_2.setForeground(Color.DARK_GRAY);
    				    btnNewButton_2.setOpaque(true);
    				    btnNewButton_3.setForeground(Color.DARK_GRAY);
    				    btnNewButton_3.setOpaque(true);
    				    btnNewButton_4.setForeground(Color.DARK_GRAY);
    				    btnNewButton_4.setOpaque(true);
    				    btnNewButton_5.setForeground(Color.DARK_GRAY);
    				    btnNewButton_5.setOpaque(true);
    				    btnNewButton_6.setForeground(Color.DARK_GRAY);
    				    btnNewButton_6.setOpaque(true);
    				    btnNewButton_7.setForeground(Color.DARK_GRAY);
    				    btnNewButton_7.setOpaque(true);
    				    btnNewButton_8.setForeground(Color.DARK_GRAY);
    				    btnNewButton_8.setOpaque(true);
    				    btnNewButton_9.setForeground(Color.DARK_GRAY);
    				    btnNewButton_9.setOpaque(true);
    				    
    				
        		}
    				
        		
        	}
        });
        c[1].setForeground(new Color(128, 128, 0));
        c[1].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        c[1].setBackground(Color.DARK_GRAY);
        NewWindowContainer.add(c[1]);
        
        c[2].setForeground(new Color(128, 128, 0));
        c[2].setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        c[2].setBackground(Color.DARK_GRAY);
        NewWindowContainer.add(c[2]);
        
        setSize(300,100);
        setResizable(false);
        setVisible(true);
        
    }
    
    public int func2dim(double x, double sx, double sy, double a, double b){


        double tmp;                                                     // 저장할 상수

        tmp = (double)( (sy-b) /( (a-sx)*(a-sx) ) );        // 상수식을 인수로 표현

        return (int)(tmp*(x-a)*(x-a) + b);                       // 전체 Y 좌표를 리턴해줌

   

   }}
