package object;

public class FunnyObject {

	private String shortString = "Hello.";
	private int primitiveInt = 15;
	private Integer IntObject = 15;
	private long primiviteLong = 30L;
	private Long longObject = 30L;

	public String publicString = "Hello in public.";

	public String getShortString() {
		return shortString;
	}

	public void setShortString(String shortString) {
		this.shortString = shortString;
	}

	public int getPrimitiveInt() {
		return primitiveInt;
	}

	public void setPrimitiveInt(int primitiveInt) {
		this.primitiveInt = primitiveInt;
	}

	public Integer getIntObject() {
		return IntObject;
	}

	public void setIntObject(Integer intObject) {
		IntObject = intObject;
	}

	public long getPrimiviteLong() {
		return primiviteLong;
	}

	public void setPrimiviteLong(long primiviteLong) {
		this.primiviteLong = primiviteLong;
	}

	public Long getLongObject() {
		return longObject;
	}

	public void setLongObject(Long longObject) {
		this.longObject = longObject;
	}
}
