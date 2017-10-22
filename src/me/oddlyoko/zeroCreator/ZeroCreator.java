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
		project.getGUIManager().showBlocksGUI();
		project.getGUIManager().showMainGUI();
		project.getBlocksManager().addAll();
		// ZeroCreator.project.getBlocksManager().updateAll();
	}

	public Project getProject() {
		return project;
	}
}
