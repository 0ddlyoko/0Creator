package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableDouble;

public class BlocksFinalDouble extends BlocksFinal<Double> {
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
		/*
		ComposantEditableDouble thisCed = (ComposantEditableDouble) getComposant(0);
		ComposantEditableDouble ced = new ComposantEditableDouble((Double) thisCed.getObject());
		ced.setX(thisCed.getX());
		ced.setY(thisCed.getY());
		BlocksFinalDouble bfd = new BlocksFinalDouble(getProject(), 0.0);
		bfd.setComposant(ced);

		return bfd;
		*/
		return new BlocksFinalDouble(getProject(), ((Double) ((ComposantEditableDouble) getComposant(0)).getObject()));
	}
}
