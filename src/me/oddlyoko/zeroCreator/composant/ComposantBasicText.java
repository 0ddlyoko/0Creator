package me.oddlyoko.zeroCreator.composant;

import java.awt.Graphics2D;

public class ComposantBasicText implements IComposant {
	private int x = 0;
	private int y = 0;
	private String txt = "";

	public ComposantBasicText(String txt) {
		this.txt = txt;
	}

	@Override
	public int getWidth(Graphics2D g2d) {
		return g2d.getFontMetrics().stringWidth(txt);
	}

	@Override
	public int getHeight(Graphics2D g2d) {
		return g2d.getFontMetrics().getHeight();
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawString(txt, x, y + getHeight(g2d));
	}

	@Override
	public void onMouseHover() {
	}

	@Override
	public void onMouseClick() {
	}

	@Override
	public void onMouseClickOutside() {
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
}
