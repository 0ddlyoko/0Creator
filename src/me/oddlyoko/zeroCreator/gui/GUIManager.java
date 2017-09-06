package me.oddlyoko.zeroCreator.gui;

import me.oddlyoko.zeroCreator.gui.frame.InformationGUI;
import me.oddlyoko.zeroCreator.gui.frame.MainGUI;
import me.oddlyoko.zeroCreator.gui.popup.GUIPopUpResult;

public class GUIManager {
	private static MainGUI mainGUI = null;
	private static InformationGUI informationGUI = null;

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

	public static InformationGUI getInformationGUI() {
		if (informationGUI == null)
			informationGUI = new InformationGUI();
		return informationGUI;
	}

	public static void showInformationGUI() {
		if (informationGUI == null)
			informationGUI = new InformationGUI();
		informationGUI.setVisible(true);
	}

	public static Object askPopUp(GUIPopUpResult popup) {
		// TODO END HERE
		return null;
	}
}
