package ObjectGenerator;

import java.util.Random;

public class MemoryEater {

	private static Random random = new Random();
	private static final int MINIMUM = 1;
	private static final int MAXIMUM = 6;

	private int[] buffer;

	public MemoryEater(int size){
			buffer = new int[size];
		}

	public static MemoryEater alloc(int sizeInMb){
		return new MemoryEater(sizeInMb*(1024*1024)/4);
	}

	public static MemoryEater allocRandom(){
		return new MemoryEater(getRandom()*(1024*1024)/4);
	}

	private static int getRandom() {
		return random.nextInt((MAXIMUM - MINIMUM) + 1) + MINIMUM;
	}
}
