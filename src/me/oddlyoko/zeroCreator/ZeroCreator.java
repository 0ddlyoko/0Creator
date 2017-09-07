package me.oddlyoko.zeroCreator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ZeroCreator {
	private static Project project = null;

	public static void main(String[] args) {
		createNewProject();
	}

	public static void createNewProject() {
		setProject(new Project());
	}

	public static void setProject(Project project) {
		if (ZeroCreator.project != null)
			ZeroCreator.project.close();
		ZeroCreator.project = project;
		ZeroCreator.project.getGUIManager().showMainGUI();
	}

	public static Project getProject() {
		return project;
	}

	private void testPopUp() {
		JOptionPane.showMessageDialog(null, "Salut !");
		JOptionPane pane = new JOptionPane("JOptionPane", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		JDialog dialog = pane.createDialog("Titre JDialog !");

		// pane.selectInitialValue();
		dialog.show();
		dialog.dispose();
	}
}
