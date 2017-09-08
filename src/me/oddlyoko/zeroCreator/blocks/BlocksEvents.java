package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.composant.ComposantBasicText;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksEventUI;

public class BlocksEvents extends Block implements IBlocksNext {
	private final String NAME = "Events";
	private InternalGUIFrame internalGUIFrame;
	private IComposant[] composants = new IComposant[1];

	private IBlocksPrevious next = null;

	public BlocksEvents(String title) {
		this(title, 20, 20);
	}

	public BlocksEvents(String title, int x, int y) {
		ComposantBasicText cbt = new ComposantBasicText(title);
		cbt.setX(10);
		cbt.setY(2);
		composants[0] = cbt;
		internalGUIFrame = new BlocksEventUI(this, x, y);
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public void move(int x, int y) {
		getInternalGUIFrame().setLocation(x, y);
		if (next != null) {
			next.getBlock().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		}
	}

	@Override
	public List<ICustomBlocks> getBlocks() {
		List<ICustomBlocks> list = new ArrayList<>();
		list.add(next);
		return list;
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (next != null && next.getBlock().equals(b)) {
			next.setPrevious(null);
			setNext(null);
		}
	}

	@Override
	public void delete() {
		if (next != null) {
			next.setPrevious(null);
			setNext(null);
		}
	}

	@Override
	public void setNext(IBlocksPrevious next) {
		if (next == null)
			this.next = null;
		else if (next instanceof BlocksInstruction) {
			this.next = next;
			next.setPrevious(this);
			next.getBlock().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		}
	}

	@Override
	public IBlocksPrevious getNext() {
		return next;
	}

	@Override
	public IComposant[] getComposantList() {
		return composants;
	}

	@Override
	public IBlocks getBlock() {
		return this;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
