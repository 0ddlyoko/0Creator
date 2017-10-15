package me.oddlyoko.zeroCreator.blocks.customBlocks;

import java.util.List;

public interface IBlockResultList extends IBlockResultType {

	public default Class<?> getReturnType() {
		return List.class;
	}
}
