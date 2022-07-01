package main;

import java.io.File;
import java.awt.Font;

public class ResourceLoader {	
	// accessable resources that will be loaded
	public static Font tetrisFontSmall;
	public static Font tetrisFontLarge;
	public static Font tetrisFontMedium;
	private static boolean loaded = false;
	
	public static void load() {
		if (loaded)
			return;
		loaded = true;
		Font tetrisBase = loadCustomFont("assets/8-bit Arcade In.ttf");
		tetrisFontSmall = deriveCustomFont(tetrisBase, 12.0f);
		tetrisFontMedium = deriveCustomFont(tetrisBase, 24.0f);
		tetrisFontLarge = deriveCustomFont(tetrisBase, 48.0f);
	}
	
	private static Font loadCustomFont(String fontFilePath) {
		try {
			File fontFile = new File(fontFilePath);
			return Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (Exception e) {
			System.out.println("Error loading custom font. Path: " + fontFilePath + ". Message: " + e.getMessage());
			System.out.println("Returning Logical \"Serif\" Font.");
			return new Font("Serif", Font.PLAIN, 1);
		}
	}
	
	private static Font deriveCustomFont(Font customFont, float size) {
		return customFont.deriveFont(size);
	}

	
}
