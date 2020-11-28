import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyEventHandler extends KeyAdapter {
	public void ketTyped(KeyEvent e) {
		
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			input.append(textField.getText() + "\n");
			textField.setText("100");
		}
	}
}
