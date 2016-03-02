package rest;

public class SampleImplementation implements SampleInterface {

	private int counter;
	private static final String CLASS_MESSAGE = "Hello it's implementation of Sample.";

	@Override
	public String message() {
		return CLASS_MESSAGE;
	}

	@Override
	public int increment() {
		return counter++;
	}

	@Override
	public int counter() {
		return counter;
	}

	@Override
	public SampleInterface copyCounter(SampleInterface source) {
		counter = source.counter();
		return this;
	}

	@Override
	public String toString() {
		return String.format("%s My counter is: %s.",CLASS_MESSAGE,counter);
	}
}
