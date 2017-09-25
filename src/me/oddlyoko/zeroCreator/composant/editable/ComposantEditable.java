package me.oddlyoko.zeroCreator.composant.editable;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.composant.IComposant;

public abstract class ComposantEditable implements IComposant {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private Color fontColor = Color.WHITE;
	private FontMetrics fm = null;
	private Object oldObj = "";
	private Object obj = "";

	public ComposantEditable(Object obj) {
		this.oldObj = obj;
		this.obj = obj;
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
		if (fm != g2d.getFontMetrics() || !oldObj.equals(obj)) {
			fm = g2d.getFontMetrics();
			oldObj = obj;
			width = fm.stringWidth(obj.toString()) + 5;
			height = fm.getHeight() + 5;
		}
		Color c = g2d.getColor();
		g2d.setColor(fontColor);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(c);
		g2d.drawString(obj.toString(), x + 2, y + fm.getHeight());
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public void setObject(Object obj) {
		this.obj = obj;
	}

	public Object getObject() {
		return obj;
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public void onClick() {
		String result = JOptionPane.showInputDialog(null, "Please Enter new value: ");
		if (result != null) {
			setObject(result);
		}
		// System.out.println("Clicked EditableText: " + txt);
	}

	@Override
	public void onRightClick() {
		System.out.println("RightClicked EditableText: ");

	}

	@Override
	public void onHover() {
		// System.out.println("Hover EditableText: " + txt);
	}

	public void setFontColor(Color c) {
		fontColor = c;
	}
}
