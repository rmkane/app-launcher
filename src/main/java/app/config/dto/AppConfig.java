package app.config.dto;

import java.util.ArrayList;
import java.util.List;

public class AppConfig {
	private String name;
	private String path;
	private List<String> arguments;

	public AppConfig() {
		name = "";
		path = "";
		arguments = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "AppConfig [name=" + name + ", path=" + path + ", arguments=" + arguments + "]";
	}
}
