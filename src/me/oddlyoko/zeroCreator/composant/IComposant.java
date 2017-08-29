package me.oddlyoko.zeroCreator.composant;

import java.awt.Graphics2D;

public interface IComposant {

	public int getWidth(Graphics2D g2d);

	public int getHeight(Graphics2D g2d);

	public void draw(Graphics2D g2d);

	public void onMouseHover();

	public void onMouseClick();

	public void onMouseClickOutside();

	public void setX(int x);

	public void setY(int y);
}
