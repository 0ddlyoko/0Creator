package me.oddlyoko.zeroCreator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.gui.GUIManager;
import me.oddlyoko.zeroCreator.gui.frame.InformationGUI;

public class ZeroCreator {
	private PluginInformation pluginInformation = null;
	private Object LOCK = new Object();
	private boolean lock1 = true;

	public ZeroCreator() {
		InformationGUI igui = GUIManager.getInformationGUI();
		GUIManager.showInformationGUI();
		igui.addWindowListener(new WindowAdapter() { // Event when
														// InformationGUI is
														// Closed
			@Override
			public void windowDeactivated(WindowEvent e) {
				lock1 = false;
				synchronized (LOCK) {
					LOCK.notifyAll();
				}
			}
		});
		synchronized (LOCK) { // Need to synchonize because
								// while(igui.isVisible()){} is bugged
			while (lock1) {
				try {
					LOCK.wait();
				} catch (InterruptedException ex) {
					break;
				}
			}
		}
		// testPopUp();
		pluginInformation = new PluginInformation(igui.getPluginName(), igui.getAuthor(), igui.getPluginVersion());
		GUIManager.getMainGUI().setTitle("0Creator ~ " + pluginInformation.getAuthor() + " : "
				+ pluginInformation.getPluginName() + " (v" + pluginInformation.getPluginVersion() + ")");
		GUIManager.showMainGUI();
	}

	public PluginInformation getPluginInformation() {
		return pluginInformation;
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
