package me.oddlyoko.zeroCreator.gui.frame.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import me.oddlyoko.zeroCreator.blocks.IBlocks;

public class Separators {
	private JButton button;
	private List<IBlocks> blocks;

	public Separators(String name) {
		button = new JButton(name);
		blocks = new ArrayList<>();
	}

	public void addBlock(IBlocks block) {
		blocks.add(block);
	}

	public JButton getButton() {
		return button;
	}

	public List<IBlocks> getBlocks() {
		return blocks;
	}
}
