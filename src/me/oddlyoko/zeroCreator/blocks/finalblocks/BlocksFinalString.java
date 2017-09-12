package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableText;

public class BlocksFinalString extends BlocksFinal {
	private final String NAME = "Final String";

	public BlocksFinalString(String defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalString(String defvalue, int x, int y) {
		super(defvalue, x, y);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		ComposantEditableText thisCet = (ComposantEditableText) getComposant(0);
		ComposantEditableText cet = new ComposantEditableText((String) thisCet.getObject());
		cet.setX(thisCet.getX());
		cet.setY(thisCet.getY());
		BlocksFinalString bfs = new BlocksFinalString("");
		bfs.setComposant(cet);

		return bfs;
	}
}
