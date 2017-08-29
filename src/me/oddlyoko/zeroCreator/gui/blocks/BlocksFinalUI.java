package me.oddlyoko.zeroCreator.gui.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.BlocksFinal;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class BlocksFinalUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	private static final int MINWIDTH = 40;
	private static final int MINHEIGHT = 25;
	private int sizeround = 5;
	private int heightIndicator = 7;
	private int widthIndicator = 10;
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
		GeneralPath path = new GeneralPath();
		int width = getTotalWidth();
		int height = getTotalHeight();

		path.moveTo(widthIndicator + sizeround, 0);
		path.lineTo(width - sizeround, 0);
		path.curveTo(width, 0, width, 0, width, sizeround);
		path.lineTo(width, height - sizeround);
		path.curveTo(width, height, width, height, width - sizeround, height);
		path.lineTo(widthIndicator + sizeround, height);
		path.curveTo(widthIndicator, height, widthIndicator, height, widthIndicator, height - sizeround);
		path.lineTo(widthIndicator, 2.5 * heightIndicator);
		makeLeftIndicator(path, widthIndicator, (int) (2.5 * heightIndicator));
		path.lineTo(widthIndicator, sizeround);
		path.curveTo(widthIndicator, 0, widthIndicator, 0, widthIndicator + sizeround, 0);
		path.closePath();
		return path;
	}

	private void makeLeftIndicator(GeneralPath path, int currX, int currY) {
		path.curveTo(currX - (widthIndicator * 1.5), currY + (heightIndicator * 2), currX - (widthIndicator * 1.5),
				currY - (heightIndicator * 3), currX, currY - heightIndicator);
	}

	@Override
	public void draw(Graphics2D g2d) {

	}

	@Override
	public int getTotalWidth() {
		return MINWIDTH;
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
