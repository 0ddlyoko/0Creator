package me.oddlyoko.zeroCreator.blocks.customBlocks;

public interface IBlockResultString extends IBlockResultType {

	public default Class<?> getReturnType() {
		return String.class;
	}
}
