package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializer extends BaseSerial {

	static String FILE_NAME = "serialized.object";

	@Override
	public void serialize(List<LoginSerializable> logins, boolean one) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
			if(one) {
				objectOutputStream.writeObject(logins.get(0));
			} else {
				objectOutputStream.writeObject(logins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginSerializable> deserialize(boolean one) {
		try  (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			if(one) {
				List<LoginSerializable> list = new ArrayList<>();
				list.add((LoginSerializable) inputStream.readObject());
				return list;
			}
			return  (List<LoginSerializable>) inputStream.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
