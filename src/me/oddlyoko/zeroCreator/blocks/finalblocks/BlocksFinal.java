package me.oddlyoko.zeroCreator.blocks.finalblocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.ICustomBlocks;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableText;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksFinalUI;

public abstract class BlocksFinal extends Block implements ICustomBlocks {
	private InternalGUIFrame internalGUIFrame;
	private IComposant[] composants = new IComposant[1];

	public BlocksFinal(Object defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinal(Object defvalue, int x, int y) {
		ComposantEditableText cet = new ComposantEditableText(defvalue.toString());
		cet.setX(13);
		cet.setY(2);
		setComposant(cet);
		internalGUIFrame = new BlocksFinalUI(this, x, y);
	}

	public void setComposant(IComposant comp) {
		composants[0] = comp;
	}

	public IComposant getComposant(int i) {
		return composants[i];
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
