package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinal;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.util.gui.BlocksUI;

public class BlocksFinalUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	private static final int MINWIDTH = 40;
	private static final int MINHEIGHT = 25;
	private BlocksFinal block;

	public BlocksFinalUI(BlocksFinal block) {
		this(block, 0, 0);
	}

	public BlocksFinalUI(BlocksFinal block, int x, int y) {
		this(block, x, y, MINWIDTH, MINHEIGHT);
	}

	public BlocksFinalUI(BlocksFinal block, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.block = block;
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public GeneralPath getPath() {
		GeneralPath path = BlocksUI.addGeneralPathFinalBlocks(new GeneralPath(), getTotalWidth(), getTotalHeight());
		path.closePath();
		return path;
	}

	@Override
	public void draw(Graphics2D g2d) {
		for (IComposant comp : block.getComposantList())
			if (comp != null)
				comp.draw(g2d);
	}

	@Override
	public int getTotalWidth() {
		int width = BlocksUI.SIZEROUND + 5 + getBlock().getComposantList()[0].getWidth() + 5;
		// return Math.max(MINWIDTH, width);
		return width;
	}

	@Override
	public int getTotalHeight() {
		return MINHEIGHT;
	}

	@Override
	public int getTotalHeightWithNextElement() {
		return getTotalHeight();
	}

	@Override
	public IBlocks getBlock() {
		return block;
	}
}
