package instrument;

import java.lang.instrument.Instrumentation;

public class MyAgent {

	public static void premain(String agentArgs, Instrumentation inst) {
		// rejestracja transformera
		inst.addTransformer(new MyClassFileTransformer());
		System.out.println("#> Rozmiar ala ma kota: " + inst.getObjectSize(new String("Ala ma kota")));
	}

}
