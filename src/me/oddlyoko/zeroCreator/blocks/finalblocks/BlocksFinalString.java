package me.oddlyoko.zeroCreator.blocks.finalblocks;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditable;

public class BlocksFinalString extends BlocksFinal<String> {
	private final String NAME = "Final String";

	public BlocksFinalString(Project project, String defvalue) {
		this(project, defvalue, 13, 2);
	}

	public BlocksFinalString(Project project, String defvalue, int x, int y) {
		super(project, defvalue, x, y);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		/*
		 * ComposantEditableText thisCet = (ComposantEditableText)
		 * getComposant(0); ComposantEditableText cet = new
		 * ComposantEditableText((String) thisCet.getObject());
		 * cet.setX(thisCet.getX()); cet.setY(thisCet.getY()); BlocksFinalString
		 * bfs = new BlocksFinalString(getProject(), ""); bfs.setComposant(cet);
		 * 
		 * return bfs;
		 */
		return new BlocksFinalString(getProject(), ((String) ((ComposantEditable) getComposant(0)).getObject()));
	}
}
