package Test;

public class AllocationsInTimeTest extends AbstractTest implements Runnable {

	private static final String OBJECTS_CREATED = "#> [%s] [Threads: %s] [Object Size Constant: %s]: %s objects created in %s seconds.";
	private static final int SLEEP_TIME = 15000;

	private static boolean keepAllocating;
	private static long count = 0;
	private static boolean constantSize;


	public static long execute(int numberOfThreads, boolean constantSize) throws InterruptedException {
		keepAllocating = true;
		count = 0;
		AllocationsInTimeTest.constantSize = constantSize;

		if(numberOfThreads == 1) {

		}
		allocateInNewThread(numberOfThreads);
		Thread.sleep(20);

		System.out.println(String.format(OBJECTS_CREATED, AllocationsInTimeTest.class.getName(), numberOfThreads, constantSize, count, (SLEEP_TIME/1000)));
		return count;
	}

	private static void allocateInNewThread(int numberOfThreads) throws InterruptedException  {
		for(int i = 0; i<numberOfThreads; i++) {
			new Thread(new AllocationsInTimeTest()).start();
		}
		Thread.sleep(SLEEP_TIME);
		keepAllocating = false;
	}

	@Override
	public void run() {
		while (keepAllocating) {
			allocObject(constantSize);
			count++;
		}
	}
}
