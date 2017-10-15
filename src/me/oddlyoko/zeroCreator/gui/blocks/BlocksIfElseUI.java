package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.conditionblocks.BlocksIfElse;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.util.gui.BlocksUI;

public class BlocksIfElseUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	public static final int MINWIDTH = 180;
	public static final int MINHEIGHT = 60;
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

		path.moveTo(x = BlocksUI.SIZEROUND, y);
		path.lineTo(x = 25 - BlocksUI.WIDTHINDICATOR, y);
		BlocksUI.addBotIndicator(path, x, y, true);
		x += BlocksUI.WIDTHINDICATOR;
		path.lineTo(x = width - BlocksUI.SIZEROUND, y);
		path.curveTo(width, 0, width, 0, x = width, y = BlocksUI.SIZEROUND);
		path.lineTo(x, y = 25 - BlocksUI.SIZEROUND);
		path.curveTo(x, 25, x, 25, x = width - BlocksUI.SIZEROUND, y = 25);
		path.lineTo(x = 40 + 25, y);
		BlocksUI.addBotIndicator(path, x, y, false);
		x -= BlocksUI.WIDTHINDICATOR;
		path.lineTo(x = 40 + BlocksUI.SIZEROUND, y);
		path.curveTo(40, y, 40, y, x = 40, y = 25 + BlocksUI.SIZEROUND);
		path.lineTo(x, y += Math.max(heightIf - 2 * BlocksUI.SIZEROUND, 25));
		path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x += BlocksUI.SIZEROUND,
				y += BlocksUI.SIZEROUND);
		path.lineTo(x = width - BlocksUI.SIZEROUND, y);
		path.curveTo(x += BlocksUI.SIZEROUND, y, x, y, x, y += BlocksUI.SIZEROUND);
		if (block.hasElse())
			path.lineTo(x, y += 10);
		path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x -= BlocksUI.SIZEROUND,
				y += BlocksUI.SIZEROUND);
		if (block.hasElse()) {
			path.lineTo(x = 40 + 25, y);
			BlocksUI.addBotIndicator(path, x, y, false);
			x -= BlocksUI.WIDTHINDICATOR;
			path.lineTo(x = 40 + BlocksUI.SIZEROUND, y);
			path.curveTo(x - BlocksUI.SIZEROUND, y, x - BlocksUI.SIZEROUND, y, x -= BlocksUI.SIZEROUND,
					y += BlocksUI.SIZEROUND);
			path.lineTo(x, y += Math.max(heightElse - 2 * BlocksUI.SIZEROUND, 25));
			path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x += BlocksUI.SIZEROUND,
					y += BlocksUI.SIZEROUND);
			path.lineTo(x = width - BlocksUI.SIZEROUND, y);
			path.curveTo(x += BlocksUI.SIZEROUND, y, x, y, x, y += BlocksUI.SIZEROUND);
			path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x -= BlocksUI.SIZEROUND,
					y += BlocksUI.SIZEROUND);
		}
		path.lineTo(x = 25, y);
		BlocksUI.addBotIndicator(path, x, y, false);
		path.lineTo(x = 0 + BlocksUI.SIZEROUND, y);
		path.curveTo(0, y, 0, y, x = 0, y -= BlocksUI.SIZEROUND);
		path.lineTo(x, y = 0 + BlocksUI.SIZEROUND);
		path.curveTo(x, 0, x, 0, x + BlocksUI.SIZEROUND, y = 0);
		path.closePath();
		return path;
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
		int height = 25 + 10 + BlocksUI.HEIGHTINDICATOR
				+ Math.max(25 + 2 * BlocksUI.SIZEROUND,
						(block.getIfChildren() == null ? 0
								: block.getIfChildren().getInternalGUIFrame().getTotalHeightWithNextElement()))
				+ (block.hasElse() ? 20 + Math.max(25 + 2 * BlocksUI.SIZEROUND, block.getElseChildren() == null ? 0
						: block.getElseChildren().getInternalGUIFrame().getTotalHeightWithNextElement()) : 0);
		/*
		 * int height = 35 + (block.getIfChildren() == null ? 0 :
		 * block.getIfChildren().getBlock().getInternalGUIFrame().
		 * getTotalHeightWithNextElement()) + (block.hasElse() ? 20 +
		 * (block.getElseChildren() == null ? 0 :
		 * block.getElseChildren().getBlock().getInternalGUIFrame().
		 * getTotalHeightWithNextElement()) : 0);
		 */
		return Math.max(MINHEIGHT + BlocksUI.HEIGHTINDICATOR, height);
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
