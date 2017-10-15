package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
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
		project.getBlocksManager().updateAll();
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
		project.getBlocksManager().updateAll();
	}

	@Override
	public void updateAll() {
		if (getChildren() != null) {
			getChildren().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		}
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
		project.getBlocksManager().updateAll();
	}

	@Override
	public void delete() {
		removeBlock(getChildren());
		project.getBlocksManager().updateAll();
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
		project.getBlocksManager().updateAll();
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
		BlocksEvents b = new BlocksEvents(project, "");
		ComposantBasicText thisCbt = (ComposantBasicText) composants[0];
		ComposantBasicText cbt = new ComposantBasicText(thisCbt.getText());
		cbt.setX(thisCbt.getX());
		cbt.setY(thisCbt.getY());
		b.composants[0] = cbt;
		// return b;
		return new BlocksEvents(project, ((ComposantBasicText) composants[0]).getText());
	}

	@Override
	public String toCode() {
		// TODO END HERE
		return null;
	}
}
