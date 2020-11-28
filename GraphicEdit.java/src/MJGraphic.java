import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
*/
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

public class MJGraphic extends JFrame implements Runnable{

	private JPanel contentPane;
	  Thread AutoSaveThread;
	    int x,y;
	    int startX;
	    int startY;
	    int lastX;
	    int lastY;
	    Container contentPane;
	    JButton fgButton;
	    JButton bgButton;
	    MyCanvas canvas;
	    Shape currentShape;
	    JLabel lblState;
	    String selected;
	    Vector<Shape> shapeList;
	    Vector<Shape> RedoList;
	    int Linesize;
	    boolean FillSelected;
	    boolean DeleteSelected;
	    boolean MoveSelected;
	    boolean ResizeSelected;
	    
	    public MJGraphic(){
	    	selected = "Line";
	        x = y = 0;
	        shapeList = new Vector<Shape>();
	        RedoList = new Vector<Shape>();


	        FillSelected = false;		//채우기 선택
	        DeleteSelected = false;	//삭제 선택
	        MoveSelected = false;		//이동 선택
	        ResizeSelected = false;   //리사이즈 선택
	        Linesize = 1;
	        AutoSaveThread = new Thread(this);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        contentPane = getContentPane();
	        
	      //MenuBar
	        JMenuBar MenuBar = new JMenuBar();
	        setJMenuBar(MenuBar);
	        
	     // 파일 저장 
	        JMenu mnFile = new JMenu("파일");
	        JMenuItem mntmSave = new JMenuItem("저장");
	        mntmSave.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String FileName = JOptionPane.showInputDialog(getContentPane(),"저장할 파일명을 입력하세요.",
	                            "그림 저장 다이얼로그",JOptionPane.QUESTION_MESSAGE);
	                    if(FileName == null)
	                        return;
	                    ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream(FileName));
	                    //배경화면 저장
	                    SaveFile.writeObject(canvas.getBackground());
	                    //그림 파일 도형 저장
	                    for(int i=0;i<shapeList.size();i++)
	                        SaveFile.writeObject(shapeList.get(i));


	                    SaveFile.close();

	                } catch (IOException e2) {
	                    // TODO: handle exception
	                    System.out.println("입출력 에러 발생!! \n");
	                    System.exit(1);
	                }
	            }
	        });
	    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MJGraphic frame = new MJGraphic();
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
	public MJGraphic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

} 
