package init;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
	private static final String BASE_URI = "http://localhost:8080/lab-07/";
	private static final String PACKAGE_NAME = "endpoint";
	private static final String INIT_MESSAGE = "WADL is available at %sapplication.wadl\nPress any button to exit.";
	private static final String PATH_MESSAGE = "Try going to http://localhost:8080/lab-07/multiply1/<your number> or http://localhost:8080/lab-7/multiply2/<your number>.";

	public static HttpServer startServer() {
		final ResourceConfig resourceConfig = new ResourceConfig().packages(PACKAGE_NAME);
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
	}

	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		System.out.println(String.format(INIT_MESSAGE, BASE_URI));
		System.out.println(PATH_MESSAGE);
		System.in.read();
		server.stop();
	}
}
