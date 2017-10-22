package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditable;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalBoolean extends BlocksFinal {
	private final String NAME = "Final List";

	public BlocksFinalBoolean(Project project, Boolean defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalBoolean(Project project, Boolean defvalue, int x, int y) {
		super(project, defvalue, x, y);
		ComposantEditableList cet = new ComposantEditableList(defvalue);
		cet.setX(13);
		cet.setY(2);
		cet.addItem(true);
		cet.addItem(false);
		cet.lock();
		setComposant(cet);
	}

	public List<Object> getItems() {
		return ((ComposantEditableList) getComposant(0)).getItems();
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksFinalBoolean(getProject(), ((Boolean) ((ComposantEditable) getComposant(0)).getObject()));
	}

	@Override
	public Class<?> getReturnType() {
		return Boolean.class;
	}
}
