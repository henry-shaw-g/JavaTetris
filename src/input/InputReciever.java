package input;

import java.awt.event.KeyEvent;

public interface InputReciever {
	public void onKeyPressed(KeyEvent event);
	
	public void onKeyReleased(KeyEvent event);
}
