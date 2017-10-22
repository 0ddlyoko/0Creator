package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.conditionblocks.BlocksIfElse;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlocksChildren;
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
		super(((block.hasElse()) ? 4 : 2), x, y, width, height);
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
		path.lineTo(x,
				y += ((block.getIfCond() == null) ? 25
						: block.getIfCond().getInternalGUIFrame().getTotalHeightWithNextElement())
						- BlocksUI.SIZEROUND);
		path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x = width - BlocksUI.SIZEROUND,
				y += BlocksUI.SIZEROUND);
		path.lineTo(x = 40 + 25, y);
		BlocksUI.addBotIndicator(path, x, y, false);
		x -= BlocksUI.WIDTHINDICATOR;
		path.lineTo(x = 40 + BlocksUI.SIZEROUND, y);
		setLocation(1, 40, y);
		path.curveTo(40, y, 40, y, x = 40, y += BlocksUI.SIZEROUND);
		path.lineTo(x, y += Math.max(heightIf - 2 * BlocksUI.SIZEROUND, 25));
		path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x += BlocksUI.SIZEROUND,
				y += BlocksUI.SIZEROUND);
		path.lineTo(x = width - BlocksUI.SIZEROUND, y);
		path.curveTo(x += BlocksUI.SIZEROUND, y, x, y, x, y += BlocksUI.SIZEROUND);
		if (block.hasElse()) {
			setLocation(2, 60, y - BlocksUI.SIZEROUND + 2);
			BlocksUI.addGeneralPathFinalBlocks(path, 60, y - BlocksUI.SIZEROUND + 2,
					(block.getElseCond() == null) ? BlocksUI.SIZEROUND + 10
							: block.getElseCond().getInternalGUIFrame().getTotalWidth(),
					(block.getElseCond() == null) ? 25
							: block.getElseCond().getInternalGUIFrame().getTotalHeightWithNextElement());
			path.moveTo(x, y);
			path.lineTo(x,
					y += ((block.getElseCond() == null) ? 25
							: block.getElseCond().getInternalGUIFrame().getTotalHeightWithNextElement())
							- BlocksUI.SIZEROUND);
		}
		path.curveTo(x, y + BlocksUI.SIZEROUND, x, y + BlocksUI.SIZEROUND, x -= BlocksUI.SIZEROUND,
				y += BlocksUI.SIZEROUND);
		if (block.hasElse()) {
			path.lineTo(x = 40 + 25, y);
			BlocksUI.addBotIndicator(path, x, y, false);
			x -= BlocksUI.WIDTHINDICATOR;
			path.lineTo(x = 40 + BlocksUI.SIZEROUND, y);
			setLocation(3, 40, y);
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
		setLocation(0, 60, 2);
		BlocksUI.addGeneralPathFinalBlocks(path, 60, 2,
				(block.getIfCond() == null) ? BlocksUI.SIZEROUND + 10
						: block.getIfCond().getInternalGUIFrame().getTotalWidth(),
				(block.getIfCond() == null) ? 25
						: block.getIfCond().getInternalGUIFrame().getTotalHeightWithNextElement());
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
		int ifCondWidth = (block.getIfCond() == null) ? BlocksUI.SIZEROUND + 10
				: block.getIfCond().getInternalGUIFrame().getTotalWidth();
		int elseCondWidth = (block.getElseCond() == null) ? BlocksUI.SIZEROUND + 10
				: block.getElseCond().getInternalGUIFrame().getTotalWidth();
		int max = Math.max(ifChildrenWidth, elseChildrenWidth);
		max = Math.max(max, ifCondWidth + 22);
		max = Math.max(max, elseCondWidth + 22);
		int width = 40 + max;
		IBlocksChildren ibc = (IBlocksChildren) getBlock();
		if (ibc.getChildren() != null)
			if (ibc.getChildren().getInternalGUIFrame().getWidth() > width)
				width = ibc.getChildren().getInternalGUIFrame().getWidth();
		return Math.max(MINWIDTH, width);
	}

	@Override
	public int getTotalHeight() {
		int height = 4
				+ ((block.getIfCond() == null) ? 25
						: block.getIfCond().getInternalGUIFrame().getTotalHeightWithNextElement())
				+ (block.getIfChildren() == null ? 25 + 2 * BlocksUI.SIZEROUND
						: block.getIfChildren().getInternalGUIFrame().getTotalHeightWithNextElement())
				+ (block.hasElse() ? ((block.getElseCond() == null ? 25
						: block.getElseCond().getInternalGUIFrame().getTotalHeight())
						+ (block.getElseChildren() == null ? 25 + 2 * BlocksUI.SIZEROUND
								: block.getElseChildren().getInternalGUIFrame().getTotalHeightWithNextElement()) + 4)
						: 0)
				+ BlocksUI.SIZEROUND * 2 + BlocksUI.HEIGHTINDICATOR;
		return height;
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
