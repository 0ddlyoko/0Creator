package me.oddlyoko.zeroCreator.blocks;

public interface IBlocksPrevious extends IBlocks {

	public void setPrevious(IBlocksNext previous);

	public IBlocksNext getPrevious();
}
