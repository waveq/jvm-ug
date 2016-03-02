package rest;

import java.net.URL;
import java.net.URLClassLoader;

public class SampleFactory {
	private static final String SAMPLE_INTERFACE_PACKAGE = "rest.SampleImplementation";


	public static SampleInterface createNewInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		URL[] urls = ((URLClassLoader)ClassLoader.getSystemClassLoader()).getURLs();

		ClassLoader myCL = new RestClassLoader(urls);

		return (SampleInterface) myCL.loadClass(SAMPLE_INTERFACE_PACKAGE).newInstance();
	}
}
