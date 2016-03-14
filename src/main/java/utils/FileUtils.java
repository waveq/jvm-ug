package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	private static final String OUTPUT_FILE_PROPERTY = "file.input";
	private static final String PROPERTIES_FILE = "config.properties";

	private static String getProperty(String propertyKey) {
		try {
			InputStream is = new FileInputStream(PROPERTIES_FILE);
			java.util.Properties prop = new java.util.Properties();
			prop.load(is);
			return prop.getProperty(propertyKey);
		} catch (Exception e) {
			System.out.println("Probably there is no such property " + propertyKey);
			return null;
		}
	}

	public static void writeToFile(String content, String fileName)  {
		try {
			java.io.FileWriter writer = new java.io.FileWriter(String.format("%s/%s", getProperty(OUTPUT_FILE_PROPERTY), fileName));
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}