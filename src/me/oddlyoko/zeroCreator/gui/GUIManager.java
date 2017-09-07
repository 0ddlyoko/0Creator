package me.oddlyoko.zeroCreator.gui;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.gui.frame.AboutGUI;
import me.oddlyoko.zeroCreator.gui.frame.InformationGUI;
import me.oddlyoko.zeroCreator.gui.frame.MainGUI;
import me.oddlyoko.zeroCreator.gui.popup.GUIPopUpResult;

public class GUIManager {
	private Project project = null;
	private MainGUI mainGUI = null;
	private InformationGUI informationGUI = null;
	private AboutGUI aboutGUI = null;

	public GUIManager(Project project) {
		this.project = project;
	}

	public void init() {
		mainGUI = new MainGUI(project);
		informationGUI = new InformationGUI(project);
		aboutGUI = new AboutGUI(project);
	}

	public MainGUI getMainGUI() {
		return mainGUI;
	}

	public void showMainGUI() {
		mainGUI.setVisible(true);
	}

	public InformationGUI getInformationGUI() {
		return informationGUI;
	}

	public void showInformationGUI() {
		informationGUI.setVisible(true);
	}

	public AboutGUI getAboutGUI() {
		return aboutGUI;
	}

	public void showAboutGUI() {
		aboutGUI.setVisible(true);
	}

	public Object askPopUp(GUIPopUpResult popup) {
		// TODO END HERE
		return null;
	}

	public void close() {
		mainGUI.dispose();
		informationGUI.dispose();
		aboutGUI.dispose();
	}
}
