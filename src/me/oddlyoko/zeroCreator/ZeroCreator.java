package me.oddlyoko.zeroCreator;

import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.gui.GUIManager;

public class ZeroCreator {

	public ZeroCreator() {
		GUIManager.showMainGUI();
		JOptionPane.showMessageDialog(null, "Salut !");
		// GUIPopUpResult dialog = new GUIPopUpResult();
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// dialog.setVisible(true);
	}

	public static void main(String[] args) {
		new ZeroCreator();
	}
}
