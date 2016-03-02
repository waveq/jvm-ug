package rest;

public interface SampleInterface {

	String message();
	int increment();
	int counter();

	SampleInterface copyCounter(SampleInterface source);
}
