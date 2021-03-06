package me.oddlyoko.zeroCreator.blocks;

import java.util.List;

import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public interface IBlocks {
	public InternalGUIFrame getInternalGUIFrame();

	public List<ICustomBlocks> getBlocks();

	public void removeBlock(IBlocks b);

	public void move(int x, int y);

	public IComposant[] getComposantList();

	public void onHover();

	public void onClick();

	public void onPress();

	public String getName();

	public void delete();

	public IBlocks clone1();

	public default void resize(int width, int height) {
		getInternalGUIFrame().setSize(width, height);
	}
}
