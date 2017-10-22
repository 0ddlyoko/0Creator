package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.conditionblocks.BlocksIfElse;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlocksChildren;
import me.oddlyoko.zeroCreator.composant.ComposantBasicText;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksEventUI;

public class BlocksEvents extends Block implements IBlocksChildren {
	private final String NAME = "Events";
	private Project project;
	private InternalGUIFrame internalGUIFrame;
	private IComposant[] composants = new IComposant[1];
	private IBlocks children;

	public BlocksEvents(Project project, String title) {
		this(project, title, 20, 20);
	}

	public BlocksEvents(Project project, String title, int x, int y) {
		this.project = project;
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
		if (x == getInternalGUIFrame().getX() && y == getInternalGUIFrame().getY())
			return;
		getInternalGUIFrame().setLocation(x, y);
	}

	@Override
	public boolean canBlockAt(IBlocks b, int x, int y) {
		if (!(b instanceof BlocksIfElse))
			return false;
		if (b == this)
			return false;
		if (y >= getInternalGUIFrame().getHeight() - 20 && y <= getInternalGUIFrame().getHeight())
			return true;
		return false;
	}

	@Override
	public void setBlockAt(IBlocks b, int x, int y) {
		if (!(b instanceof BlocksIfElse))
			return;
		if (b == this)
			return;
		if (y >= getInternalGUIFrame().getHeight() - 20 && y <= getInternalGUIFrame().getHeight())
			setChildren(b);
	}

	@Override
	public void updateAll() {
		if (getChildren() != null)
			getChildren().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
	}

	@Override
	public List<IBlocks> getBlocks() {
		return new ArrayList<>();
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (b == null)
			return;
		if (getChildren() != null && getChildren().equals(b)) {
			getChildren().setParent(null);
			setChildren(null);
		}
	}

	@Override
	public void delete() {
		removeBlock(getChildren());
	}

	@Override
	public IBlocks getChildren() {
		return children;
	}

	@Override
	public void setChildren(IBlocks b) {
		if (this.children != null)
			this.children.setParent(null);
		this.children = b;
		if (b != null)
			b.setParent(this);
	}

	@Override
	public IComposant[] getComposantList() {
		return composants;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks getParent() {
		return null;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksEvents(project, ((ComposantBasicText) composants[0]).getText());
	}

	@Override
	public String toCode() {
		// TODO END HERE
		return null;
	}

	@Override
	public Class<?> getReturnType() {
		return null;
	}
}
