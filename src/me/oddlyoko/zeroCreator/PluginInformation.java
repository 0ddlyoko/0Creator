package me.oddlyoko.zeroCreator;

public class PluginInformation {
	private String pluginName;
	private String author;
	private String pluginVersion;

	public PluginInformation(String pluginName, String author, String pluginVersion) {
		this.pluginName = pluginName;
		this.author = author;
		this.pluginVersion = pluginVersion;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPluginVersion() {
		return pluginVersion;
	}

	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}
}
