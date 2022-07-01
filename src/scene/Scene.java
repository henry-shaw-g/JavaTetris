package scene;

import main.Main;
import graphics.*;
import java.awt.event.KeyEvent;

public abstract class Scene {
	private Main main;
	private Window window;
	protected boolean active;
	
	public Scene(Main main, Window window) {
		this.main = main;
		this.window = window;
	}
	
	Window getWindow() {
		return window;
	}
	
	public abstract void update(double timeDelta);
	public abstract void onKeyPressed(KeyEvent event);
	public abstract void onKeyReleased(KeyEvent event);
	public void setActive(boolean active) {
		this.active = active;
	}
}
