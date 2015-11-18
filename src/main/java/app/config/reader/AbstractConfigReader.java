package app.config.reader;

import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.config.dto.Config;

import com.google.gson.Gson;

public abstract class AbstractConfigReader<T extends Config> {
	private static ClassLoader loader = AbstractConfigReader.class.getClassLoader();
	protected static Gson gson = new Gson();
	private static final Pattern envVarRegex;

	static {
		String envVar = "[\\w\\(\\)]+";
		String expression = "\\$\\{(" + envVar + "+)\\}|\\$(" + envVar + ")";

		envVarRegex = Pattern.compile(expression);
	}

	protected static <T extends Config> T parse(String filename, Class<T> configClass) {
		return gson.fromJson(getInputStream(filename), configClass);
	}

	protected static String resolveEnvVars(String path) {
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

	protected static InputStreamReader getInputStream(String filename) {
		return new InputStreamReader(loader.getResourceAsStream(filename));
	}
}
