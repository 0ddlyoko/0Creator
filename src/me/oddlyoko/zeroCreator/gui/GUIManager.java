package me.oddlyoko.zeroCreator.gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.gui.frame.AboutGUI;
import me.oddlyoko.zeroCreator.gui.frame.InformationGUI;
import me.oddlyoko.zeroCreator.gui.frame.MainGUI;
import me.oddlyoko.zeroCreator.gui.frame.blocks.BlocksGUI;

public class GUIManager {
	private Project project = null;
	private MainGUI mainGUI = null;
	private InformationGUI informationGUI = null;
	private AboutGUI aboutGUI = null;
	private BlocksGUI blocksGUI = null;

	public GUIManager(Project project) {
		this.project = project;
	}

	public void init() {
		// Set Default Theme
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		blocksGUI = new BlocksGUI(project);
		mainGUI = new MainGUI(project);
		aboutGUI = new AboutGUI(project);
		informationGUI = new InformationGUI(project);
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

	public BlocksGUI getBlocksGUI() {
		return blocksGUI;
	}

	public void showBlocksGUI() {
		blocksGUI.setVisible(true);
	}

	public void close() {
		mainGUI.stop();
		mainGUI.dispose();
		informationGUI.dispose();
		aboutGUI.dispose();
		blocksGUI.dispose();
	}
}
