package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CustomClassLoader extends java.lang.ClassLoader{

	private static final String SPARK_URL = "http://0.0.0.0:4567/SampleImplementation.class";

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (name.endsWith("SampleImplementation")) {
			return findClass(name);
		}

		return super.loadClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] bytes = null;
		try {
			bytes = loadDataFromSparkRest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Class<?> result = defineClass(name, bytes, 0, bytes.length);
		return result;
	}

	private byte[] loadDataFromSparkRest() throws IOException {
		String url = SPARK_URL;
		URL myUrl = new URL(url);
		URLConnection connection = myUrl.openConnection();
		InputStream input = connection.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int data = input.read();

		while (data != -1) {
			buffer.write(data);
			data = input.read();
		}

		input.close();

		byte[] classData = buffer.toByteArray();
		return classData;
	}
}
