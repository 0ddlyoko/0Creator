package me.oddlyoko.zeroCreator.blocks.customBlocks;

import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;

public abstract class BlocksChildren extends Block {
	private IBlocks children;

	public IBlocks getChildren() {
		return children;
	}

	public void setChildren(IBlocks b) {
		if (this.children != null)
			this.children.setParent(null);
		this.children = b;
	}
}
