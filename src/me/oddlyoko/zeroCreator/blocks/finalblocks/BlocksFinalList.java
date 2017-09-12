package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.List;

import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalList extends BlocksFinal {
	private final String NAME = "Final List";

	public BlocksFinalList(Object defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalList(Object defvalue, int x, int y) {
		super(defvalue, x, y);
		ComposantEditableList cet = new ComposantEditableList(defvalue);
		cet.setX(13);
		cet.setY(2);
		setComposant(cet);
	}

	public void add(Object obj) {
		((ComposantEditableList) getComposant(0)).addItem(obj);
	}

	public void remove(Object obj) {
		((ComposantEditableList) getComposant(0)).removeItem(obj);
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
		ComposantEditableList cel = new ComposantEditableList(thisCel.getObject());
		cel.setX(thisCel.getX());
		cel.setY(thisCel.getY());
		BlocksFinalList bfl = new BlocksFinalList(thisCel.getObject());
		bfl.setComposant(cel);
		for (Object obj : thisCel.getItems())
			bfl.add(obj);

		return bfl;
	}
}
