package me.oddlyoko.zeroCreator.composant;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class ComposantButton implements IComposant {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private Color background = Color.WHITE;
	private Color backgroundActive = Color.GRAY;
	private Color font = Color.BLACK;
	private Color fontActive = Color.WHITE;
	// private FontMetrics fm = null;
	private char c;
	private boolean active = false;
	private IComposantButtonClicked iCBC = null;

	public ComposantButton(char c, IComposantButtonClicked iCBC) {
		this(10, 10, c, iCBC);
	}

	public ComposantButton(int width, int height, char c, IComposantButtonClicked iCBC) {
		this.width = width;
		this.height = height;
		this.c = c;
		this.iCBC = iCBC;
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
		FontMetrics fm = g2d.getFontMetrics();
		int carWidth = fm.stringWidth(Character.toString(c));
		int carHeight = fm.getHeight();

		Color oldC = g2d.getColor();

		g2d.setColor(isActive() ? backgroundActive : background);
		g2d.fillRect(x, y, width, height);

		g2d.setColor(isActive() ? fontActive : font);
		g2d.drawString(Character.toString(c), x + ((width - carWidth) / 2),
				y + ((height - carHeight) / 2) + fm.getAscent());
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

	public void setC(char c) {
		this.c = c;
	}

	public char getC() {
		return c;
	}

	public void setFont(Color font) {
		this.font = font;
	}

	public Color getFont() {
		return font;
	}

	public void setFontActive(Color font) {
		this.fontActive = font;
	}

	public Color getFontActive() {
		return fontActive;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackgroundActive(Color background) {
		this.backgroundActive = background;
	}

	public Color getBackgroundActive() {
		return backgroundActive;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public IComposantButtonClicked getIComposantButtonClicked() {
		return iCBC;
	}

	@Override
	public boolean isEditable() {
		return false;
	}

	@Override
	public void onClick() {
		boolean old = active;
		ComposantButtonClicked cbc = new ComposantButtonClicked(this, !old);
		iCBC.execute(cbc);
		setActive(cbc.getNewActive());
	}

	@Override
	public void onRightClick() {
		// System.out.println("RightClicked BasicText: " + txt);
	}

	@Override
	public void onHover() {
		// System.out.println("Hover BasicText: " + txt);
	}

	public interface IComposantButtonClicked {
		void execute(ComposantButtonClicked cbc);
	}

	public class ComposantButtonClicked {
		private ComposantButton cb;
		private boolean newActive;

		public ComposantButtonClicked(ComposantButton cb, boolean newActive) {
			this.cb = cb;
			this.newActive = newActive;
		}

		public ComposantButton getComposantButton() {
			return cb;
		}

		public boolean getNewActive() {
			return newActive;
		}

		public void setNewActive(boolean newActive) {
			this.newActive = newActive;
		}
	}
}
