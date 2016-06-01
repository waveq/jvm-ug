package serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "logins")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginContainer {

	@XmlElement(name = "login")
	private List<LoginSerializable> logins = null;

	public List<LoginSerializable> getLogins() {
		return logins;
	}

	public void setLogins(List<LoginSerializable> logins) {
		this.logins = logins;
	}
}
