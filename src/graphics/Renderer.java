package graphics;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;


public abstract class Renderer {
	private boolean active;
	private Canvas canvas; 
	private BufferStrategy windowBufferStrat;
	
	public Renderer() {
		// makes compiler errors go away
	}
	
	public Renderer(Window window) {
		super();
		windowBufferStrat = window.getBufferStrategy();
		canvas = window.getCanvas();
	}
	
	public int getWidth() {
		return canvas.getWidth();
	}
	
	public int getHeight() {
		return canvas.getHeight();
	}
	
	public void render() {
		if (!active)
			return;
		Graphics graphics = windowBufferStrat.getDrawGraphics();
		onRender((Graphics2D) graphics);
		graphics.dispose();
		windowBufferStrat.show();
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract void onRender(Graphics2D graphics);
}
