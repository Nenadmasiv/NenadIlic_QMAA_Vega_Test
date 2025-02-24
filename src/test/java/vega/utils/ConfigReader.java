package vega.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	static {
		try (FileInputStream fileInputStream = new FileInputStream(TestConstants.CONFIG_FILE_PATH)) {
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (IOException e) {
			throw new RuntimeException("Could not read config file", e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
