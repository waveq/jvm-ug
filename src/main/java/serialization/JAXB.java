package serialization;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class JAXB extends BaseSerial {

	static String FILE_NAME = "jaxb.xml";

	@Override
	public void serialize(List<LoginSerializable> logins, boolean one) {
		try {

			if(one) {
				Marshaller marshaller = JAXBContext.newInstance(LoginSerializable.class).createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(logins.get(0), new FileWriter(FILE_NAME));
			} else {
				LoginContainer loginContainer = new LoginContainer();
				loginContainer.setLogins(logins);
				Marshaller marshaller = JAXBContext.newInstance(LoginContainer.class).createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(loginContainer, new FileWriter(FILE_NAME));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public List<LoginSerializable> deserialize(boolean one) {
		try {
			if(one) {
				List<LoginSerializable> list = new ArrayList<>();
				list.add((LoginSerializable) JAXBContext.newInstance(LoginSerializable.class)
						.createUnmarshaller().unmarshal(new FileReader(FILE_NAME)));
				return list;
			} else {
				return ((LoginContainer) JAXBContext.newInstance(LoginContainer.class)
						.createUnmarshaller().unmarshal(new FileReader(FILE_NAME))).getLogins();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
