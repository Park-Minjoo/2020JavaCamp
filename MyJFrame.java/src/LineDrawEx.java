import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class LineDrawEx extends JFrame {
	
	public LineDrawEx(){
		setContentPane(new MyPanel());
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		Vector<Point> sv = new Vector<Point>(); // 시작
		Vector<Point> se = new Vector<Point>(); // 끝점
		
		public MyPanel(){
			this.addMouseListener(new MyMouseListener()); // 리스너
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g); // 부모 페인트호출
			
			if(sv.size() == 0) // 벡터에 암것도없으면 리턴
				return;
			
			for(int i=0;i<sv.size();i++){ //벡터크기만큼
				Point sp = sv.get(i); // 벡터값을꺼내다
				Point ep = se.get(i);	
				g.drawLine(sp.x, sp.y, ep.x, ep.y);//그리다
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
	
	public static void main(String[] args) {
		new LineDrawEx();
	}
}
