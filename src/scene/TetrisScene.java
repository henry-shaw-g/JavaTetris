package scene;

import java.lang.System;
import java.awt.event.KeyEvent;
import util.EventTimer;
import graphics.*;
import main.*;
import tetris.*;

class TetrisInputObject {
	private int down;
	private int right;
	private int left;
	private boolean rotate;
	public TetrisInputObject() {
		down = 0;
		right = 0;
		left = 0;
	}
	
	public void setRight(boolean state) {
		right = state? 1: 0;
	}
	
	public void setLeft(boolean state) {
		left = state? 1: 0;
	}
	
	public void setDown(boolean state) {
		down = state? 1: 0;
	}
	
	public void setRotate(boolean state) {
		rotate = state;
	}
	
	public int getHorizontal() {
		return left - right;
	}
	
	public int getVertical() {
		return down; 
	}
	
	public boolean getRotate() {
		return rotate;
	}
}

public class TetrisScene extends Scene {
	// overall game config
	private static long THREAD_SLEEP_DURATION = 10;
	public static double FALL_TIMER_PERIOD = 0.25;
	
	private TetrisRenderer renderer;
	private TetrisInputObject inputState;
	private EventTimer fallTimer;
	public int[][] cells;
	public Shape currentShape;
	
	public TetrisScene(Main main, Window window) {
		super(main, window);
		//init tetris game state
		cells = new int[TetrisConfig.CELLS_HIGH][TetrisConfig.CELLS_WIDE];
		fallTimer = new EventTimer(FALL_TIMER_PERIOD);
		nextShape();
		inputState = new TetrisInputObject();
		renderer = new TetrisRenderer(this, window);
	}
	
	public int[][] getCells() {
		return cells;
	}
	
	public Shape getCurrentShape() {
		return currentShape;
	}
	
	private void nextShape() {
		ShapeType nextShapeType = ShapeTypeIndex.getSingleton().getRandomShape();
		currentShape = new Shape(nextShapeType, 0, 0);
	}
	
	private void emptyFullRows(Shape placedShape) {
		for (int y = placedShape.y; y < Math.min(cells.length, placedShape.y + placedShape.getHeight()); y++) {
			boolean full = true;
			for (int x = 0; x < TetrisConfig.CELLS_WIDE; x++) {
				if (cells[y][x] == 0) {
					full = false;
					break;
				}
			}
			if (full) {
				// move all above rows down
				// make top row new empty row
				for (int y1 = y - 1; y1 >= 0; y1--) {
					cells[y1 + 1] = cells[y1];
				}
				cells[0] = new int[TetrisConfig.CELLS_WIDE];
				System.out.println(cells);
			}
		}
	}
	
	public void update(double deltaTime) {
		renderer.render();
		updateShape();
	}
	
	private void updateShape() {
		if (fallTimer.check()) {
			if (currentShape.canMove(cells, 0, 1)) {
				currentShape.move(0, 1);
			} else if (currentShape.onGround) {
				currentShape.place(cells);
				emptyFullRows(currentShape);
				nextShape();
			} else {
				currentShape.onGround = true;
			}
			fallTimer.reset();
		}
	}
	
	public void onKeyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				if (currentShape.canMove(cells, 1, 0))
					currentShape.move(1, 0);
				inputState.setRight(true);
				break;
			case KeyEvent.VK_LEFT:
				if (currentShape.canMove(cells, -1, 0))
					currentShape.move(-1, 0);
				inputState.setLeft(true);
				break;
			case KeyEvent.VK_DOWN:
				if (currentShape.canMove(cells, 0, 1))
					currentShape.move(0, 1);
				inputState.setDown(true);
				break;
			case KeyEvent.VK_UP:
				inputState.setRotate(true);
				if (currentShape.canRotate(cells)) {
					currentShape.rotate();
				}
				break;
		}
	}
	
	public void onKeyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				inputState.setRight(false);
				break;
			case KeyEvent.VK_LEFT:
				inputState.setLeft(false);
				break;
			case KeyEvent.VK_DOWN:
				inputState.setDown(false);
				break;
			case KeyEvent.VK_UP:
				inputState.setRotate(false);
				break;
		}
	}
	
	public void setActive(boolean active) {
		super.setActive(active);
		renderer.setActive(active);
	}
}