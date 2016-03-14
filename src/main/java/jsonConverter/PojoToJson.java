package jsonConverter;

import java.lang.reflect.Field;
import java.util.*;

import com.google.gson.Gson;

public class PojoToJson {

	private static final String CLOSING_BRACKET = "}";
	private static final String QUOTE_MARK = "\"";
	private static final String COLON = ":";
	private static final String COMMA = ",";
	private static final String EXCEPTION = "!!! Ups, błąd przy wyciąganiu wartości pola. !!!";

	Gson gson;

	public String pojoToJsonWithReflection(Object objectInstance) {
		Class objectClass = objectInstance.getClass();

		Field[] fields = objectClass.getDeclaredFields();
		StringBuilder builder = new StringBuilder("{");
		for(int i=0;i<fields.length;i++) {
			if(isCollection(fields[i])) {
				continue;
			}
			fields[i].setAccessible(true);
			builder.append(QUOTE_MARK).append(fields[i].getName()).append(QUOTE_MARK).append(COLON);

			try {
				builder.append(QUOTE_MARK).append(fields[i].get(objectInstance).toString()).append(QUOTE_MARK);
			} catch (IllegalAccessException e) {
				System.out.println(EXCEPTION);
			}

			if(i < fields.length - 1 && !isCollection(fields[i+1])) {
				builder.append(COMMA);
			} else {
				builder.append(CLOSING_BRACKET);
			}
		}
		return builder.toString();
	}

	private boolean isCollection(Field field) {
		return Collection.class.isAssignableFrom(field.getType());
	}

	public String pojoToJsonWithGson(Object objectInstance) {
		gson = new Gson();
		String json = gson.toJson(objectInstance);

		return json;
	}
}
