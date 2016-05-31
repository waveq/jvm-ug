package serialization;

import java.io.*;
import java.util.Random;

public class LoginExternalizable implements Externalizable{

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public LoginExternalizable(){
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

		final LoginExternalizable other = (LoginExternalizable) object;
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(username);
		out.writeObject(password);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		username = (String) in.readObject();
		password = (String) in.readObject();
	}
}
