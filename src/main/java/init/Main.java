package init;

import serialization.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final String SERIALIZATION = "Serialization: ";
	private static final String DESERIALIZATION = "Deserialization: ";

	private static int numberOfTests = 10;
	private static int numberOfLogins = 1000;

	public static List<LoginExternalizable> logins = new ArrayList();
	public static List<BaseSerial> serializers = new ArrayList();

	public static void main(String[] args) {
		for (int i = 0; i < numberOfLogins; i++) {
			LoginExternalizable login = new LoginExternalizable();
			logins.add(login);
		}

		serializers.add(new Serializer());
		serializers.add(new Jackson());
		serializers.add(new GsonConverter());
		serializers.add(new Externalizer());

		System.out.println(SERIALIZATION);
		testSerialization();
		System.out.println(DESERIALIZATION);
		testDeserialization();
	}

	static public void testSerialization() {
		for (BaseSerial serializer : serializers) {
			long processingTime = 0;
			for (int i = 0; i < numberOfTests; i++) {
				processingTime += serializer.testSerialization(logins);
			}
			double average = processingTime / numberOfTests;
		}
	}

	static public void testDeserialization() {
		for (BaseSerial serializer : serializers) {
			long processingTime = 0;
			for (int i = 0; i < numberOfTests; i++) {
				processingTime += serializer.testDeserialization(logins);
			}
			double average = processingTime / numberOfTests;
		}
	}
}