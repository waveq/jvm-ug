package reflection;

import jsonConverter.PojoToJson;
import object.FunnyObject;
import utils.FileUtils;

public class Main {

	private static final String FUNNY_OBJECT_PACKAGE = "object.FunnyObject";

	public static void main(String args[])  {

			PojoToJson pojoToJson = new PojoToJson();

			FileUtils.writeToFile(pojoToJson.pojoToJsonWithReflection(new FunnyObject()), "reflection.json");
			FileUtils.writeToFile(pojoToJson.pojoToJsonWithGson(new FunnyObject()), "gson.json");
	}


}
