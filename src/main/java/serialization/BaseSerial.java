package serialization;

import java.util.List;

public abstract class BaseSerial {

	public long testSerialization(List<LoginExternalizable> logins) {
		long startTime = System.currentTimeMillis();
		serialize(logins);
		return System.currentTimeMillis() - startTime;
	}

	public long testDeserialization(List<LoginExternalizable> logins) {
		long startTime = System.currentTimeMillis();
		deserialize();
		return System.currentTimeMillis() - startTime;
	}

	public abstract void serialize(List<LoginExternalizable> logins);
	public abstract List<LoginExternalizable> deserialize();
}
