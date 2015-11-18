package app.config.reader;

import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import app.config.dto.AppConfig;
import app.config.dto.RootConfig;

public class ConfigReader {
	private static ClassLoader loader = ConfigReader.class.getClassLoader();
	private static Gson gson = new Gson();
	private static Class<RootConfig> confClass = RootConfig.class;
	private static final Pattern envVarRegex;
	
	static {
		String envVar = "[\\w\\(\\)]+";
		String expression = "\\$\\{(" + envVar + "+)\\}|\\$(" + envVar + ")";
		
		envVarRegex = Pattern.compile(expression);
	}

	public static RootConfig read(String filename) {
		return load(gson.fromJson(getInputStream(filename), confClass));
	}

	protected static RootConfig load(RootConfig config) {
		if (config != null && config.getApplications() != null) {
			for (AppConfig app : config.getApplications()) {
				app.setPath(resolveEnvVars(app.getPath()));
			}
		}

		return config;
	}

	private static String resolveEnvVars(String path) {
		if (path == null) {
			return null;
		}

		Matcher m = envVarRegex.matcher(path);
		StringBuffer sb = new StringBuffer();

		while (m.find()) {
			String envVar = m.group(0); 
			String envVarName = null == m.group(1) ? m.group(2) : m.group(1);

			m.appendReplacement(sb, resolveEnvVar(envVar, envVarName));
		}

		return m.appendTail(sb).toString();
	}

	private static String resolveEnvVar(String envVar, String name) {
		try {
			return Matcher.quoteReplacement(System.getenv(name));
		} catch (NullPointerException e) {
			System.err.println("Warning: Environment variable does no exist: " + name);
		}
		return Matcher.quoteReplacement(envVar);
	}

	private static InputStreamReader getInputStream(String filename) {
		return new InputStreamReader(loader.getResourceAsStream(filename));
	}
}
