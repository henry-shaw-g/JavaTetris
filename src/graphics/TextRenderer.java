package graphics;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

enum HorizontalAlignMode {
	Left(0),
	Center(1),
	Right(2);
	
	public int p;
	
	HorizontalAlignMode(int i) {
		p = i;
	}
}

enum VerticalAlignMode {
	Top(0),
	Center(1),
	Bottom(2);
	
	public int p;
	
	VerticalAlignMode(int i) {
		p = i;
	}
}


public class TextRenderer {
	public static HorizontalAlignMode H_LEFT = HorizontalAlignMode.Left;
	public static HorizontalAlignMode H_CENTER = HorizontalAlignMode.Center;
	public static HorizontalAlignMode H_RIGHT = HorizontalAlignMode.Right;
	
	public static VerticalAlignMode V_TOP = VerticalAlignMode.Top;
	public static VerticalAlignMode V_CENTER = VerticalAlignMode.Center;
	public static VerticalAlignMode V_BOTTOM = VerticalAlignMode.Bottom;
	
	private String string;
	private Font font;
	private int posX;
	private int posY;
	private HorizontalAlignMode hAlign = HorizontalAlignMode.Left;
	private VerticalAlignMode vAlign = VerticalAlignMode.Top;
	
	public TextRenderer(String s, Font f, int pX, int pY) {
		string = s;
		font = f;
		posX = pX;
		posY = pY;
	}
	
	public TextRenderer(String s, Font f) {
		this(s, f, 0, 0);
	}
	
	public TextRenderer(String s, Font f, int pX, int pY,  HorizontalAlignMode hA, VerticalAlignMode vA) {
		this(s, f, pX, pY);
		hAlign = hA;
		vAlign = vA;
	}
	
	public TextRenderer(String s, Font f, HorizontalAlignMode hA, VerticalAlignMode vA) {
		this(s, f, 0, 0, hA, vA);
	}
	
	public void setText(String s) {
		this.string = s;
	}
	
	public void render(Graphics2D g, int pX, int pY) {
		FontMetrics fontMetrics = g.getFontMetrics(font);
		Rectangle2D bounds = fontMetrics.getStringBounds(string, g);
		int x = (int)(pX - (bounds.getWidth() * hAlign.p)/2);
		int y = (int)(pY - (bounds.getHeight() * vAlign.p)/2);
		g.setFont(font);
		g.drawString(string, x, y);
	}
	
	public void render(Graphics2D g) {
		render(g, posX, posY);
	}
}
