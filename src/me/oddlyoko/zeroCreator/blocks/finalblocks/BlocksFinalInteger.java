package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditable;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableInteger;

public class BlocksFinalInteger extends BlocksFinal {
	private final String NAME = "Final Integer";

	public BlocksFinalInteger(Project project, int defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalInteger(Project project, int defvalue, int x, int y) {
		super(project, defvalue, x, y);
		ComposantEditableInteger cei = new ComposantEditableInteger(defvalue);
		cei.setX(13);
		cei.setY(2);
		setComposant(cei);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksFinalInteger(getProject(), ((Integer) ((ComposantEditable) getComposant(0)).getObject()));
	}

	@Override
	public Class<?> getReturnType() {
		return Integer.class;
	}
}
