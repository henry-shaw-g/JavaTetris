package scene;

import main.*;
import graphics.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class EndMenuRenderer extends Renderer {
	public EndMenuRenderer(graphics.Window window) {
		super(window);
	}
	
	public void onRender(Graphics2D g) {
		
	}
}

public class EndMenuScene extends Scene {
	private Main mainHandle; 
	
	public EndMenuScene(Main main, graphics.Window window) {
		super(main, window);
		mainHandle = main;
	}
	
	public void update(double deltaTime) {
		
	}
	
	public void onKeyPressed(KeyEvent event) {
		
	}
	
	public void onKeyReleased(KeyEvent event) {
		
	}
}
