package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditable;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalBoolean extends BlocksFinal<Boolean> {
	private final String NAME = "Final List";

	public BlocksFinalBoolean(Project project, Boolean defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalBoolean(Project project, Boolean defvalue, int x, int y) {
		super(project, defvalue, x, y);
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
		/*
		 * ComposantEditableList thisCel = (ComposantEditableList)
		 * getComposant(0); ComposantEditableList cel = new
		 * ComposantEditableList(thisCel.getObject()); cel.setX(thisCel.getX());
		 * cel.setY(thisCel.getY()); cel.addItem(true); cel.addItem(false); if
		 * (thisCel.isLock()) cel.lock(); else cel.unlock(); BlocksFinalBoolean
		 * bfb = new BlocksFinalBoolean(getProject(), (cel.getObject()
		 * instanceof Boolean) ? (Boolean) cel.getObject() : false);
		 * bfb.setComposant(cel);
		 * 
		 * return bfb;
		 */
		return new BlocksFinalBoolean(getProject(), ((Boolean) ((ComposantEditable) getComposant(0)).getObject()));
	}
}
