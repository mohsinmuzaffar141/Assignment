package dataProvider;


import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {
	Properties pro;
	public Logger logger;

	public ConfigDataProvider() throws IOException {

		pro = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
		pro.load(ip);
	}

	public String getDataFromConfig (String keyToSearch)
	{

		return pro.getProperty(keyToSearch);
	}

	public String getFirefoxPath()

	{
		return pro.getProperty("geckoPath");
	}

	public String getIEPath()

	{
		return pro.getProperty("IEPath");
	}

	public String getChromePath()

	{
		return pro.getProperty("chromePath");
	}

	public String getBrowser ()

	{
		return pro.getProperty("browserType");
	}

	public String getApplicationUrl (String url)

	{
		return pro.getProperty(url);
	}

	public String userName()

	{
		return pro.getProperty("userName");
	}
	public String passWord()

	{
		return pro.getProperty("pass");
	}

}
