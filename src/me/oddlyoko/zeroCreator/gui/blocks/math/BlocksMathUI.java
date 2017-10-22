package me.oddlyoko.zeroCreator.gui.blocks.math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.mathblocks.BlocksMath;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.util.gui.BlocksUI;

public class BlocksMathUI extends InternalGUIFrame {
	private static final long serialVersionUID = 1L;
	private static final int MINWIDTH = 40;
	private static final int MINHEIGHT = 30;
	private BlocksMath block;

	public BlocksMathUI(BlocksMath block) {
		this(block, 0, 0);
	}

	public BlocksMathUI(BlocksMath block, int x, int y) {
		this(block, x, y, MINWIDTH, MINHEIGHT);
	}

	public BlocksMathUI(BlocksMath block, int x, int y, int width, int height) {
		super(block.getBlocksMathEnum().getVars().length, x, y, width, height);
		this.block = block;
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public GeneralPath getPath() {
		BlocksMath bm = (BlocksMath) getBlock();
		GeneralPath path = BlocksUI.addGeneralPathFinalBlocks(new GeneralPath(), getTotalWidth(), getTotalHeight());
		int x = BlocksUI.SIZEROUND + 5 + getBlock().getComposantList()[0].getWidth()
				+ getBlock().getComposantList()[1].getWidth() + 16;
		for (int i = 0; i < bm.getBlocks().size(); i++) {
			setLocation(i, x, 2);
			if (bm.getBlocks().get(i) != null) {
				BlocksUI.addGeneralPathFinalBlocks(path, x, 2,
						bm.getBlocks().get(i).getInternalGUIFrame().getTotalWidth(),
						bm.getBlocks().get(i).getInternalGUIFrame().getTotalHeight());
				x += bm.getBlocks().get(i).getInternalGUIFrame().getTotalWidth() + 5;
			} else {
				BlocksUI.addGeneralPathFinalBlocks(path, x, 2, BlocksUI.SIZEROUND + 10, 25);
				x += BlocksUI.SIZEROUND + 10 + 5;
			}
		}
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
		BlocksMath bm = (BlocksMath) getBlock();
		int width = BlocksUI.SIZEROUND + 5 + getBlock().getComposantList()[0].getWidth()
				+ getBlock().getComposantList()[1].getWidth() + 10;
		for (IBlocks b : bm.getBlocks())
			width += ((b == null) ? BlocksUI.SIZEROUND + 10 : b.getInternalGUIFrame().getWidth()) + 5;
		// return Math.max(MINWIDTH, width);
		return width;
	}

	@Override
	public int getTotalHeight() {
		BlocksMath bm = (BlocksMath) getBlock();
		int height = MINHEIGHT;
		for (IBlocks b : bm.getBlocks())
			if (b != null && b.getInternalGUIFrame().getHeight() + 5 > height)
				height = b.getInternalGUIFrame().getHeight() + 5;
		return height;
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
