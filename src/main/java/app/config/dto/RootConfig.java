package app.config.dto;

import java.util.List;

public class RootConfig {
	private List<AppConfig> applications;

	public List<AppConfig> getApplications() {
		return applications;
	}

	public void setApplications(List<AppConfig> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "RootConfig [applications=" + applications + "]";
	}
}
