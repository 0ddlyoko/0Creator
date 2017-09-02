package me.oddlyoko.zeroCreator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.gui.GUIManager;

public class ZeroCreator {

	public ZeroCreator() {
		// testPopUp();
		GUIManager.showMainGUI();
		// GUIPopUpResult dialog = new GUIPopUpResult();
		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// dialog.setVisible(true);
	}

	private void testPopUp() {
		JOptionPane.showMessageDialog(null, "Salut !");
		JOptionPane pane = new JOptionPane("JOptionPane", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = pane.createDialog("Titre JDialog !");

		// pane.selectInitialValue();
		dialog.show();
		dialog.dispose();
	}

	public static void main(String[] args) {
		new ZeroCreator();
	}
}
