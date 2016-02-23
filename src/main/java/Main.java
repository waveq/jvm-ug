package com.waveq.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main  {
	static Scanner scanner = new Scanner(System.in);
	static List<String> first = new ArrayList<>();
	static int counter = 0;

	public static void main(String args[]) {
		System.out.println("1 - outOfMemory\n2 - stackOverflow");
		String answer = scanner.nextLine();

		if(answer.equals("1")) {
			try {
				outOfMemory();
			} catch (OutOfMemoryError e) {
				System.out.println(String.format("Added %s Strings to list.\n", counter) + e);
			}
		} else if (answer.equals("2")) {
			try {
				stackOverFlow();
			} catch(StackOverflowError e) {
				System.out.println(String.format("Done %s invokes.\n", counter) + e);
			}
		}

	}

	private static void outOfMemory() {
		while(true) {
			counter++;
			first.add("memory");
		}
	}

	private static void  stackOverFlow() {
		first();
	}

	private static void  first() {
		counter++;
		second();
	}

	private static void second() {
		counter++;
		first();
	}
}
