package me.oddlyoko.zeroCreator.blocks;

public interface IBlocksPrevious extends ICustomBlocks {

	public void setPrevious(IBlocksNext previous);

	public IBlocksNext getPrevious();
}
