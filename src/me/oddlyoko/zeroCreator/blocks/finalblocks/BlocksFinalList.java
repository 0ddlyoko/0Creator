package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.List;

import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalList extends BlocksFinal {

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
}
