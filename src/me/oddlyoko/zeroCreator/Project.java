package me.oddlyoko.zeroCreator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import me.oddlyoko.zeroCreator.gui.GUIManager;

public class Project {
	/*
	 * private Object LOCK = null; private boolean lock = true;
	 */
	private GUIManager guiManager = new GUIManager(this);
	private PluginInformation pluginInformation = new PluginInformation(null, null, null);

	public Project() {
		guiManager.init();
		guiManager.getInformationGUI().addWindowListener(new WindowAdapter() { // Event
																				// when
																				// InformationGUI
																				// is
																				// Closed
			@Override
			public void windowClosed(WindowEvent e) {
				/*
				 * lock = false; synchronized (LOCK) { LOCK.notifyAll(); }
				 */
				pluginInformation = new PluginInformation(guiManager.getInformationGUI().getPluginName(),
						guiManager.getInformationGUI().getAuthor(), guiManager.getInformationGUI().getPluginVersion());
				guiManager.getMainGUI().setTitle("0Creator ~ " + pluginInformation.getAuthor() + " : "
						+ pluginInformation.getPluginName() + " (v" + pluginInformation.getPluginVersion() + ")");
			}
		});
	}

	public void askPluginInformation() {
		/*
		 * LOCK = new Object(); lock = true;
		 */
		guiManager.showInformationGUI();
		/*
		 * synchronized (LOCK) { // Need to do that because //
		 * while(igui.isVisible()){} is bugged while (lock) { try { LOCK.wait();
		 * } catch (InterruptedException ex) { break; } } }
		 */
	}

	public GUIManager getGUIManager() {
		return guiManager;
	}

	public PluginInformation getPluginInformation() {
		return pluginInformation;
	}

	public void close() {
		guiManager.close();
	}
}
