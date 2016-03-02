package rest;

import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static spark.Spark.*;

public class RestApp {

	private static SampleInterface sampleInterface;
	private static final String PATH_TO_CHILD_COMPILED_OBJECT = "/Users/szymonre/workspace/jvmInternals/jvm-ug/target/classes/rest/SampleImplementation.class";
	private static final String FILE_TAG = "file:";
	private static final String SERVER_ERROR = "Server error";
	private static final String RESPONDED_MESSAGE = "Responded";

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
		sampleInterface = SampleFactory.createNewInstance();
		get("/SampleImplementation.class", (request, response) -> proceedResponse(response));
	}

	private static Object proceedResponse(Response response) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		HttpServletResponse raw = response.raw();
		try {
			raw.getOutputStream().write(loadClassData());
			raw.getOutputStream().flush();
			raw.getOutputStream().close();
		} catch (Exception e) {
			halt(405, SERVER_ERROR);
		}

		System.out.println(RESPONDED_MESSAGE);
		return response.raw();
	}

	private static byte[] loadClassData() throws IOException {
		String url = FILE_TAG+PATH_TO_CHILD_COMPILED_OBJECT;
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

