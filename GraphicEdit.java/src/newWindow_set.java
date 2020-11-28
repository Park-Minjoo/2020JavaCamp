import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;

class newWindow_set extends JFrame {
	    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
	    newWindow_set() {
	        setTitle("Settings");
	        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
	        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
	        
	        JPanel NewWindowContainer = new JPanel();
	        NewWindowContainer.setBackground(new Color(189, 183, 107));
	        setContentPane(NewWindowContainer);
	        
	        JButton color = new JButton("Color");
	        color.setForeground(new Color(128, 128, 128));
	        color.setBackground(new Color(128, 128, 128));
	        NewWindowContainer.add(color);
	        
	        JButton fill = new JButton("Fill");
	        fill.setForeground(new Color(128, 128, 128));
	        fill.setBackground(new Color(128, 128, 128));
	        NewWindowContainer.add(fill);
	        
	        setBounds(0,120,300,100);
	        setResizable(false);
	        setVisible(true);
	    }
}