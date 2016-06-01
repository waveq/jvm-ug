package serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.Random;

@XmlRootElement(name = "singleLogin")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginSerializable implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "username")
	private String username;
	@XmlElement(name = "password")
	private String password;

	public LoginSerializable(){
		this.username = this.randomString();
		this.password = this.randomString();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object object){
		if (object == null) {
			return false;
		}

		final LoginSerializable other = (LoginSerializable) object;
		if ((this.username == null) ? (other.username != null) : !this.username.equals(username)) {
			return false;
		}

		if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
			return false;
		}
		return true;
	}

	private String randomString(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			sb.append(chars[random.nextInt(chars.length)]);
		}
		return sb.toString();
	}
}
