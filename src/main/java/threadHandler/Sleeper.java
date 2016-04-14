package threadHandler;

public class Sleeper {

	public static int sleepForParamTime(String time, int multiplier){
		int sleepTime = Integer.parseInt(time) * multiplier;
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sleepTime;
	}
}
