package GenericUtitlity;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtitlity {

	public String readDatafromPropertyFile(String key) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/Data/Common_data.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
	public String readDatafromPropertyFileForLogin(String key) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/Data/Common_DataForLogin.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
}
