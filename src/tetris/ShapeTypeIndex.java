package tetris;

import java.util.Random;

public class ShapeTypeIndex {
	private static Random random = new Random();
	private static ShapeTypeIndex singleton;
	private ShapeType[] shapeTypes;
	
	private ShapeTypeIndex() {
		ShapeType[] values = ShapeType.values();
		shapeTypes = new ShapeType[values.length];
		for (ShapeType shapeType: values) {
			shapeTypes[shapeType.index - 1] = shapeType;
		}
	}
	
	public static ShapeTypeIndex getSingleton() {
		if (singleton == null) {
			singleton = new ShapeTypeIndex();
		}
		return singleton;
	}
	
	public ShapeType getShapeFromIndex(int index) {
		return shapeTypes[index - 1];
	}
	
	public ShapeType getRandomShape() {
		return shapeTypes[random.nextInt(shapeTypes.length)];
	}
}
