package init;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Main {

	private static final String TEXT_DATE = "31-01-2016";
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	private static final String SAFE = "Safe method";
	private static final String UNSAFE = "Unsafe method";
	private static final String EXCEPTION_MESSAGE = "Exception raised in %s: %s";
	private static final int NUMBER_OF_THREADS = 30;

	private static final ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>(){
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(DATE_FORMAT);
		}
	};

	public static void main(String args[])  {
		threadSafeSimpleDateFormat(TEXT_DATE);
		notThreadSafeSimpleDateFormat(TEXT_DATE);
	}

	private static void notThreadSafeSimpleDateFormat(String date) {
		System.out.println(UNSAFE);

		final DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Callable<Date> task = () -> format.parse(date);

		try {
			testIt(task);
		} catch (Exception e) {
			System.out.println(String.format(EXCEPTION_MESSAGE, UNSAFE, e.getMessage()));
		}
	}

	private static void threadSafeSimpleDateFormat(String date) {
		System.out.println(SAFE);
		Callable<Date> task = () -> convert(date);

		try {
			testIt(task);
		} catch (Exception e) {
			System.out.println(String.format(EXCEPTION_MESSAGE, SAFE, e.getMessage()));
		}
	}

	private static void testIt(Callable<Date> task) throws Exception {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		List<Future<Date>> results = new ArrayList<>();

		for(int i = 0 ; i < NUMBER_OF_THREADS ; i++){
			results.add(exec.submit(task));
		}
		exec.shutdown();

		for(Future<Date> result : results){
			System.out.println(result.get());
		}
	}

	private static Date convert(String date) throws ParseException {
		return dateFormatThreadLocal.get().parse(date);
	}
}
