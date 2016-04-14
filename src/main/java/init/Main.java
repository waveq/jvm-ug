package init;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
	private static final String BASE_URI = "http://localhost:8080/timetest/";
	private static final String PACKAGE_NAME = "endpoint";
	private static final String INIT_MESSAGE = "Jersey app started with WADL available at %sapplication.wadl\n Hit enter to stop it..";

	public static HttpServer startServer() {
		final ResourceConfig resourceConfig = new ResourceConfig().packages(PACKAGE_NAME);
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
	}

	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		System.out.println(String.format(INIT_MESSAGE, BASE_URI));

		System.in.read();
		server.stop();
	}
}
