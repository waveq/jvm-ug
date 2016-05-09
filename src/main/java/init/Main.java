package init;

import Test.AllocationsInTimeTest;
import Test.TimeForAllocationsTest;

import java.util.Scanner;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String WELCOME_MESSAGE = "To execute test type its number.\n\n"
			+ "Test 1: Allocations in 1 thread, objects with constant size\n"
			+ "11 - Time needed for allocating n objects.\n12 - Number of allocations in 15 seconds.\n\n"

			+ "Test 1: Allocations in 5 threads, objects with constant size\n"
			+ "21 - Time needed for allocating n objects.\n22 - Number of allocations in 15 seconds.\n\n"

			+ "Test 3: Allocations in 1 threads, objects with different sizes\n"
			+ "31 - Time needed for allocating n objects.\n32 - Number of allocations in 15 seconds.\n\n"

			+ "Test 4: Allocations in 5 threads, objects with different sizes\n"
			+ "41 - Time needed for allocating n objects.\n42 - Number of allocations in 15 seconds.\n\n";

	public static void main(String[] args) throws InterruptedException {

		System.out.println(WELCOME_MESSAGE);
		int response = scanner.nextInt();
		if(response == 11) {
			TimeForAllocationsTest.execute(1, true);
		} else if(response == 12) {
			AllocationsInTimeTest.execute(1, true);
		} else if(response == 21) {
			TimeForAllocationsTest.execute(4, true);
		} else if(response == 22) {
			AllocationsInTimeTest.execute(4, true);
		} else if(response == 31) {
			TimeForAllocationsTest.execute(1, false);
		} else if(response == 32) {
			AllocationsInTimeTest.execute(1, false);
		} else if(response == 41) {
			TimeForAllocationsTest.execute(4, false);
		} else if(response == 42) {
			AllocationsInTimeTest.execute(4, false);
		}
	}
}
