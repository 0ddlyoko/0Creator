package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableDouble;

public class BlocksFinalDouble extends BlocksFinal {

	public BlocksFinalDouble(double defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalDouble(double defvalue, int x, int y) {
		super(defvalue, x, y);
		ComposantEditableDouble cet = new ComposantEditableDouble(defvalue);
		cet.setX(13);
		cet.setY(2);
		setComposant(cet);
	}
}
