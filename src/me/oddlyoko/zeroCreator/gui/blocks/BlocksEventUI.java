package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.BlocksEvents;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class BlocksEventUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	private static final int MINWIDTH = 180;
	private static final int MINHEIGHT = 25;
	private int sizeround = 5;
	private int heightIndicator = 10;
	private int widthIndicator = 7;
	private BlocksEvents block;

	public BlocksEventUI(BlocksEvents block) {
		this(block, 0, 0);
	}

	public BlocksEventUI(BlocksEvents block, int x, int y) {
		this(block, x, y, MINWIDTH, MINHEIGHT);
	}

	public BlocksEventUI(BlocksEvents block, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.block = block;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public GeneralPath getPath() {
		GeneralPath path = new GeneralPath();
		int width = getTotalWidth();
		int height = getTotalHeight();

		path.moveTo(sizeround, 0);
		path.lineTo(width - sizeround, 0);
		path.curveTo(width, 0, width, 0, width, sizeround);
		path.lineTo(width, height - heightIndicator - sizeround);
		path.curveTo(width, height - heightIndicator, width, height - heightIndicator, width - sizeround, 25);
		path.lineTo(25, height - heightIndicator);
		makeBotIndicator(path, 25, height - heightIndicator);
		path.lineTo(sizeround, height - heightIndicator);
		path.curveTo(0, height - heightIndicator, 0, height - heightIndicator, 0, height - heightIndicator - sizeround);
		path.lineTo(0, 0 + sizeround);
		path.curveTo(0, 0, 0, 0, 0 + sizeround, 0);
		path.closePath();
		return path;
	}

	public void makeBotIndicator(GeneralPath path, int currX, int currY) {
		path.curveTo(currX + (widthIndicator * 2), currY + (heightIndicator * 1.5), currX - (widthIndicator * 3),
				currY + (heightIndicator * 1.5), currX - widthIndicator, currY);
	}

	@Override
	public void draw(Graphics2D g2d) {
		for (IComposant comp : block.getComposantList())
			comp.draw(g2d);
	}

	@Override
	public int getTotalWidth() {
		int width = (block.getNext() == null ? 0 : block.getNext().getBlock().getInternalGUIFrame().getTotalWidth());
		return Math.max(MINWIDTH, width);
	}

	@Override
	public int getTotalHeight() {
		return MINHEIGHT + heightIndicator;
	}

	@Override
	public int getTotalHeightWithNextElement() {
		int height = (block.getNext() == null ? 0
				: block.getNext().getBlock().getInternalGUIFrame().getTotalHeightWithNextElement());
		return getTotalHeight() + height;
	}

	@Override
	public IBlocks getBlock() {
		return block;
	}
}
