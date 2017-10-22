package me.oddlyoko.zeroCreator.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.gui.blocks.IBlocksUI;

public abstract class InternalGUIFrame extends JPanel implements IBlocksUI {
	private static final long serialVersionUID = 1L;
	private Location[] locations;

	public InternalGUIFrame(int nbLocations) {
		this(nbLocations, 0, 0, 50, 50);
	}

	public InternalGUIFrame(int nbLocations, int x, int y) {
		this(nbLocations, x, y, 50, 50);
	}

	public InternalGUIFrame(int nbLocations, int x, int y, int width, int height) {
		locations = new Location[nbLocations];
		setBounds(x, y, width, height);
		setOpaque(false);
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		checkSize();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GeneralPath path = getPath();
		g2d.setColor(getColor());
		g2d.fill(path);
		g2d.setColor(Color.BLACK);
		if (getBlock() instanceof Block) {
			if (((Block) getBlock()).isHover())
				g2d.setColor(Color.YELLOW);
			if (((Block) getBlock()).isClicked())
				g2d.setColor(Color.GREEN);
		}
		g2d.draw(path);
		draw(g2d);
		for (IBlocks b : getBlock().getBlocks()) {
			if (b != null) {
				b.getInternalGUIFrame().repaint();
			}
		}
		if (getBlock() instanceof Block) {
			if (((Block) getBlock()).isHover() || ((Block) getBlock()).isClicked()) {
				g2d.setColor(Color.RED);
				g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
			}
		}
	}

	private void checkSize() {
		int width = getTotalWidth();
		int height = getTotalHeightWithNextElement();
		if (getWidth() != width || getHeight() != height)
			getBlock().resize(width, height);
	}

	public boolean isInside(int x, int y) {
		return x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight();
	}

	public void setLocation(int index, int x, int y) {
		if (locations.length <= index)
			return;
		locations[index] = new Location(x, y);
	}

	public Location getLocation(int index) {
		if (locations.length <= index)
			return null;
		return locations[index];
	}

	public Location[] getLocations() {
		return locations;
	}

	public void setNbLocations(int nbLocations) {
		Location[] locTemp = new Location[nbLocations];
		for (int i = 0; i < locations.length; i++)
			if (i < nbLocations)
				locTemp[i] = locations[i];
		locations = locTemp;
	}
}
