package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.conditionblocks.BlocksIfElse;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalBoolean;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalDouble;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalInteger;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalString;
import me.oddlyoko.zeroCreator.blocks.mathblocks.BlocksMath;
import me.oddlyoko.zeroCreator.blocks.mathblocks.BlocksMath.BlocksMathEnum;

public class BlocksManager {
	private ArrayList<IBlocks> blocks = new ArrayList<>();
	private Project project;

	public BlocksManager(Project project) {
		this.project = project;
	}

	public void init() {
	}

	public void addBlock(IBlocks b) {
		blocks.add(b);
		project.getGUIManager().getMainGUI().getContentPane().add(b.getInternalGUIFrame());
	}

	public void removeBlock(IBlocks b) {
		b.delete();
		blocks.remove(b);
		project.getGUIManager().getMainGUI().getContentPane().remove(b.getInternalGUIFrame());
	}

	public void removeBlocks(IBlocks b) {
		if (b == blocks.get(0)) {
			JOptionPane.showMessageDialog(null, "You can't delete this block", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (IBlocks block : b.getBlocks())
			if (block != null)
				removeBlocks(block);
		removeBlock(b);
	}

	public void addAll() {
		BlocksEvents be1 = new BlocksEvents(project, "On Plugin Load");
		be1.move(10, 10);
		addBlock(be1);
		BlocksIfElse bif1 = new BlocksIfElse(project);
		BlocksIfElse bif11 = new BlocksIfElse(project);
		BlocksIfElse bif2 = new BlocksIfElse(project);
		BlocksIfElse bif3 = new BlocksIfElse(project);
		addBlock(bif1);
		addBlock(bif11);
		addBlock(bif2);
		addBlock(bif3);
		be1.setChildren(bif1);
		bif1.setIfChildren(bif11);
		bif1.setElse(true);
		bif1.setChildren(bif2);
		bif2.setChildren(bif3);
		bif3.setElse(true);
		BlocksFinalString bf1 = new BlocksFinalString(project, "0ddlyoko");
		BlocksFinalString bf2 = new BlocksFinalString(project, "C'est");
		BlocksFinalString bf3 = new BlocksFinalString(project, "Un");
		BlocksFinalString bf4 = new BlocksFinalString(project, "Developpeur");
		BlocksFinalString bf5 = new BlocksFinalString(project, "En");
		BlocksFinalString bf6 = new BlocksFinalString(project, "Mousse");
		BlocksFinalInteger bf7 = new BlocksFinalInteger(project, 1);
		BlocksFinalInteger bf8 = new BlocksFinalInteger(project, 2);
		BlocksFinalInteger bf9 = new BlocksFinalInteger(project, 3);
		BlocksFinalDouble bf10 = new BlocksFinalDouble(project, 4.1);
		BlocksFinalBoolean bf11 = new BlocksFinalBoolean(project, true);
		bf1.move(project.getGUIManager().getMainGUI().getWidth() / 2, 50);
		bf2.move(project.getGUIManager().getMainGUI().getWidth() / 2, 70);
		bf3.move(project.getGUIManager().getMainGUI().getWidth() / 2, 90);
		bf4.move(project.getGUIManager().getMainGUI().getWidth() / 2, 110);
		bf5.move(project.getGUIManager().getMainGUI().getWidth() / 2, 130);
		bf6.move(project.getGUIManager().getMainGUI().getWidth() / 2, 150);
		bf7.move(project.getGUIManager().getMainGUI().getWidth() / 2, 170);
		bf8.move(project.getGUIManager().getMainGUI().getWidth() / 2, 190);
		bf9.move(project.getGUIManager().getMainGUI().getWidth() / 2, 210);
		bf10.move(project.getGUIManager().getMainGUI().getWidth() / 2, 230);
		bf11.move(project.getGUIManager().getMainGUI().getWidth() / 2, 250);
		addBlock(bf1);
		addBlock(bf2);
		addBlock(bf3);
		addBlock(bf4);
		addBlock(bf5);
		addBlock(bf6);
		addBlock(bf7);
		addBlock(bf8);
		addBlock(bf9);
		addBlock(bf10);
		addBlock(bf11);
		BlocksMath bm = new BlocksMath(project, BlocksMathEnum.MAX);
		BlocksMath bm2 = new BlocksMath(project, BlocksMathEnum.MIN);
		addBlock(bm);
		addBlock(bm2);
	}

	public void updateAll() {
		for (IBlocks b : blocks)
			b.updateAll();
	}

	public ArrayList<IBlocks> getBlocks() {
		return blocks;
	}
}
