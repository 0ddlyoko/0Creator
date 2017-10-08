package me.oddlyoko.zeroCreator.composant;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class ComposantBasicText extends Composant {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private Color font = Color.BLACK;
	private FontMetrics fm = null;
	private String oldTxt = "";
	private String txt = "";

	public ComposantBasicText(String txt) {
		this.oldTxt = txt;
		this.txt = txt;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean isInside(int x, int y) {
		return (x > this.x && x < this.x + width && y > this.y && y < this.y + height);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void draw(Graphics2D g2d) {
		if (fm != g2d.getFontMetrics() || !oldTxt.equalsIgnoreCase(txt)) {
			fm = g2d.getFontMetrics();
			oldTxt = txt;
			width = fm.stringWidth(txt);
			height = fm.getHeight();
		}
		Color oldC = g2d.getColor();
		g2d.setColor(font);
		g2d.drawString(txt, x, y + getHeight());
		g2d.setColor(oldC);
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public void setText(String txt) {
		this.txt = txt;
	}

	public String getText() {
		return txt;
	}

	public void setFont(Color font) {
		this.font = font;
	}

	public Color getFont() {
		return font;
	}

	@Override
	public boolean isEditable() {
		return false;
	}

	@Override
	public void onClick() {
		// System.out.println("Clicked BasicText: " + txt);
	}

	@Override
	public void onRightClick() {
		// System.out.println("RightClicked BasicText: " + txt);
	}

	@Override
	public void onHover() {
		// System.out.println("Hover BasicText: " + txt);
	}
}
