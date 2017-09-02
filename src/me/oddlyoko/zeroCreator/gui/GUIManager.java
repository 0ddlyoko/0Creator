package me.oddlyoko.zeroCreator.gui;

import me.oddlyoko.zeroCreator.gui.popup.GUIPopUpResult;

public class GUIManager {
	private static MainGUI mainGUI = null;

	public static MainGUI getMainGUI() {
		if (mainGUI == null)
			mainGUI = new MainGUI();
		return mainGUI;
	}

	public static void showMainGUI() {
		if (mainGUI == null)
			mainGUI = new MainGUI();
		mainGUI.setVisible(true);
	}

	public static Object askPopUp(GUIPopUpResult popup) {
		// TODO END HERE
		return null;
	}
}
