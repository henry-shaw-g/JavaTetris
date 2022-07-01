package tetris;

public class Shape {
	private int[][] cells = {
			{1, 1, 1},
			{0, 1, 0},
			{0, 0, 0},
	};
	public int index;
	public int x;
	public int y;
	public boolean onGround;
	
	public Shape() {
		x = 0;
		y = 0;
		onGround = false;
	}
	
	public Shape(ShapeType shapeType, int initialX, int initialY) {
		index = shapeType.index;
		cells = shapeType.cells.clone();
		x = initialX;
		y = initialY;
	}
	
	public int getHeight() {
		return cells.length;
	}
	
	public int getWidth() {
		return cells[0].length;
	}
	
	public boolean canMove(int[][] staticCells, int dX, int dY) {
		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[1].length; x++) {
				if (cells[y][x] == 1) {
					if ((this.x + dX + x) < 0 || (this.x + dX + x) >= staticCells[0].length)
						return false;
					if ((this.y + dY + y) < 0 || (this.y + dY + y) >= staticCells.length)
						return false;
					int staticCell = staticCells[this.y + y + dY][this.x + x + dX];
					if (staticCell > 0)
						return false;
				}
			}
		}
		return true;
	}
	
	public boolean canRotate(int[][] staticCells) {
		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[1].length; x++) {
				if (cells[y][x] == 1) {
					int rX = cells.length - y - 1;
					int rY = x;
					if ((this.y + rY) < 0 || (this.y + rY) >= staticCells.length) {
							return false;
					}
					if ((this.x + rX) < 0 || (this.x + rX) >= staticCells[0].length) {
							return false;
					}	
					int staticCell = staticCells[this.y + rY][this.x + rX];
					if (staticCell > 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void move(int dX, int dY) {
		x += dX;
		y += dY;
	}
	
	public void rotate() {
		int[][] newCells = new int[cells[0].length][cells.length];
		for (int y = 0; y < cells.length; y++) {
			for (int x = 0; x < cells[1].length; x++) {
				if (cells[y][x] == 1) {
					int rX = cells.length - y - 1;
					int rY = x;
					newCells[rY][rX] = 1;
				}
			}
		}
		cells = newCells;
	}
	
	public void place(int[][] cells) {
		for (int y = 0; y < this.cells.length; y++) {
			for (int x = 0; x < this.cells[1].length; x++) {
				if (this.cells[y][x] == 1) {
					cells[this.y + y][this.x + x] = index;
				}
			}
		}
	}
	
	public int[][] getCells() {
		return cells;
	}
}
