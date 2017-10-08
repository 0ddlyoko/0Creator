package me.oddlyoko.zeroCreator.blocks;

import java.util.List;

import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public interface IBlocks {
	
	public InternalGUIFrame getInternalGUIFrame();
	public List<IBlocks> getBlocks();
	public void removeBlock(IBlocks b);
	public IBlocks getParent();
	public void setParent(IBlocks b);
	public void move(int x, int y);
	public void updateAll();
	public IComposant[] getComposantList();
	public void onHover();
	public void onClick();
	public void onRightClick();
	public void onPress();
	public String getName();
	public void delete();
	public IBlocks clone1();
	public String toCode();
	
	public default void resize(int width, int height) {
		getInternalGUIFrame().setSize(width, height);
	}
}
