package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.List;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalBoolean extends BlocksFinal {
	private final String NAME = "Final List";

	public BlocksFinalBoolean(Boolean defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalBoolean(Boolean defvalue, int x, int y) {
		super(defvalue, x, y);
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
		ComposantEditableList thisCel = (ComposantEditableList) getComposant(0);
		BlocksFinalBoolean bfb = new BlocksFinalBoolean(
				(thisCel.getObject() instanceof Boolean) ? (Boolean) thisCel.getObject() : false);
		ComposantEditableList cel = new ComposantEditableList(thisCel.getObject());
		cel.setX(thisCel.getX());
		cel.setY(thisCel.getY());
		cel.addItem(true);
		cel.addItem(false);
		bfb.setComposant(cel);

		return bfb;
	}
}
