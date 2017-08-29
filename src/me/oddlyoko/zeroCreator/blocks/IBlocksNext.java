package me.oddlyoko.zeroCreator.blocks;

public abstract interface IBlocksNext extends IBlocks {

	public void setNext(IBlocksPrevious next);

	public IBlocksPrevious getNext();
}
