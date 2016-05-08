package init;

import Test.AllocationsInTimeTest;
import Test.TimeForAllocationsTest;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		TimeForAllocationsTest.count(1, true);
		AllocationsInTimeTest.count(1, true);

		TimeForAllocationsTest.count(5, true);
		AllocationsInTimeTest.count(5, true);

		TimeForAllocationsTest.count(1, false);
		AllocationsInTimeTest.count(1, false);

		TimeForAllocationsTest.count(5, false);
		AllocationsInTimeTest.count(5, false);
	}
}
