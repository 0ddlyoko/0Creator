package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.conditionblocks.BlocksIfElse;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class BlocksIfElseUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	public static final int MINWIDTH = 180;
	public static final int MINHEIGHT = 60;
	protected int sizeround = 5;
	protected int heightIndicator = 10;
	protected int widthIndicator = 7;
	protected BlocksIfElse block;

	public BlocksIfElseUI(BlocksIfElse block) {
		this(block, 0, 0);
	}

	public BlocksIfElseUI(BlocksIfElse block, int x, int y) {
		this(block, x, y, MINWIDTH, MINHEIGHT);
	}

	public BlocksIfElseUI(BlocksIfElse block, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.block = block;
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public GeneralPath getPath() {
		GeneralPath path = new GeneralPath();
		int x = 0;
		int y = 0;
		int width = getTotalWidth();
		// int height = getTotalHeight();
		int heightIf = (block.getIfChildren() == null ? 0
				: block.getIfChildren().getInternalGUIFrame().getTotalHeightWithNextElement());
		int heightElse = (block.getElseChildren() == null ? 0
				: block.getElseChildren().getInternalGUIFrame().getTotalHeightWithNextElement());

		path.moveTo(x = sizeround, y);
		path.lineTo(x = 25 - widthIndicator, y);
		makeBotIndicator(path, x, y, true);
		x += widthIndicator;
		path.lineTo(x = width - sizeround, y);
		path.curveTo(width, 0, width, 0, x = width, y = sizeround);
		path.lineTo(x, y = 25 - sizeround);
		path.curveTo(x, 25, x, 25, x = width - sizeround, y = 25);
		path.lineTo(x = 40 + 25, y);
		makeBotIndicator(path, x, y, false);
		x -= widthIndicator;
		path.lineTo(x = 40 + sizeround, y);
		path.curveTo(40, y, 40, y, x = 40, y = 25 + sizeround);
		path.lineTo(x, y += Math.max(heightIf - 2 * sizeround, 25));
		path.curveTo(x, y + sizeround, x, y + sizeround, x += sizeround, y += sizeround);
		path.lineTo(x = width - sizeround, y);
		path.curveTo(x += sizeround, y, x, y, x, y += sizeround);
		if (block.hasElse())
			path.lineTo(x, y += 10);
		path.curveTo(x, y + sizeround, x, y + sizeround, x -= sizeround, y += sizeround);
		if (block.hasElse()) {
			path.lineTo(x = 40 + 25, y);
			makeBotIndicator(path, x, y, false);
			x -= widthIndicator;
			path.lineTo(x = 40 + sizeround, y);
			path.curveTo(x - sizeround, y, x - sizeround, y, x -= sizeround, y += sizeround);
			path.lineTo(x, y += Math.max(heightElse - 2 * sizeround, 25));
			path.curveTo(x, y + sizeround, x, y + sizeround, x += sizeround, y += sizeround);
			path.lineTo(x = width - sizeround, y);
			path.curveTo(x += sizeround, y, x, y, x, y += sizeround);
			path.curveTo(x, y + sizeround, x, y + sizeround, x -= sizeround, y += sizeround);
		}
		path.lineTo(x = 25, y);
		makeBotIndicator(path, x, y, false);
		path.lineTo(x = 0 + sizeround, y);
		path.curveTo(0, y, 0, y, x = 0, y -= sizeround);
		path.lineTo(x, y = 0 + sizeround);
		path.curveTo(x, 0, x, 0, x + sizeround, y = 0);
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
		for (IComposant comp : block.getComposantList())
			comp.draw(g2d);
	}

	@Override
	public int getTotalWidth() {
		int ifChildrenWidth = (block.getIfChildren() == null ? 0
				: block.getIfChildren().getInternalGUIFrame().getTotalWidth());
		int elseChildrenWidth = (block.getElseChildren() == null ? 0
				: block.getElseChildren().getInternalGUIFrame().getTotalWidth());
		int width = 40 + Math.max(ifChildrenWidth, elseChildrenWidth);
		return Math.max(MINWIDTH, width);
	}

	@Override
	public int getTotalHeight() {
		int height = 25 + 10 + heightIndicator
				+ Math.max(25 + 2 * sizeround,
						(block.getIfChildren() == null ? 0
								: block.getIfChildren().getInternalGUIFrame().getTotalHeightWithNextElement()))
				+ (block.hasElse()
						? 20 + Math.max(25 + 2 * sizeround,
								block.getElseChildren() == null ? 0
										: block.getElseChildren().getInternalGUIFrame().getTotalHeightWithNextElement())
						: 0);
		/*
		 * int height = 35 + (block.getIfChildren() == null ? 0 :
		 * block.getIfChildren().getBlock().getInternalGUIFrame().
		 * getTotalHeightWithNextElement()) + (block.hasElse() ? 20 +
		 * (block.getElseChildren() == null ? 0 :
		 * block.getElseChildren().getBlock().getInternalGUIFrame().
		 * getTotalHeightWithNextElement()) : 0);
		 */
		return Math.max(MINHEIGHT + heightIndicator, height);
		// return getPath().getBounds().height;
	}

	@Override
	public int getTotalHeightWithNextElement() {
		int totalHeight = getTotalHeight();
		int nextHeight = 0;

		nextHeight = block.getChildren() == null ? 0
				: block.getChildren().getInternalGUIFrame().getTotalHeightWithNextElement();
		return totalHeight + nextHeight;
	}

	@Override
	public IBlocks getBlock() {
		return block;
	}
}
