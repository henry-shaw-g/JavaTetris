package graphics;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Dimension;

public class Window {
	private int width;
	private int height;
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bufferStrat;
	
	public Window(int width, int height, String name) {
		this.width = width;
		this.height = height;
		
		canvas = new Canvas();
		Dimension canvasDim = new Dimension(width, height);
		canvas.setPreferredSize(canvasDim);
		canvas.setMaximumSize(canvasDim);
		canvas.setMinimumSize(canvasDim);
		
		frame = new JFrame(name);
		frame.setResizable(false);
		frame.setSize(width + frame.getInsets().left + frame.getInsets().right, height + frame.getInsets().top + frame.getInsets().bottom);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(canvas);
		frame.pack();
		
		frame.setVisible(true);
		canvas.createBufferStrategy(2);
		bufferStrat = canvas.getBufferStrategy();		
	}
	
	public BufferStrategy getBufferStrategy() {
		return bufferStrat;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getCanvasSizeX() {
		return width;
	}
	
	public int getCanvasSizeY() {
		return height;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
