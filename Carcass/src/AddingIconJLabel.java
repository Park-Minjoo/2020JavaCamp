import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AddingIconJLabel {
  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame();
    frame.setTitle("JLabel Test");
    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ImageIcon imageIcon = new ImageIcon("/Users/minjoo/Desktop/eclipse-workspace/Carcass/YELLOW.png");
    JLabel label = new JLabel(imageIcon);

    frame.add(label);
    frame.pack();
    frame.setVisible(true);
  }
}