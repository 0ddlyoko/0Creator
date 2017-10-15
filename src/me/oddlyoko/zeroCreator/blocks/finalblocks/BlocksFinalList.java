package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlockResultList;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class BlocksFinalList extends BlocksFinal implements IBlockResultList {
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
		return new BlocksFinalList(getProject(), ((ComposantEditableList) getComposant(0)).getObject(),
				((ComposantEditableList) getComposant(0)).getItems().toArray());
	}
}
