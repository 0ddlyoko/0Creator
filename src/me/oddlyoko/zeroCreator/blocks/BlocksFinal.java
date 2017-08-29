package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksFinalUI;

public class BlocksFinal implements IBlocks {
	private InternalGUIFrame internalGUIFrame;
	private IComposant[] composants = new IComposant[0];

	public BlocksFinal() {
		internalGUIFrame = new BlocksFinalUI(this);
	}

	public BlocksFinal(int x, int y) {
		internalGUIFrame = new BlocksFinalUI(this, x, y);
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public List<IBlocks> getBlocks() {
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
}
