package app.config.reader;

import app.config.dto.AppConfig;
import app.config.dto.RootConfig;

public class RootConfigReader extends AbstractConfigReader<RootConfig> {
	public static RootConfig read(String filename) {
		return load(parse(filename, RootConfig.class));
	}

	protected static RootConfig load(RootConfig config) {
		if (config != null && config.getApplications() != null) {
			for (AppConfig app : config.getApplications()) {
				app.setPath(resolveEnvVars(app.getPath()));
			}
		}

		return config;
	}
}
