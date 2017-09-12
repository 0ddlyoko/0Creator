package me.oddlyoko.zeroCreator;

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
		ZeroCreator.project.getGUIManager().showBlocksGUI();
		ZeroCreator.project.getGUIManager().showMainGUI();
	}

	public static Project getProject() {
		return project;
	}
}
