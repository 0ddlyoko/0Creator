package me.oddlyoko.zeroCreator.composant;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class ComposantEditableText implements IComposant {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private FontMetrics fm = null;
	private String oldTxt = "";
	private String txt = "";

	public ComposantEditableText(String txt) {
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
			width = fm.stringWidth(txt) + 5;
			height = fm.getHeight() + 5;
		}
		Color c = g2d.getColor();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(c);
		g2d.drawString(txt, x + 2, y + fm.getHeight());
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

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public void onClick() {
		String result = JOptionPane.showInputDialog(null, "Please Enter new value: ");
		if (result != null) {
			txt = result;
		}
		// System.out.println("Clicked EditableText: " + txt);
	}

	@Override
	public void onHover() {
		// System.out.println("Hover EditableText: " + txt);
	}
}
