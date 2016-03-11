package application;

import java.util.Random;

public class App {

	public void runService() {
		Random rand = new Random();

		SomeService s = null;

		for (int i = 0; i < 3; i++) {
			switch (Math.abs(rand.nextInt() % 3)) {
			case 0:
				s = new Class1();
				break;
			case 1:
				s = new Class2();
				break;
			case 2:
				s = new Class3();
				break;
			}
			// Notice late-binding here
			s.doSomething();
		}
	}

	public static void main(String[] args) {
		App app = new App();
		app.runService();
	}


	/*
	04:11:43 {lab-04} ~/workspace/jvmInternals/jvm-ug$ jar cfm target/agent.jar myManifest -C target/classes/ .

	04:11:43 {lab-04} ~/workspace/jvmInternals/jvm-ug$ java -javaagent:target/agent.jar -cp target/classes/. application.App
	 */
}
