package ObjectGenerator;

import java.util.Random;

public class MemoryEater {

	private static Random random = new Random();
	private static int counter = 1;

	private int[] buffer;

	public MemoryEater(int size){
			buffer = new int[size];
		}

	public static MemoryEater alloc(int sizeInMb){
		return new MemoryEater(sizeInMb*(1024*1024)/4);
	}

	public static MemoryEater allocDifferent(){
		return new MemoryEater(getNumber()*(1024*1024)/4);
	}

	private static int getNumber() {
		counter ++;
		if(counter == 20) {
			counter = 1;
		}
		return counter;
	}
}
