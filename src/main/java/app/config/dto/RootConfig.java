package app.config.dto;

import java.io.Serializable;
import java.util.List;

public class RootConfig implements Config, Serializable {
	private static final long serialVersionUID = -700395636932705876L;

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
