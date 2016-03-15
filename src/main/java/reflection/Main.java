package reflection;

import jsonConverter.PojoToJson;
import object.FunnyObject;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	private static final long NUMBER_OF_INVOKES = 100000;

	private static final String REFLECTION_FILE_NAME = "reflection.json";
	private static final String GSON_FILE_NAME = "gson.json";
	private static final String JACKSON_FILE_NAME = "jackson.json";


	private static final String INIT_MESSAGE = "\n\n================================\n\nSTARTING %s ITERATION OF TESTS\n\n#> Invoke number set to %s.\n\n";
	private static final String TIME_ELAPSED = "#> Time elapsed: %s.\n";

	private static final String RESULTS_TEMPLATE = "%s, averaged: \t\t\t\t%s";
	private static final String REMOVING_WORST = "Removing worst result from %s: %s - which was in %s iteration";
	private static final String REMOVING_BEST = "Removing best result from %s: %s - which was in %s iteration\n";

	private static FunnyObject funnyObject = new FunnyObject();
	private static PojoToJson pojoToJson = new PojoToJson();

	private static long testStartTime = 0;
	private static long testEndTime = 0;


	private static final String TESTS_INIT = "#> Starting %s tests.";
	private static final String REFLECTION = "Reflection";
	private static final String GSON = "Gson";
	private static final String JACKSON = "Jackson";

	private static List<Long> pojoToJsonReflection = new ArrayList<>();
	private static List<Long> pojoToJsonGson = new ArrayList<>();
	private static List<Long> pojoToJsonJackson = new ArrayList<>();

	public static void main(String args[]) {

		saveJsonToFile();
		for(int i = 1;i<=5;i++) {
			printConfigurtion(i);
			performReflectionTests();
			performGsonTests();
			performJacksonTests();
		}
		deleteWorstAndBestResults();
		presentResults();
	}

	private static void saveJsonToFile() {
		FileUtils.writeToFile(pojoToJson.pojoToJsonWithReflection(funnyObject), REFLECTION_FILE_NAME);
		FileUtils.writeToFile(pojoToJson.pojoToJsonWithGson(funnyObject), GSON_FILE_NAME);
		FileUtils.writeToFile(pojoToJson.pojoToJsonWithJackson(new FunnyObject()), JACKSON_FILE_NAME);
	}

	private static void presentResults() {
		long pojoToJsonReflectionAverage = (long) pojoToJsonReflection.stream().mapToLong(el -> el.longValue()).average().getAsDouble();

		long pojoToJsonGsonAverage = (long) pojoToJsonGson.stream().mapToLong(el -> el.longValue()).average().getAsDouble();

		long pojoToJsonJacksonAverage = (long) pojoToJsonJackson.stream().mapToLong(el -> el.longValue()).average().getAsDouble();

		System.out.println("\n\n================================\n\n");
		System.out.println(String.format(RESULTS_TEMPLATE, REFLECTION, pojoToJsonReflectionAverage));

		System.out.println();
		System.out.println(String.format(RESULTS_TEMPLATE, GSON, pojoToJsonGsonAverage));

		System.out.println();
		System.out.println(String.format(RESULTS_TEMPLATE, JACKSON, pojoToJsonJacksonAverage));
	}

	private static void deleteWorstAndBestResults() {
		int worstIterationIndex = pojoToJsonReflection.indexOf(Collections.max(pojoToJsonReflection));
		System.out.println(String.format(REMOVING_WORST, REFLECTION, Collections.max(pojoToJsonReflection), worstIterationIndex));
		pojoToJsonReflection.remove(pojoToJsonReflection.indexOf(Collections.max(pojoToJsonReflection)));

		int bestIterationIndex = getBestIterationIndexBeforeWorstDeleted(worstIterationIndex, pojoToJsonReflection);
		System.out.println(String.format(REMOVING_BEST, REFLECTION, Collections.min(pojoToJsonReflection), bestIterationIndex));
		pojoToJsonReflection.remove(pojoToJsonReflection.indexOf(Collections.min(pojoToJsonReflection)));


		worstIterationIndex = pojoToJsonGson.indexOf(Collections.max(pojoToJsonGson));
		System.out.println(String.format(REMOVING_WORST, GSON, Collections.max(pojoToJsonGson), worstIterationIndex));
		pojoToJsonGson.remove(pojoToJsonGson.indexOf(Collections.max(pojoToJsonGson)));

		bestIterationIndex = getBestIterationIndexBeforeWorstDeleted(worstIterationIndex, pojoToJsonGson);
		System.out.println(String.format(REMOVING_BEST, GSON, Collections.min(pojoToJsonGson), bestIterationIndex));
		pojoToJsonGson.remove(pojoToJsonGson.indexOf(Collections.min(pojoToJsonGson)));


		worstIterationIndex = pojoToJsonJackson.indexOf(Collections.max(pojoToJsonJackson));
		System.out.println(String.format(REMOVING_WORST, GSON, Collections.max(pojoToJsonJackson), worstIterationIndex));
		pojoToJsonJackson.remove(pojoToJsonJackson.indexOf(Collections.max(pojoToJsonJackson)));

		bestIterationIndex = getBestIterationIndexBeforeWorstDeleted(worstIterationIndex, pojoToJsonJackson);
		System.out.println(String.format(REMOVING_BEST, GSON, Collections.min(pojoToJsonJackson), bestIterationIndex));
		pojoToJsonJackson.remove(pojoToJsonJackson.indexOf(Collections.min(pojoToJsonJackson)));
	}

	private static int getBestIterationIndexBeforeWorstDeleted(int worstIterationIndex, List<Long> listToFetchFrom) {
		int bestIterationIndex = listToFetchFrom.indexOf(Collections.min(listToFetchFrom));
		if(worstIterationIndex <= bestIterationIndex) {
			return ++bestIterationIndex;
		}
		return bestIterationIndex;
	}

	private static void printConfigurtion(int iteration) {
		System.out.println(String.format(INIT_MESSAGE, iteration, NUMBER_OF_INVOKES));
	}

	private static void performReflectionTests() {
		System.out.println(String.format(TESTS_INIT, REFLECTION));

		testStartTime = System.nanoTime();
		pojoToJsonWithReflection(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();

		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
		pojoToJsonReflection.add(testEndTime - testStartTime);
	}

	private static void performGsonTests() {
		System.out.println(String.format(TESTS_INIT, GSON));

		testStartTime = System.nanoTime();
		pojoToJsonWithGson(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();

		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
		pojoToJsonGson.add(testEndTime - testStartTime);
	}

	private static void performJacksonTests() {
		System.out.println(String.format(TESTS_INIT, JACKSON));

		testStartTime = System.nanoTime();
		pojoToJsonWithJackson(NUMBER_OF_INVOKES);
		testEndTime = System.nanoTime();

		System.out.println(String.format(TIME_ELAPSED, (testEndTime - testStartTime)));
		pojoToJsonJackson.add(testEndTime - testStartTime);
	}


	private static void pojoToJsonWithReflection(long numberOfInvokes) {
		for (int i = 0; i < numberOfInvokes; i++) {
			pojoToJson.pojoToJsonWithReflection(funnyObject);
		}
	}

	private static void pojoToJsonWithGson(long numberOfInvokes) {
		for (int i = 0; i < numberOfInvokes; i++) {
			pojoToJson.pojoToJsonWithGson(funnyObject);
		}
	}

	private static void pojoToJsonWithJackson(long numberOfInvokes) {
		for (int i = 0; i < numberOfInvokes; i++) {
			pojoToJson.pojoToJsonWithJackson(funnyObject);
		}
	}
}
