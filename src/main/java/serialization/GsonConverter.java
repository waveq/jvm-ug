package serialization;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter extends BaseSerial {

	private static final String FILE_NAME = "gson.json";
	private static final String UTF8 = "UTF-8";

	@Override
	public void serialize(List<LoginExternalizable> logins) {
		try(Writer writer = new OutputStreamWriter(new FileOutputStream(FILE_NAME) , UTF8)){
			new GsonBuilder().create().toJson(logins, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginExternalizable> deserialize() {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
			return new Gson().fromJson(reader, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
