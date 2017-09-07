package me.oddlyoko.zeroCreator.blocks.finalblocks;

public class BlocksFinalString extends BlocksFinal {
	private final String NAME = "Final String";

	public BlocksFinalString(String defvalue) {
		this(defvalue, 13, 2);
	}

	public BlocksFinalString(String defvalue, int x, int y) {
		super(defvalue, x, y);
	}

	@Override
	public String getName() {
		return NAME;
	}
}
