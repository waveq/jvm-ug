package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Externalizer extends BaseSerial {

	static String FILE_NAME = "externalized.object";

	@Override
	public void serialize(List<LoginExternalizable> logins) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			for (LoginExternalizable login : logins) {
				login.writeExternal(objectOutputStream);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<LoginExternalizable> deserialize() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			new LoginExternalizable().readExternal(objectInputStream);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}