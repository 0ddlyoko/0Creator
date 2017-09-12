package me.oddlyoko.zeroCreator.blocks;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinal;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalDouble;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalInteger;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalList;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalString;

public class BlocksManager {
	private ArrayList<IBlocks> blocks = new ArrayList<>();
	private Project project;

	public BlocksManager(Project project) {
		this.project = project;
	}

	public void init() {
		BlocksEvents be1 = new BlocksEvents("On Plugin Load");
		be1.move(10, 10);
		addBlock(be1);
		BlocksInstruction bi1 = new BlocksInstruction();
		BlocksInstruction bi11 = new BlocksInstruction();
		BlocksInstruction bi2 = new BlocksInstruction();
		BlocksInstruction bi3 = new BlocksInstruction();
		addBlock(bi1);
		addBlock(bi11);
		addBlock(bi2);
		addBlock(bi3);
		be1.setNext(bi1);
		bi1.setChildren(bi11);
		bi1.setNext(bi2);
		bi2.setNext(bi3);
		bi3.setEnd(true);
		BlocksFinal bf1 = new BlocksFinalString("0ddlyoko");
		BlocksFinal bf2 = new BlocksFinalString("C'est");
		BlocksFinal bf3 = new BlocksFinalString("Un");
		BlocksFinal bf4 = new BlocksFinalString("Developpeur");
		BlocksFinal bf5 = new BlocksFinalString("En");
		BlocksFinal bf6 = new BlocksFinalString("Mousse");
		BlocksFinal bf7 = new BlocksFinalInteger(1);
		BlocksFinal bf8 = new BlocksFinalInteger(2);
		BlocksFinal bf9 = new BlocksFinalInteger(3);
		BlocksFinal bf10 = new BlocksFinalDouble(4.1);
		BlocksFinalList bf11 = new BlocksFinalList(Boolean.TRUE);
		bf11.add(true);
		bf11.add(false);
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
		for (ICustomBlocks block : b.getBlocks())
			if (block != null)
				removeBlocks(block.getBlock());
		removeBlock(b);
	}

	public ArrayList<IBlocks> getBlocks() {
		return blocks;
	}
}
