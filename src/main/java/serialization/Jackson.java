package serialization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson extends BaseSerial {

	private static final String FILE_NAME = "jackson.json";

	@Override
	public void serialize(List<LoginSerializable> logins, boolean one) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(one) {
				mapper.writeValue(new File(FILE_NAME), logins.get(0));
			} else {
				mapper.writeValue(new File(FILE_NAME), logins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginSerializable> deserialize(boolean one) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			if(one) {
				List<LoginSerializable> list = new ArrayList<>();
				list.add(mapper.readValue(new File(FILE_NAME), LoginSerializable.class));
				return list;
			}
			return mapper.readValue(new File(FILE_NAME), List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
