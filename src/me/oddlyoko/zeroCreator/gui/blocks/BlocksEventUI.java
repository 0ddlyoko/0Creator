package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.BlocksEvents;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.util.gui.BlocksUI;

public class BlocksEventUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	private static final int MINWIDTH = 180;
	private static final int MINHEIGHT = 25;
	private BlocksEvents block;

	public BlocksEventUI(BlocksEvents block) {
		this(block, 0, 0);
	}

	public BlocksEventUI(BlocksEvents block, int x, int y) {
		this(block, x, y, MINWIDTH, MINHEIGHT);
	}

	public BlocksEventUI(BlocksEvents block, int x, int y, int width, int height) {
		super(0, x, y, width, height);
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

		path.moveTo(BlocksUI.SIZEROUND, 0);
		path.lineTo(width - BlocksUI.SIZEROUND, 0);
		path.curveTo(width, 0, width, 0, width, BlocksUI.SIZEROUND);
		path.lineTo(width, height - BlocksUI.HEIGHTINDICATOR - BlocksUI.SIZEROUND);
		path.curveTo(width, height - BlocksUI.HEIGHTINDICATOR, width, height - BlocksUI.HEIGHTINDICATOR,
				width - BlocksUI.SIZEROUND, 25);
		path.lineTo(25, height - BlocksUI.HEIGHTINDICATOR);
		BlocksUI.addBotIndicator(path, 25, height - BlocksUI.HEIGHTINDICATOR, false);
		path.lineTo(BlocksUI.SIZEROUND, height - BlocksUI.HEIGHTINDICATOR);
		path.curveTo(0, height - BlocksUI.HEIGHTINDICATOR, 0, height - BlocksUI.HEIGHTINDICATOR, 0,
				height - BlocksUI.HEIGHTINDICATOR - BlocksUI.SIZEROUND);
		path.lineTo(0, 0 + BlocksUI.SIZEROUND);
		path.curveTo(0, 0, 0, 0, 0 + BlocksUI.SIZEROUND, 0);
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
		int width = (block.getChildren() == null ? 0 : block.getChildren().getInternalGUIFrame().getTotalWidth());
		return Math.max(MINWIDTH, width);
	}

	@Override
	public int getTotalHeight() {
		return MINHEIGHT + BlocksUI.HEIGHTINDICATOR;
	}

	@Override
	public int getTotalHeightWithNextElement() {
		int height = (block.getChildren() == null ? 0
				: block.getChildren().getInternalGUIFrame().getTotalHeightWithNextElement());
		return getTotalHeight() + height;
	}

	@Override
	public IBlocks getBlock() {
		return block;
	}
}
