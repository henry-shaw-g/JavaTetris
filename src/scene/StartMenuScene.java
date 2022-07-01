package scene;

import main.Main;
import main.ResourceLoader;
import graphics.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class StartMenuRenderer extends Renderer {
	private TextRenderer tetrisTitle;
	private TextRenderer tetrisSubtitle;
	
	public StartMenuRenderer(graphics.Window window) {
		super(window);
		tetrisTitle = new TextRenderer("Tetris", ResourceLoader.tetrisFontLarge, TextRenderer.H_CENTER, TextRenderer.V_CENTER);
		tetrisSubtitle = new TextRenderer("Press any key to play", ResourceLoader.tetrisFontMedium, TextRenderer.H_CENTER, TextRenderer.V_CENTER);
		
	}
	
	public void onRender(Graphics2D g) {		
		int width = getWidth();
		int height = getHeight();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		tetrisTitle.render(g, width/2, height/2);
		tetrisSubtitle.render(g, width/2, height/2 + 20);
		/*g.setFont(ResourceLoader.tetrisFontLarge);
		g.drawString("Tetris", width/2, height/2);
		g.setFont(ResourceLoader.tetrisFontMedium);
		g.drawString("Press any key to play", width/2, height/2 + 20);*/
	}
}

public class StartMenuScene extends Scene {
	private Main mainHandle;
	private StartMenuRenderer renderer;
	
	public StartMenuScene(Main main, graphics.Window window) {
		super(main, window);
		mainHandle = main;
		renderer = new StartMenuRenderer(window);
	}
	
	public void update(double timeStep) {
		renderer.render();
	}
	
	public void onKeyPressed(KeyEvent event) {
		// proceed to tetris game
		System.out.println("changing scene to tetris gameplay.");
		mainHandle.changeScene(mainHandle.tetrisScene);
	}
	
	public void onKeyReleased(KeyEvent event) {
		// do nothing
	}
	
	public void setActive(boolean active) {
		super.setActive(active);
		renderer.setActive(active);
	}
	
}
