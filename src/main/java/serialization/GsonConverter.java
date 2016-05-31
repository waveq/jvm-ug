package serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter extends BaseSerial {

	private static final String FILE_NAME = "gson.json";
	private static final String UTF8 = "UTF-8";

	@Override
	public void serialize(List<LoginSerializable> logins, boolean one) {
		try(Writer writer = new OutputStreamWriter(new FileOutputStream(FILE_NAME) , UTF8)){
			if(one) {
				new GsonBuilder().create().toJson(logins.get(0), writer);
			} else {
				new GsonBuilder().create().toJson(logins, writer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginSerializable> deserialize(boolean one) {
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
			if(one) {
				List<LoginSerializable> list = new ArrayList<>();
				list.add(new Gson().fromJson(reader, LoginSerializable.class));
				return list;
			}
			return new Gson().fromJson(reader, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
