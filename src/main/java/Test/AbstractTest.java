package Test;

import ObjectGenerator.MemoryEater;

public abstract class AbstractTest {

	protected static void allocObject(boolean constantSize) {
		if(constantSize) {
			MemoryEater.alloc(20);
		} else {
			MemoryEater.allocDifferent();
		}
	}
}
