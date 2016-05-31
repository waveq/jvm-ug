package serialization;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson extends BaseSerial {

	private static final String FILE_NAME = "jackson.json";

	@Override
	public void serialize(List<LoginExternalizable> logins) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(FILE_NAME), logins);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginExternalizable> deserialize() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(new File(FILE_NAME), List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
