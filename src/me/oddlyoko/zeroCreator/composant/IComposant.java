package me.oddlyoko.zeroCreator.composant;

import java.awt.Graphics2D;

public interface IComposant {

	public int getWidth();

	public int getHeight();

	public boolean isInside(int x, int y);

	public void draw(Graphics2D g2d);

	public void setX(int x);

	public int getX();

	public void setY(int y);

	public int getY();

	public boolean isEditable();

	public void onClick();

	public void onHover();
}
