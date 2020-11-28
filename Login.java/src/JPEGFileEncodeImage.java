import java.awt.BorderLayout;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JPEGFileEncodeImage {
	public static void main(String[] args) {
		Image image1 = null;
//		Image image2 = null;
//		Image image3 = null;
	    try {
	        // 파일로부터 이미지 읽기
	        File sourceimage = new File("/Users/minjoo/Desktop/1.jpeg");
	        image1 = ImageIO.read(sourceimage);
	    
	        // InputStream으로부터 이미지 읽기
//	        InputStream is = new BufferedInputStream(
//	            new FileInputStream("/Users/minjoo/Desktop/1.jpeg"));
//	        image2 = ImageIO.read(is);
//	    
	        // URL로 부터 이미지 읽기
//	        URL url = new URL("http://www.ojc.asia/images/new_logo.gif");
//	        image3 = ImageIO.read(url);
	    } catch (IOException e) {
	    }
	    
	    // Use a label to display the image
	    JFrame frame = new JFrame();
	    
	    JLabel label1 = new JLabel(new ImageIcon(image1));
//	    JLabel label2 = new JLabel(new ImageIcon(image2));
//	    JLabel label3 = new JLabel(new ImageIcon(image3));
	    
	    frame.getContentPane().add(label1);
//	    frame.getContentPane().add(label2, BorderLayout.NORTH);
//	    frame.getContentPane().add(label3, BorderLayout.SOUTH);
	    
	    frame.pack();
	    frame.setVisible(true);
	}
}