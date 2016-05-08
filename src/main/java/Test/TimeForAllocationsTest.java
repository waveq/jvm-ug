package Test;
import ObjectGenerator.*;


public class TimeForAllocationsTest implements Runnable {

	private static final String TIME_FOR_ALLOCATION_MS = "#> [%s] [Threads: %s] [Object Size Constant: %s]: time for allocation %s objects: %s ms.";
	private static final int NUMBER_OF_OBJECTS = 1000;
	private static final double MILISECONDS_DIVIDER = 1000000.0;
	private static long time = 0;
	private static long objectsCreated = 0;
	private static boolean constantSize;

	public static void count(int numberOfThreads, boolean constantSize) throws InterruptedException {
		time = 0;
		objectsCreated = 0;
		TimeForAllocationsTest.constantSize = constantSize;

		for(int i=0; i<numberOfThreads; i++){
			new Thread(new TimeForAllocationsTest()).start();
		}
		Thread.sleep(5000);
		System.out.println(String.format(TIME_FOR_ALLOCATION_MS, TimeForAllocationsTest.class.getName(), numberOfThreads, constantSize, NUMBER_OF_OBJECTS, time/MILISECONDS_DIVIDER));
	}

	@Override
	public void run() {
		time += allocObjects(NUMBER_OF_OBJECTS);
	}

	private static long allocObjects(int numberOfObjects) {
		long startTime = System.nanoTime();
		while(objectsCreated <= numberOfObjects) {
			allocObject(constantSize);
			objectsCreated++;
		}
		return System.nanoTime() - startTime;
	}

	private static void allocObject(boolean constantSize) {
		if(constantSize) {
			MemoryEater.alloc(1);
		} else {
			MemoryEater.allocRandom();
		}
	}
}