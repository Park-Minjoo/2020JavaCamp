import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Test extends JFrame {

       public Test() {
           JPanel container = new JPanel(new GridLayout(3, 3));
           for (int i = 0; i < 9; i++) {
               JLabel label = new JLabel("Label" + i);
               label.setPreferredSize(new Dimension(100, 100));
               label.setBorder(BorderFactory.createLineBorder(Color.black));
               label.addMouseListener(new MouseListener() {
                   @Override
                   public void mouseClicked(MouseEvent e) {
                       Icon icon = UIManager.getIcon("/Users/minjoo/Desktop/eclipse-workspace/Carcass/YELLOW.png");

                       JLabel clickedLabel = (JLabel) e.getSource();
                       Container parent = clickedLabel.getParent();
                       clearIcons(parent);
                       clickedLabel.setIcon(icon);
                   }

                   private void clearIcons(Container parent) {
                       Component[] components = parent.getComponents();
                       for (Component component : components) {
                           ((JLabel) component).setIcon(null);
                       }
                   }

                   @Override
                   public void mousePressed(MouseEvent e) {
                   }

                   @Override
                   public void mouseReleased(MouseEvent e) {
                   }

                   @Override
                   public void mouseEntered(MouseEvent e) {
                   }

                   @Override
                   public void mouseExited(MouseEvent e) {
                   }
               });
               container.add(label);
           }
           add(container);
       }

       public static void main(String[] args) {
           Test frame = new Test();
           frame.setVisible(true);
           frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
           frame.setLocationRelativeTo(null);
           frame.pack();
       }
}