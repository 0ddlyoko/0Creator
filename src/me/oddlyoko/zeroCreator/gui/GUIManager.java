package me.oddlyoko.zeroCreator.gui;

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
}
