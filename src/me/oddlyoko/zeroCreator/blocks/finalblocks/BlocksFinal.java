package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.BlockResultType;
import me.oddlyoko.zeroCreator.blocks.customBlocks.BlocksChildren;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditable;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableText;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksFinalUI;

public abstract class BlocksFinal<E> extends BlockResultType<E> {
	private Project project;
	private InternalGUIFrame internalGUIFrame;
	private IComposant[] composants = new IComposant[1];

	public BlocksFinal(Project project, Object defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinal(Project project, Object defvalue, int x, int y) {
		this.project = project;
		ComposantEditableText cet = new ComposantEditableText(defvalue.toString());
		cet.setX(13);
		cet.setY(2);
		setComposant(cet);
		internalGUIFrame = new BlocksFinalUI(this, x, y);
		project.getBlocksManager().updateAll();
	}

	public void setComposant(IComposant comp) {
		composants[0] = comp;
	}

	public IComposant getComposant(int i) {
		return composants[i];
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public List<IBlocks> getBlocks() {
		return new ArrayList<>();
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (getParent() != null && getParent().equals(b)) {
			((BlocksChildren) getParent()).setChildren(null);
			setParent(null);
		}
	}

	@Override
	public void delete() {
		if (getParent() != null) {
			((BlocksChildren) getParent()).setChildren(null);
			setParent(null);
		}
		project.getBlocksManager().updateAll();
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
		;
	}

	@Override
	public IComposant[] getComposantList() {
		return composants;
	}

	public Project getProject() {
		return project;
	}

	@Override
	public String toCode() {
		return ((ComposantEditable) getComposant(0)).getObject().toString();
	}
}
