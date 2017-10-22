package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditable;

public class BlocksFinalString extends BlocksFinal {
	private final String NAME = "Final String";

	public BlocksFinalString(Project project, String defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalString(Project project, String defvalue, int x, int y) {
		super(project, defvalue, x, y);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksFinalString(getProject(), ((String) ((ComposantEditable) getComposant(0)).getObject()));
	}

	@Override
	public Class<?> getReturnType() {
		return String.class;
	}
}
