package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableDouble;

public class BlocksFinalDouble extends BlocksFinal {
	private final String NAME = "Final Double";

	public BlocksFinalDouble(double defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalDouble(double defvalue, int x, int y) {
		super(defvalue, x, y);
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
		ComposantEditableDouble thisCed = (ComposantEditableDouble) getComposant(0);
		ComposantEditableDouble ced = new ComposantEditableDouble((Double) thisCed.getObject());
		ced.setX(thisCed.getX());
		ced.setY(thisCed.getY());
		BlocksFinalDouble bfd = new BlocksFinalDouble(0.0);
		bfd.setComposant(ced);

		return bfd;
	}
}
