package me.oddlyoko.zeroCreator.blocks.customBlocks;

public interface IBlockResultBoolean extends IBlockResultType {

	public default Class<?> getReturnType() {
		return Boolean.class;
	}
}
