package tetris;

public final class TetrisConfig {
	public static final int CELLS_WIDE = 10;
	public static final int CELLS_HIGH = 20;
	public static final int CELL_PIXEL_OFFSET_X = 16;
	public static final int CELL_PIXEL_OFFSET_Y = 16;
	public static final int CELL_PIXEL_HEIGHT = 32;
	public static final int CELL_PIXEL_WIDTH = 32;
	
	public static int getTetrisCanvasHeight() {
		return CELLS_HIGH * CELL_PIXEL_HEIGHT + 2 * CELL_PIXEL_OFFSET_Y;
	}
	
	public static int getTetrisCanvasWidth() {
		return CELLS_WIDE * CELL_PIXEL_WIDTH + 2 * CELL_PIXEL_OFFSET_X;
	}
	
}
