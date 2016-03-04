package reflection;

import object.FunnyObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
	private static final String FUNNY_OBJECT_PACKAGE = "object.FunnyObject";
	private static final String PUBLIC_STRING = "publicString";
	private static final String SET_PRIMITIVE_INT = "setPrimitiveInt";

	private static final int EXAMPLE_INT_VALUE = 99;
	private static final String EXAMPLE_STRING_VALUE = "Bum, bum, bum.";
	private static final long NUMBER_OF_INVOKES = 10000000;

	private static final String INIT_MESSAGE = "#> Invoke number set to %s.";
	private static final String REFLECTION_TESTS_INIT = "#> Starting reflection tests.\n";
	private static final String NATIVE_TESTS_INIT = "#> Starting native tests.\n";
	private static final String EXCEPTION_MESSAGE = "#> Something went wrong during reflection test.\n";
	private static final String ALL_REFLECTION_TESTS_TIME = "#> Time elapsed for all reflection tests: %s.\n";
	private static final String ALL_NATIVE_TESTS_TIME = "#> Time elapsed for all native tests: %s.\n";
	private static final String TIME_ELAPSED = "#> Time elapsed: %s.\n";

	private static FunnyObject funnyObject = new FunnyObject();
	private static String stringValue = "";

	private static long testStartTime = 0;
	private static long testEndTime = 0;
	private static long allTestsStartTime = 0;
	private static long allTestsEndTime = 0;

	public static void main(String args[]) {
		printConfigurtion();
		performReflectionTests();
		performTests();
	}

	private static void printConfigurtion() {
		System.out.println(String.format(INIT_MESSAGE, NUMBER_OF_INVOKES));
	}

	private static void performTests() {
		System.out.println(NATIVE_TESTS_INIT);
		allTestsStartTime = System.nanoTime();

		testStartTime = System.nanoTime();
		getPublicString(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();
		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));

		testStartTime = System.nanoTime();
		setPublicString(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();
		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));

		testStartTime = System.nanoTime();
		invokePublicMethodWithArg(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();
		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));

		allTestsEndTime = System.nanoTime();
		System.out.println(String.format(ALL_NATIVE_TESTS_TIME, (allTestsEndTime-allTestsStartTime)));
	}

	private static void performReflectionTests() {
		System.out.println(REFLECTION_TESTS_INIT);
		allTestsStartTime = System.nanoTime();

		try {
			Class funnyObjectClass = Class.forName(FUNNY_OBJECT_PACKAGE);
			Object funnyObjectInstance = funnyObjectClass.newInstance();

			testStartTime = System.nanoTime();
			getPublicStringWithReflection(NUMBER_OF_INVOKES, funnyObjectClass, funnyObjectInstance);
			testEndTime = System.nanoTime();
			System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));

			testStartTime = System.nanoTime();
			setPublicStringWithReflection(NUMBER_OF_INVOKES, funnyObjectClass, funnyObjectInstance);
			testEndTime = System.nanoTime();
			System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));

			testStartTime = System.nanoTime();
			invokePublicMethodWithArgumentUsingReflection(NUMBER_OF_INVOKES, funnyObjectClass, funnyObjectInstance);
			testEndTime = System.nanoTime();
			System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));

			allTestsEndTime = System.nanoTime();
			System.out.println(String.format(ALL_REFLECTION_TESTS_TIME, (allTestsEndTime-allTestsStartTime)));
		} catch(Exception e) {
			System.out.println(EXCEPTION_MESSAGE);
		}
	}

	private static void getPublicStringWithReflection(long numberOfInvokes, Class objectClass, Object objectInstance) {
		System.out.println("Get public String with Reflection.");
		try {
			Field publicStringField;
			for (int i = 0; i < numberOfInvokes; i++) {
				publicStringField = objectClass.getDeclaredField(PUBLIC_STRING);
				stringValue = (String) publicStringField.get(objectInstance);
			}
		} catch(Exception e) {
			System.out.println(EXCEPTION_MESSAGE);
		}
	}

	private static void setPublicStringWithReflection(long numberOfInvokes, Class objectClass, Object objectInstance) {
		System.out.println("Set public String with Reflection.");
		try {
			Field publicStringField;
			for (int i = 0; i < numberOfInvokes; i++) {
				publicStringField = objectClass.getDeclaredField(PUBLIC_STRING);
				publicStringField.set(objectInstance, EXAMPLE_STRING_VALUE);
			}
		} catch(Exception e) {
			System.out.println(EXCEPTION_MESSAGE);
		}
	}

	private static void invokePublicMethodWithArgumentUsingReflection(long numberOfInvokes, Class objectClass, Object objectInstance) {
		System.out.println("Invoke method with argument using reflection.");
		try {
			Method setPrimitiveInt;
			for (int i = 0; i < numberOfInvokes; i++) {
				setPrimitiveInt = objectClass.getDeclaredMethod(SET_PRIMITIVE_INT, int.class);
				setPrimitiveInt.invoke(objectInstance, EXAMPLE_INT_VALUE);
			}
		} catch(Exception e) {
			System.out.println(EXCEPTION_MESSAGE);
		}
	}

	private static void getPublicString(long numberOfInvokes) {
		System.out.println("Get public String.");
		for(int i=0; i<numberOfInvokes; i++) {
			stringValue = funnyObject.publicString;
		}
	}

	private static void setPublicString(long numberOfInvokes) {
		System.out.println("Set public String.");
		for(int i=0; i < numberOfInvokes; i++) {
			funnyObject.publicString = EXAMPLE_STRING_VALUE;
		}
	}

	private static void invokePublicMethodWithArg(long numberOfInvokes) {
		System.out.println("Invoke method with argument.");
		for(int i=0; i < numberOfInvokes; i++) {
			funnyObject.setPrimitiveInt(EXAMPLE_INT_VALUE);
		}
	}
}
