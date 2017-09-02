package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.composant.ComposantEditableText;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksFinalUI;

public class BlocksFinal extends Block implements ICustomBlocks {
	private InternalGUIFrame internalGUIFrame;
	private IComposant[] composants = new IComposant[1];

	public BlocksFinal(String defvalue) {
		this(defvalue, 20, 20);
	}

	public BlocksFinal(String defvalue, int x, int y) {
		ComposantEditableText cet = new ComposantEditableText(defvalue);
		cet.setX(13);
		cet.setY(2);
		composants[0] = cet;
		internalGUIFrame = new BlocksFinalUI(this, x, y);
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public List<ICustomBlocks> getBlocks() {
		return new ArrayList<>();
	}

	@Override
	public void move(int x, int y) {
		getInternalGUIFrame().setLocation(x, y);
	}

	@Override
	public IComposant[] getComposantList() {
		return composants;
	}

	@Override
	public IBlocks getBlock() {
		return this;
	}
}
