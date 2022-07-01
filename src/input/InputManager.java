package input;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent;
import java.util.HashMap;


class KeyInputManager extends KeyAdapter {
	private InputReciever listener;
	private HashMap<Integer, Boolean> keyStateTable;
	
	public KeyInputManager(InputReciever keyListener) {
		keyStateTable = new HashMap<Integer, Boolean>();
		listener = keyListener;
	}
	
	public void keyPressed(KeyEvent event) {
		Integer key = Integer.valueOf(event.getKeyCode());
		Boolean pressed = keyStateTable.containsKey(key);
		if (pressed) {
			return;
		}
		keyStateTable.put(key, Boolean.valueOf(true));
		listener.onKeyPressed(event);
	}
	
	public void keyReleased(KeyEvent event) {
		Integer key = Integer.valueOf(event.getKeyCode());
		keyStateTable.remove(key);
		listener.onKeyReleased(event);
	}
	
	public void attach(Component component) {
		component.requestFocus();
		component.addKeyListener(this);
	}
}

public class InputManager {
	public KeyInputManager keyInputManager;
	
	public InputManager(InputReciever reciever) {
		keyInputManager = new KeyInputManager(reciever);
	}
	
	public void attach(Component component) {
		keyInputManager.attach(component);
	} 
	
	public static void main(String[] args) {
		JFrame testWindow = new JFrame("Test Input Manager");
		testWindow.setSize(500, 500);
		testWindow.setVisible(true);
		
		InputReciever testReciever = new InputReciever() {
			public void onKeyReleased(KeyEvent event) {
				System.out.println(event);
			}
			public void onKeyPressed(KeyEvent event) {
				System.out.println(event);
			}
		};
		
		InputManager testManager = new InputManager(testReciever);
		testManager.attach(testWindow);
	}
}
