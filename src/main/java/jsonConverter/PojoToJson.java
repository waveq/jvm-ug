package jsonConverter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import com.google.gson.Gson;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

public class PojoToJson {

	private static final String CLOSING_BRACKET = "}";
	private static final String QUOTE_MARK = "\"";
	private static final String COLON = ":";
	private static final String COMMA = ",";
	private static final String EXCEPTION = "!!! Ups, błąd przy wyciąganiu wartości pola. !!!";
	private static final String EXCEPTION_JACKSON = "!!! Ups, błąd przy jacksonowaniu. !!!";

	private Gson gson;
	private ObjectMapper mapper = new ObjectMapper();

	public PojoToJson(){
		gson = new Gson();

		mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
				.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
	}

	public String pojoToJsonWithReflection(Object objectInstance) {
		Class objectClass = objectInstance.getClass();

		Field[] fields = objectClass.getDeclaredFields();
		StringBuilder builder = new StringBuilder("{");
		boolean stringField;
		try {

			for(int i=0;i<fields.length;i++) {
				if(isCollection(fields[i])) {
					continue;
				}

				fields[i].setAccessible(true);
				stringField = fields[i].get(objectInstance) instanceof String;

				builder.append(QUOTE_MARK).append(fields[i].getName()).append(QUOTE_MARK).append(COLON);

				if(stringField) {
					builder.append(QUOTE_MARK);
				}
				builder.append(fields[i].get(objectInstance).toString());
				if(stringField) {
					builder.append(QUOTE_MARK);
				}

				if(i < fields.length - 1 && !isCollection(fields[i + 1])) {
					builder.append(COMMA);
				} else {
					builder.append(CLOSING_BRACKET);
				}
			}
		} catch (IllegalAccessException e) {
			System.out.println(EXCEPTION);
		}
		return builder.toString();
	}

	private boolean isCollection(Field field) {
		return Collection.class.isAssignableFrom(field.getType());
	}

	public String pojoToJsonWithGson(Object objectInstance) {
		return gson.toJson(objectInstance);
	}

	public String pojoToJsonWithJackson(Object objectInstance) {
		try {
			return mapper.writeValueAsString(objectInstance);
		} catch (IOException e) {
			System.out.println(EXCEPTION_JACKSON);
		}

		return null;
	}
}
