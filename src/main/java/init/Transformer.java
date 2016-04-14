package init;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class Transformer implements ClassFileTransformer {

	private static final String SLEEPER_CLASS_NAME = "Sleeper";
	private static final String SLEEP_FOR_PARAM_TIME = "sleepForParamTime";
	private static final String ELAPSED_TIME = "elapsedTime";
	private static final String ELAPSED_TIME_ATTRIBUTION = "elapsedTime = System.currentTimeMillis();";
	private static final String EXECUTE_TWO_LINES_OF_CODE_TEMPLATE = " { %s %s }";
	private static final String DIFFERENCE = "elapsedTime = System.currentTimeMillis() - elapsedTime;";
	private static final String RESULT_PRINT = "System.out.println(\"Method elapsedTime = \" + elapsedTime);";
	private static final char SLASH = '/';
	private static final char DOT = '.';

	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] bytes) throws IllegalClassFormatException {

		byte[] result = bytes;

		if (className.contains(SLEEPER_CLASS_NAME)) {
			try {
				String dotClassName = className.replace(SLASH, DOT);
				ClassPool classPool = ClassPool.getDefault();
				CtClass ctClass = classPool.get(dotClassName);
				CtMethod sleepForTimeMethod = ctClass.getDeclaredMethod(SLEEP_FOR_PARAM_TIME);

				sleepForTimeMethod.addLocalVariable(ELAPSED_TIME, CtClass.longType);
				sleepForTimeMethod.insertBefore(ELAPSED_TIME_ATTRIBUTION);
				sleepForTimeMethod.insertAfter(String.format(EXECUTE_TWO_LINES_OF_CODE_TEMPLATE, DIFFERENCE, RESULT_PRINT));

				result = ctClass.toBytecode();
			}

			catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
