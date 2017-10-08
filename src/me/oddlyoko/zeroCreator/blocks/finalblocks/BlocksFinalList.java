package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalList extends BlocksFinal<List<Object>> {
	private final String NAME = "Final List";

	public BlocksFinalList(Project project, Object defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalList(Project project, Object defvalue, Object[] listvalue) {
		this(project, defvalue, listvalue, 13, 2);
	}

	public BlocksFinalList(Project project, Object defvalue, int x, int y) {
		this(project, defvalue, new Object[] { defvalue }, x, y);
	}

	public BlocksFinalList(Project project, Object defvalue, Object[] listvalue, int x, int y) {
		super(project, defvalue, x, y);
		ComposantEditableList cet = new ComposantEditableList(defvalue);
		cet.setX(13);
		cet.setY(2);
		for (Object obj : listvalue)
			cet.addItem(obj);
		setComposant(cet);
	}

	public void add(Object obj) {
		((ComposantEditableList) getComposant(0)).addItem(obj);
	}

	public void remove(Object obj) {
		((ComposantEditableList) getComposant(0)).removeItem(obj);
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
		 * cel.setY(thisCel.getY()); if (thisCel.isLock()) cel.lock(); else
		 * cel.unlock(); BlocksFinalList bfl = new BlocksFinalList(getProject(),
		 * cel.getObject()); bfl.setComposant(cel); for (Object obj :
		 * thisCel.getItems()) bfl.add(obj);
		 * 
		 * return bfl;
		 */
		return new BlocksFinalList(getProject(), ((ComposantEditableList) getComposant(0)).getObject(),
				((ComposantEditableList) getComposant(0)).getItems().toArray());
	}
}
