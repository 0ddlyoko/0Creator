package me.oddlyoko.zeroCreator.blocks.customBlocks;

public interface IBlockResultDouble extends IBlockResultType {

	public default Class<?> getReturnType() {
		return Double.class;
	}
}
