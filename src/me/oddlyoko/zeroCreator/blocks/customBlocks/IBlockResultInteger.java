package me.oddlyoko.zeroCreator.blocks.customBlocks;

public interface IBlockResultInteger extends IBlockResultType {

	public default Class<?> getReturnType() {
		return Integer.class;
	}
}
