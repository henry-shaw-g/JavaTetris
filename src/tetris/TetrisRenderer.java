package tetris;


import java.awt.Color;
import java.awt.Graphics2D;
import graphics.Window;
import graphics.Renderer;
import scene.TetrisScene;

public class TetrisRenderer extends Renderer {
	TetrisScene tetris;
	
	public TetrisRenderer(TetrisScene tetris, Window window) {
		super(window);
		this.tetris = tetris;
	}
	
	public void onRender(Graphics2D graphics) {
		int canvasWidth = getWidth();
		int canvasHeight = getHeight();
		graphics.setColor(new Color(20, 20, 20));
		graphics.fillRect(0, 0, canvasWidth, canvasHeight);
		// draw back of cells
		graphics.setColor(new Color(0, 0, 0));
		graphics.fillRect(TetrisConfig.CELL_PIXEL_OFFSET_X, TetrisConfig.CELL_PIXEL_OFFSET_Y, TetrisConfig.CELLS_WIDE * TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELLS_HIGH * TetrisConfig.CELL_PIXEL_HEIGHT);
		// draw cells
		int[][] cells = tetris.getCells();
		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[0].length; x++) {
				var cell = cells[y][x];
				if (cell > 0) {
					ShapeType shapeType = ShapeTypeIndex.getSingleton().getShapeFromIndex(cell);
					graphics.setColor(shapeType.color);
					graphics.drawImage(shapeType.image, TetrisConfig.CELL_PIXEL_OFFSET_X  + x * TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_OFFSET_Y + y * TetrisConfig.CELL_PIXEL_HEIGHT, TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_HEIGHT, null);
				}
			}
		}
		// draw current shape
		Shape currentShape = tetris.getCurrentShape();
		ShapeType currentShapeType = ShapeTypeIndex.getSingleton().getShapeFromIndex(currentShape.index);
		int[][] shapeCells = currentShape.getCells();
		graphics.setColor(currentShapeType.color);
		for (var y = 0; y < shapeCells.length; y++) {
			for (var x = 0; x < shapeCells.length; x++) {
				var cell = shapeCells[y][x];
				if (cell == 1) {
					//graphics.fillRect(TetrisConfig.CELL_PIXEL_OFFSET_X  + (currentShape.x + x) * TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_OFFSET_Y + (currentShape.y + y) * TetrisConfig.CELL_PIXEL_HEIGHT, TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_HEIGHT);
					graphics.drawImage(currentShapeType.image, TetrisConfig.CELL_PIXEL_OFFSET_X  + (currentShape.x + x) * TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_OFFSET_Y + (currentShape.y + y) * TetrisConfig.CELL_PIXEL_HEIGHT, TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_HEIGHT, null);
				}
			}
		}
	}
	
	public static int getWindowHeight(int CELLS_HIGH) {
		System.out.println(CELLS_HIGH * TetrisConfig.CELL_PIXEL_HEIGHT + " " + TetrisConfig.CELL_PIXEL_OFFSET_Y * 2);
		return CELLS_HIGH * TetrisConfig.CELL_PIXEL_HEIGHT + 2 * TetrisConfig.CELL_PIXEL_OFFSET_Y;
	}
}
