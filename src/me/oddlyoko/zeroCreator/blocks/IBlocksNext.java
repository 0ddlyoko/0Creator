package me.oddlyoko.zeroCreator.blocks;

public abstract interface IBlocksNext extends ICustomBlocks {

	public void setNext(IBlocksPrevious next);

	public IBlocksPrevious getNext();
}
