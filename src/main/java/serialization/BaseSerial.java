package serialization;

import java.util.List;

public abstract class BaseSerial  {

	public long testSerialization(List<LoginSerializable> logins, boolean one) {
		long startTime = System.currentTimeMillis();
		serialize(logins, one);
		return System.currentTimeMillis() - startTime;
	}


	public long testDeserialization(boolean one) {
		long startTime = System.currentTimeMillis();
		deserialize(one);
		return System.currentTimeMillis() - startTime;
	}

	public abstract void serialize(List<LoginSerializable> logins, boolean one);
	public abstract List<LoginSerializable> deserialize(boolean one);

}
