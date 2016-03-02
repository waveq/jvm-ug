package rest;

import java.net.URL;
import java.net.URLClassLoader;

public class RestClassLoader extends URLClassLoader {

	public RestClassLoader(URL[] urls) {
		super(urls);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (name.contains("SampleImplementation")) {
			return findClass(name);
		}
		return super.loadClass(name);
	}
}
