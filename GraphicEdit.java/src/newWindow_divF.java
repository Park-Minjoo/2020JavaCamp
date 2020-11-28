import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

class newWindow_divF extends JFrame {
	    // 버튼이 눌러지면 만들어지는 새 창을 정의한 클래스
	    newWindow_divF() {
	        setTitle("Diverse Functions");
	        // 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
	        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
	        
	        JPanel NewWindowContainer = new JPanel();
	        NewWindowContainer.setBackground(new Color(112, 128, 144));
	        setContentPane(NewWindowContainer);
	        
	        JButton dd = new JButton("Drag & Drop");
	        dd.setForeground(new Color(128, 128, 128));
	        NewWindowContainer.add(dd);
	        
	        JButton delete = new JButton("Delete");
	        delete.setForeground(new Color(128, 128, 128));
	        NewWindowContainer.add(delete);
	        
	        JButton undo = new JButton("Undo");
	        undo.setForeground(new Color(128, 128, 128));
	        NewWindowContainer.add(undo);
	        
	        JButton redo = new JButton("Redo");
	        redo.setForeground(Color.GRAY);
	        NewWindowContainer.add(redo);
	        
	        setBounds(0,220,300,100);
	        setResizable(false);
	        setVisible(true);
	    }
	}