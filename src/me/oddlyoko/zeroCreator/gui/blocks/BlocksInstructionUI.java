package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.BlocksInstruction;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class BlocksInstructionUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	public static final int MINWIDTH = 180;
	public static final int MINHEIGHT = 60;
	protected int sizeround = 5;
	protected int heightIndicator = 10;
	protected int widthIndicator = 7;
	protected BlocksInstruction block;

	public BlocksInstructionUI(BlocksInstruction block) {
		this(block, 0, 0);
	}

	public BlocksInstructionUI(BlocksInstruction block, int x, int y) {
		this(block, x, y, MINWIDTH, MINHEIGHT);
	}

	public BlocksInstructionUI(BlocksInstruction block, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.block = block;
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public GeneralPath getPath() {
		GeneralPath path = new GeneralPath();
		int width = getTotalWidth();
		int height = getTotalHeight();

		path.moveTo(sizeround, 0);
		path.lineTo(25 - widthIndicator, 0);
		makeBotIndicator(path, 25 - widthIndicator, 0, true);
		path.lineTo(width - sizeround, 0);
		path.curveTo(width, 0, width, 0, width, sizeround);
		path.lineTo(width, 25 - sizeround);
		path.curveTo(width, 25, width, 25, width - sizeround, 25);
		path.lineTo(40 + 25, 25);
		makeBotIndicator(path, 40 + 25, 25, false);
		path.lineTo(40 + sizeround, 25);
		path.curveTo(40, 25, 40, 25, 40, 25 + sizeround);
		path.lineTo(40, height - heightIndicator - 15);
		path.curveTo(40, height - heightIndicator - 10, 40, height - heightIndicator - 10, 40 + sizeround,
				height - heightIndicator - 10);
		path.lineTo(width - sizeround, height - heightIndicator - 10);
		path.curveTo(width, height - heightIndicator - 10, width, height - heightIndicator - 10, width,
				height - heightIndicator - 10 + sizeround);
		path.curveTo(width, height - (block.isEnd() ? 0 : heightIndicator), width,
				height - (block.isEnd() ? 0 : heightIndicator), width - sizeround,
				height - (block.isEnd() ? 0 : heightIndicator));
		if (!block.isEnd()) {
			path.lineTo(25, height - heightIndicator);
			makeBotIndicator(path, 25, height - heightIndicator, false);
		}
		path.lineTo(0 + sizeround, height - (block.isEnd() ? 0 : heightIndicator));
		path.curveTo(0, height - (block.isEnd() ? 0 : heightIndicator), 0,
				height - (block.isEnd() ? 0 : heightIndicator), 0,
				height - (block.isEnd() ? 0 : heightIndicator) - sizeround);
		path.lineTo(0, 0 + sizeround);
		path.curveTo(0, 0, 0, 0, 0 + sizeround, 0);
		path.closePath();
		return path;
	}

	public void makeBotIndicator(GeneralPath path, int currX, int currY, boolean fromleft) {
		if (fromleft)
			path.curveTo(currX - (widthIndicator * 2), currY + (heightIndicator * 1.5), currX + (widthIndicator * 3),
					currY + (heightIndicator * 1.5), currX + widthIndicator, currY);
		else
			path.curveTo(currX + (widthIndicator * 2), currY + (heightIndicator * 1.5), currX - (widthIndicator * 3),
					currY + (heightIndicator * 1.5), currX - widthIndicator, currY);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// Nothing to do here
	}

	@Override
	public int getTotalWidth() {
		int width = 40 + (block.getChildren() == null ? 0
				: block.getChildren().getBlock().getInternalGUIFrame().getTotalWidth());
		return Math.max(MINWIDTH, width);
	}

	@Override
	public int getTotalHeight() {
		int height = 35 + (block.getChildren() == null ? 0
				: block.getChildren().getBlock().getInternalGUIFrame().getTotalHeightWithNextElement());
		return Math.max(MINHEIGHT + heightIndicator, height) + (block.isEnd() ? 0 : heightIndicator);
	}

	@Override
	public int getTotalHeightWithNextElement() {
		int totalHeight = getTotalHeight();
		int nextHeight = 0;
		if (!block.isEnd())
			nextHeight = block.getNext() == null ? 0
					: block.getNext().getBlock().getInternalGUIFrame().getTotalHeightWithNextElement();
		return totalHeight + nextHeight;
	}

	@Override
	public IBlocks getBlock() {
		return block;
	}
}
