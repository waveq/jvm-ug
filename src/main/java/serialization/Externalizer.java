package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Externalizer  {

	static String FILE_NAME = "externalized.object";

	public long testSerialization(List<LoginExternalizable> logins, boolean one) {
		long startTime = System.currentTimeMillis();
		serialize(logins, one);
		return System.currentTimeMillis() - startTime;
	}

	public long testDeserialization(boolean one) {
		long startTime = System.currentTimeMillis();
		deserialize(one);
		return System.currentTimeMillis() - startTime;
	}

	private void serialize(List<LoginExternalizable> logins, boolean one) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			if(one) {
				objectOutputStream.writeObject(logins.get(0));
			} else {
				objectOutputStream.writeObject(logins);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private List<LoginExternalizable> deserialize(boolean one) {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			if(one) {
				List<LoginExternalizable> list = new ArrayList<>();
				list.add((LoginExternalizable) objectInputStream.readObject());
				return list;
			}
			return (List<LoginExternalizable>) objectInputStream.readObject();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}