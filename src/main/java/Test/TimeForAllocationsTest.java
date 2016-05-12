package Test;

public class TimeForAllocationsTest extends AbstractTest implements Runnable {

	private static final String TIME_FOR_ALLOCATION_MS = "#> [%s] [Threads: %s] [Object Size Constant: %s]: time for allocation %s objects: %s seconds.";
	private static final int NUMBER_OF_OBJECTS = 5000;
	private static final double SECONDS_DIVIDER = 1000000000.0;
	private static final int SLEEP_TIME = 20000;

	private static long startTime = 0;
	private static long endTime = 0;
	private static long objectsCreated = 0;
	private static boolean constantSize;
	private static int threadCount = 0;

	private static int threadsFinishedCounter = 0;
	public static void execute(int numberOfThreads, boolean constantSize) throws InterruptedException {
		threadCount = numberOfThreads;
		TimeForAllocationsTest.constantSize = constantSize;

		startTime = System.nanoTime();
		for(int i=0; i<numberOfThreads; i++){
			new Thread(new TimeForAllocationsTest()).start();
		}
		if(endTime < 1) {
			Thread.sleep(SLEEP_TIME);
		}
		long resultTime = endTime- startTime;

		System.out.println(String.format(TIME_FOR_ALLOCATION_MS, TimeForAllocationsTest.class.getName(), numberOfThreads, constantSize, NUMBER_OF_OBJECTS, resultTime/SECONDS_DIVIDER));
	}

	@Override
	public void run() {
		allocObjects(NUMBER_OF_OBJECTS);
	}

	private static void allocObjects(int numberOfObjects) {
		while(objectsCreated <= numberOfObjects) {
			allocObject(constantSize);
			objectsCreated++;
		}

		threadsFinishedCounter ++;
		if(threadsFinishedCounter == threadCount) {
			endTime = System.nanoTime();
		}
	}
}