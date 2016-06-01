package init;

import serialization.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final String SERIALIZATION = "\nSerialization, number of objects: %s.";
	private static final String DESERIALIZATION = "\nDeserialization, number of objects: %s.";
	private static final String RESULT = "[%s]: time: %s ns";

	private static int numberOfTests = 100;
	private static int numberOfFirstIterationObjects = 10;
	private static int numberOfSecondIterationObjects = 10000;

	private static List<LoginExternalizable> loginsExternalizableZeroIteration = new ArrayList();
	private static List<LoginSerializable> loginsSerializableZeroIteration = new ArrayList();


	private static List<LoginExternalizable> loginsExternalizableFirstIteration = new ArrayList();
	private static List<LoginSerializable> loginsSerializableFirstIteration = new ArrayList();

	private static List<LoginExternalizable> loginsExternalizableSecondIteration = new ArrayList();
	private static List<LoginSerializable> loginsSerializableSecondIteration = new ArrayList();

	private static List<BaseSerial> serializers = new ArrayList();

	private static void prepareObjects(int numberOfIterations, List<LoginSerializable> serializables,
			List<LoginExternalizable> externalizables) {
		for (int i = 0; i < numberOfIterations; i++) {
			LoginSerializable loginSerializable = new LoginSerializable();
			LoginExternalizable loginExternalizable = new LoginExternalizable();

			serializables.add(loginSerializable);
			externalizables.add(loginExternalizable);
		}
	}

	public static void main(String[] args) {
		prepareObjects(1, loginsSerializableZeroIteration, loginsExternalizableZeroIteration);
		prepareObjects(numberOfFirstIterationObjects, loginsSerializableFirstIteration, loginsExternalizableFirstIteration);
		prepareObjects(numberOfSecondIterationObjects, loginsSerializableSecondIteration, loginsExternalizableSecondIteration);

		serializers.add(new Serializer());
		serializers.add(new Jackson());
		serializers.add(new GsonConverter());
		serializers.add(new JAXB());

		System.out.println(String.format(SERIALIZATION, 1));
		testSerialization(loginsSerializableZeroIteration, loginsExternalizableZeroIteration);
		System.out.println(String.format(DESERIALIZATION, 1));
		testDeserialization(true);

		System.out.println(String.format(SERIALIZATION, numberOfFirstIterationObjects));
		testSerialization(loginsSerializableFirstIteration, loginsExternalizableFirstIteration);
		System.out.println(String.format(DESERIALIZATION, numberOfFirstIterationObjects));
		testDeserialization(false);

		System.out.println(String.format(SERIALIZATION, numberOfSecondIterationObjects));
		testSerialization(loginsSerializableSecondIteration, loginsExternalizableSecondIteration);
		System.out.println(String.format(DESERIALIZATION, numberOfSecondIterationObjects));
		testDeserialization(false);
	}

	private static void testSerialization(List<LoginSerializable> loginSerializables, List<LoginExternalizable> loginExternalizables) {
		boolean one = false;
		if(loginSerializables.size() == 1) {
			one = true;
		}
		for (BaseSerial serializer : serializers) {
			long processingTime = 0;
			for (int i = 0; i < numberOfTests; i++) {
				processingTime += serializer.testSerialization(loginSerializables, one);
			}
			System.out.println(String.format(RESULT, serializer.getClass().getName(), processingTime));
		}

		Externalizer externalizer = new Externalizer();
		long processingTime = 0;
		for (int i = 0; i < numberOfTests; i++) {

			processingTime += externalizer.testSerialization(loginExternalizables, one);
		}
		System.out.println(String.format(RESULT, externalizer.getClass().getName(), processingTime));
	}

	private static void testDeserialization(boolean one) {
		for (BaseSerial serializer : serializers) {
			long processingTime = 0;
			for (int i = 0; i < numberOfTests; i++) {
				processingTime += serializer.testDeserialization(one);
			}
			System.out.println(String.format(RESULT, serializer.getClass().getName(), processingTime));
		}

		Externalizer externalizer = new Externalizer();
		long processingTime = 0;
		for (int i = 0; i < numberOfTests; i++) {
			processingTime += externalizer.testDeserialization(one);
		}
		System.out.println(String.format(RESULT, externalizer.getClass().getName(), processingTime));
	}
}