package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksInstructionUI;

public class BlocksInstruction extends Block implements IBlocksNext, IBlocksPrevious {
	private final String NAME = "Instruction";
	private InternalGUIFrame internalGUIFrame;
	private boolean end = false;
	private IComposant[] composants = new IComposant[0];

	private IBlocksPrevious children = null;
	private IBlocksPrevious next = null;
	private IBlocksNext previous = null;

	public BlocksInstruction() {
		internalGUIFrame = new BlocksInstructionUI(this);
	}

	public BlocksInstruction(int x, int y) {
		internalGUIFrame = new BlocksInstructionUI(this, x, y);
	}

	public IBlocksPrevious getChildren() {
		return children;
	}

	public void setChildren(IBlocksPrevious children) {
		if (children == null)
			this.children = null;
		else if (children instanceof BlocksInstruction) {
			this.children = children;
			children.getBlock().move(getInternalGUIFrame().getX() + 40, getInternalGUIFrame().getY() + 25);
		}
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public void move(int x, int y) {
		getInternalGUIFrame().setLocation(x, y);
		if (children != null)
			children.getBlock().move(getInternalGUIFrame().getX() + 40, getInternalGUIFrame().getY() + 25);
		if (!end && next != null) {
			next.getBlock().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		}
	}

	@Override
	public List<ICustomBlocks> getBlocks() {
		List<ICustomBlocks> list = new ArrayList<>();
		list.add(children);
		if (!end) {
			list.add(next);
		}
		return list;
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (children != null && children.getBlock().equals(b)) {
			children.setPrevious(null);
			setChildren(null);
		} else if (next != null && next.getBlock().equals(b)) {
			next.setPrevious(null);
			setNext(null);
		} else if (previous != null && previous.getBlock().equals(b)) {
			previous.setNext(null);
			setPrevious(null);
		}
	}

	@Override
	public void delete() {
		if (children != null) {
			children.setPrevious(null);
			setChildren(null);
		}
		if (next != null) {
			next.setPrevious(null);
			setNext(null);
		}
		if (previous != null) {
			previous.setNext(null);
			setPrevious(null);
		}
	}

	@Override
	public IBlocksPrevious getNext() {
		return next;
	}

	@Override
	public void setNext(IBlocksPrevious next) {
		if (!end) {
			if (next == null)
				this.next = null;
			else if (next instanceof BlocksInstruction) {
				this.next = next;
				next.setPrevious(this);
				next.getBlock().move(getInternalGUIFrame().getX(),
						getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
			}
		}
	}

	@Override
	public IBlocksNext getPrevious() {
		return previous;
	}

	@Override
	public void setPrevious(IBlocksNext previous) {
		this.previous = previous;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
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
