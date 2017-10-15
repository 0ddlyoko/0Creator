package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlockResultDouble;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableDouble;

public class BlocksFinalDouble extends BlocksFinal implements IBlockResultDouble {
	private final String NAME = "Final Double";

	public BlocksFinalDouble(Project project, double defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalDouble(Project project, double defvalue, int x, int y) {
		super(project, defvalue, x, y);
		ComposantEditableDouble ced = new ComposantEditableDouble(defvalue);
		ced.setX(13);
		ced.setY(2);
		setComposant(ced);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksFinalDouble(getProject(), ((Double) ((ComposantEditableDouble) getComposant(0)).getObject()));
	}
}
