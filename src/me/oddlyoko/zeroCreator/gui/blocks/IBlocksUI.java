package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.IBlocks;

public interface IBlocksUI {
	public Color getColor(); // Return Blocks Color

	public GeneralPath getPath(); // Return the path

	public int getTotalWidth(); // Return total width

	public int getTotalHeight(); // Return total height

	public int getTotalHeightWithNextElement(); // Return total height with next
												// element

	public void draw(Graphics2D g2d); // Draw

	public IBlocks getBlock(); // Return specific Blocks
}
