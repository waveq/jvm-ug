package reflection;

import object.FunnyObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectionTest {
	private static final String FUNNY_OBJECT_PACKAGE = "object.FunnyObject";
	private static final String PUBLIC_STRING = "publicString";
	private static final String SET_PRIMITIVE_INT = "setPrimitiveInt";

	private static final int EXAMPLE_INT_VALUE = 99;
	private static final String EXAMPLE_STRING_VALUE = "Bum, bum, bum.";
	private static final long NUMBER_OF_INVOKES = 10000;

	private static final String INIT_MESSAGE = "\n\n================================\n\nSTARTING %s ITERATION OF TESTS\n\n#> Invoke number set to %s.";
	private static final String REFLECTION_TESTS_INIT = "#> Starting reflection tests.\n";
	private static final String NATIVE_TESTS_INIT = "#> Starting native tests.\n";
	private static final String EXCEPTION_MESSAGE = "#> Something went wrong during reflection test.\n";
	private static final String ALL_REFLECTION_TESTS_TIME = "#> Time elapsed for all reflection tests: %s.\n";
	private static final String ALL_NATIVE_TESTS_TIME = "#> Time elapsed for all native tests: %s.\n";
	private static final String TIME_ELAPSED = "#> Time elapsed: %s.\n";
	private static final String NATIVE = "Native";
	private static final String REFLECTION = "Reflection";
	private static final String GET_PUBLIC = "get public value";
	private static final String SET_PUBLIC = "set public value";
	private static final String INVOKE_METHOD = "invoke method";
	private static final String ALL_TESTS = "tests summarized";
	private static final String RESULTS_TEMPLATE = "%s %s, averaged: \t\t\t\t%s";

	private static FunnyObject funnyObject = new FunnyObject();
	private static String stringValue = "";

	private static long testStartTime = 0;
	private static long testEndTime = 0;
	private static long allTestsStartTime = 0;
	private static long allTestsEndTime = 0;

	private static List<Long> getNative = new ArrayList<>();
	private static List<Long> setNative = new ArrayList<>();
	private static List<Long> methodNative = new ArrayList<>();
	private static List<Long> allNative = new ArrayList<>();

	private static List<Long> getReflection = new ArrayList<>();
	private static List<Long> setReflection = new ArrayList<>();
	private static List<Long> methodReflection = new ArrayList<>();
	private static List<Long> allReflection = new ArrayList<>();

	public static void main(String args[]) {

		for(int i = 1;i<=10;i++) {
			printConfigurtion(i);
			performNativeTests();
			performReflectionTests();
		}
		deleteWorstAndBestResults();
		presentResults();

	}

	private static void presentResults() {
		long getNativeAveraged = (long) getNative.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
		long setNativeAveraged = (long) setNative.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
		long methodNativeAveraged = (long) methodNative.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
		long allNativeAveraged = (long) allNative.stream().mapToLong(el -> el.longValue()).average().getAsDouble();

		long getReflectionAveraged = (long) getReflection.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
		long setReflectionAveraged = (long) setReflection.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
		long methodReflectionAveraged = (long) methodReflection.stream().mapToLong(el -> el.longValue()).average().getAsDouble();
		long allReflectionAveraged = (long) allReflection.stream().mapToLong(el -> el.longValue()).average().getAsDouble();

		System.out.println("\n\n================================\n\n");
		System.out.println(String.format(RESULTS_TEMPLATE, NATIVE, GET_PUBLIC, getNativeAveraged));
		System.out.println(String.format(RESULTS_TEMPLATE, NATIVE, SET_PUBLIC, setNativeAveraged));
		System.out.println(String.format(RESULTS_TEMPLATE, NATIVE, INVOKE_METHOD, methodNativeAveraged));
		//System.out.println(String.format(RESULTS_TEMPLATE, NATIVE, ALL_TESTS, allNativeAveraged));

		System.out.println();
		System.out.println(String.format(RESULTS_TEMPLATE, REFLECTION, GET_PUBLIC, getReflectionAveraged));
		System.out.println(String.format(RESULTS_TEMPLATE, REFLECTION, SET_PUBLIC, setReflectionAveraged));
		System.out.println(String.format(RESULTS_TEMPLATE, REFLECTION, INVOKE_METHOD, methodReflectionAveraged));
		//System.out.println(String.format(RESULTS_TEMPLATE, REFLECTION, ALL_TESTS, allReflectionAveraged));
	}

	private static void deleteWorstAndBestResults() {
		getNative.remove(getNative.indexOf(Collections.min(getNative)));
		getNative.remove(getNative.indexOf(Collections.max(getNative)));
		setNative.remove(setNative.indexOf(Collections.min(setNative)));
		setNative.remove(setNative.indexOf(Collections.max(setNative)));
		methodNative.remove(methodNative.indexOf(Collections.min(methodNative)));
		methodNative.remove(methodNative.indexOf(Collections.max(methodNative)));
		allNative.remove(allNative.indexOf(Collections.min(allNative)));
		allNative.remove(allNative.indexOf(Collections.max(allNative)));

		getReflection.remove(getReflection.indexOf(Collections.min(getReflection)));
		getReflection.remove(getReflection.indexOf(Collections.max(getReflection)));
		setReflection.remove(setReflection.indexOf(Collections.min(setReflection)));
		setReflection.remove(setReflection.indexOf(Collections.max(setReflection)));
		methodReflection.remove(methodReflection.indexOf(Collections.min(methodReflection)));
		methodReflection.remove(methodReflection.indexOf(Collections.max(methodReflection)));
		allReflection.remove(allReflection.indexOf(Collections.min(allReflection)));
		allReflection.remove(allReflection.indexOf(Collections.max(allReflection)));
	}

	private static void printConfigurtion(int iteration) {
		System.out.println(String.format(INIT_MESSAGE, iteration, NUMBER_OF_INVOKES));
	}

	private static void performNativeTests() {
		System.out.println(NATIVE_TESTS_INIT);
		allTestsStartTime = System.nanoTime();

		testStartTime = System.nanoTime();
		getPublicString(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();
		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
		getNative.add(testEndTime - testStartTime);

		testStartTime = System.nanoTime();
		setPublicString(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();
		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
		setNative.add(testEndTime - testStartTime);

		testStartTime = System.nanoTime();
		invokePublicMethodWithArg(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();
		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
		methodNative.add(testEndTime - testStartTime);

		allTestsEndTime = System.nanoTime();
		System.out.println(String.format(ALL_NATIVE_TESTS_TIME, (allTestsEndTime-allTestsStartTime)));
		allNative.add(allTestsEndTime-allTestsStartTime);
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
			getReflection.add(testEndTime - testStartTime);

			testStartTime = System.nanoTime();
			setPublicStringWithReflection(NUMBER_OF_INVOKES, funnyObjectClass, funnyObjectInstance);
			testEndTime = System.nanoTime();
			System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
			setReflection.add(testEndTime - testStartTime);


			testStartTime = System.nanoTime();
			invokePublicMethodWithArgumentUsingReflection(NUMBER_OF_INVOKES, funnyObjectClass, funnyObjectInstance);
			testEndTime = System.nanoTime();
			System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
			methodReflection.add(testEndTime - testStartTime);


			allTestsEndTime = System.nanoTime();
			System.out.println(String.format(ALL_REFLECTION_TESTS_TIME, (allTestsEndTime-allTestsStartTime)));
			allReflection.add(allTestsEndTime-allTestsStartTime);
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
