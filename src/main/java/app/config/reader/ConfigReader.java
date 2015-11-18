package app.config.reader;

import java.io.InputStreamReader;

import com.google.gson.Gson;

import app.config.dto.RootConfig;

public class ConfigReader {
	private static ClassLoader loader = ConfigReader.class.getClassLoader();
	
	public static RootConfig read(String filename) {
		return new Gson().fromJson(getInputStream(filename), RootConfig.class);
	}

	private static InputStreamReader getInputStream(String filename) {
		return new InputStreamReader(loader.getResourceAsStream(filename));
	}
}
