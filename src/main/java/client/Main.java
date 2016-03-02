package client;

public class Main  {
	private static final String CHILD_PACKAGE = "rest.SampleImplementation";

	public static void main(String args[]) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
		CustomClassLoader customClassLoader = new CustomClassLoader();

		Object child = customClassLoader.loadClass(CHILD_PACKAGE).newInstance();

		System.out.println("Class Loader: " + child.getClass().getClassLoader());
		System.out.println(child);
	}
}
