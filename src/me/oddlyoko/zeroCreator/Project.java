package me.oddlyoko.zeroCreator;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import me.oddlyoko.zeroCreator.blocks.BlocksManager;
import me.oddlyoko.zeroCreator.gui.GUIManager;

public class Project {
	/*
	 * private Object LOCK = null; private boolean lock = true;
	 */
	private GUIManager guiManager = new GUIManager(this);
	private PluginInformation pluginInformation = new PluginInformation("", "", "");
	private BlocksManager blocksManager = new BlocksManager(this);

	public Project() {
		guiManager.init();
		guiManager.getMainGUI().setTitle("0Creator ~ " + pluginInformation.getAuthor() + " : "
				+ pluginInformation.getPluginName() + " (v" + pluginInformation.getPluginVersion() + ")");
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
		guiManager.getMainGUI().addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				moveBlocksGUI(e.getComponent());
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				super.componentMoved(e);
				moveBlocksGUI(e.getComponent());
			}

			private void moveBlocksGUI(Component c) {
				Point p1 = c.getLocationOnScreen();
				p1.setLocation(p1.x - guiManager.getBlocksGUI().getWidth(), p1.y);
				if (p1.x < 0)
					p1.x = 0;
				if (p1.y < 0)
					p1.y = 0;
				guiManager.getBlocksGUI().setLocation(p1);
				guiManager.getBlocksGUI().requestFocus();
			}
		});
		blocksManager.init();
	}

	public void askPluginInformation() {
		guiManager.showInformationGUI();
	}

	public GUIManager getGUIManager() {
		return guiManager;
	}

	public PluginInformation getPluginInformation() {
		return pluginInformation;
	}

	public BlocksManager getBlocksManager() {
		return blocksManager;
	}

	public void close() {
		guiManager.close();
	}
}
