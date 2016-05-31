package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializer extends BaseSerial {

	static String FILE_NAME = "serialized.object";

	@Override
	public void serialize(List<LoginExternalizable> logins) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
			objectOutputStream.writeObject(logins);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginExternalizable> deserialize() {
		try  (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			return  (List<LoginExternalizable>) inputStream.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
