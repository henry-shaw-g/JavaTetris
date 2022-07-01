package tetris;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;


public enum ShapeType {
	Square(
			1, 
			new int[][] {
				{1, 1},
				{1, 1},
			}, 
			new Color(100, 200, 200),
			"assets/Block_Yellow.png"),
	LLeft(
			2,
			new int[][] {
				{0, 1, 0},
				{0, 1, 0},
				{1, 1, 0},
			},
			new Color(0, 0, 200),
			"assets/Block_Blue.png"),
	LRight(
			3,
			new int[][] {
				{0, 1, 0},
				{0, 1, 0},
				{0, 1, 1},
			},
			new Color(255, 186, 66),
			"assets/Block_Orange.png"),
	T(
			4,
			new int[][] {
				{1, 1, 1},
				{0, 1, 0},
				{0, 0, 0},
			},
			new Color(154, 45, 227),
			"assets/Block_Purple.png"),
	Line(
			5,
			new int[][] {
				{0, 0, 1, 0},
				{0, 0, 1, 0},
				{0, 0, 1, 0},
				{0, 0, 1, 0}
			},
			new Color(154, 45, 227),
			"assets/Block_Teal.png"),
	ZLeft(
			6,
			new int[][] {
				{1, 1, 0},
				{0, 1, 1},
				{0, 0, 0},
			},
			new Color(227, 45, 45),
			"assets/Block_Green.png"),
	ZRight(
			7,
			new int[][] {
				{0, 1, 1},
				{1, 1, 0},
				{0, 0, 0},
			},
			new Color(60, 227, 45),
			"assets/Block_Red.png");
	
	public int index;
	public int[][] cells;
	public Color color; // legacy
	public BufferedImage image; // todo: use image texture/spritesheet for memory locality
	
	private static BufferedImage cachedDefaultBuffer;
	private static BufferedImage getDefaultBuffer() {
		if (cachedDefaultBuffer == null) {
			cachedDefaultBuffer = new BufferedImage(TetrisConfig.CELL_PIXEL_WIDTH, TetrisConfig.CELL_PIXEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		}
		return cachedDefaultBuffer;
	}
	
	ShapeType(int i, int[][] cellMap, Color c) {
		index = i;
		cells = cellMap;
		color = c;
		image = getDefaultBuffer();
	}
	
	ShapeType(int i, int[][] cellMap, Color c, String imageFilePath) {
		index = i;
		cells = cellMap;
		color = c;
		File fileObj = new File(imageFilePath);
		try {
			image = ImageIO.read(fileObj);
		} catch(IOException exception) {
			System.out.println("Warning:");
			System.out.println("Could not load image from file. Path: " + imageFilePath);
			image = getDefaultBuffer();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ShapeType.Square.image);
	}
}
