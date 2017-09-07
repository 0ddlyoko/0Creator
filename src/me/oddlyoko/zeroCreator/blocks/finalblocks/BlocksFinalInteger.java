package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableInteger;

public class BlocksFinalInteger extends BlocksFinal {
	private final String NAME = "Final Integer";

	public BlocksFinalInteger(int defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalInteger(int defvalue, int x, int y) {
		super(defvalue, x, y);
		ComposantEditableInteger cet = new ComposantEditableInteger(defvalue);
		cet.setX(13);
		cet.setY(2);
		setComposant(cet);
	}

	@Override
	public String getName() {
		return NAME;
	}
}
